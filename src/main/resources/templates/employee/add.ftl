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

<form action="/employee/save" method="POST">
	First Name:<input name="firstName" type="input" value="${employee.firstName!''}" />
	<br/>
	Last Name:<input name="lastName" type="input" value="${employee.lastName!''}"/>
	<br/>
	Job Description:<input name="jobTitle" type="input" value="${employee.jobTitle!''}"/>
	<br/>
	Gender:<input name="gender" type="input" value="${employee.gender!''}"/>
	<br/>
	Birthdate:<input name="birthDate" type="input" value="[#if employee.birthDate??]${employee.birthDate?string('dd/MM/yyyy')}[/#if]"/>
	<br/>
	Employment Date:<input name="employmentDate" type="input" value="[#if employee.employmentDate??]${employee.employmentDate?string('dd/MM/yyyy')}[/#if]"/>
	<br/>
	<input type="hidden" name="id" value="[#if employee.id??]${employee.id?c}[/#if]">
	<input type="submit" value="Save">
	<a href="/employee">Cancel</a>
</form>


[/#escape]