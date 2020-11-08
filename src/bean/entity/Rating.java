package bean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Rating")
@NamedQueries({
        @NamedQuery(name = "Rating.deleteByUID",
                query = "DELETE FROM Rating r WHERE r.user.id = :UID"),
        @NamedQuery(name = "Rating.deleteByIID",
                query = "DELETE FROM Rating r WHERE r.image.id = :IID"),
        @NamedQuery(name = "Rating.checkRated",
                query = "SELECT COUNT(r.id) FROM Rating r WHERE r.image.id = :IID AND r.user.id = :UID"),
        @NamedQuery(name = "Rating.unrate",
                query = "DELETE FROM Rating r WHERE r.user.id = :UID AND r.image.id = :IID")
})
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RID")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IID", nullable = false)
    private Image image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID", nullable = false)
    private User user;
    @Column(name = "point", nullable = false)
    private Integer point;

    public Rating() {
    }

    public Rating(String IID, String UID, Integer point){
        this.image = new Image();
        this.image.setId(IID);
        this.user = new User();
        this.user.setId(UID);
        this.point = point;
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

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
