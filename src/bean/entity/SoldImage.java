package bean.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Sold_Image")
@NamedQueries({
        @NamedQuery(name = "SoldImage.deleteByUID",
                query = "DELETE FROM SoldImage s WHERE s.user.id = :UID"),
        @NamedQuery(name = "SoldImage.deleteByIID",
                query = "DELETE FROM SoldImage s WHERE s.image.id = :IID")
})
public class SoldImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SID")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IID", nullable = false)
    private Image image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID", nullable = false)
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    public SoldImage() {
    }

    public SoldImage(String IID, String UID, Date timestamp) {
        this.image = new Image();
        this.image.setId(IID);
        this.user = new User();
        this.user.setId(UID);
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
