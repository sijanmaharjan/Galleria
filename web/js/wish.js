function addToWishlist(el, id, added){
    if(added){
        alert("Already In Wishlist!");
    }else{
        $.get('galleria.wish', {
            id:id
        }, function (data) {
            $(el).attr('onclick', "addToWishlist(this, '" + id + "', true)");
            alert("Added to Wishlist");
        }).fail(handleRequestFailure);
    }
    event.stopPropagation();
}