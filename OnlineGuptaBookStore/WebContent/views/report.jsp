<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revenue Report</title><%@ include file="header.jsp"%>
</head>
<body>
	<c:url value="/ReportServlet" var="url" />
	<form action="${url }" method="get">

		<center>Please select the Date:			<input type="date" name="hisdate" required> &nbsp; <input
				type="submit" value="generate">
		</center>
	</form>
	<h3 align="center" style="color:red">${msg }</h3>
<c:if test="${not empty historyList }">
<h3 align="center">Daily Revenue</h3><hr>
	<table border="2" align="center">
		<thead>
			<tr>
				<th>Book_Id</th>
				<th>Book_Name</th>
				<th>Quantity</th>
				<th>Price</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${historyList}" var="list">
				<tr>
					<td>${list.bookid}</td>
					<td>${list.bookname}</td>
					<td align="right">${list.quantity}</td>
					<td>${list.price}</td>
					<%-- 					<c:choose>
					<c:when test="${list.bookid==bookid }">
					<c:set value="${q}${qun}" var="qun"/>
					<c:set value="${p}${price }" var="price"/>
					</c:when>
					</c:choose>
					<c:set value="${list.quantity }" var="q"/><c:set value="${list.price }" var="p"/>
					<c:set value="${list.quantity }" var="q"/><c:set value="${list.price }" var="p"/>
<c:out value="${q}"/> <c:out value="${p}"/>					<c:out value="${qun}"/> <c:out value="${price}"/><br>					
					<c:set value="${list.bookid }" var="bookid"/>					
					 <c:if test="${list.bookid != bookid}" >
					<td>${list.bookid}</td>
					<td>${list.bookname}</td>
					<td>${qun}</td>
					<td>${price}</td>
					 </c:if>
					<c:if test="${list.bookid==bookid }">
					<c:set value="${list.quantity+qun }" var="qun"/>
					<c:set value="${list.price+price }" var="price"/>					
					 <<<continue>>>
					</c:if>
					<c:out value="${qun }"/>
					<c:out value="${price }"/>					
					<c:if test="${not empty qun and not empty price}" >
					<td>${list.bookid}</td>
					<td>${list.bookname}</td>
					<td>${qun}</td>
					<td>${price}</td>
					</c:if> --%>
					<c:set value="${list.price+totalPrice }" var="totalPrice"/>
					<c:set value="${list.quantity+totalQuantity}" var="totalQuantity"/>
				</tr>
			</c:forEach>
			<tr><td colspan="3" align="center">Total Revenue</td><td>${totalPrice}</td></tr>
			<tr><td colspan="3" align="center">Total No. of Quantity Sold</td><td align="right">${totalQuantity}</td></tr>
		</tbody>

	</table>
	</c:if>
</body>
</html>