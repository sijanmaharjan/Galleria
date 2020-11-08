<%@ page import="bean.entity.option.Gender" %>
<%@ page import="java.time.ZoneId" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" beanName="user" scope="request" type="bean.view.DetailedUser"/>
<div id="profile-container" class="profile-view">
    <img id="profile-picture" src="images/profile.png" style="background-color: white"/>
    <div id="profile-content">
        <div id="profile">
            <i class="fas fa-pen" id="profile-editor-icon" onclick="$('#profile').hide();$('#editor').show();$('#profile-container').attr('class','editor-view');"></i>
            <h3><b><u:Formatted firstName="${user.firstName}" lastName="${user.lastName}"/></b></h3>
            <p style="margin-top: -10px">
                <b>@${user.username}</b><br/>
                <small>
                    <u:Rating stars="${user.overallRating}"/>
                </small>
            </p>
            <p>
                ${user.accountNumber} <br/>
                ${user.email} <br/>
                <u:Age dob="${user.birthDate}"/>, ${user.gender.toString()} <br/>
            </p>
            <p>
                <i class="fas fa-upload"></i> ${user.totalUploads}, <i class="fas fa-heart"></i> ${user.likesCollected} <br/>
                <i class="fas fa-wallet"></i> $<u:Rounded number="${user.earned}"/>
            </p>
            <button class="custom-btn" onclick="submitRedeemRequest()">REDEEM</button>
        </div>
        <div id="editor">
            <form class="editForm" method="post" action="galleria.update(profile)" onsubmit="
                const redirect = window.location.href;
                $('#profile-redirect').val(redirect);
            ">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-2">
                            <label>Name</label>
                        </div>
                        <div class="col-sm-5">
                            <input type="text" class="col-sm-12" name="firstName" placeholder="First" value="${user.firstName}" required/>
                        </div>
                        <div class="col-sm-5">
                            <input type="text"  class="col-sm-12" name="lastName" placeholder="Last" value="${user.lastName}" required/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label>Gender</label>
                        </div>
                        <div class="col-sm-10">
                            <select name="gender" class="selectpicker" required>
                                <option disabled>Select Gender</option>
                                <option value="Male" <%=user.getGender().equals(Gender.Male)? "selected":""%>>Male</option>
                                <option value="Female" <%=user.getGender().equals(Gender.Female)? "selected":""%>>Female</option>
                                <option value="Others" <%=user.getGender().equals(Gender.Others)? "selected":""%>>Others</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label>DOB</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="date" name="birthDate" class="form-control" value="<%=user.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString()%>" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label>Email</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="email" name="email" placeholder="your email" value="${user.email}" class="form-control" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label>Username</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="text" name="username" placeholder="username" value="${user.username}" class="form-control" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label>Old</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="password" name="oldPsw" placeholder="old password" class="form-control" >
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label>New</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="password" name="newPsw" placeholder="new password" class="form-control" >
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label>Confirm</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="password" name="confirm" placeholder="confirm new password" class="form-control" >
                        </div>
                    </div>
                </div>
                <button class="custom-btn" type="submit">UPDATE PROFILE</button>
                <input id="profile-redirect" type="text" name="redirect" value="galleria.oh" hidden style="height: 0px">
            </form>
            <form action="galleria.remove(user.account)" method="post">
                <input id="profile-psw" type="password" name="password" placeholder="enter password to delete" style="display: none;width: 100%;">
                <button id="profile-delete-btn" type="button" class="custom-btn flex-fill" style="background-color: darkred" onclick="$(this).hide(); $('#profile-psw').show();">Remove Account</button>
            </form>
        </div>
    </div>
</div>

<script>
    const profileContainer = $('#profile-container');
    function displayProfile(){
        displayMask();
        $(profileContainer).slideDown(120);
        event.stopPropagation();
    }
    function hideProfile(){
        hideMask();
        $(profileContainer).slideUp(100);
        $('#profile').show();$('#editor').hide();
        $('#profile-container').attr('class','profile-view');
        $('#profile-psw').hide(); $('#profile-delete-btn').show();
    }
    $(mask).click(function () {
        hideProfile()
    })
    $(profileContainer).click(function (event) {
        event.stopPropagation();
    })
    function submitRedeemRequest() {
        hideProfile();
        $.get('galleria.submit(redeem_request)', function (data) {
            if (data) {
                alert(data);
            } else {
                alert("Request submitted successfully.");
            }
        }).fail(handleRequestFailure);
    }
</script>