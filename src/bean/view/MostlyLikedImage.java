package bean.view;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mostly_liked_images")
public class MostlyLikedImage  implements Serializable {
    @Id
    @Column(name = "IID")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "likes")
    private Long likes;

    public MostlyLikedImage() {
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

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
}
