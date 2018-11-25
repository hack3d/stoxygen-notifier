package de.stoxygen.model;

public class UserNotificationData {

    private String username;
    private String channel;
    private String receiver_address;

    public UserNotificationData() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    @Override
    public String toString() {
        return "UserNotificationData{" +
                "username='" + username + '\'' +
                ", channel='" + channel + '\'' +
                ", receiver_address='" + receiver_address + '\'' +
                '}';
    }
}
