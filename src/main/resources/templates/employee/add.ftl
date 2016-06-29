[#ftl]
[#import "/spring.ftl" as spring /]
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Employees List</title>
    <link  href="[@spring.url '/css/bootstrap.min.css' /]" rel="stylesheet">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="[@spring.url '/js/bootstrap.min.js' /] "></script>
	
	
	<script>
	function goToEmployeeList() {
		window.location.href = '/employee';
	}
	</script>
</head>
[#escape x as x?html]


<div class="container">
		<a href="/"> <img src="[@spring.url '/images/logo.png' /]" width="100"/>


		<ol class="breadcrumb">
			<li><a href="/">Home</a></li>
			<li class="active">Employees</li>
		</ol>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">[#if employee.id??]Edit[#else]Add[/#if] Employee</h3>
			</div>





			<div class="panel-body">
			
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
				<input type="hidden" name="id" value="[#if employee.id??]${employee.id?c}[/#if]">
					<div class="form-group">
						<label for="firstName">First name</label> 
						<input type="text"
							class="form-control" id="firstName" name="firstName"
							placeHolder="First Name" value="${employee.firstName!''}"/>
					</div>
					<div class="form-group">
						<label for="lastName">Last name</label> 
						<input type="text"
							class="form-control" id="lastName" name="lastName"
							placeHolder="Last Name" value="${employee.lastName!''}"/>
					</div>


					<div class="form-group">
						<label for="gender">Gender</label> <label> <input
							type="radio" name="gender" id="gender" value="MALE" [#if employee.gender?? && employee.gender == 'MALE']checked[/#if]>&nbsp;Male&nbsp;</input>
							<input type="radio" name="gender" id="gender" [#if employee.gender?? && employee.gender == 'FEMALE']checked[/#if] value="FEMALE">&nbsp;Female&nbsp;</input>
							<input type="radio" name="gender" id="gender" [#if employee.gender?? && employee.gender == 'UNSPECIFIED']checked[/#if] value="UNSPECIFIED">&nbsp;Not&nbsp;Specified&nbsp;</input>
						</label>
					</div>

					<div class="form-group">
						<label for="birthDate">Birthdate</label>
						<div class="form-group">
							<input type="text" id="birthDate" name="birthDate"
								placeHolder="mm-dd-yyyy" value="[#if employee.birthDate??]${employee.birthDate?string('dd-MM-yyyy')}[/#if]" />
						</div>
					</div>

					<div class="form-group">
						<label for="employmentDate">Employment date</label>
						<div class="form-group">
							<input type="text" id="employmentDate" name="employmentDate"
								placeHolder="mm-dd-yyyy" value="[#if employee.employmentDate??]${employee.employmentDate?string('dd-MM-yyyy')}[/#if]" />
						</div>
					</div>


					<div class="form-group">
						<label for="jobTitle">Job Title</label> 
						<input type="text"
							class="form-control" id="jobTitle" name="jobTitle"
							placeHolder="Job title" value="${employee.jobTitle!''}" />
					</div>



					<div class="container-fluid">
						<div class="collapse navbar-collapse">
							<ul class="nav navbar-nav navbar-right">
								<li><button type="submit" class="btn btn-default"
										onclick="javascript:goToEmployeeList();return false">Cancel</button></li>
								<li>&nbsp;&nbsp;&nbsp;</li>
								<li><button type="submit" class="btn btn-default">Save</button></li>
							</ul>
						</div>
					</div>
					<br /> <input type="hidden" class="form-control" id="id" value="0" />
			</form>
			</div>
		</div>
	</div>

[/#escape]