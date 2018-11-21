<#import "parts/common.ftl" as c>
<@c.page>

         <div>
             ${event.place}
             ${event.date}
             ${event.name}
             <div>
            <#if event.filename??>
                <img src="/img/${event.filename}"
            </#if>
             </div>
             <div>
                 Количество участников ${count}/${event.numberofguests}
             </div>
         </div>
<a class="btn btn-primary mt-5" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Хочу пойти!
</a>
<div class="collapse <#if guest??>show</#if>" id="collapseExample">
    <div>
        <form method="post" enctype="multipart/form-data">
            <div class="form-group mt-3">
            <input type="text"  class="form-control ${(guestnameError??)?string('is-invalid', '')}"
                   value="<#if guest??>${guest.guestname}</#if>"    name="guestname" placeholder="Введите ваше имя" />
                <#if guestnameError??>
                    <div class="invalid-feedback">
                        ${guestnameError}
                    </div>
                </#if>
        </div>
            <div class="form-group mt-3">
                <input type="email" name="email"  class="form-control ${(emailError??)?string('is-invalid', '')}"
                       value="<#if guest??>${guest.email}</#if>" placeholder="email@address.com" />
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="number" name="age"  class="form-control ${(ageError??)?string('is-invalid', '')}"
                       value="<#if guest??>${guest.age}</#if>" placeholder="Введите ваш возраст" />
                <#if ageError??>
                    <div class="invalid-feedback">
                        Поле не должно быть пустым
                    </div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button  class="btn btn-primary"type="submit">Зарегистрировать участие</button>
            </div>
        </form>
    </div>
</div>

</@c.page>