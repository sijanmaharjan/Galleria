<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="signupForm">
    <form class="signupForm" method="post" action="galleria.signup">
        <div class="container">
            <div class="row">
                <div class="col-sm-2">
                    <label>Name</label>
                </div>
                <div class="col-sm-5">
                    <input type="text" name="firstName" placeholder="First" required/>
                </div>
                <div class="col-sm-5">
                    <input type="text" name="lastName" placeholder="Last" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <label>Gender</label>
                </div>
                <div class="col-sm-10">
                    <select name="gender" id="gender" required>
                        <option disabled selected>Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Others">Others</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <label>DOB</label>
                </div>
                <div class="col-sm-10">
                    <input type="date" name="birthDate" class="form-control" required>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <label>Email</label>
                </div>
                <div class="col-sm-10">
                    <input type="email" name="email" placeholder="your email" class="form-control" required>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <label>Username</label>
                </div>
                <div class="col-sm-10">
                    <input type="text" name="username" placeholder="username" class="form-control" required>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <label>Password</label>
                </div>
                <div class="col-sm-10">
                    <input type="password" name="password" placeholder="password" class="form-control" required>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <label>Confirm</label>
                </div>
                <div class="col-sm-10">
                    <input type="password" name="confirm" placeholder="confirm password" class="form-control" required>
                </div>
            </div>
        </div>
        <button id="sign-up-btn" type="submit">Signup</button>
        <a class="btn btn-link" onclick="showLoginFormOnLoginContainer(this)">already have account? Log In</a>
    </form>
</div>