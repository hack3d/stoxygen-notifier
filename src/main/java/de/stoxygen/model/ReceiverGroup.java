package de.stoxygen.model;


import javax.persistence.*;

@Entity
public class ReceiverGroup extends Auditable<String> {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiverGroupsId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationCategory category;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private String channel;

    @Column(nullable = false)
    private String receiverAddress;

    public ReceiverGroup() {}

    public Long getReceiverGroupsId() {
        return receiverGroupsId;
    }

    public NotificationCategory getCategory() {
        return category;
    }

    public void setCategory(NotificationCategory category) {
        this.category = category;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
}
