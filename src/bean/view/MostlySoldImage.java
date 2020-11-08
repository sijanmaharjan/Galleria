package bean.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "mostly_sold_images")
public class MostlySoldImage  implements Serializable {
    @Id
    @Column(name = "IID")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "sales")
    private Long sales;

    public MostlySoldImage() {
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

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }
}
