package com.ibks.cpay;

public class HexConverter {

    private String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.toUpperCase(Character.forDigit((num >> 4) & 0xF, 16));
        hexDigits[1] = Character.toUpperCase(Character.forDigit((num & 0xF), 16));
        return new String(hexDigits);
    }

    public String encodeHexString(byte[] byteArray) {
        StringBuffer hexStringBuffer = new StringBuffer();
        for (byte b : byteArray) {
            hexStringBuffer.append(byteToHex(b));
            hexStringBuffer.append(' ');
        }
        return hexStringBuffer.toString();
    }

    public String FromByteToHex(byte[] byteArray) {
        StringBuilder hexStringBuffer = new StringBuilder();
        for (byte b : byteArray) {
            hexStringBuffer.append(byteToHex(b));
        }
        return hexStringBuffer.toString();
    }

}
