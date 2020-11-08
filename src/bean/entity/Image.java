package bean.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Image")
@NamedQueries({
        @NamedQuery(name = "Image.list",
                query = "SELECT i FROM Image i"),
        @NamedQuery(name = "Image.listByUID",
                query = "SELECT i FROM Image i WHERE i.user.id = :UID ORDER BY i.uploadTime DESC"),
        @NamedQuery(name = "Image.listByCID",
                query = "SELECT i FROM Image i WHERE i.category.id = :CID"),
        @NamedQuery(name = "Image.deleteByUID",
                query = "DELETE FROM Image i WHERE i.user.id = :UID"),
        @NamedQuery(name = "Image.deleteByID",
                query = "DELETE FROM Image i WHERE i.id = :IID"),
        @NamedQuery(name = "Image.checkOwner",
                query = "SELECT COUNT(i.id) FROM Image i WHERE i.id = :IID AND i.user.id = :UID"),
        @NamedQuery(name = "Image.checkFree",
                query = "SELECT COUNT(i.id) FROM Image i WHERE i.id = :IID AND i.price = 0.0"),
        @NamedQuery(name = "Image.checkPurchased",
                query = "SELECT COUNT(s.id) FROM SoldImage s WHERE s.image.id = :IID AND s.user.id = :UID"),
        @NamedQuery(name = "Image.checkAccessible",
                query = "SELECT COUNT(i.id) FROM Image i WHERE i.id = :IID AND i.user.id <> :UID AND true = :accept"),
        @NamedQuery(name = "Image.toggle_availability",
                query = "UPDATE Image i SET i.available= :available WHERE i.id= :IID AND i.user.id = :UID")
})
public class Image implements Serializable {
    @Id
    @Column(name = "IID", length = 40)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CID")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID", nullable = false)
    private User user;
    @Column(name = "title", length = 50, nullable = false)
    private String title;
    @Column(name = "description", length = 120, nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "source", length = 50, nullable = false)
    private String source;
    @Column(name = "available", nullable = false)
    private Boolean available = true;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upload_time", nullable = false)
    private Date uploadTime;

    public Image() {
    }

    public Image(String title, String description, String source) {
        this.title = title;
        this.description = description;
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
