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
			<div class="center_body_bg">
				<div class="left_body_wrap">
					<div class="left_body_main">
						<div class="main_wrap">
							<form name="registerForm" action="controller" method="post">
								<input type="hidden" name="command" value="register" />
								<p style="padding: 25px 0 0 120px;" class="user_text">Реєстрація</p>

								<p style="padding: 40px 0 0 50px;" class="user_name_text">
									телефон <span style="color: #f60219;"> *</span> +380 <input
										type="text" id="loginField" name="loginField" maxlength="50"
										style="padding: 4px 0 0 20px;" class="contact_filed"
										value="введіть телефон"
										onfocus="javascript:clearField(this,'введіть телефон')"
										onblur="javascript:fillField(this,'введіть телефон')" />
								</p>
								<c:choose>
									<c:when test="${not empty Error}">
										<p style="padding: 5px 0 0 120px;">
											<a id="telephoneError" class="user_name_error_text">Користувач
												із таким телефоном уже існує</a>
										</p>
									</c:when>
									<c:otherwise>
										<p style="padding: 5px 0 0 120px;">
											<a id="telephoneError" class="user_name_error_text"></a>
										</p>
									</c:otherwise>
								</c:choose>

								<p style="padding: 2px 0 0 80px;" class="user_name_text">
									пароль<span style="color: #f60219;"> *</span> <input
										type="password" id="passField" name="passField" maxlength="50"
										style="padding: 15px 0 0 25px;" class="contact_filed"
										value="password"
										onfocus="javascript:clearField(this,'password')"
										onblur="javascript:fillField(this,'password')" />
								</p>
								<p style="padding: 5px 0 0 120px;">
									<a id="passError" class="user_name_error_text"></a>
								</p>

								<p style="padding: 2px 0 0 42px;" class="user_name_text">
									ще раз пароль<span style="color: #f60219;"> *</span> <input
										type="password" id="passConfirmField" name="passConfirmField" maxlength="50"
										style="padding: 15px 0 0 25px;" class="contact_filed"
										value="password"
										onfocus="javascript:clearField(this,'password')"
										onblur="javascript:fillField(this,'password')" />
								</p>
								<p style="padding: 5px 0 0 120px;">
									<a id="passConfirmError" class="user_name_error_text"></a>
								</p>

								<p style="padding: 2px 0 0 98px;" class="user_name_text">
									ім'я <span style="color: #f60219;"> *</span> <input type="text"
										id="firstNameField" name="firstNameField" maxlength="50"
										style="padding: 15px 0 0 30px;" class="contact_filed"
										value="введіть ім я"
										onfocus="javascript:clearField(this,'введіть ім я')"
										onblur="javascript:fillField(this,'введіть ім я')" />
								</p>
								<p style="padding: 5px 0 0 120px;">
									<a id="firstNameError" class="user_name_error_text"></a>
								</p>

								<p style="padding: 6px 0 0 68px;" class="user_name_text">
									прізвище<span style="color: #f60219;"> *</span> <input
										type="text" id="lastNameField" name="lastNameField" maxlength="50"
										style="padding: 15px 0 0 30px;" class="contact_filed"
										value="введіть прізвище"
										onfocus="javascript:clearField(this,'введіть прізвище')"
										onblur="javascript:fillField(this,'введіть прізвище')" />
								</p>
								<p style="padding: 5px 0 0 120px;">
									<a id="lastNameError" class="user_name_error_text"></a>
								</p>

								<p style="padding: 4px 0 0 85px;" class="user_name_text">
									адреса <input type="text" id="addressField" name="addressField" maxlength="50"
										style="padding: 15px 0 0 30px;" class="contact_filed"
										value="введіть адресу"
										onfocus="javascript:clearField(this,'введіть адресу')"
										onblur="javascript:fillField(this,'введіть адресу')" />
								</p>

								<p style="padding: 30px 0 0 150px;">
									<a href="#" onclick="javascript:validateForm();"
										class="log">Добре</a>
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
