package bean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Favourite")
@NamedQueries({
        @NamedQuery(name = "Favourite.deleteByUID",
                query = "DELETE FROM Favourite f WHERE f.user.id = :UID"),
        @NamedQuery(name = "Favourite.deleteByIID",
                query = "DELETE FROM Favourite f WHERE f.image.id = :IID"),
        @NamedQuery(name = "Favourite.deleteBy_IID_UID",
                query = "DELETE FROM Favourite f WHERE f.user.id = :UID AND f.image.id = :IID")
})
public class Favourite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FID")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IID", nullable = false)
    private Image image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID", nullable = false)
    private User user;

    public Favourite() {
    }

    public Favourite(String IID, String UID){
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
