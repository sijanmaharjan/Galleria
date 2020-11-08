package bean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Bank_Detail")
@NamedQueries({
        @NamedQuery(name = "BankDetail.deleteByUID",
                query = "DELETE FROM BankDetail b WHERE b.user.id = :UID"),
        @NamedQuery(name = "BankDetail.get",
                query = "SELECT b FROM BankDetail b WHERE b.user.id = :UID")
})
public class BankDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BID")
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID", nullable = false)
    private User user;
    @Column(name = "bank_name", length = 100, nullable = false)
    private String bankName;
    @Column(name = "account_name", length = 150, nullable = false)
    private String accountName;
    @Column(name = "account_number", length = 32, nullable = false)
    private String accountNumber;

    public BankDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
