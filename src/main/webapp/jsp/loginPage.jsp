<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Spring3Example</title>
<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
</head>

<style>
.error {
	color: #EF1313;
	font-style: italic;
}
</style>
<script type="text/javascript">
	function checkUserExists(email) {
		alert(email.value);
		$
				.ajax({
					type : "GET",
					url : "http://localhost:8080/test-service/userexists?email="+email.value+"",
					dataType : "json",
					Accept : "application/json",
					contentType : "application/json",
					success : function(resp) {
						if (resp) {
							$('#div1').text("User already exists");
							$('#subDiv').css('display','none');
						} else {
							$('#subDiv').css('display','inline');
							$('#div1').text("");
						}
					},
					error : function(e) {
						alert('Error121212: ' + e);
					}
				});
	}

	$("submit").click(function() {
		alert('Button click!')
		return false;
	});
</script>
</head>
<body>

	<form:form action="login" commandName="userDetails">
		<table>
			<tr>
				<td><font face="verdana" size="2px">User</font></td>
				<td>:</td>
				<td><font face="verdana" size="2"> <form:input
							path="user" /> <form:errors path="user" class="error"></form:errors>
				</font></td>
			</tr>
			<tr>
				<td><font face="verdana" size="2px">Email</font></td>
				<td>:</td>
				<td><font face="verdana" size="2"> <form:input
							path="email" onchange="checkUserExists(this)" /> <form:errors
							path="email" class="error"></form:errors>
						<div id="div1"></div>
				</font></td>
			</tr>
			<tr>
				<td><font face="verdana" size="2px">Phone</font></td>
				<td>:</td>
				<td><font face="verdana" size="2"> <form:input
							path="phone" /> <form:errors path="phone" class="error"></form:errors>
				</font></td>
			</tr>
			<tr>
				<td><font face="verdana" size="2px">Blog</font></td>
				<td>:</td>
				<td><font face="verdana" size="2"> <form:input
							path="blog" /> <form:errors path="blog" class="error"></form:errors>
				</font></td>
			</tr>
			<tr>
				<td><div id="subDiv"><input type="submit" id="submit" name="submit"
					value="Submit" /></div></td>
			</tr>
		</table>
	</form:form>
</body>
</html>