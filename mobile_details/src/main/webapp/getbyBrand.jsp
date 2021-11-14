<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Filter by Brand</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="<c:url value="/resources/script.js" />"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>
<div> 
	<nav class="navbar navbar-dark bg-primary" >
  		<a class="navbar-brand" href="index">Home</a>
	</nav>
</div>
<div class="container">
<h2>Filter by Brand Name</h2>
	<form action="getByBrandName" method="post">
		<div class="form-group row">
			<div class="col-sm-2">
				Brand Name:
			</div>
			<div class="col-sm-6">
				<input class="form-control" type="text" name="brandName" placeholder="Enter brand name" onblur="validateName()">
			</div>
			<div class="col"><label class="color-valid" id="nameValid"></label></div>
		</div>
		<div class="form-group row">
			<div class="col-sm-2">
				<input type="submit" class="btn btn-primary" value="Filter" onclick="validateField()">
			</div>
			<div class="col-sm-6">
				<input type="reset" class="btn btn-primary" value="Reset">
			</div>
			
		</div>
	</form>
</div>
<div class="card-footer text-muted fixed-bottom">
    <h6 class="text-center">@Copyright Reserved 2021</h6>
</div>
</body>
</html>