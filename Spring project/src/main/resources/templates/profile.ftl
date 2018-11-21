<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<h5>${username}</h5>
<form method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">New password:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Repeat:</label>
        <div class="col-sm-6">
            <input type="password" name="password2" class="form-control" placeholder="Repeat new password" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Изменить пароль</button>
    <h5>${message?ifExists}</h5>
</form>
</@c.page>