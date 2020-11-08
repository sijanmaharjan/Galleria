<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <a class='nav-link <%=(p==null||p.equals("a"))?"font-weight-bold": ""%>' href="galleria.admin?p=a">
                    <div class="sb-nav-link-icon" style="width: 20px; text-align: center"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>
                <a class='nav-link <%="b".equals(p)?"font-weight-bold": ""%>' href="galleria.admin?p=b">
                    <div class="sb-nav-link-icon" style="width: 20px; text-align: center"><i class="fas fa-user"></i></div>
                    User Accounts
                </a>
                <a class='nav-link <%="c".equals(p)?"font-weight-bold": ""%>' href="galleria.admin?p=c">
                    <div class="sb-nav-link-icon" style="width: 20px; text-align: center"><i class="fas fa-money-bill"></i></div>
                    <c:if test="${requests.size()>0}"><small class="req-count">${requests.size()}</small></c:if>Redeem Requests
                </a>
                <a class='nav-link <%="d".equals(p)?"font-weight-bold": ""%>' href="galleria.admin?p=d">
                    <div class="sb-nav-link-icon" style="width: 20px; text-align: center"><i class="fas fa-bars"></i></div>
                    Image Categories
                </a>
            </div>
        </div>
    </nav>
</div>
