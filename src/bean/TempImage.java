package bean;

import java.io.Serializable;

public class TempImage implements Serializable {
    private String id;
    private String title;
    private String source;
    private String description;
    private String ratings;
    private boolean favourite;
    private boolean free;
    private boolean ownedOrFree;
    private Double price;


    public TempImage() {
    }

    public TempImage(String id, String title, String source, String description, String ratings, boolean favourite, boolean free, boolean ownedOrFree, Double price) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.description = description;
        this.ratings = ratings;
        this.favourite = favourite;
        this.free = free;
        this.ownedOrFree = ownedOrFree;
        this.price = price;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isOwnedOrFree() {
        return ownedOrFree;
    }

    public void setOwnedOrFree(boolean ownedOrFree) {
        this.ownedOrFree = ownedOrFree;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
