package bean.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "highly_rated_images")
public class HighlyRatedImage implements Serializable {
    @Id
    @Column(name = "IID")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "rates")
    private Float rates;
    @Column(name = "rate_count")
    private Integer rateCount;

    public HighlyRatedImage() {
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

    public Float getRates() {
        return rates;
    }

    public void setRates(Float rates) {
        this.rates = rates;
    }

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }
}
