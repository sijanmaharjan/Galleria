package controller.taglib;

import bean.remote.WishBeanRemote;

import javax.ejb.EJB;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class IsInWishlist extends SimpleTagSupport {

    private String IID;
    private String UID;

    @EJB
    private WishBeanRemote wishBeanRemote;

    public void setIID(String IID) {
        this.IID = IID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print(wishBeanRemote.availableInWishlist(IID, UID)? "true":"false");
    }
}
