package controller.taglib;

import bean.remote.ImageBeanRemote;

import javax.ejb.EJB;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class IsOwnedOrAccessible extends SimpleTagSupport {
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
        StringWriter sw = new StringWriter();
        JspWriter out = getJspContext().getOut();
        getJspBody().invoke(sw);
        String[] body = sw.toString().split("\n");
        boolean condition = imageBeanRemote.isOwner(IID, UID) || imageBeanRemote.isAccessible(IID, UID);
        String open = "<if:"+(condition?"true":"false")+">";
        String close = "</if:"+(condition?"true":"false")+">";
        String concern = "";boolean isConcerned = false;
        for(String txt: body){
            String text = txt;
            if(txt.contains(open)){
                isConcerned = true;
                String[] texts = txt.split(open);
                text = texts.length > 1 ? texts[1] : "";
            }
            if(isConcerned){
                concern += text;
            }
            if(txt.contains(close)){
                concern = concern.split(close)[0];
                break;
            }
        }
        out.print(concern.trim());
    }
}