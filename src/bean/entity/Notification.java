package bean.entity;

import bean.entity.option.NotificationCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Notification")
@NamedQueries({
        @NamedQuery(name = "Notification.messages",
                query = "SELECT n FROM Notification n WHERE n.user.id = :UID order by n.timestamp desc "),
        @NamedQuery(name = "Notification.message_count",
                query = "SELECT COUNT(n.id) FROM Notification n WHERE n.user.id = :UID"),
        @NamedQuery(name = "Notification.removeById",
                query = "DELETE FROM Notification n WHERE n.user.id = :UID AND n.id = :NID"),
        @NamedQuery(name = "Notification.removeByUID",
                query = "DELETE FROM Notification n WHERE n.user.id = :UID"),
        @NamedQuery(name = "Notification.removeByIID",
                query = "DELETE FROM Notification n WHERE n.payload = :IID")
})
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NID")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    private NotificationCode code;
    @Column(name = "payload", length = 150, nullable = false)
    private String payload;
    @Column(name = "message", length = 120, nullable = false)
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    public Notification() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NotificationCode getCode() {
        return code;
    }

    public void setCode(NotificationCode code) {
        this.code = code;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
