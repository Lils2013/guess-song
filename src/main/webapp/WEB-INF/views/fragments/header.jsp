<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <a href="<spring:url value="/"/>" class="navbar-brand">Guess the Song</a>
        </div>

        <ul class="nav navbar-nav">

            <li><a href="<spring:url value="/game/newGame"/>">New game</a></li>


            <sec:authorize access="authenticated" var="authenticated"/>
            <c:choose>
                <c:when test="${authenticated}">
                    <li>
                        <p class="navbar-text">
                            Welcome
                            <sec:authentication property="name"/>
                            <a id="logout" href="#">Logout</a>
                        </p>
                        <form id="logout-form" action="<c:url value="/logout"/>" method="post">
                            <sec:csrfInput/>
                        </form>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<spring:url value="/login/"/>">Sign In</a></li>
                </c:otherwise>
            </c:choose>

        </ul>

    </div>
</nav>