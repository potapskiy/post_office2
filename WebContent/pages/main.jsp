<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <link rel="stylesheet" type="text/css" href="finance.css">
    </head>
<body>
	<center>	
      <h1>Hello, ${user}</h1>
      
      <br>        
      <table class="menu" frame="hsides">
      	<tr>
      	<td>
      		<form name="showHistoryForm" action="controller" method="post" accept-charset="UTF-8">
      			<input type="hidden" name="command" value="showhistory">
      			<a href="#" onclick="document.forms['showHistoryForm'].submit();return false;">Show history</a>
      		</form>
      	</td>
      	<td>
      		<form name="mohthlyIncomeForm" action="controller" method="post" accept-charset="UTF-8">
      			<input type="hidden" name="command" value="monthlystatistic">
      			<input type="hidden" name="type" value="Income">
      			<a href="#" onclick="document.forms['mohthlyIncomeForm'].submit();return false;">Monthly income</a>
      		</form>
      	</td>	
      	<td>
      		<form name="mohthlySpendingForm" action="controller" method="post" accept-charset="UTF-8">
      			<input type="hidden" name="command" value="monthlystatistic">
      			<input type="hidden" name="type" value="Spending">
      			<a href="#" onclick="document.forms['mohthlySpendingForm'].submit();return false;">Monthly spending</a>
      		</form>
      	</td>
      	<td>
      		<a href="index.jsp">Log out</a>	
      	</td>
      	</tr>
      </table>
      
      <br>
      <h1>Add new record</h1>
       	
      
      <form name="addRecord" action="controller" method="post" accept-charset="UTF-8">
					
						<input type="hidden" name="command" value="addrecord">
						<table class="box" frame="box">
							<caption class="message"> ${message}</caption>
      						<c:remove var="message" scope="request"/>
      	
							<tr>
							   <td>Type:</td>
							   <td>
							   		<select name="typelist">
										<option value="Income">Income</option>
										<option value="Spending">Spending</option>
									</select> 
							   </td>
							</tr>
							<tr>
							   <td>Info:</td>
							   <td><input type="text" name="info"/></td>
							</tr>
							<tr>
							   <td>Amount:</td>
							   <td><input type="number" min="0" step="any" name="amount"/></td>
							</tr>
							
							<tr>
							     <td align="center"><input type="submit" value="Add"/></td>
						    </tr>
						</table>
					</form>
      
        </center> 	  
    </body>
</html>
