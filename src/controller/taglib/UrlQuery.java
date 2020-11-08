package controller.taglib;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class UrlQuery extends SimpleTagSupport {
    private static String url = "galleria.oh";
    private String page;
    private String CID;
    private String filterBy;
    private String searchText;
    private Double max;
    private Double min;

    public void setPage(String page) {
        this.page = page;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.print(getFinalUrl());
    }

    public String getFinalUrl(){
        String finalUrl = url+"?p="+page;
        if(searchText != null && !searchText.equals("")) finalUrl += "&s="+searchText;
        if(CID != null && !CID.equals("")) finalUrl += "&c="+CID;
        if(filterBy != null && !filterBy.equals("")) finalUrl += "&f="+filterBy;
        if(min != null) finalUrl += "&min="+min;
        if(max != null) finalUrl += "&max="+max;
        return finalUrl;
    }
}
