<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <a class='nav-link <%=(p==null||p.equals("a"))?"font-weight-bold": ""%>' href="galleria.dash?p=a">
                    <div class="sb-nav-link-icon" style="width: 20px; text-align: center"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>
                <a class='nav-link <%="b".equals(p)?"font-weight-bold": ""%>' href="galleria.dash?p=b">
                    <div class="sb-nav-link-icon" style="width: 20px; text-align: center"><i class="fas fa-info"></i></div>
                    Statement
                </a>
                <a class='nav-link <%="c".equals(p)?"font-weight-bold": ""%>' href="galleria.dash?p=c">
                    <div class="sb-nav-link-icon" style="width: 20px; text-align: center"><i class="fas fa-image"></i></div>
                    Images
                </a>
                <a class='nav-link <%="d".equals(p)?"font-weight-bold": ""%>' href="galleria.dash?p=d">
                    <div class="sb-nav-link-icon" style="width: 20px; text-align: center"><i class="fas fa-bank"></i></div>
                    Bank Details
                </a>
            </div>
        </div>
    </nav>
</div>
