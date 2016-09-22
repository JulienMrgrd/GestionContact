<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add contact</title>
</head>
<body>

	<form method=post action="NewContact">
		First Name : <input type="text" name="firstname"><br>
		Last Name : <input type="text" name="lastname"><br>
		emailC : <input type="text" name="email"><br>
	
		<br/>
		
		<input class="button" type="submit" value="submit">
		<input class="button" type="reset" value="reset">
	
	</form>
	
</body>
</html>