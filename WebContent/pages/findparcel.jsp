<%@page import="office.entities.Parcel"%>
<%@page import="office.entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="STATUS_AT_START" value="<%=Parcel.STATUS_AT_START%>" />
<c:set var="STATUS_IN_WAY" value="<%=Parcel.STATUS_IN_WAY%>" />
<c:set var="STATUS_IN_END" value="<%=Parcel.STATUS_IN_END%>" />
<c:set var="STATUS_DONE" value="<%=Parcel.STATUS_DONE%>" />
<c:set var="ADMIN" value="<%=User.ADMIN%>" />
<c:set var="COURIER" value="<%=User.COURIER%>" />
<c:set var="LOADER" value="<%=User.LOADER%>" />
<c:set var="MANAGER" value="<%=User.MANAGER%>" />
<c:set var="USER" value="<%=User.USER%>" />
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Нова пошта++</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/login.js" type="text/javascript"></script>
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
						<li><a href="./index" class="homeactive"> Головна</a></li>
						<li><a href="./about" class="home"> Про нас</a></li>
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
			<div class="left_body_wrap">
				<div class="left_body_main">
					<div class="main_wrap">
						<c:choose>
							<c:when test="${empty parcel}">
								<form name="findParcelForm" action="controller" method="post">
									<input type="hidden" name="command" value="findparcel" />
									<p style="padding: 0px 0 0 50px;" class="text">
										Введіть ідентифікаційний номер посилки <input type="text"
											id="parcelIdField" name="parcelIdField" maxlength="50"
											style="padding: 3px 0 0 10px;" class="contact_filed1"
											value="" />
										<p style="padding: 10px 0 0 260px;">
											<a href="#"
												onclick="document.forms['findParcelForm'].submit(); return false;"
												class="log">Знайти</a>
										</p>
									</p>
								</form>
							</c:when>
							
							<c:otherwise>							
								<c:choose>
									<c:when test="${parcel.status eq -1}">
										Посилки, з таким номером не знайдено
									</c:when >
									<c:when test="${parcel.status eq STATUS_AT_START}">
										Ваша посилка зараз в місті відправника. Чекайте, ше чекайте.
									</c:when >
									<c:when test="${parcel.status eq STATUS_IN_WAY}">
										Ваша посилка зараз в дорозі. Чекайте. Ше трошки...
									</c:when>
									<c:when test="${parcel.status eq STATUS_AT_END}">
										Дана посилка зараз в місті отримувача. Приходьте, щоб забрати.
									</c:when>
									<c:when test="${parcel.status eq STATUS_DONE}">
										Посилку успішно отримали. Надсилайте ще!
									</c:when>
								
							</c:choose>

								<%
									session.removeAttribute("parcel");
								%>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>

			<c:choose>
				<c:when test="${empty user}">

					<form name="loginForm" action="controller" method="post">
						<div class="right_body_wrap">
							<div class="right_body_bg">
								<div class="login_wrap">
									<input type="hidden" name="command" value="login">

									<p class="user_text">ВХІД</p>

									<p class="user_name_text">
										телефон +380<span style="color: #f60219;"> *</span>
									</p>
									<p style="padding: 8px 0 0 28px;">
										<input type="text" name="loginField" maxlength="50"
											class="contact_filed" value="введіть телефон"
											onfocus="javascript:clearField(this,'введіть телефон')"
											onblur="javacript:fillField(this,'введіть телефон')" />
									</p>
									<p class="user_name_text1">
										пароль<span style="color: #f60219;"> *</span>
									</p>
									<p style="padding: 8px 0 0 28px;">
										<input type="password" name="pass" maxlength="50"
											class="contact_filed" value="admin"
											onfocus="javascript:clearField(this,'password')"
											onblur="javacript:fillField(this,'password')" />
									</p>
									<p style="padding: 16px 0 0 16px;">
										<a href="#"
											onclick="document.forms['loginForm'].submit(); return false;"
											class="log">Увійти</a>
									</p>
									<p class="user_name_error_text">
										<c:if test="${not empty Error}">Невірний телефон або пароль</c:if>
									</p>
									<p style="padding: 12px 0 0 16px;">
										<a href="./createacc" class="read_more1">Створити новий
											акаунт</a>
									</p>
								</div>
							</div>
						</div>
					</form>

				</c:when>

				<c:otherwise>
					<form name="logoutForm" action="controller" method="post">
						<input type="hidden" name="command" value="logout" />
						<div class="right_body_wrap">
							<div class="right_body_loginned_bg">
								<div class="login_wrap">
									<p class="user_name_text">Ви увійшли як ${user.firstName}</p>
									<p style="padding: 12px 0 0 16px;">
										<a href="./profile" class="read_more1">Мій профіль</a>
									</p>
									<c:choose>
										<c:when test="${user.getKind() eq USER}">
											<p style="padding: 12px 0 0 16px;">
												<a href="./myparcels" class="read_more1">Мої посилки</a>
											</p>
										</c:when>
										<c:when test="${user.getKind() eq MANAGER}">
											<p style="padding: 12px 0 0 16px;">
												<a href="./registerparcel" class="read_more1">Зареєструвати посилку</a>
											</p>
											<p style="padding: 12px 0 0 16px;">
												<a href="./receiveparcel" class="read_more1">Отримати посилку</a>
											</p>
										</c:when>
										<c:when test="${user.getKind() eq LOADER}">
											<p style="padding: 12px 0 0 16px;">
												<a href="./sendparcel" class="read_more1">Відправити посилку</a>
											</p>
											<p style="padding: 12px 0 0 16px;">
												<a href="./loadparcel" class="read_more1">Отримати посилку</a>
											</p>
										</c:when>
										<c:when test="${user.getKind() eq COURIER}">
											<p style="padding: 12px 0 0 16px;">
												<a href="./viewparcels" class="read_more1">Переглянути посилки</a>
											</p>											
										</c:when>

									</c:choose>

									<p style="padding: 16px 0 0 16px;">
										<a href="#"
											onclick="document.forms['logoutForm'].submit(); return false;"
											class="log">Вийти</a>
									</p>
								</div>
							</div>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
			<br class="blank" />
		</div>
	</div>
	<div class="footer_wrap">
		<div class="footer_area">
			<div class="footer_nav_area">
				<p class="footer_nav_text">
					<a href="./index" class="footeractive">Головна</a> | <a
						href="./about" class="footer">Про нас</a> | <a href="./services"
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
