package ecourse.app.sharedBoard.server.presentation;

import eapli.framework.infrastructure.authz.application.Authenticator;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.app.sharedBoard.server.domain.SBPCodeEnum;
import ecourse.app.sharedBoard.server.domain.SBPMessageFormat;
import ecourse.boardManagement.application.BoardController;
import ecourse.boardManagement.domain.Board;
import ecourse.boardManagement.domain.BoardParticipant;
import ecourse.boardManagement.domain.BoardStatusEnum;
import ecourse.boardManagement.domain.PostIt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ecourse.app.sharedBoard.server.domain.ColorOutputUtils.*;

public class SBPServerController implements Runnable {
    public static final int SERVER_PORT = 2048;

    byte version = 1;
    Authenticator authenticationService = AuthzRegistry.authenticationService();
    AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    BoardController boardController = new BoardController();
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private OutputStream out;
    private InputStream in;

    public SBPServerController() {
    }

    @Override
    public void run() {
        try {
            // Create the server socket
            serverSocket = new ServerSocket(SERVER_PORT);
            infoMessage("Server port: " + SERVER_PORT);

            while (true) {
                // Wait for a new connection
                clientSocket = serverSocket.accept();

                // Create the input and output streams
                out = clientSocket.getOutputStream();
                in = clientSocket.getInputStream();

                byte[] request = new byte[4096];
                int bytesRead;
                try {
                    bytesRead = in.read(request);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (bytesRead == 0) {
                    stopConnection();
                    return;
                }

                SBPMessageFormat requestFormatted = SBPMessageFormat.fromByteArray(request);

                switch (requestFormatted.code()) {
                    case 0 -> handleCommunicationTestRequest();
                    case 1 -> handleEndOfSessionRequest();
                    case 4 -> handleAuthenticationRequest(requestFormatted);
                    case 5 -> handleCreateBoardRequest(requestFormatted);
                    case 6 -> handleDeleteBoardRequest();
                    case 7 -> handleViewBoardsRequest();
                    case 8 -> handleShareBoardRequest();
                    case 9 -> handleRemovePartipantRequest();
                    case 10 -> handleToggleParticipantWriteAccessRequest();
                    case 11 -> handleViewParticipantsRequest();
                    case 12 -> handleArchiveBoardRequest();
                    case 13 -> handleUnarchiveBoardRequest();
                    case 14 -> handleViewArchivedBoardsRequest();
                    case 15 -> handleCreateAPostIt();
                    case 16 -> handleChangeAPostIt();
                    default -> handleInvalidRequest();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles a list participants request.
     */
    private void handleViewParticipantsRequest() {
        // Get the authenticated user
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        SystemUser user = authorizationService.session().get().authenticatedUser();

        // Get all the boards owned by the user
        List<Board> ownedBoards = boardController.findBoardsThatUserCanAddPostIt(user);

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ARCHIVED) {
                continue;
            }
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        var boardSelectedBytes = sendRequestAndGetResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        if (boardSelectedBytes == null) {
            sendErrorResponse();
            return;
        }

        if (boardSelectedBytes.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        Long boardId = Long.parseLong(new String(boardSelectedBytes.getData()));

        // Retrieve the board from the database
        Board board = boardController.findBoardById(boardId);
        if (board == null) {
            sendErrorResponse();
            return;
        }

        // Get all the participants of the board
        List<BoardParticipant> participants = boardController.findParticipantsByBoard(board);

        if (participants == null) {
            errorMessage("Error retrieving participants!");
            return;
        }

        if (participants.isEmpty()) {
            errorMessage("No participants found!");
            return;
        }

        // Send the list of participants to the client
        sb = new StringBuilder();
        for (BoardParticipant participant : participants) {
            sb.append(participant.identity().getUser().name());
        }

        sendResponse(SBPCodeEnum.LIST_PART.getCode(), sb.toString().getBytes());
        successMessage("Listed participants");
        sendAcknowledgeResponse();
    }

    /**
     * Handles a communication test request.
     */
    private void handleCommunicationTestRequest() {
        successMessage("Communication test request received");
        sendAcknowledgeResponse();
    }

    /**
     * Handles an end of session request.
     */
    private void handleEndOfSessionRequest() {
        successMessage("End of session request received");
        sendAcknowledgeResponse();
        stopConnection();
        warningMessage("Connection closed");
        System.exit(0);
    }

    /**
     * Handles an authentication request.
     *
     * @param request The request to handle.
     */
    private void handleAuthenticationRequest(SBPMessageFormat request) {
        byte[] data = request.getData();

        if (data.length == 0) {
            sendErrorResponse();
            return;
        }

        String[] credentials = new String(data).split("\0");
        if (credentials.length != 2) {
            sendErrorResponse();
            return;
        }

        String username = credentials[0];
        String password = credentials[1];


        Optional<UserSession> authenticated = authenticationService.authenticate(username, password);

        sendAuthenticationResponse(authenticated.isPresent());
    }

    /**
     * Handles a create board request.
     *
     * @param request The request to handle.
     */
    private void handleCreateBoardRequest(SBPMessageFormat request) {
        byte[] data = request.getData();

        if (data.length == 0) {
            sendErrorResponse();
            return;
        }

        String[] board = new String(data).split("\0");
        if (board.length != 3) {
            sendErrorResponse();
            return;
        }

        String title = board[0];
        int rows = Integer.parseInt(board[1]);
        int columns = Integer.parseInt(board[2]);

        // Check if AuthorizationService has a session and it's not empty
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        Board newBoard = boardController.createBoard(title, authorizationService.session().get().authenticatedUser(), rows, columns);

        if (newBoard != null) {
            sendAcknowledgeResponse();
            successMessage("Board created successfully!");
        } else {
            sendErrorResponse();
            errorMessage("Board creation failed!");
        }
    }

    /**
     * Handles a delete board request.
     */
    private void handleDeleteBoardRequest() {
        // Get the authenticated user
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        SystemUser user = authorizationService.session().get().authenticatedUser();

        // Get all the boards owned by the user
        List<Board> ownedBoards = boardController.findBoardsByOwner(user);

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ARCHIVED) {
                continue;
            }
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        var boardSelectedBytes = sendRequestAndGetResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        if (boardSelectedBytes == null) {
            sendErrorResponse();
            return;
        }

        if (boardSelectedBytes.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        Long boardId = Long.parseLong(new String(boardSelectedBytes.getData()));

        // Retrieve the board from the database
        Board board = boardController.findBoardById(boardId);
        if (board == null) {
            sendErrorResponse();
            return;
        }

        // Delete the board
        try {
            boardController.deleteBoard(board);
        } catch (Exception e) {
            sendErrorResponse();
            return;
        }

        sendAcknowledgeResponse();
        successMessage("Board deleted successfully!");
    }

    /**
     * Handles a share board request.
     */
    private void handleShareBoardRequest() {
        // Get the authenticated user
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        SystemUser user = authorizationService.session().get().authenticatedUser();

        // Get all the boards owned by the user
        List<Board> ownedBoards = boardController.findBoardsByOwner(user);

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ARCHIVED) {
                continue;
            }
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        var boardSelectedBytes = sendRequestAndGetResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        if (boardSelectedBytes == null) {
            sendErrorResponse();
            return;
        }

        if (boardSelectedBytes.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        Long boardId = Long.parseLong(new String(boardSelectedBytes.getData()));

        // Retrieve the board from the database
        Board board = boardController.findBoardById(boardId);
        if (board == null) {
            sendErrorResponse();
            return;
        }

        // Ask the client which system user they would like to add as a participant
        List<SystemUser> allSystemUsers = boardController.findAllSystemUsers();
        List<BoardParticipant> participants = board.getParticipants();

        // filter out the participants that are already in the board and the owner
        List<SystemUser> availableSystemUsers = allSystemUsers
                .stream()
                .filter(systemUser -> participants.stream().noneMatch(boardParticipant -> boardParticipant.id().getUser().equals(systemUser)))
                .filter(systemUser -> !systemUser.equals(board.getOwner()))
                .toList();

        if (availableSystemUsers.isEmpty()) {
            sendErrorResponse();
            return;
        }

        sb = new StringBuilder();
        for (SystemUser systemUser : availableSystemUsers) {
            sb.append(systemUser.identity())
                    .append("\0");
        }

        var participant = sendRequestAndGetResponse(SBPCodeEnum.LIST_PART.getCode(), sb.toString().getBytes());

        if (participant == null) {
            sendErrorResponse();
            return;
        }

        if (participant.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        String systemUserId = new String(participant.getData());

        var sharedBoard = boardController.shareBoard(board, systemUserId);

        if (sharedBoard != null) {
            sendAcknowledgeResponse();
            successMessage("Board shared successfully!");
        } else {
            sendErrorResponse();
            errorMessage("Board sharing failed!");
        }
    }

    /**
     * Handles a Remove Participant request.
     */
    private void handleRemovePartipantRequest() {
        // Get the authenticated user
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        SystemUser user = authorizationService.session().get().authenticatedUser();

        // Get all the boards owned by the user
        List<Board> ownedBoards = boardController.findBoardsByOwner(user);

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ARCHIVED) {
                continue;
            }
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        var boardSelectedBytes = sendRequestAndGetResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        if (boardSelectedBytes == null) {
            sendErrorResponse();
            return;
        }

        if (boardSelectedBytes.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        Long boardId = Long.parseLong(new String(boardSelectedBytes.getData()));

        // Retrieve the board from the database
        Board board = boardController.findBoardById(boardId);
        if (board == null) {
            sendErrorResponse();
            return;
        }

        // Ask the client which system user they would like to remove as a participant
        List<BoardParticipant> participants = board.getParticipants();

        if (participants.isEmpty()) {
            sendErrorResponse();
            return;
        }

        sb = new StringBuilder();
        for (BoardParticipant participant : participants) {
            sb.append(participant.id()
                            .getUser()
                            .identity())
                    .append("\0");
        }

        var participant = sendRequestAndGetResponse(SBPCodeEnum.LIST_PART.getCode(), sb.toString().getBytes());

        if (participant == null) {
            sendErrorResponse();
            return;
        }

        if (participant.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        String systemUserId = new String(participant.getData());
        try {
            boardController.removeParticipant(board, systemUserId);

        } catch (Exception e) {
            sendErrorResponse();
            errorMessage("Participant removal failed!");
        }
        sendAcknowledgeResponse();
        successMessage("Participant removed successfully!");
    }

    /**
     * Handles a Toggle Participant write access request.
     */
    private void handleToggleParticipantWriteAccessRequest() {
        // Get the authenticated user
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        SystemUser user = authorizationService.session().get().authenticatedUser();

        // Get all the boards owned by the user
        List<Board> ownedBoards = boardController.findBoardsByOwner(user);

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ARCHIVED) {
                continue;
            }
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        var boardSelectedBytes = sendRequestAndGetResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        if (boardSelectedBytes == null) {
            sendErrorResponse();
            return;
        }

        if (boardSelectedBytes.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        Long boardId = Long.parseLong(new String(boardSelectedBytes.getData()));

        // Retrieve the board from the database
        Board board = boardController.findBoardById(boardId);
        if (board == null) {
            sendErrorResponse();
            return;
        }

        // Ask the client which system user they would like to toggle write access
        List<BoardParticipant> participants = board.getParticipants();

        if (participants.isEmpty()) {
            sendErrorResponse();
            return;
        }

        sb = new StringBuilder();
        for (BoardParticipant participant : participants) {
            sb.append(participant.id()
                            .getUser()
                            .identity())
                    .append("\0");
        }

        var participant = sendRequestAndGetResponse(SBPCodeEnum.LIST_PART.getCode(), sb.toString().getBytes());

        if (participant == null) {
            sendErrorResponse();
            return;
        }

        if (participant.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        String systemUserId = new String(participant.getData());
        var newboard = boardController.toggleParticipantWriteAccess(board, systemUserId);

        if (newboard != null) {
            sendAcknowledgeResponse();
            successMessage("Participant write access toggled successfully!");
        } else {
            sendErrorResponse();
            errorMessage("Participant write access toggle failed!");
        }

    }

    /**
     * Handles an archive board request.
     */
    private void handleArchiveBoardRequest() {
        // Get the authenticated user
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        SystemUser user = authorizationService.session().get().authenticatedUser();

        // Get all the boards owned by the user
        List<Board> ownedBoards = boardController.findBoardsByOwner(user);

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ARCHIVED) {
                continue;
            }
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        var boardSelectedBytes = sendRequestAndGetResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        if (boardSelectedBytes == null) {
            sendErrorResponse();
            return;
        }

        if (boardSelectedBytes.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        Long boardId = Long.parseLong(new String(boardSelectedBytes.getData()));

        // Retrieve the board from the database
        Board board = boardController.findBoardById(boardId);
        if (board == null) {
            sendErrorResponse();
            return;
        }

        var archivedBoard = boardController.archiveBoard(board);

        if (archivedBoard != null) {
            sendAcknowledgeResponse();
            successMessage("Board archived successfully!");
        } else {
            sendErrorResponse();
            errorMessage("Board archiving failed!");
        }
    }

    /**
     * Handles an unarchive board request.
     */
    private void handleUnarchiveBoardRequest() {
        // Get the authenticated user
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        SystemUser user = authorizationService.session().get().authenticatedUser();

        // Get all the boards owned by the user
        List<Board> ownedBoards = boardController.findBoardsByOwner(user);

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ACTIVE) {
                continue;
            }
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        var boardSelectedBytes = sendRequestAndGetResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        if (boardSelectedBytes == null) {
            sendErrorResponse();
            return;
        }

        if (boardSelectedBytes.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        Long boardId = Long.parseLong(new String(boardSelectedBytes.getData()));

        // Retrieve the board from the database
        Board board = boardController.findBoardById(boardId);
        if (board == null) {
            sendErrorResponse();
            return;
        }

        var unarchivedBoard = boardController.unarchiveBoard(board);

        if (unarchivedBoard != null) {
            sendAcknowledgeResponse();
            successMessage("Board unarchived successfully!");
        } else {
            sendErrorResponse();
            errorMessage("Board unarchiving failed!");
        }
    }

    /**
     * Handles a view archived boards request.
     */
    private void handleViewArchivedBoardsRequest() {
        // Get the authenticated user
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        SystemUser user = authorizationService.session().get().authenticatedUser();

        // Get all the boards owned by the user
        List<Board> ownedBoards = boardController.findBoardsByOwner(user);

        //check if there are any archived boards
        boolean hasArchivedBoards = false;
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ARCHIVED) {
                hasArchivedBoards = true;
                break;
            }
        }

        if (!hasArchivedBoards) {
            sendErrorResponse();
            errorMessage("No archived boards!");
            return;
        }

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ACTIVE) {
                continue;
            }
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        sendResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        successMessage("Archived boards listed successfully!");
    }

    /**
     * Handles a view boards request.
     */
    private void handleViewBoardsRequest() {
        List<Board> boards = boardController.findAllBoards();

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : boards) {
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        sendResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        successMessage("Boards listed successfully!");
    }

    /**
     * Handels a create a post-it request.
     */
    private void handleCreateAPostIt() {
        // Get the authenticated user
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        SystemUser user = authorizationService.session().get().authenticatedUser();

        // Get all the boards owned by the user
        List<Board> ownedBoards = boardController.findBoardsThatUserCanAddPostIt(user);

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ARCHIVED) {
                continue;
            }
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        var boardSelectedBytes = sendRequestAndGetResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        if (boardSelectedBytes == null) {
            sendErrorResponse();
            return;
        }

        if (boardSelectedBytes.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        Long boardId = Long.parseLong(new String(boardSelectedBytes.getData()));

        // Retrieve the board from the database
        Board board = boardController.findBoardById(boardId);
        if (board == null) {
            sendErrorResponse();
            return;
        }

        //response has postit data
        var response = sendRequestAndGetResponse(SBPCodeEnum.ACK.getCode(), new byte[0]);

        if (response == null) {
            sendErrorResponse();
            return;
        }

        String[] postItData = new String(response.getData()).split("\0");
        String postItTitle = postItData[0];
        String postItContent = postItData[1];
        String postItRow = postItData[2];
        String postItColumn = postItData[3];

        board = boardController.createPostIt(board, postItTitle, postItContent, Integer.parseInt(postItRow), Integer.parseInt(postItColumn));

        if (board != null) {
            sendAcknowledgeResponse();
            successMessage("Post-it created successfully!");
        } else {
            sendErrorResponse();
            errorMessage("Post-it creation failed!");
        }
    }

    /**
     * Handles a change a post-it request.
     */
    private void handleChangeAPostIt() {
        // Get the authenticated user
        if ((!authorizationService.hasSession()) || authorizationService.session().isEmpty()) {
            errorMessage("Session error!");
            stopConnection();
            return;
        }

        SystemUser user = authorizationService.session().get().authenticatedUser();

        // Get all the boards owned by the user
        List<Board> ownedBoards = boardController.findBoardsThatUserCanAddPostIt(user);

        // Send the list of boards to the client
        StringBuilder sb = new StringBuilder();
        // We need the board id and the board title,
        // because there might be multiple boards with the same title from different users.
        for (Board board : ownedBoards) {
            if (board.getStatus() == BoardStatusEnum.ARCHIVED) {
                continue;
            }
            sb.append(board.identity())
                    .append("\0")
                    .append(board.getTitle())
                    .append("\0");
        }
        var boardSelectedBytes = sendRequestAndGetResponse(SBPCodeEnum.LIST_BOARD.getCode(), sb.toString().getBytes());

        if (boardSelectedBytes == null) {
            sendErrorResponse();
            return;
        }

        if (boardSelectedBytes.getData().length == 0) {
            sendErrorResponse();
            return;
        }

        Long boardId = Long.parseLong(new String(boardSelectedBytes.getData()));

        // Retrieve the board from the database
        Board board = boardController.findBoardById(boardId);
        if (board == null) {
            sendErrorResponse();
            return;
        }

        //get list of postits of the board
        List<PostIt> postIts = board.getPostIts();
        if (postIts == null) {
            sendErrorResponse();
            return;
        }

        if (postIts.isEmpty()) {
            sendErrorResponse();
            return;
        }

        StringBuilder sb2 = new StringBuilder();
        for (var specificPostIt : postIts) {
            sb2.append(specificPostIt.identity())
                    .append("\0")
                    .append(specificPostIt.getTitle())
                    .append("\0");
        }

        // get postit id
        var response = sendRequestAndGetResponse(SBPCodeEnum.CHG_POSTIT.getCode(), sb2.toString().getBytes());

        if (response == null) {
            sendErrorResponse();
            return;
        }

        if (response.code() != SBPCodeEnum.CHG_POSTIT.getCode()) {
            sendErrorResponse();
            errorMessage("Wrong request type received!");
            return;
        }

        if (response.getData().length == 0) {
            sendErrorResponse();
            return;
        }


        var postitContent = new String(response.getData()).split("\0");
        Long postItId = Long.parseLong(postitContent[0]);
        String content = postitContent[1];

        board = boardController.changePostIt(board, postItId, content);

        if (board != null) {
            sendAcknowledgeResponse();
            successMessage("Post-it created successfully!");
        } else {
            sendErrorResponse();
            errorMessage("Post-it creation failed!");
        }
    }


    private void handleInvalidRequest() {
        sendErrorResponse();
    }

    private void sendAcknowledgeResponse() {
        sendResponse(SBPCodeEnum.ACK.getCode(), new byte[0]);
    }

    private void sendErrorResponse() {
        sendResponse(SBPCodeEnum.ERR.getCode(), new byte[0]);
    }

    private void sendAuthenticationResponse(boolean success) {
        SBPCodeEnum code = success ? SBPCodeEnum.ACK : SBPCodeEnum.ERR;
        sendResponse(code.getCode(), new byte[0]);
    }

    private void sendResponse(byte messageCode, byte[] data) {
        SBPMessageFormat response = new SBPMessageFormat(version, messageCode, data);
        byte[] responseBytes = response.toByteArray();

        try {
            out.write(responseBytes);
        } catch (IOException e) {
            System.out.println("Error sending response");
            return;
        }

        stopConnection();
    }

    private SBPMessageFormat sendRequestAndGetResponse(byte messageCode, byte[] data) {
        SBPMessageFormat response = new SBPMessageFormat(version, messageCode, data);
        byte[] requestBytes = response.toByteArray();

        try {
            out.write(requestBytes);
        } catch (IOException e) {
            errorMessage("Error sending request!");
            stopConnection();
        }

        byte[] responseBytes = new byte[4096];
        int bytesRead;
        try {
            bytesRead = in.read(responseBytes);
        } catch (IOException e) {
            errorMessage("\n### Read response failed! ###\n");
            return null;
        }
        return SBPMessageFormat.fromByteArray(Arrays.copyOf(responseBytes, bytesRead));
    }

    private void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}