<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="images" beanName="images" type="java.util.List<bean.entity.Image>" scope="request"/>
<jsp:useBean id="zone2" beanName="zone2" type="java.time.ZoneId" scope="request"/>

<h3 class="mt-4">Your Uploads</h3>
<button class="custom-btn" onclick="displayUploadContainer()">Upload New</button>
<br/>
<div class="table-responsive">
    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
        <thead>
        <tr>
            <th>Thumb</th>
            <th>Category</th>
            <th>Title</th>
            <th>Available</th>
            <th>Amount</th>
            <th>Uploaded On</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${images}" var="image">
            <tr>
                <td><a class="cursor-pointer" onclick="previewImage(event, '${image.id}')"><img style="max-height: 100px; max-width: 100px" src="thumbnails/${image.source}" alt=""></a></td>
                <td>${image.category.title}</td>
                <td>${image.title}</td>
                <td><input onclick="toggleAvailability('${image.id}', ${!image.available})" type="checkbox" ${image.available?"checked":""}/></td>
                <td>$${image.price}</td>
                <td>${image.uploadTime.toInstant().atZone(zone2).toLocalDateTime().toString()}</td>
                <td>
                    <i class="cursor-pointer fas fa-pen" onclick="displayUpdateContainer('${image.id}', '${image.source}', '${image.category.title}', '${image.title}', '${image.description}', ${image.price})"></i>
                    <i class="cursor-pointer fas fa-trash" onclick="deleteImage('${image.id}');"></i>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    function toggleAvailability(IID, available){
        $.post('galleria.availability',{
            id: IID,
            available: available
        }, function(data){
        }).fail(handleRequestFailure);
    }
</script>