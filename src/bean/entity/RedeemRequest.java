package bean.entity;

import bean.entity.option.RedemptionStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Redeem_Request")
@NamedQueries({
        @NamedQuery(name = "RedeemRequest.deleteByUID",
                query = "DELETE FROM RedeemRequest r WHERE r.user.id = :UID"),
        @NamedQuery(name = "RedeemRequest.listPending",
                query = "SELECT r FROM RedeemRequest r WHERE r.status = :queue OR r.status = :accepted ORDER BY r.timestamp DESC"),
        @NamedQuery(name = "RedeemRequest.response",
                query = "UPDATE RedeemRequest r SET r.status= :status WHERE r.id= :RRID")
})
public class RedeemRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RRID")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID", nullable = false)
    private User user;
    @Column(name = "amount", nullable = true)
    private Double amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RedemptionStatus status = RedemptionStatus.in_queue;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = false)
    private Date timestamp = new Date();

    public RedeemRequest() {
    }

    public RedeemRequest(String UID) {
        this.user = new User();
        this.user.setId(UID);
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public RedemptionStatus getStatus() {
        return status;
    }

    public void setStatus(RedemptionStatus status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
