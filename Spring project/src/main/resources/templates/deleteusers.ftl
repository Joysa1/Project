<#import "parts/common.ftl" as c>
<@c.page>
<div>Список пользователей</div>
<#list users as user>
    <div>
        ${user.username}
        <a href="/admin/users/${user.id}">delete</a>
    </div>
</#list>
</@c.page>