package ecourse.app.sharedBoard.console.domain;

/**
 * Enumerates the message codes used in the communication between the client and the server.
 * When changing this enum don't forget to change the correspondent enum in the server side,
 * as well as the main switch case in the server.
 */
public enum SBPCodeEnum {
    COMMTEST((byte) 0),                    //Communications test request
    DISCONN((byte) 1),                     //Disconnect request
    ACK((byte) 2),                         //Acknowledgement
    ERR((byte) 3),                         //Error
    AUTH((byte) 4),                        //User authentication request
    // Board requests
    CRT_BOARD((byte) 5),                   //Create board request
    DEL_BOARD((byte) 6),                   //Delete board request
    LIST_BOARD((byte) 7),                  //List board request, should contain pairs of board id + board title separated by \0

    // Participant requests
    SRD_BOARD((byte) 8),                   //Shared board request
    REM_PART((byte) 9),                    //Remove participant request, should contain system id of the participant
    TOGGLE_PERM_PART((byte) 10),           //Toggle participant permission request, should contain system id of the participant
    LIST_PART((byte) 11),                  //List participants request, should contain a list of participants separated by \0

    // Archive requests
    ARCHIVE_BOARD((byte) 12),              //Archive board request
    RESTORE_BOARD((byte) 13),              //Restore board request
    VIEW_ARCH_BOARD((byte) 14),            //View archived boards request

    // Post-it requests
    CRT_POSTIT((byte) 15),                 //Create post-it request
    CHG_POSTIT((byte) 16),                 //Change post-it request
    UNDO_POSTIT((byte) 17),                //Undo post-it request
    VIEW_HST_UPD_BOARD((byte) 18),         //View history update board request
    VIEW_REAL_TIME_UPD_BOARD((byte) 19),   //View real-time update board request

    // Select requests
    SEL_BOARD((byte) 20),                  //Select board request, should contain board id
    SEL_PART((byte) 21);                   //Select participant request, should contain system id of the participant


    private final byte codeName;

    SBPCodeEnum(byte code) {
        this.codeName = code;
    }

    public byte getCode() {
        return codeName;
    }
}
