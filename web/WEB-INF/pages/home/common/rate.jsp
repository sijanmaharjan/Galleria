<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="rating-container">
    <i id="star1" onclick="pointSelected(1)" class="far fa-star fa-2x"></i>
    <i id="star2" onclick="pointSelected(2)" class="far fa-star fa-2x"></i>
    <i id="star3" onclick="pointSelected(3)" class="far fa-star fa-2x"></i>
    <i id="star4" onclick="pointSelected(4)" class="far fa-star fa-2x"></i>
    <i id="star5" onclick="pointSelected(5)" class="far fa-star fa-2x"></i>
    &nbsp;
    &nbsp;
    &nbsp;
    <input type="number" hidden id="rate-point" value="0">
    <button id="rate-submit" class="btn btn-info">RATE (0 stars)</button>
    <button class="btn btn-danger" onclick="cancelRating()"><i class="fas fa-times"></i></button>
</div>

<script>
    function pointSelected(point) {
        $('#rate-point').val(point);
        $('#rate-submit').text('RATE ('+point+' stars)');
        for(let i=point; i>=1; i--){
            const id = '#star'+i;
            if($(id).hasClass('far')) {
                $(id).removeClass('far');
                $(id).addClass('fas');
            }
        }
        for(let i=point+1; i<=5; i++){
            const id = '#star'+i;
            if($(id).hasClass('fas')) {
                $(id).removeClass('fas');
                $(id).addClass('far');
            }
        }
    }
    function rateImage(id) {
        const params = window.location.search;
        $('#rate-submit').attr('onclick', "window.location = 'galleria.rate?id="+id+"&point='+$('#rate-point').val()+'&redirect="+encodeURIComponent('galleria.oh'+params)+"'")
        $('#rating-container').fadeIn(100);
        event.stopPropagation();
    }
    function unrateImage(id) {
        const params = window.location.search;
        window.location = 'galleria.rate?id='+id+'&redirect='+encodeURIComponent('galleria.oh'+params);
        event.stopPropagation();
    }
    function cancelRating(){
        $('#rate-submit').removeAttr('onclick');
        $('#rating-container').fadeOut(100);
        pointSelected(0);
    }
</script>