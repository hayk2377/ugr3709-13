<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registration Form</title>
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<h2>Registration Form</h2>
		<form action="register" method = 'post'>
			Name: <input type="text" name = "name" required><br>
			Email: <input type="text" name = "email" required><br>
			Password: <input type="password" name ="password" required><br>
		
			<input type="submit" value="Register">
		</form>
	</body>
</html>