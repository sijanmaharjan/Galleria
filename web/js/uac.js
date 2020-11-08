let lForm;
const loginContainer = $('#loginForm');
const signupContainer = $('#signupForm');

function toggleLoginForm() {
    $(signupContainer).hide();
    if($(loginContainer).is(':hidden')){
        $(loginContainer).show();
    }else{
        $(loginContainer).hide();
    }
}

function showSignupFormOnLoginContainer() { //for ease of user
    //stores login form in memory
    lForm = $(loginContainer).children('form');
    $(signupContainer).hide();  //hides signup form container
    $(loginContainer).html($(signupContainer).children('form')); //displays signup form on login form container
}

function showLoginFormOnLoginContainer(caller) {
    //checks if login form container is displaying signup form
    const loginForm = $(caller).parent('form').parent("div[id='loginForm']");
    if(loginForm.length) {  //if so
        console.assert(!$(loginContainer).is(':hidden'));   //verifies login form container is visible already

        //resets signup form back in signup form container
        $(signupContainer).html($(loginContainer).children('form'));
        //gets back login form into login form container
        $(loginContainer).html($(lForm));
    }else{
        $(signupContainer).hide();  //hides signup form container
        $(loginContainer).show();   //shows login form container
    }
}
function toggleSignupForm() {
    //hides login form container
    $(loginContainer).hide();
    //checks if signup form container is hidden
    if($(signupContainer).is(':hidden')){
        //checks if signup form is displayed on login form container
        if(!($(signupContainer).children('form').length)){
            //gets back signup form into signup form container
            $(signupContainer).html($(loginContainer).children('form'));
            //resets login form back in login form container
            $(loginContainer).html($(lForm));
        }
        $(signupContainer).show();  //shows signup form container
    }else{
        $(signupContainer).hide();  //hides signup form container
    }
    event.stopPropagation()
}

document.addEventListener("click", function () {
    $(signupContainer).hide();
    $(loginContainer).hide();
})
document.getElementById('loginBtn').addEventListener("click", function (event) {
    event.stopPropagation()
})
document.getElementById('loginForm').addEventListener("click", function (event) {
    event.stopPropagation()
})
document.getElementById('signupForm').addEventListener("click", function (event) {
    event.stopPropagation()
})
document.getElementById('signupBtn').addEventListener("click", function (event) {
    event.stopPropagation()
})