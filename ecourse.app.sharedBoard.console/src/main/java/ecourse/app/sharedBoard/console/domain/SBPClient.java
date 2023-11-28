package ecourse.app.sharedBoard.console.domain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import static ecourse.app.sharedBoard.console.domain.ColorOutputUtils.errorMessage;
import static ecourse.app.sharedBoard.console.domain.SBPCodeEnum.*;

public class SBPClient {
    private static final int SERVER_PORT = 2048;
    private static final String SERVER_IP = "localhost";
    byte version = 1;
    private Socket clientSocket;
    private OutputStream out;
    private InputStream in;

    /**
     * Starts the connection to the server
     */
    public boolean startConnection() {
        try {
            clientSocket = new Socket(SERVER_IP, SERVER_PORT);
            out = clientSocket.getOutputStream();
            in = clientSocket.getInputStream();
        } catch (IOException e) {
            errorMessage("\n### Server is not running! ###\n");
            return false;
        }
        return true;
    }

    /**
     * Sends a request to the server and returns the response
     *
     * @param messageCode The message code for the request
     * @param data        The data for the request
     * @return The response from the server
     */
    public SBPMessageFormat sendRequest(byte messageCode, byte[] data) {
        SBPMessageFormat request = new SBPMessageFormat(version, messageCode, data);
        byte[] requestBytes = request.toByteArray();
        try {
            out.write(requestBytes);
        } catch (IOException e) {
            errorMessage("\n### Send request failed! ###\n");
            return null;
        }

        // Get the response from the server
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

    /**
     * Sends a communications test request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendCommunicationsTestRequest() {
        return sendRequest(COMMTEST.getCode(), new byte[0]);
    }

    /**
     * Sends an authentication request to the server
     *
     * @param username The username to authenticate with
     * @param password The password to authenticate with
     * @return The response from the server
     */
    public SBPMessageFormat sendAuthenticationRequest(String username, String password) {
        byte[] usernameBytes = username.getBytes();
        byte[] passwordBytes = password.getBytes();

        // Append \0 to the end of the username and password, in order to turn them into null-terminated strings.
        usernameBytes = Arrays.copyOf(usernameBytes, usernameBytes.length + 1);
        passwordBytes = Arrays.copyOf(passwordBytes, passwordBytes.length + 1);

        // Create the data for the request. Data will be in the format: [username][password]
        byte[] data = new byte[usernameBytes.length + passwordBytes.length];
        System.arraycopy(usernameBytes, 0, data, 0, usernameBytes.length);
        System.arraycopy(passwordBytes, 0, data, usernameBytes.length, passwordBytes.length);

        return sendRequest(AUTH.getCode(), data);
    }

    /**
     * Sends an end of session request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendEndOfSessionRequest() {
        return sendRequest(DISCONN.getCode(), new byte[0]);
    }

    /**
     * Stops the connection to the server
     */
    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a create board request to the server
     *
     * @param title The title of the board
     * @param rows  The number of rows in the board
     * @param cols  The number of columns in the board
     * @return The response from the server
     */
    public SBPMessageFormat sendCreateBoardRequest(String title, int rows, int cols) {
        byte[] titleBytes = title.getBytes();
        byte[] rowsBytes = String.valueOf(rows).getBytes();
        byte[] colsBytes = String.valueOf(cols).getBytes();

        titleBytes = Arrays.copyOf(titleBytes, titleBytes.length + 1);
        rowsBytes = Arrays.copyOf(rowsBytes, rowsBytes.length + 1);
        colsBytes = Arrays.copyOf(colsBytes, colsBytes.length + 1);

        byte[] data = new byte[titleBytes.length + rowsBytes.length + colsBytes.length];

        System.arraycopy(titleBytes, 0, data, 0, titleBytes.length);
        System.arraycopy(rowsBytes, 0, data, titleBytes.length, rowsBytes.length);
        System.arraycopy(colsBytes, 0, data, titleBytes.length + rowsBytes.length, colsBytes.length);

        return sendRequest(CRT_BOARD.getCode(), data);
    }

    /**
     * Send a postIt data request to the server
     *
     * @param title   The title of the postIt
     * @param content The content of the postIt
     * @param row     The row of the postIt
     * @param column  The column of the postIt
     * @return The response from the server
     */
    public SBPMessageFormat sendPostItRequest(String title, String content, int row, int column) {
        byte[] postItTitle = title.getBytes();
        byte[] postItContent = content.getBytes();
        byte[] postItRow = Integer.toString(row).getBytes();
        byte[] postItColumn = Integer.toString(column).getBytes();

        postItTitle = Arrays.copyOf(postItTitle, postItTitle.length + 1);
        postItContent = Arrays.copyOf(postItContent, postItContent.length + 1);
        postItRow = Arrays.copyOf(postItRow, postItRow.length + 1);
        postItColumn = Arrays.copyOf(postItColumn, postItColumn.length + 1);

        byte[] postItData = new byte[postItTitle.length + postItContent.length + postItRow.length + postItColumn.length];

        System.arraycopy(postItTitle, 0, postItData, 0, postItTitle.length);
        System.arraycopy(postItContent, 0, postItData, postItTitle.length, postItContent.length);
        System.arraycopy(postItRow, 0, postItData, postItTitle.length + postItContent.length, postItRow.length);
        System.arraycopy(postItColumn, 0, postItData, postItTitle.length + postItContent.length + postItRow.length, postItColumn.length);

        return sendRequest(CRT_POSTIT.getCode(), postItData);
    }

    /**
     * Sends a share board request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendShareBoardRequest() {
        return sendRequest(SRD_BOARD.getCode(), new byte[0]);
    }

    /**
     * Sends a delete board request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendDeleteBoardRequest() {
        return sendRequest(DEL_BOARD.getCode(), new byte[0]);
    }

    /**
     * Sends a remove board request to the server
     *
     * @return The response from the server.
     */
    public SBPMessageFormat removeBoardParticipantRequest() {
        return sendRequest(REM_PART.getCode(), new byte[0]);
    }

    /**
     * Sends a toggle write access request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat toggleCanWriteAccessParticipantRequest() {
        return sendRequest(TOGGLE_PERM_PART.getCode(), new byte[0]);
    }

    /**
     * Sends a view updates request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendViewUpdatesRequest() {
        return sendRequest(VIEW_REAL_TIME_UPD_BOARD.getCode(), new byte[0]);
    }

    /**
     * Sends a create post-it request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendCreatePostItRequest() {
        return sendRequest(CRT_POSTIT.getCode(), new byte[0]);
    }

    /**
     * Sends a change post-it request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendChangePostItRequest() {
        return sendRequest(CHG_POSTIT.getCode(), new byte[0]);
    }

    /**
     * Sends an undo post-it change request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendUndoPostItChangeRequest() {
        return sendRequest(UNDO_POSTIT.getCode(), new byte[0]);
    }

    /**
     * Sends a view history of updates in board request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendViewHistoryRequest() {
        return sendRequest(VIEW_HST_UPD_BOARD.getCode(), new byte[0]);
    }

    /**
     * Sends an archive board request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendArchiveBoardRequest() {
        return sendRequest(ARCHIVE_BOARD.getCode(), new byte[0]);
    }

    /**
     * Sends an unarchive board request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendRestoreBoardRequest() {
        return sendRequest(RESTORE_BOARD.getCode(), new byte[0]);
    }

    /**
     * Sends a view archived boards request to the server
     *
     * @return The response from the server
     */
    public SBPMessageFormat sendViewArchivedBoardsRequest() {
        return sendRequest(VIEW_ARCH_BOARD.getCode(), new byte[0]);
    }

    /**
     * Sends a list boards request to the server.
     *
     * @return The response from the server.
     */
    public SBPMessageFormat sendListBoardsRequest() {
        return sendRequest(LIST_BOARD.getCode(), new byte[0]);
    }

    /**
     * Sends a list participants request to the server.
     *
     * @return The response from the server.
     */
    public SBPMessageFormat viewBoardParticipantsRequest() {
        return sendRequest(LIST_PART.getCode(), new byte[0]);
    }
}
