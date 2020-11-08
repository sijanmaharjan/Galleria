package controller.taglib;

import bean.remote.ImageBeanRemote;

import javax.ejb.EJB;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class IfAccessible extends SimpleTagSupport {
    private String UID;
    private String IID;

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setIID(String IID) {
        this.IID = IID;
    }

    @EJB
    private ImageBeanRemote imageBeanRemote;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        boolean isAccessible = imageBeanRemote.isAccessible(IID, UID);
        if(isAccessible){
            StringWriter sw = new StringWriter();
            getJspBody().invoke(sw);
            out.print(sw.toString());
        }
    }
}
