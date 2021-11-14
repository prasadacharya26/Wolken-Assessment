<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored = "false" %>
 	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="<c:url value="/css/style.css" />">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<title>Result</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-dark bg-primary">
	  		
	  		<a class="navbar-brand" href="index">Home</a>
	  		<a class="navbar-brand" href="saveData">Save</a>
			<a class="navbar-brand" href="getbyPrice">Filter by Price</a>
			<a class="navbar-brand" href="getbyBrand">Filter by Brand</a>
			<a class="navbar-brand" href="updatePriceByModelno">Update Price</a>
			<a class="navbar-brand" href="updateAvailabilityByModelname">Update Availability</a>
		</nav>
	</div>
	<div class="container">
		<h2>${msg}</h2>
	</div>
	<div class="card-footer text-muted fixed-bottom bg-dark">
    	<h6 class="text-center">@Copyright Reserved 2021</h6>
  	</div>	
</body>
</html>