function markFavourite(event, el, id, remark) {
    $.get("galleria.favourite", {
        id: id,
        remark: remark
    }, function (data) {
        if (remark === 'like') {
            $(el).removeClass("far");
            $(el).addClass("fas");
            $(el).attr("onclick", "markFavourite(event, this, '" + id + "', 'dislike');")
        } else {
            $(el).removeClass("fas");
            $(el).addClass("far");
            $(el).attr("onclick", "markFavourite(event, this, '" + id + "', 'like');")
        }
    }).fail(handleRequestFailure);
    event.stopPropagation();
}

function downloadImage(id) {
    window.location = 'galleria.download(image)?id='+id;
    event.stopPropagation();
}