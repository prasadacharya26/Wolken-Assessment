<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<link  href="<c:url value="/webapp/style/style.css"/>" rel="stylesheet"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<div>
	<nav class="navbar navbar-dark bg-primary">
  		<a class="navbar-brand" href="index">Home</a>
	</nav>
</div>
<div class="container">
	<h2>Mobile Details</h2>
	<div class="index-top">
		<a href="saveData"><button type="button" class="btn btn-primary btn-lg">Save</button></a>
		<a href="getbyPrice"><button type="button" class="btn btn-primary btn-lg">Get by Price</button></a>
		<a href="getbyBrand"><button type="button" class="btn btn-primary btn-lg">Get by Brand</button></a>
		<a href="updatePriceByModelno"><button type="button" class="btn btn-primary btn-lg">Update Price By Modelno</button></a>
		<a href="updateAvailabilityByModelname"><button type="button" class="btn btn-primary btn-lg">Update Availability By Modelname</button></a>
	</div>
	</div>
	<div class="card-footer text-muted fixed-bottom">
    	<h6 class="text-center">@Copyright Reserved 2021</h6>
  	</div>
</body>
</html>
