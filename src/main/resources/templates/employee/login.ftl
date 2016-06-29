[#ftl]

[#assign head]

[/#assign]
[#escape x as x?html]


[#if errors??]
    <div>
        <ul>
            [#list errors as error]
            <br>
                <b style="color:red">
                [#if error.field??]${error.field}: [/#if]${error.defaultMessage}
                </b>
            [/#list]
        </ul>
    </div>
[/#if]

<form action="/login/save" method="POST">
	user:<input name="userName" type="input" />
	<br/>
	pass:<input name="password" type="password" />
	<br/>
	<input type="submit" value="Save">
	
</form>


[/#escape]