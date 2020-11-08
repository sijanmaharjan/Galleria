<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="users" beanName="users" type="java.util.List<bean.entity.User>" scope="request"/>
<jsp:useBean id="zone" beanName="zone" type="java.time.ZoneId" scope="request"/>

<h3 class="mt-4">Users List</h3>
<div class="table-responsive">
    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
        <thead>
        <tr>
            <th>Join Date</th>
            <th>Full Name</th>
            <th>Wallet</th>
            <th>Blocked</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.joinDate.toInstant().atZone(zone).toLocalDateTime().toString()}</td>
                <td><a id="display" style="cursor: pointer" onclick="displayProfile(<u:MapUser UID="${user.id}"/>, ${user.blocked}, true)"><u:Formatted firstName="${user.firstName}" lastName="${user.lastName}"/></a></td>
                <td>$<u:Rounded number="${user.earned}"/></td>
                <td><input name="blocked" type="checkbox" ${user.blocked?"checked":""} disabled/></td>
                <td><button class="custom-btn" onclick="blockAUser(this, <u:MapUser UID="${user.id}"/>, ${!user.blocked});">${!user.blocked?"Block": "Unblock"}</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<script>
    $('.navbar').click(function () {
        hideMask();
        hideProfile();
    })
    function blockAUser(el, user, block) {
        blockUser(user.id, block, function(data){
            const item = $(el).parent('td').parent('tr').find('input[name=blocked]');
            const display = $(el).parent('td').parent('tr').find('a#display');
            if(block){
                $(display).attr('onclick', "displayProfile("+getUser(user).replaceAll("class='", "class=\\'").replaceAll("'>", "\\'>")+", "+block+")");
                $(el).text('Unblock');
                $(el).attr('onclick', "blockAUser(this, "+getUser(user).replaceAll("class='", "class=\\'").replaceAll("'>", "\\'>")+", "+(!block)+");");
                $(item).attr('checked', 'checked');
            }else {
                $(display).attr('onclick', "displayProfile("+getUser(user).replaceAll("class='", "class=\\'").replaceAll("'>", "\\'>")+", "+(block)+")");
                $(el).text('Block');
                $(el).attr('onclick', "blockAUser(this, "+getUser(user).replaceAll("class='", "class=\\'").replaceAll("'>", "\\'>")+", "+(!block)+");");
                $(item).removeAttr('checked');
            }
        })
    }

    function getUser(user){
        return "{" +
            "id:'" +user.id+"'," +
            "name:'" +user.name+"'," +
            "username:'@" +user.username+"'," +
            "ratings:'" +user.ratings+"'," +
            "account:'" +user.account+"'," +
            "email:'" +user.email+"'," +
            "dob:'" +user.dob+"'," +
            "gender:'" +user.gender+"'," +
            "uploads:'" +user.uploads+"'," +
            "likesCollected:'" +user.LikesCollected+"'," +
            "earnings:'" +user.earnings+"'" +
            "}";
    }
</script>