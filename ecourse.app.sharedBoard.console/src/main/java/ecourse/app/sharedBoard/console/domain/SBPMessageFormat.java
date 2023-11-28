package ecourse.app.sharedBoard.console.domain;

public class SBPMessageFormat {
    private byte version;
    private byte code;
    private byte dLength1;
    private byte dLength2;
    private byte[] data;

    public SBPMessageFormat(byte version, byte code, byte dLength1, byte dLength2, byte[] data) {
        this.version = version;
        this.code = code;
        this.dLength1 = dLength1;
        this.dLength2 = dLength2;
        this.data = data;
    }

    public SBPMessageFormat(byte version, byte code, byte[] data) {
        this.version = version;
        this.code = code;
        this.data = data;
        calculateDataLength();
    }

    public static SBPMessageFormat fromByteArray(byte[] message) {
        byte version = message[0];
        byte code = message[1];
        byte dLength1 = message[2];
        byte dLength2 = message[3];

        int dataLength = calculateDataLength(dLength1, dLength2);
        byte[] data = new byte[dataLength];
        if (dataLength > 0) {
            int maxIndex = Math.min(message.length - 4, dataLength);
            System.arraycopy(message, 4, data, 0, maxIndex);
        }

        return new SBPMessageFormat(version, code, dLength1, dLength2, data);
    }

    public static int calculateDataLength(byte dLength1, byte dLength2) {
        return (dLength1 & 0xFF) + (256 * (dLength2 & 0xFF));
    }

    public byte[] toByteArray() {
        int dataLength = calculateDataLength();
        int messageLength = 4 + dataLength;
        byte[] message = new byte[messageLength];

        message[0] = version;
        message[1] = code;
        message[2] = dLength1;
        message[3] = dLength2;

        if (dataLength > 0) {
            System.arraycopy(data, 0, message, 4, dataLength);
        }

        return message;
    }

    public byte getVersion() {
        return version;
    }

    public byte getCode() {
        return code;
    }

    public byte getDLength1() {
        return dLength1;
    }

    public byte getDLength2() {
        return dLength2;
    }

    public byte[] getData() {
        return data;
    }

    public int calculateDataLength() {
        int dataLength = this.data.length;
        dLength1 = (byte) (dataLength & 0xFF);
        dLength2 = (byte) ((dataLength >> 8) & 0xFF);
        return dataLength;
    }
}


