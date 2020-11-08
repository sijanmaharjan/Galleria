package bean.remote;

import bean.entity.Category;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface CategoryBeanRemote {
    List<Category> getList();
    Category find(String title);
    Category getOrCreate(String title);
    void update(Category category);
    void delete(String id);
}
