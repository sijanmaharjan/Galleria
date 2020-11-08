package bean.view;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "app_status")
@NamedQueries({
        @NamedQuery(name = "AppStatus.get", query = "SELECT s FROM AppStatus s")
})
public class AppStatus implements Serializable {
    @Id
    @Column(name = "total_images")
    private Long totalImages;
    @Column(name = "total_users")
    private Long totalUsers;
    @Column(name = "tax_collected")
    private Double taxCollected;
    @Column(name = "service_charge_collected")
    private Double serviceChargeCollected;

    public AppStatus() {
    }

    public Long getTotalImages() {
        return totalImages;
    }

    public void setTotalImages(Long totalImages) {
        this.totalImages = totalImages;
    }

    public Long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Double getTaxCollected() {
        return taxCollected;
    }

    public void setTaxCollected(Double taxCollected) {
        this.taxCollected = taxCollected;
    }

    public Double getServiceChargeCollected() {
        return serviceChargeCollected;
    }

    public void setServiceChargeCollected(Double serviceChargeCollected) {
        this.serviceChargeCollected = serviceChargeCollected;
    }
}
