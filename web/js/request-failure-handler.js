function handleRequestFailure(error) {
    switch (error.status) {
        case 402:
            alert("You are logged out of this session!");
            break;
        case 403:
            alert("Unfortunately You are blocked from accessing this site!");
            break;
        default:
            alert("something went wrong");
    }
    location.reload();
}