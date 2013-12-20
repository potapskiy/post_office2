<%@page import="office.entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Нова пошта++</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/login.js" type="text/javascript"></script>
<script src="js/register.js" type="text/javascript"></script>
</head>
<body>

	<div class="header_area">
		<div class="main_area">
			<p class="collus_text">Служба цілодобової підтримки: 800-567-8888</p>
		</div>
		<div class="main_area">
			<div class="logo_wrap">
				<p class="logo_pad">
					<a href="./index"><img src="images/logo_all.jpg" alt=""
						border="0" /></a>
				</p>
			</div>
			<div class="navarea_wrap">
				<div class="nav_wrap">

					<ul>
						<li><a href="./index" class="home"> Головна</a></li>
						<li><a href="./about" class="homeactive"> Про нас</a></li>
						<li><a href="./services" class="home">Послуги</a></li>
						<li><a href="./contacts" class="contact">Контакти</a></li>
					</ul>



					<br class="blank" />
				</div>
			</div>
			<br class="blank" />
		</div>

	</div>

	<div class="main_area">
		<div class="body_wrap">
			<div class="center_body_profile_bg">
				<div class="left_body_wrap">
					<div class="left_body_main">
						<div class="main_wrap">
							<form name="registerForm" action="controller" method="post">
								<input type="hidden" name="command" value="register" />
								<p style="padding: 25px 0 0 120px;" class="user_text">Мій
									профіль</p>
								<%
									User user = (User) session.getAttribute("user");
								%>
								<p style="padding: 40px 0 0 70px;" class="user_name_text">
									телефон <input type="text" id="loginField" name="loginField"
										maxlength="50" style="padding: 4px 0 0 20px;"
										class="contact_filed" value="+380<%=user.getTelephone()%>"
										disabled />
								</p>

								<p style="padding: 2px 0 0 98px;" class="user_name_text">
									ім'я <input type="text" id="firstNameField"
										name="firstNameField" maxlength="50"
										style="padding: 15px 0 0 30px;" class="contact_filed"
										value="<%=user.getFirstName()%>" disabled />
								</p>

								<p style="padding: 6px 0 0 68px;" class="user_name_text">
									прізвище <input type="text" id="lastNameField"
										name="lastNameField" maxlength="50"
										style="padding: 15px 0 0 30px;" class="contact_filed"
										value="<%=user.getSecondName()%>" disabled />
								</p>

								<p style="padding: 4px 0 0 78px;" class="user_name_text">
									адреса <input type="text" id="addressField" name="addressField"
										maxlength="50" style="padding: 15px 0 0 30px;"
										class="contact_filed" value="<%=user.getAddress()%>" disabled />
								</p>
								<c:if test="${not empty Success}">
									<p style="padding: 15px 0 0 100px;" class="user_name_success_text">Профіль успішно змінено</p>
								</c:if>

								<p style="padding: 10px 0 0 150px;">
									<a href="./edit_profile" onclick="javascript:validateForm();"
										class="log">Редагувати</a>
								</p>
							</form>
						</div>
						</span><br /> <br />
					</div>
				</div>
			</div>
		</div>
		<br class="blank" />
	</div>

	<div class="footer_wrap">
		<div class="footer_area">
			<div class="footer_nav_area">
				<p class="footer_nav_text">
					<a href="./index" class="footer">Головна</a> | <a href="./about"
						class="footeractive">Про нас</a> | <a href="./services"
						class="footer">Послуги</a> | <a href="./contacts" class="footer">Контакти</a>
				</p>
			</div>
			<div class="copy_wrap">
				Copyright &copy; <a href="#">Нова Пошта++</a>, All rights reserved
			</div>
			<br class="blank" />
		</div>
	</div>
</body>
</html>
