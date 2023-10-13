package com.sms;

class SMSRecord {
    private String senderNumber;
    private String receiverNumber;
    private int messageLength;
    private String messageType;
    private String message;
    private String messageText;

    public SMSRecord(String senderNumber, String receiverNumber, int messageLength, String messageType, String message) {
        this.senderNumber = senderNumber;
        this.receiverNumber = receiverNumber;
        this.messageLength = messageLength;
        this.messageType = messageType;
        this.message = message;
        this.messageText = message;

    }


    public String getSenderNumber() {
        return senderNumber;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }
    public String getMessageText() {
        return messageText;
    }

}