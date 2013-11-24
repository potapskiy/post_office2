<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:remove var="user" scope="session" />
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>
	<center>
	<h1>Your finances!</h1>
	
				<form name="loginForm" action="controller" method="post"
					accept-charset="UTF-8">
					<input type="hidden" name="command" value="login">
					<table class="box" frame="box">
						<caption class="error">
							<c:if test="${not empty Error}">Incorrect login or password</c:if>
						</caption>
						<c:remove var="Error" scope="request" />

						<tr>
							<td>Login:</td>
							<td><input type="text" name="login" /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="pass" /></td>
						</tr>
						<tr>
							<td align="center"><input type="submit" value="Log in" /></td>
						</tr>
					</table>
				</form>
				
				
				
				<a href="registration.jsp">Registration</a>
				
				

	</center>


</body>
</html>