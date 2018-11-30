package de.stoxygen.model;

import java.io.Serializable;

public class TransmissionMessage implements Serializable {

    private Long transmissionsId;
    private String receiver;
    private String channel;
    private Long notificationsId;

    public TransmissionMessage() {}

    public TransmissionMessage(Long transmissionsId, String receiver, String channel, Long notificationsId) {
        this.transmissionsId = transmissionsId;
        this.receiver = receiver;
        this.channel = channel;
        this.notificationsId = notificationsId;
    }

    public Long getTransmissionsId() {
        return transmissionsId;
    }

    public void setTransmissionsId(Long transmissionsId) {
        this.transmissionsId = transmissionsId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getNotificationsId() {
        return notificationsId;
    }

    public void setNotificationsId(Long notificationsId) {
        this.notificationsId = notificationsId;
    }

    @Override
    public String toString() {
        return "TransmissionMessage{" +
                "transmissionsId=" + transmissionsId +
                ", receiver='" + receiver + '\'' +
                ", channel='" + channel + '\'' +
                ", notificationsId=" + notificationsId +
                '}';
    }
}
