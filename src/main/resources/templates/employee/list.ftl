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
	
</head>
[#escape x as x?html]

<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Panel heading</div>
  <div class="panel-body">
    <a href="/employee/add" style="float:right;margin-right:50px">
    	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
    </a>
  </div>

  Current user: [#if currentUser??]${currentUser.userName!''}[/#if]
  <table class="table">
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Birthday</th>
		<th>Job Title</th>
		<th></th>
		
	</tr>
	<!-- begin iteration -->
	[#if employees??]
		[#list employees as employee] 
			<tr>
				<td>${employee.firstName}</td>
				<td>${employee.lastName}</td>
				<td>${employee.birthDate?string('dd.MM.yyyy')}</td>
				<td>${employee.jobTitle}</td>
				<td><a href="/employee/edit?id=${employee.id?c}">EDIT</a></td>
			</tr>
		[/#list]
	[/#if]
	
	<!-- end iteration -->

</table>
</div>



[/#escape]
