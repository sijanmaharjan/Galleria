package bean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Wishlist")
@NamedQueries({
        @NamedQuery(name = "Wishlist.deleteByUID",
                query = "DELETE FROM Wishlist w WHERE w.user.id = :UID"),
        @NamedQuery(name = "Wishlist.deleteByIID",
                query = "DELETE FROM Wishlist w WHERE w.image.id = :IID"),
        @NamedQuery(name = "Wishlist.deleteBy_IID_UID",
                query = "DELETE FROM Wishlist w WHERE w.user.id = :UID AND w.image.id = :IID"),
        @NamedQuery(name = "Wishlist.checkAvailable",
                query = "SELECT COUNT(w) FROM Wishlist w WHERE w.user.id = :UID AND w.image.id = :IID"),
        @NamedQuery(name = "Wishlist.getByUID",
                query = "SELECT w FROM Wishlist w WHERE w.user.id = :UID")
})
public class Wishlist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WID")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IID", nullable = false)
    private Image image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID", nullable = false)
    private User user;

    public Wishlist() {
    }

    public Wishlist(String IID, String UID) {
        this.image = new Image();
        this.image.setId(IID);
        this.user = new User();
        this.user.setId(UID);
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
}
