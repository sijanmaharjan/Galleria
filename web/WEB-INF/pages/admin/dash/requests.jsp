<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="requests" beanName="requests" type="java.util.List<bean.entity.RedeemRequest>" scope="request"/>
<jsp:useBean id="zone2" beanName="zone2" type="java.time.ZoneId" scope="request"/>

<h3 class="mt-4">Users List</h3>
<div class="table-responsive">
    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
        <thead>
        <tr>
            <th>Request Date</th>
            <th>User Account</th>
            <th>Bank Account</th>
            <th>Request Amount</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requests}" var="request">
            <tr>
                <td>${request.timestamp.toInstant().atZone(zone2).toLocalDateTime().toString()}</td>
                <td><a onclick="displayProfile(<u:MapUser UID="${request.user.id}"/>, ${request.user.blocked})" style="cursor: pointer"><u:Formatted firstName="${request.user.firstName}" lastName="${request.user.lastName}"/></a></td>
                <td><u:BankAccount UID="${request.user.id}"/></td>
                <td>$<u:Rounded number="${request.amount}"/></td>
                <td><button class="btn btn-success" onclick="markDone(${request.id})" style="${!request.status.toString().equals("accepted")?"display: none":""}">Mark As Done</button><button onclick="markAccepted(${request.id})" class="btn btn-info" style="${!request.status.toString().equals("in_queue")?"display: none":""}">Accept</button>&nbsp;<button onclick="markDenied(${request.id})" class="btn btn-danger" style="${!request.status.toString().equals("in_queue")?"display: none":""}">Deny</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<script>
    function markDone(rrid) {
        $.post('galleria.admin.response(request)', {
            id: rrid,
            response: 'success'
        },function (data) {
            window.location.reload();
        }).fail(handleRequestFailure)
    }
    function markAccepted(rrid) {
        $.post('galleria.admin.response(request)', {
            id: rrid,
            response: 'accepted'
        },function (data) {
            window.location.reload();
        }).fail(handleRequestFailure)
    }
    function markDenied(rrid) {
        $.post('galleria.admin.response(request)', {
            id: rrid,
            response: 'declined'
        },function (data) {
            window.location.reload();
        }).fail(handleRequestFailure)
    }
</script>