<%@page import="office.entities.Parcel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
						<p>
							<span class="web_text"> Ваші посилки </span><br /> <br /> <br />
							<br />
							<%
								ArrayList<Parcel> sentParcels = (ArrayList<Parcel>) request
										.getAttribute("sentparcels");
								ArrayList<Parcel> receivedParcels = (ArrayList<Parcel>) request
										.getAttribute("receivedparcels");
							%>
						<p>							
							<span><a class="nulla">Надіслані </a> <img
								src="images/outbox.jpg" alt="" /></span><br /> <br /> <span
								style="line-height: 18px;">
								<% if (sentParcels.isEmpty()) { %>
								Порожньо...
								<% } else { %><table>
									<tr align="center">
										<th>Ім'я</th>
										<th></th>
										<th></th>
										<th>Прізвище</th>
										<th></th>
										<th></th>
										<th>Телефон</th>
										<th></th>
										<th></th>
										<th>Коли відправлено</th>
										<th></th>
										<th></th>
										<th>Коли отримано</th>
										<th></th>
										<th></th>
										<th>Маса, кг</th>
										<th></th>
										<th></th>
										<th>Вартість</th>
										<th></th>
										<th></th>
										<th>Статус</th>
										<th></th>
										<th></th>
									</tr>
									<%
										for (Parcel p : sentParcels) {
									%>

									<tr align="center">
										<td><%=p.getClientToName()%></td>
										<td></td>
										<td></td>
										<td><%=p.getClientToSurname()%></td>
										<td></td>
										<td></td>
										<td><%=p.getClientToTel()%></td>
										<td></td>
										<td></td>
										<td><%=p.getDateFrom()%></td>
										<td></td>
										<td></td>
										<td><%=p.getDateTo()%></td>
										<td></td>
										<td></td>
										<td><%=p.getWeight()%></td>
										<td></td>
										<td></td>
										<td><%=p.getPrice()%></td>
										<td></td>
										<td></td>
										<td><%=p.getStatus()%></td>
										<td></td>
										<td></td>
									</tr>
									<%
										}
									%>
								</table>
								<%} %>
							</span><br /> <br />
						</p>
						<p>
							<span><a class="nulla">Отримані </a> <img
								src="images/inbox.jpg" alt="" /></span><br /> <br /> <span
								style="line-height: 18px;">
								<% if (receivedParcels.isEmpty()) { %>
								Порожньо...
								<% } else { %><table>
									<tr align="center">
										<th>Ім'я</th>
										<th></th>
										<th></th>
										<th>Прізвище</th>
										<th></th>
										<th></th>
										<th>Телефон</th>
										<th></th>
										<th></th>
										<th>Коли відправлено</th>
										<th></th>
										<th></th>
										<th>Коли отримано</th>
										<th></th>
										<th></th>
										<th>Маса, кг</th>
										<th></th>
										<th></th>
										<th>Вартість</th>
										<th></th>
										<th></th>
										<th>Статус</th>
										<th></th>
										<th></th>
									</tr>
									<%
										for (Parcel p : receivedParcels) {
									%>

									<tr align="center">
										<td><%=p.getClientToName()%></td>
										<td></td>
										<td></td>
										<td><%=p.getClientToSurname()%></td>
										<td></td>
										<td></td>
										<td><%=p.getClientToTel()%></td>
										<td></td>
										<td></td>
										<td><%=p.getDateFrom()%></td>
										<td></td>
										<td></td>
										<td><%=p.getDateTo()%></td>
										<td></td>
										<td></td>
										<td><%=p.getWeight()%></td>
										<td></td>
										<td></td>
										<td><%=p.getPrice()%></td>
										<td></td>
										<td></td>
										<td><%=p.getStatus()%></td>
										<td></td>
										<td></td>
									</tr>
									<%
										}
									%>
								</table>
								<%} %>
							</span><br /> <br />
						</p>

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
										телефон<span style="color: #f60219;"> *</span>
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
											class="contact_filed" value="password"
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
