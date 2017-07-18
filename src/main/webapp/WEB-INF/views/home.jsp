<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Manager</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
	
	<jsp:include page="../views/fragments/header.jsp"></jsp:include>
	<div class="container">
        <h2>Score: ${sessionScope.score}</h2>
        <c:if test="${sessionScope.result != null}">
            <h2>${sessionScope.result}</h2>
        </c:if>
        <h2>Choices</h2>
        <table class="table table-hover">
            <tbody>
            <tr>
                <th>Name</th><th>Artist</th>
            </tr>
            <c:forEach items="${sessionScope.question.songs}" var="song">
                <tr>
                    <td><a href="<spring:url value="/answer?id=${song.id}"/>">${song.name}</a></td>
                    <td>${song.artist}</td>
                </tr>
                <c:if test="${song.id == sessionScope.question.answerId}">
                    <audio id="player" controls="controls">
                        <source id="sourceMp3" type="audio/mp3" src="<spring:url value="/resources/mp3/${song.source}"/>"/>
                        Your browser does not support the audio element.
                    </audio>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
	</div>
</body>
</html>