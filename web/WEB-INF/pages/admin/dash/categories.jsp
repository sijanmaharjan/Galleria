<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="categories" beanName="categories" type="java.util.List<bean.entity.Category>" scope="request"/>

<h3 class="mt-4">Image Categories</h3>
<button class="custom-btn" onclick="displayCreateContainer()">Create New</button><br/>
<div class="table-responsive">
    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
        <thead>
        <tr>
            <th>Title</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${categories}" var="cat">
            <tr>
                <td>${cat.title}</td>
                <td>
                    <i class="cursor-pointer fas fa-pen" onclick="displayUpdateContainer('${cat.id}', '${cat.title}')"></i>
                    <i class="cursor-pointer fas fa-trash" onclick="deleteCategory('${cat.id}');"></i>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<script>
    function displayCreateContainer() {
        const title = prompt("Create New Image Category");
        if(title){
            $.post('galleria.admin.new(category)',{
                title:title
            },function (data) {
                window.location.reload();
            }).fail(handleRequestFailure)
        }
    }
    function displayUpdateContainer(id) {
        const title = prompt("Give New Title to Update");
        if(title){
            $.post('galleria.admin.update(category)',{
                id:id,
                title:title
            },function (data) {
                window.location.reload();
            }).fail(handleRequestFailure)
        }
    }
    function deleteCategory(id) {
        if(confirm("Are you sure to delete image category?")){
            $.get('galleria.admin.delete(category)',{
                id:id
            },function (data) {
                window.location.reload();
            }).fail(handleRequestFailure)
        }
    }
</script>