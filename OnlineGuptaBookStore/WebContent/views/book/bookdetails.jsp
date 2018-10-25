<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><%@include file="../header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
.thumbnail {
	padding: 0px;
}

.panel {
	position: relative;
}

.panel>.panel-heading:after, .panel>.panel-heading:before {
	position: absolute;
	top: 11px;
	left: -16px;
	right: 100%;
	width: 0;
	height: 0;
	display: block;
	content: " ";
	border-color: transparent;
	border-style: solid solid outset;
	pointer-events: none;
}

.panel>.panel-heading:after {
	border-width: 7px;
	border-right-color: #f7f7f7;
	margin-top: 1px;
	margin-left: 2px;
}

.panel>.panel-heading:before {
	border-right-color: #ddd;
	border-width: 8px;
}
</style>
<script>
	$(document).ready(function() {
		//-- Click on detail
		$("ul.menu-items > li").on("click", function() {
			$("ul.menu-items > li").removeClass("active");
			$(this).addClass("active");
		})

		$(".attr,.attr2").on("click", function() {
			var clase = $(this).attr("class");

			$("." + clase).removeClass("active");
			$(this).addClass("active");
		})

		//-- Click on QUANTITY
		$(".btn-minus").on("click", function() {
			var now = $(".section > div > input").val();
			if ($.isNumeric(now)) {
				if (parseInt(now) - 1 > 0) {
					now--;
				}
				$(".section > div > input").val(now);
			} else {
				$(".section > div > input").val("1");
			}
		})
		$(".btn-plus").on("click", function() {
			var now = $(".section > div > input").val();
			if ($.isNumeric(now)) {
				$(".section > div > input").val(parseInt(now) + 1);
			} else {
				$(".section > div > input").val("1");
			}
		})
	})
</script>
<style>
ul>li {
	margin-right: 25px;
	font-weight: lighter;
	cursor: pointer
}

li.active {
	border-bottom: 3px solid silver;
}

.item-photo {
	display: flex;
	justify-content: center;
	align-items: center;
	border-right: 1px solid #f6f6f6;
}

.menu-items {
	list-style-type: none;
	font-size: 11px;
	display: inline-flex;
	margin-bottom: 0;
	margin-top: 20px
}

.btn-success {
	width: 100%;
	border-radius: 0;
}

.section {
	width: 100%;
	margin-left: -15px;
	padding: 2px;
	padding-left: 15px;
	padding-right: 15px;
	background: #f8f9f9
}

.title-price {
	margin-top: 30px;
	margin-bottom: 0;
	color: black
}

.title-attr {
	margin-top: 0;
	margin-bottom: 0;
	color: black;
}

.btn-minus {
	cursor: pointer;
	font-size: 7px;
	display: flex;
	align-items: center;
	padding: 5px;
	padding-left: 10px;
	padding-right: 10px;
	border: 1px solid gray;
	border-radius: 2px;
	border-right: 0;
}

.btn-plus {
	cursor: pointer;
	font-size: 7px;
	display: flex;
	align-items: center;
	padding: 5px;
	padding-left: 10px;
	padding-right: 10px;
	border: 1px solid gray;
	border-radius: 2px;
	border-left: 0;
}

div.section>div {
	width: 100%;
	display: inline-flex;
}

div.section>div>input {
	margin: 0;
	padding-left: 5px;
	font-size: 10px;
	padding-right: 5px;
	max-width: 18%;
	text-align: center;
}

.attr, .attr2 {
	cursor: pointer;
	margin-right: 5px;
	height: 20px;
	font-size: 10px;
	padding: 2px;
	border: 1px solid gray;
	border-radius: 2px;
}

.attr.active, .attr2.active {
	border: 1px solid orange;
}

@media ( max-width : 426px) {
	.container {
		margin-top: 0px !important;
	}
	.container>.row {
		padding: 0 !important;
	}
	.container>.row>.col-xs-12.col-sm-5 {
		padding-right: 0;
	}
	.container>.row>.col-xs-12.col-sm-9>div>p {
		padding-left: 0 !important;
		padding-right: 0 !important;
	}
	.container>.row>.col-xs-12.col-sm-9>div>ul {
		padding-left: 10px !important;
	}
	.section {
		width: 104%;
	}
	.menu-items {
		padding-left: 0;
	}
}

ul.horizontal-slide {
    margin: 0;
    padding: 0;
    width: 100%;
    white-space: nowrap;
    overflow-x: auto;
}

ul.horizontal-slide li[class*="col"] {
    display: inline-block;
    float: none;
}

ul.horizontal-slide li[class*="col"]:first-child {
    margin-left: 0;
}

