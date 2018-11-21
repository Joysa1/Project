<#import "parts/common.ftl" as c>
<@c.page>
<div>Список мероприятий</div>
    <#list events as event>
    <div>
        ${event.name}
        ${event.place}
        ${event.date}
        <a href="/admin/events/${event.name}">delete</a>

    </div>
    </#list>
</@c.page>