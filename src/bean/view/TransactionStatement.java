package bean.view;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transaction_statement")
@NamedQueries({
        @NamedQuery(name = "TransactionStatement.get", query = "SELECT t FROM TransactionStatement t WHERE t.id = :UID ORDER BY t.date DESC")
})
public class TransactionStatement implements Serializable {
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    @Column(name = "UID")
    private String id;
    @Column(name = "particulars")
    private String particulars;
    @Column(name = "debit")
    private Double debit;
    @Column(name = "credit")
    private Double credit;

    public TransactionStatement() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
