package bean.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "most_expensive_images")
public class MostExpensiveImage implements Serializable {
    @Id
    @Column(name = "IID")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Double price;

    public MostExpensiveImage() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
