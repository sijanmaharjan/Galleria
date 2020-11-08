<%@ page import="bean.view.Wish" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="wishes" scope="request" type="java.util.List<bean.view.Wish>"/>
<div style="min-height: 100vh">
    <h1 class="my-4">Your Wishlist</h1>
    <c:forEach items="${wishes}" var="image">
        <p style="width: 100%">${image.title} <a class="float-right">$${image.price}</a></p>
    </c:forEach>
    <hr/>
    <%Double total = 0.0; for(Wish w: wishes){ total+=w.getPrice(); }%>
    <p style="width: 100%">Total <a class="float-right">$<u:Rounded number="<%=total%>"/></a></p>
    <c:if test="<%=total>0%>">
        <button class="custom-btn" onclick="checkout()">Proceed to Checkout</button>
    </c:if>
</div>
<script>
    function checkout() {
        $.get('galleria.checkout(wishes)', function (data) {
            $('#download-wish-list').fadeIn(100);
            displayMask();
        }).fail(handleRequestFailure);
        event.stopPropagation();
    }
    $('#download-wish-list').click(function (event) {
        event.stopPropagation();
    })
    $(mask).click(function (event) {
        $('#download-wish-list').fadeOut(100);
        window.location = 'galleria.oh?p=e';
        event.stopPropagation();
    })
</script>
