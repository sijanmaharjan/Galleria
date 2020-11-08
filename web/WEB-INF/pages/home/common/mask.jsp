<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="full-body-mask"></div>
<script>
    const mask = $('.full-body-mask');
    function displayMask() {
        $(mask).fadeIn(120);
    }
    function hideMask() {
        $(mask).fadeOut(120);
    }
</script>