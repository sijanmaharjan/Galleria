package bean.view;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "images")
@NamedQueries({
        @NamedQuery(name = "DetailedImage.find",
                query = "SELECT i FROM DetailedImage i WHERE i.UID = :UID AND i.id = :IID"),
        @NamedQuery(name = "Image.findById",
                query = "SELECT i FROM Image i WHERE i.id = :IID AND i.user.id = :UID")
})
public class DetailedImage implements Serializable {
    @Id
    @Column(name = "IID")
    private String id;
    @Column(name = "UID")
    private String UID;
    @Column(name = "uploaded_by")
    private String uploadedBy;
    @Column(name = "category")
    private String category;
    @Column(name = "title")
    private String title;
    @Column(name = "source")
    private String source;
    @Column(name = "price")
    private Double price;
    @Column(name = "is_free")
    private Boolean free;
    @Column(name = "ratings")
    private Float ratings;
    @Column(name = "rate_count")
    private Long rateCount;
    @Column(name = "is_favourite")
    private Boolean favourite;

    public DetailedImage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Float getRatings() {
        return ratings;
    }

    public void setRatings(Float ratings) {
        this.ratings = ratings;
    }

    public Long getRateCount() {
        return rateCount;
    }

    public void setRateCount(Long rateCount) {
        this.rateCount = rateCount;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }
}
