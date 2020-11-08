<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="statements" beanName="statements" scope="request" type="java.util.List<bean.view.TransactionStatement>"/>
<jsp:useBean id="zone" scope="request" type="java.time.ZoneId" beanName="zone"/>

<h3 class="mt-4">Transaction Statement</h3>
<div class="table-responsive">
    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Particulars</th>
            <th>Debit</th>
            <th>Credit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="stmt" items="${statements}">
            <u:Statement zone="${zone}" obj="${stmt}"/>
        </c:forEach>
        </tbody>
    </table>
</div>