</style>
<title>${bookdetail.bookname}details</title>
</head>
<body>
<form action="CartServlet" method="post">
	<div class="container">
		<div class="row">
			<div class="col-xs-4 item-photo">
				<img style="max-width: 100%;"
					src="<c:url value='resources/images/uploadDir/${bookdetail.getBookid()}.png'/>" />
			</div>
			<div class="col-xs-5" style="border: 0px solid gray">
				<!-- Datos del vendedor y titulo del producto -->
				<h3>${bookdetail.bookname}</h3>
				<h6 class="title-price">Description</h6>
				<h3 style="margin-top: 0px;">${bookdetail.bookdesc}</h3>

				<!-- Precios -->
				<h6 class="title-price">
					<small>Price</small>
				</h6>
				<h3 style="margin-top: 0px;">RS. ${bookdetail.price }</h3>

				<!-- Detalles especificos del producto -->
<c:if test="${sessionScope.rolename=='ROLE_USER' }">
<input type="hidden" name="bookid" value="${bookdetail.bookid}">
				<div class="section" style="padding-bottom: 20px;">
					<h6 class="title-attr">
						<small>Quantity</small>
					</h6>
					</c:if>
					<c:if test="${sessionScope.rolename=='ROLE_USER' }">
					<div>
						<div class="btn-minus">
							<span class="glyphicon glyphicon-minus"></span>
						</div>
						<input type="number" value="1" min="1" max="${bookdetail.quantity }" name="quantity" />
						<div class="btn-plus">
							<span class="glyphicon glyphicon-plus"></span>
						</div>
					</div>
					</c:if>
					
				</div>
<c:if test="${sessionScope.rolename=='ROLE_USER' }">
				<!-- Botones de compra -->
				<div class="section" style="padding-bottom: 20px;">
					<button class="btn btn-success">
						<span style="margin-right: 20px"
							class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
						Add To Cart
					</button>

				</div>
				</c:if>
			</div>


		</div>
	</div>
	</form>
	
	<!-- related books -->
	<div class="container">
	<h3 style="">Related Books By Subject</h3>
	<div class="container" style="background-color:white;">
        <div class="row">
              <ul class="horizontal-slide">
              <c:forEach items="${listSub}" var="sub">
              <c:if test="${sub.bookid!=subid}">
              <c:if test="${sub.bookid!=bookdetail.subid}">
                <a href="FetchBookByIdServlet?id=${sub.bookid}"><li class="col-md-2"><img class="thumbnail" style="max-width: 100%;" src="<c:url value='resources/images/uploadDir/${sub.getBookid()}.png'/>"/>${sub.bookname}</li></a>
              </c:if></c:if><c:set var="subid" value="${sub.bookid }"/></c:forEach>
              </ul>
          </div>
    </div>
    </div>
    <div class="row">
    <div class="col-md-5"></div>
    <div class="col-md-2"></div>
    <div class="col-md-5"></div>
    </div><div class="container">
    <h3 style="">Related Books By Author</h3>
    <div class="container" style="background-color:white;">
        <div class="row">
       
              <ul class="horizontal-slide">
              <c:forEach items="${listAuth}" var="auth">
              <c:if test="${auth.bookid!=authid}">
              <c:if test="${auth.bookid!=bookdetail.authid}">
                <a href="FetchBookByIdServlet?id=${auth.bookid}"><li class="col-md-2"><img class="thumbnail"style="max-width: 100%;" src="<c:url value='resources/images/uploadDir/${auth.getBookid()}.png'/>"/>${auth.bookname}</li></a>         
              </c:if></c:if><c:set var="authid" value="${auth.bookid }"/>
              </c:forEach>
              </ul>
          
        </div>
    </div></div>
	
	<!-- /related books -->
	
	
	<%-- <ul>
			<li><a href="FetchBookByIdServlet?id=${bookdetail.bookid}"> ${bookdetail.bookname}<br>${bookdetail.bookdesc}
			<br>${bookdetail.price}<br><a href="" clas="btn btn-info">Add to cart</a>&nbsp; <a href="" class="btn btn-info">Buy Now</a>
			</a><br></li>
	</ul> --%><hr/><h3 align="center">Comments</h3>
	<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6">
	<c:if test="${sessionScope.rolename=='ROLE_USER' }">
	<c:url value="/CommentServlet" var="url"/>
	<form action="${url }" method="post">
	<input type="hidden" value="${bookdetail.bookid }" name="id">
		<b>User Comments:</b><br><textarea name="cmt" style="width: 140px; height: 50px;">
		
		</textarea>&nbsp; <input type="submit" value="Post Comment">
	</form>
	</c:if>
	</div>
	</div><hr>
	<div class="row">
	<c:forEach items="${listcmt}" var="cmt">
		<div class="col-sm-1">
			<div class="thumbnail">
				<img class="img-responsive user-photo"
					src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
			</div>
			
		</div>
		

		<div class="col-sm-5">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>${cmt.customername}</strong> <span class="text-muted">commented	</span>
				</div>
				<div class="panel-body">${cmt.content }</div>
				<!-- /panel-body -->
			</div>
			<!-- /panel panel-default -->
		</div>
		<!-- /col-sm-5 --></c:forEach>>
	</div>

</body>
</html>