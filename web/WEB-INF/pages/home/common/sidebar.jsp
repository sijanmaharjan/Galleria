<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="min-height: 70vh">
    <h5 class="my-4">${p==null || "a".equals(p)?"All Images": "b".equals(p)?"Your Uploads": "c".equals(p)?"Your Favourites":""}</h5>
    <a class="search-actions">
        <i class="clear-search fas fa-times-circle"
           style="opacity: <%=request.getAttribute("s") != null ? 1.0 : 0.0%>"
           onclick="
                   $('.search-field').val('');
                   $(this).css('opacity','0.0');
                   $('.search-field').focus();
                   window.location = '<u:URLQuery page="${p}" CID="${c}" filterBy="${f}" min="${min}" max="${max}"/>';
        "
        ></i>&nbsp;
        <i class="vertical-splitter"></i>&nbsp;&nbsp;
        <i onclick="window.location = '<u:URLQuery page="${p}" searchText="search_text" CID="${c}" filterBy="${f}" min="${min}" max="${max}"/>'.replace('search_text', $('#searchField').val());"
           class="search-icon fas fa-search"
        ></i></a>
    <form onsubmit="
        window.location = '<u:URLQuery page="${p}" searchText="search_text" CID="${c}" filterBy="${f}" min="${min}" max="${max}"/>'.replace('search_text', $('#searchField').val());
        return false;
    ">
    <input
            autofocus
            type="text"
            id="searchField"
            class="search-field"
            name="search"
            placeholder="Search Images"
            value="${s}"
            onkeyup="if(this.value !== ''){$('.clear-search').css('opacity', '1.0')}else{$('.clear-search').css('opacity','0.0')}"
    /><button type="submit" hidden>search</button></form><br/><br/>
    <p style="width: 100%">Categories:
        <c:if test="<%=request.getAttribute("c") != null%>">
            <a onclick="window.location = '<u:URLQuery page="${p}" searchText="${s}" filterBy="${f}" min="${min}" max="${max}"/>';" class="clear-btn btn btn-link">clear <i class="fas fa-times-circle"></i></a>
        </c:if>
    </p>
    <div class="list-group" style="font-size: 10pt">
        <c:forEach var="category" items="${categories}">
            <a href='<u:URLQuery page="${p}" searchText="${s}" CID="${category.id}" filterBy="${f}" min="${min}" max="${max}"/>' class="list-group-item <u:ifSelected id="${category.id}" selected="${c}">font-weight-bold</u:ifSelected>">${category.title}</a>
        </c:forEach>
    </div>
    <br/>
    <br/>
    <p>Filter By:
        <c:if test="<%=request.getAttribute("f") != null%>">
            <a onclick="window.location = '<u:URLQuery page="${p}" searchText="${s}" CID="${c}"/>';" class="clear-btn btn btn-link">clear <i class="fas fa-times-circle"></i></a>
        </c:if>
    </p>
    <div class="list-group" style="font-size: 10pt">
        <a href='<u:URLQuery page="${p}" searchText="${s}" CID="${c}" filterBy="mostly_liked"/>' class="list-group-item <u:ifSelected id="mostly_liked" selected="${f}">font-weight-bold</u:ifSelected>">Mostly Liked</a>
        <a href='<u:URLQuery page="${p}" searchText="${s}" CID="${c}" filterBy="highly_rated"/>' class="list-group-item <u:ifSelected id="highly_rated" selected="${f}">font-weight-bold</u:ifSelected>">Highly Rated</a>
        <a href='<u:URLQuery page="${p}" searchText="${s}" CID="${c}" filterBy="price_range" min="0.0" max="0.0"/>' class="list-group-item <c:if test="<%="price_range".equals(request.getAttribute("f")) && request.getAttribute("min").equals(0d) && request.getAttribute("max").equals(0d)%>">font-weight-bold</c:if>">Free Images</a>
        <a href="#" class="list-group-item <c:if test="<%="price_range".equals(request.getAttribute("f")) && (!request.getAttribute("min").equals(0d) || !request.getAttribute("max").equals(0d))%>">font-weight-bold</c:if>">
            <p>
                <label onclick="filterByPrice(event, '<u:URLQuery page="${p}" searchText="${s}" CID="${c}" filterBy="price_range" min="0.0" max="1.0"/>');" style="cursor: pointer" for="amount">Price range:</label>
                <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
            </p>

            <div id="slider-range"></div>
        </a>
    </div>
</div>
<script>
    $( function() {
        $( "#slider-range" ).slider({
            range: true,
            min: 0,
            max: 5,
            values: [ <%=request.getAttribute("min") == null? 0: request.getAttribute("min")%>, <%=request.getAttribute("max") == null? 5: request.getAttribute("max")%> ],
            slide: function( event, ui ) {
                $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
            }
        });
        $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
            " - $" + $( "#slider-range" ).slider( "values", 1 ) );
        $( "#slider-range" ).mouseup(function (event) {
            let param = window.location.search;
            param = param.replace("?", "");
            let params = param.split("&");
            let finalParams = "?";
            for(let i=0; i< params.length; i++){
                if(!params[i].startsWith("min=") && !params[i].startsWith("max=") && !params[i].startsWith("f=") ){
                    finalParams += params[i]+"&";
                }
            }
            finalParams += "f=price_range";
            finalParams += "&min="+($( "#slider-range" ).slider( "values", 0 ));
            finalParams += "&max="+($( "#slider-range" ).slider( "values", 1 ));
            filterByPrice(event, "galleria.oh"+finalParams);
        })
    } );
    function filterByPrice(event, url) {
        window.location = url
                            .replace('0.0', $( '#slider-range' ).slider( 'values', 0 ))
                            .replace('1.0', $( '#slider-range' ).slider( 'values', 1 ));
        event.stopPropagation();
    }
</script>