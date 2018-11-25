package de.stoxygen.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transmission extends Auditable<String>{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transmissionsId;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String channel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransmissionStatus transmissionStatus;

    @Column(nullable = false)
    private Integer resendCount = 0;

    @Column
    private Date sent;

    @ManyToOne
    private Notification notification;

    public Transmission() {}

    public Transmission(String receiver, String channel, TransmissionStatus transmissionStatus, Notification notification) {
        this.receiver = receiver;
        this.channel = channel;
        this.transmissionStatus = transmissionStatus;
        this.notification = notification;
    }

    public Long getTransmissionsId() {
        return transmissionsId;
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

    public TransmissionStatus getTransmissionStatus() {
        return transmissionStatus;
    }

    public void setTransmissionStatus(TransmissionStatus transmissionStatus) {
        this.transmissionStatus = transmissionStatus;
    }

    public Integer getResendCount() {
        return resendCount;
    }

    public void setResendCount(Integer resendCount) {
        this.resendCount = resendCount;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "transmissionsId=" + transmissionsId +
                ", receiver='" + receiver + '\'' +
                ", channel='" + channel + '\'' +
                ", transmissionStatus=" + transmissionStatus +
                ", resendCount=" + resendCount +
                ", sent=" + sent +
                ", notification=" + notification +
                '}';
    }
}
