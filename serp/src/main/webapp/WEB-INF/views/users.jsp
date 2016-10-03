<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Hello World!</h2>
<h1 id="date-and-time"></h1>
<button type="button" onclick="document.getElementById('date-and-time').innerHTML = setInterval('updateClock()', 1000)">
Click me to show date and time</button>
<h2>List of Employees</h2>  
    <table>
        <tr>
            <td>ID</td><td>Full Name</td><td>Salary</td><td>SSN</td><td></td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
            <td>${user.id}</td>
            <td>${user.fullName}</td>
            </tr>
        </c:forEach>
    </table>

<script>	
	function getUTCTimeStamp()
	{
		var utcDateTimeNow = new Date();
		var getUTCDay = utcDateTimeNow.getUTCDay();
		var getUTCMonth = utcDateTimeNow.getUTCMonth();
		var getUTCFullYear = utcDateTimeNow.getUTCFullYear();

		var dateTimeNow= getUTCDay + "/" + getUTCMonth + "/" + getUTCFullYear;
		return dateTimeNow;
	}

	function updateClock ( )
 	{
 	var currentTime = new Date ( );
  	var currentHours = currentTime.getHours ( );
  	var currentMinutes = currentTime.getMinutes ( );
  	var currentSeconds = currentTime.getSeconds ( );

  	// Pad the minutes and seconds with leading zeros, if required
  	currentMinutes = ( currentMinutes < 10 ? "0" : "" ) + currentMinutes;
  	currentSeconds = ( currentSeconds < 10 ? "0" : "" ) + currentSeconds;

  	// Choose either "AM" or "PM" as appropriate
  	var timeOfDay = ( currentHours < 12 ) ? "AM" : "PM";

  	// Convert the hours component to 12-hour format if needed
  	currentHours = ( currentHours > 12 ) ? currentHours - 12 : currentHours;

  	// Convert an hours component of "0" to "12"
  	currentHours = ( currentHours == 0 ) ? 12 : currentHours;

  	// Compose the string for display
  	var currentTimeString = currentHours + ":" + currentMinutes + ":" + currentSeconds + " " + timeOfDay;
  	
  	
   	$("#clock").html(currentTimeString);
   	  	
 	}

	$(document).ready(function()
	{
	   setInterval('updateClock()', 1000);
	});
</script>
</body>
</html>
