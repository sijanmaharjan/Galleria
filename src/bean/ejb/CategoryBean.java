package bean.ejb;

import bean.entity.Category;
import bean.entity.EntityMan;
import bean.entity.Image;
import bean.remote.CategoryBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Stateless
public class CategoryBean implements CategoryBeanRemote {
    @Override
    public List<Category> getList() {
        return EntityMan.execute(em->em.createNamedQuery("Category.list", Category.class).getResultList());
    }

    @Override
    public Category find(String title) {
        try {
            return EntityMan.execute(em -> em.createNamedQuery("Category.findByTitle", Category.class).setParameter("title", title).getSingleResult());
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Category getOrCreate(String title) {
        Category category= find(title);
        if(category == null){
            Category cat = new Category(UUID.randomUUID().toString(), title);
            EntityMan.execTransaction(em->em.persist(cat));
            category = cat;
        }
        return category;
    }

    @Override
    public void update(Category category) {
        EntityMan.execTransaction(em->em.merge(category));
    }

    @Override
    public void delete(String CID) {
        ImageBean imageBean = new ImageBean();
        List<Image> images = imageBean.listByCategoryId(CID);
        EntityMan.execTransaction(ex->{
            for(Image image : images) {
                String IID = image.getId();
                ex.createNamedQuery("Favourite.deleteByIID").setParameter("IID", IID).executeUpdate();
                ex.createNamedQuery("Notification.removeByIID").setParameter("IID", IID).executeUpdate();
                ex.createNamedQuery("Rating.deleteByIID").setParameter("IID", IID).executeUpdate();
                ex.createNamedQuery("SoldImage.deleteByIID").setParameter("IID", IID).executeUpdate();
                ex.createNamedQuery("Wishlist.deleteByIID").setParameter("IID", IID).executeUpdate();
                ex.createNamedQuery("Image.deleteByID").setParameter("IID", IID).executeUpdate();
            }
            ex.createNamedQuery("Category.deleteByID").setParameter("CID", CID).executeUpdate();
        });
    }
}
