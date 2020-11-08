<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="detail" beanName="detail" scope="request" type="bean.entity.BankDetail"/>
<h3 class="mt-4">Your Bank Details</h3>
<div class="table-responsive">
    <table class="table" id="dataTable" width="100%" cellspacing="0">
        <tbody>
        <tr>
            <td>Bank Name</td>
            <td><a id="v1">${detail.bankName}</a>
                <select id="e1" style="display: none">
                    <option value="" disabled <%=(detail.getBankName()==null||"".equals(detail.getBankName())?"selected":"")%>>Select Bank Name</option>
                    <option value="NIC Asia Ltd" <%=("NIC Asia Ltd".equals(detail.getBankName())?"selected":"")%>>NIC Asia Ltd</option>
                    <option value="Prabhu Bank Ltd" <%=("Prabhu Bank Ltd".equals(detail.getBankName())?"selected":"")%>>Prabhu Bank Ltd</option>
                    <option value="Everest Bank Ltd" <%=("Everest Bank Ltd".equals(detail.getBankName())?"selected":"")%>>Everest Bank Ltd</option>
                    <option value="Nabil Bank Ltd" <%=("Nabil Bank Ltd".equals(detail.getBankName())?"selected":"")%>>Nabil Bank Ltd</option>
                    <option value="NMB Bank Ltd" <%=("NMB Bank Ltd".equals(detail.getBankName())?"selected":"")%>>NMB Bank Ltd</option>
                    <option value="Siddhartha Bank Ltd" <%=("Siddhartha Bank Ltd".equals(detail.getBankName())?"selected":"")%>>Siddhartha Bank Ltd</option>
                    <option value="Machhapuchhre Bank Ltd" <%=("NIC Machhapuchhre Ltd".equals(detail.getBankName())?"selected":"")%>>Machhapuchhre Bank Ltd</option>
                </select></td>
            <td>
                <i id='edit1' class="edit-icon fas fa-pen" onclick="edit(this)" style="cursor: pointer"></i>
                <i id="c1" class="fas fa-times-circle" onclick="cancel(this)" style="display: none; color:red;cursor: pointer"></i>
                <button id='btn1' class="btn btn-sm btn-primary" onclick="update(this)" style="display: none">Update</button>
            </td>
        </tr>
        <tr>
            <td>Account Name</td>
            <td><a id="v2">${detail.accountName}</a><input id="e2" type="text" value="${detail.accountName}" style="display: none"/></td>
            <td>
                <i id='edit2' class="edit-icon fas fa-pen" onclick="edit(this)" style="cursor: pointer"></i>
                <i id="c2" class="fas fa-times-circle" onclick="cancel(this)" style="display: none; color:red;cursor: pointer"></i>
                <button id='btn2' class="btn btn-sm btn-primary" onclick="update(this)" style="display: none">Update</button>
            </td>
        </tr>
        <tr>
            <td>Account Number</td>
            <td><a id="v3">${detail.accountNumber}</a><input id="e3" type="text" value="${detail.accountNumber}" style="display: none"/></td>
            <td>
                <i id='edit3' class="edit-icon fas fa-pen" onclick="edit(this)" style="cursor: pointer"></i>
                <i id="c3" class="fas fa-times-circle" onclick="cancel(this)" style="display: none; color:red;cursor: pointer"></i>
                <button id='btn3' class="btn btn-sm btn-primary" onclick="update(this)" style="display: none">Update</button>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<script>
    function edit(el) {
        const num = $(el).attr('id').replace('edit','');
        const v = '#v'+num;
        const e = '#e'+num;
        const btn = '#btn'+num;
        const c = '#c'+num;
        $(el).hide();
        $(v).hide();
        $(e).show();
        $(btn).show();
        $(c).show();
    }
    function cancel(el) {
        const num = $(el).attr('id').replace('c','');
        const v = '#v'+num;
        const e = '#e'+num;
        const btn = '#btn'+num;
        const edit = '#edit'+num;
        $(e).val($(v).text());
        $(el).hide();
        $(e).hide();
        $(btn).hide();
        $(v).show();
        $(edit).show();
    }
    function update(el){
        //make post request
        $.post('galleria.update(user.bank.detail)', {
            bankName: $('#e1').val(),
            accountName: $('#e2').val(),
            accountNumber: $('#e3').val(),
        },function (data) {
            const num = $(el).attr('id').replace('btn', '');
            const v = '#v' + num;
            const e = '#e' + num;
            const c = '#c' + num;
            const edit = '#edit' + num;
            $(v).text($(e).val());
            $(el).hide();
            $(e).hide();
            $(c).hide();
            $(v).show();
            $(edit).show();
        }).fail(handleRequestFailure);
    }
</script>