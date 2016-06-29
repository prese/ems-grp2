[#ftl]
[#import "/spring.ftl" as spring /]
<!DOCTYPE html>
<html>
<head lang="en">

<title>SCI - EMS</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link
	href="[@spring.url '/css/bootstrap.min.css' /]" rel="stylesheet" media="screen" />


</head>
<body>

	<div class="container">
		<a href="/"> <img src="[@spring.url '/images/logo.png' /]" width="100"/>
		</a>

		<ol class="breadcrumb">
			<li class="active">Home</li>
			<li><a href="/employee">Employees</a></li>
			<li><a href="/login">Login</a></li>
		</ol>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Building Professional Web Apps with
					Spring Boot, jQuery, bootstrap</h3>
			</div>

			<div class="panel-body">


				<h2>Spring Boot</h2>
				<hr />
				<h3>Step 1 - What is Spring Boot?</h3>
				Spring Boot makes it easy to create stand-alone, production-grade
				Spring based Applications that you can "just run". <a
					href="http://projects.spring.io/spring-boot/">Read more</a>
				<h3>Step 2 - details about Spring Boot</h3>
				<ul>
					<li>Spring Boot documentation - <a
						href="http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-documentation">Read
							More</a>
					</li>
					<li>Spring Boot tutorial - <a
						href="https://springframework.guru/spring-boot-web-application-part-2-using-thymeleaf/">Read
							More</a></li>
				</ul>

				<h3>Step 3 - create Spring Boot Project</h3>
				<ol>
					<li>Go to <a href="http://start.spring.io/">http://start.spring.io/
					</a></li>
					<li>Create your mvn project and download it</li>
					<li>Follow the code structure described <a
						href="http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#using-boot-structuring-your-code">here</a>
					</li>
					<li>Run your first Spring Boot app with maven: <code>mvn
							clean package spring-boot:run</code></li>
					<li>Access it from browser: <a href="http://localhost:8080">http://localhost:8080</a></li>
				</ol>

				<hr />
				<h2>Domain, Services, DAOs</h2>
				<hr />
				<h3>Step 1 - Define your domain model</h3>
				Based on the user stories, identify your domain model. How to do it?
				Read in: Thinking in Java: 16: Analysis and Design
				<h3>Step 2 - define your service</h3>
				<ul>
					<li>Group together the functionalities and create for each
						group a service.</li>
					<li>For each service implement intensive unit testing.</li>
					<li>Isolate the persistence related functionalities in
						interfaces called DAOs. Check more about DAOs, <a
						href="http://www.oracle.com/technetwork/java/dataaccessobject-138824.html">here</a>
					</li>
					<li>Wrap the Services as REST Controller, <a
						href="https://spring.io/guides/gs/actuator-service/">check
							here</a></li>
				</ul>
				<hr />
				<h2>Automatic Deployment with Heroku</h2>
				<hr />
				<h3>What is Heroku</h3>
				Heroku makes it easy to deploy and scale Java apps in the cloud. <a
					hred="https://devcenter.heroku.com/categories/java">Read more</a>


				<h3>Deploy your Spring Boot App to Heroku</h3>
				<ol>
					<li>Create an account to Heroku: <a
						href="http://www.heroku.com">www.heroku.com</a></li>
					<li>Commit your Spring Boot application to github</li>
					<li>Install heroku CLI, see <a href="https://devcenter.heroku.com/articles/heroku-command">here</a>
					<li>Create a java application for Heroku and link it to the
						Heroku application, see <a
						href="https://devcenter.heroku.com/articles/github-integration">here</a>
						
						Do not forget to enable the application from herodu - RESOURCES menu.
					</li>
					<li>Configure the automatic deployment in Heroku, see <a
						href="https://devcenter.heroku.com/articles/github-integration#automatic-deploys">here</a></li>
					<li>Create an commit to github the Procfile, having the
						following content <code>web: java -Dserver.port=$PORT -jar
							target/&lt;your project name&gt;-0.0.1-SNAPSHOT.jar</code>
					</li>
					<li>
						Now you should be able to access your web application on heroku, like: <a href="https://sci-ems.herokuapp.com/">https://&lt;your-app-name&gt;.herokuapp.com/</a>
					</li>
					<li>
						Configure Postgresql on heroku: <a href="https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java">Read Here</a>
					</li>
					<li>
						Get logs from heroku: <a href="https://devcenter.heroku.com/articles/logging">Read here</a>
					</li>
				</ol>

				<hr />
				<h2>FTL - freemarker templates</h2>
				FTL is template engine which extends the HTML in order to support operations like:
				<ul>
				<li>variable binding</li>
				<li>flow control</li>
				<li>loops</li>
				<li>etc.</li>
				</ul>	
				Just have a look here: <a href="http://freemarker.org/">http://freemarker.org/</a>
				<hr />


				<h2>Bootstrap</h2>
				Bootstrap is one of the most used CSS for implemeting responsive web apps.
				
				Get it from here: <a href="http://getbootstrap.com/">http://getbootstrap.com/</a>
				<hr />

				<h2>Sample app code</h2>
				Sample app is a simple implementation for an Employee Management
				System(one of your homeworks). Get it from here:
				<code>git clone https://github.com/prese/ems-grp2.git</code>
				<hr />

			</div>
		</div>
	</div>

</body>
</html>