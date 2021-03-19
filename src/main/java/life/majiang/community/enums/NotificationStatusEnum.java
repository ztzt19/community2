package life.majiang.community.enums;

/**
 * @author zt
 * @create 2021-03-18 16:12
 */
public enum NotificationStatusEnum {
    UNREAD(0),READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
