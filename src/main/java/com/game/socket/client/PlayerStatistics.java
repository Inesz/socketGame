package com.game.socket.client;

public class PlayerStatistics {
    private boolean initiator;
    private Integer msgSentNumber;
    private Integer msgReceivedNumber;
    private String message;

    private PlayerStatistics() {
    }

    public PlayerStatistics(boolean initiator, Integer msgSentNumber, Integer msgReceivedNumber, String message) {
        this.initiator = initiator;
        this.msgSentNumber = msgSentNumber;
        this.msgReceivedNumber = msgReceivedNumber;
        this.message = message;
    }

    public boolean isInitiator() {
        return initiator;
    }

    public void setInitiator(boolean initiator) {
        this.initiator = initiator;
    }

    public Integer getMsgSentNumber() {
        return msgSentNumber;
    }

    public void incrementMsgSentNumber() {
        ++this.msgSentNumber;
    }

    public Integer getMsgReceivedNumber() {
        return msgReceivedNumber;
    }

    public void incrementMsgReceivedNumber() {
        ++this.msgReceivedNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
