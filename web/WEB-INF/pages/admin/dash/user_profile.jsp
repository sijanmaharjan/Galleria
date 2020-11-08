<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="profile-container" class="profile-view">
    <img id="profile-picture" src="images/profile.png" style="background-color: white"/>
    <div id="profile-content">
        <div id="profile">
            <h3><b id="name"></b></h3>
            <p style="margin-top: -10px">
                <b id="username"></b><br/>
                <small id="ratings"></small>
            </p>
            <p>
                <a id="account"></a> <br/>
                <a id="email"></a> <br/>
                <a id="dob"></a>, <a id="gender"></a> <br/>
            </p>
            <p>
                <i class="fas fa-upload"></i> <a id="uploads"></a>, <i class="fas fa-heart"></i> <a id="likesCollected"></a> <br/>
                <i class="fas fa-wallet"></i> $<a id="earnings"></a>
            </p>
            <button class="custom-btn" id="block"></button>
        </div>
    </div>
</div>

<script>
    const profileContainer = $('#profile-container');
    function displayProfile(user, blocked, show){
        $(profileContainer).find('#name').text(user.name);
        $(profileContainer).find('#username').text(user.username);
        $(profileContainer).find('#ratings').html(user.ratings);
        $(profileContainer).find('#account').text(user.account);
        $(profileContainer).find('#email').text(user.email);
        $(profileContainer).find('#dob').text(user.dob);
        $(profileContainer).find('#gender').text(user.gender);
        $(profileContainer).find('#uploads').text(user.uploads);
        $(profileContainer).find('#likesCollected').text(user.likesCollected);
        $(profileContainer).find('#earnings').text(user.earnings);
        if(show) {
            $(profileContainer).find('#block').text(blocked ? "UNBLOCK" : "BLOCK USER");
            $(profileContainer).find('#block').attr('onclick', "blockUser('" + user.id + "', " + (!blocked) + ", function(data){alert('User " + (blocked ? "Unblocked" : "Blocked") + "!');window.location.reload();})");
        }else{
            $(profileContainer).find('#block').hide();
        }
        displayMask();
        $(profileContainer).slideDown(120);
        event.stopPropagation();
    }
    function hideProfile(){
        hideMask()
        $(profileContainer).slideUp(100);
    }
    $(mask).click(function () {
        hideProfile();
    })
    $(profileContainer).click(function (event) {
        event.stopPropagation();
    })
    function blockUser(id, block, callback) {
        $.post('galleria.admin.block(user)',{
            _id: id,
            block: block
        }, function (data) {
            callback(data);
        }).fail(handleRequestFailure)
    }
</script>