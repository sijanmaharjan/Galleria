<div id="upload-container">
    <form class="uploadForm" method="post" >
        <div class="container">
            <div class="row" id="form-img-container">
                <div class="col-sm-2">
                    <label>Image</label>
                </div>
                <div class="col-sm-10">
                    <button type="button" class="custom-btn float-left" onclick="$('#up-img').click()">Select</button><a id='up-fn' style="font-size: 8pt">No File Selected</a>
                    <input onchange="$('#up-fn').text(this.value)" type="file" accept="image/jpeg" id="up-img" name="imageFile" placeholder="Select Image" hidden/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <label>Category</label>
                </div>
                <div class="col-sm-10">
                    <input type="text" name="category" placeholder="Select Category or Type new" required
                           style="  position: absolute;
                                    margin-top: 3px;
                                    height: 23px;
                                    border-radius: 6px 0px 0px 6px;
                                    width: 90%;
                                    background-color: #efedeb;
                                    border-right: 1px solid #cdc7c2;
                                    margin-left: 1px; "
                    />
                    <select name="c-select" onchange="$(this).siblings('input[name=category]').val($(this).val())">
                        <option disabled selected></option>

                        <c:forEach var="category" items="${categories}">
                            <option value="${category.title}">${category.title}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <label>Title</label>
                </div>
                <div class="col-sm-10">
                    <input type="text" name="title" placeholder="Image Title" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <label>Description</label>
                </div>
                <div class="col-sm-10">
                    <textarea name="description" placeholder="little information about your image..." required maxlength="225"></textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <label>Price ($)</label>
                </div>
                <div class="col-sm-10">
                    <input type="number" onchange="checkBankDetail(this)" step="0.01" name="price" placeholder="Price" value="0" min="0.00" max="5.00" required/>
                </div>
            </div>
            <div id="upload-bank-detail" style="display: none">
                <div class="row">
                    <div class="col-sm-12">
                        <small>Bank Details</small>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <label>Bank</label>
                    </div>
                    <div class="col-sm-10">
                        <select name="bank" id="up-bank">
                            <option disabled selected>Select Bank Name</option>
                            <option value="NIC Asia Ltd">NIC Asia Ltd</option>
                            <option value="Prabhu Bank Ltd">Prabhu Bank Ltd</option>
                            <option value="Everest Bank Ltd">Everest Bank Ltd</option>
                            <option value="Nabil Bank Ltd">Nabil Bank Ltd</option>
                            <option value="NMB Bank Ltd">NMB Bank Ltd</option>
                            <option value="Siddhartha Bank Ltd">Siddhartha Bank Ltd</option>
                            <option value="Machhapuchhre Bank Ltd">Machhapuchhre Bank Ltd</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <label>Ac Name</label>
                    </div>
                    <div class="col-sm-10">
                        <input type="text" name="acName" class="form-control" placeholder="Account Name" id="up-acc">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <label>Ac Number</label>
                    </div>
                    <div class="col-sm-10">
                        <input type="text" name="acNumber" class="form-control" placeholder="Account Number" id="up-acn">
                    </div>
                </div>
            </div>
        </div>
        <button id="upload-btn" class="custom-btn" type="submit" style="width: 100%"></button>
        <div style="background-color: #ccc; padding: 10px; border-radius: 15px">
            <small>
                <i class="fas fa-info"></i>
                <pre>
                Bank details is used to transfer available earnings to your bank account when you choose to redeem.
                #Note:
                - The amount shall be transferred within 5 hrs after you redeem your earnings.
                - Tax amount plus 10% service charge will be deducted from redeemed amount
            </pre>
            </small>
        </div>
        <input type="text" name="id" hidden>
        <input type="text" name="redirect" value="" hidden>
    </form>
</div>
<script>
    const container = $('#upload-container');
    function checkBankDetail(el) {
        if($(el).val() > 0.0 && bankDetailRequired){
            $('#upload-bank-detail').show();
            $('#up-bank').attr('required', 'required');
            $('#up-acc').attr('required', 'required');
            $('#up-acn').attr('required', 'required');
        }else{
            $('#upload-bank-detail').hide();
            $('#up-bank').removeAttr('required');
            $('#up-acc').removeAttr('required');
            $('#up-acn').removeAttr('required');
        }
    }
    function displayUploadContainer() {
        $(container).find("#form-img-container").show();
        $(container).find("form.uploadForm").attr('action', 'galleria.new(image)');
        $(container).find("form.uploadForm").attr('enctype', "multipart/form-data");
        $(container).find("input[name=imageFile]").attr('required', 'required');
        $(container).find("button#upload-btn").text('Upload New');
        $(container).find("input[name=redirect]").val(window.location.href);
        $(container).slideDown(120);
        $(mask).fadeIn(120);
        event.stopPropagation();
    }

    function clearForm() {
        $(container).find('a#up-fn').text('No File Selected');
        $(container).find("form.uploadForm").removeAttr('enctype');
        $(container).find('input[name=imageFile]').val('');
        $(container).find('input[name=category]').val('');
        $(container).find('input[name=title]').val('');
        $(container).find('textarea[name=description]').text('');
        $(container).find('input[name=price]').val(0.0);
        $(container).find('input[name=id]').val('');
    }

    function hideUploadContainer() {
        $(container).slideUp(100);
        $(mask).fadeOut(100);
        clearForm();
    }
    function displayUpdateContainer(id, image, category, title, desc, price) {
        $(container).find("#form-img-container").hide();
        $(container).find("form.uploadForm").attr('action', 'galleria.update(image)');
        $(container).find("input[name=imageFile]").removeAttr('required');
        $(container).find("button#upload-btn").text('Update Details');
        $(container).find('a#up-fn').text(image);
        $(container).find('input[name=id]').val(id);
        $(container).find('input[name=category]').val(category);
        $(container).find('input[name=title]').val(title);
        $(container).find('textarea[name=description]').text(desc);
        $(container).find('input[name=price]').val(price);
        $(container).find("input[name=redirect]").val(window.location.href);
        checkBankDetail($(container).find('input[name=price]'))
        $(container).slideDown(120);
        $('.full-body-mask').fadeIn(120);
        event.stopPropagation();
    }
    function hideUpdateContainer() {
        $(container).slideUp(100);
        $(mask).fadeOut(100);
        clearForm();
    }
    $(mask).click(function () {
        hideUploadContainer();
        hideUpdateContainer();
        clearForm();
    });
    $(container).click(function (event) {
        event.stopPropagation();
    });
    function deleteImage(IID){
        if(confirm("Are you sure to delete?")){
            window.location='galleria.delete(image)?id='+IID+'&redirect='+encodeURIComponent(window.location.href);
        }
        event.stopPropagation();
    }
</script>