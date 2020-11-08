package bean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Category")
@NamedQueries({
        @NamedQuery(name = "Category.list",
                query = "SELECT c FROM Category c"),
        @NamedQuery(name = "Category.findByTitle",
                query = "SELECT c FROM Category c WHERE  c.title=:title"),
        @NamedQuery(name = "Category.deleteByID",
        query = "DELETE FROM Category c WHERE  c.id=:CID")
})
public class Category implements Serializable {
    @Id
    @Column(name = "CID", length = 40)
    private String id;
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    public Category() {
    }

    public Category(String title) {
        this.title = title;
    }

    public Category(String id, String title) {
        this.id = id;
        this.title = title;
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
}
