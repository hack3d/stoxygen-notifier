package de.stoxygen.model;

import javax.persistence.*;

@Entity
public class Notification extends Auditable<String> {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationsId;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationCategory category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationSeverity notificationSeverity;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

    @Column(nullable = false)
    private String contentType = "text/plain";

    public Notification() {}

    public Notification(String sender, NotificationCategory category, NotificationSeverity notificationSeverity, String content,
                        String description, NotificationStatus notificationStatus) {
        this.sender = sender;
        this.category = category;
        this.notificationSeverity = notificationSeverity;
        this.content = content;
        this.description = description;
        this.notificationStatus = notificationStatus;
    }

    public Long getNotificationsId() {
        return notificationsId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public NotificationCategory getCategory() {
        return category;
    }

    public void setCategory(NotificationCategory category) {
        this.category = category;
    }

    public NotificationSeverity getNotificationSeverity() {
        return notificationSeverity;
    }

    public void setNotificationSeverity(NotificationSeverity notificationSeverity) {
        this.notificationSeverity = notificationSeverity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
