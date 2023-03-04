<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />

        <head>
            <link href="css/navbar.css" rel="stylesheet">
        </head>

        <nav class="navbar navbar-expand navbar-dark bg-dark">
            <div class="container-fluid fixed">
                <a class="navbar-brand">
                    <img src="photos/logo-white.png" width="160" height="45" alt="">
                </a>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">

                        <li class="nav-item active">
                            <a class="nav-link" href="home">
                                <fmt:message key="navbar.home" />
                            </a>
                        </li>

                        <c:if test="${sessionScope.isAdmin}">
                            <li class="nav-item">
                                <a class="nav-link" href="employee">
                                    <fmt:message key="navbar.employee" />
                                </a>
                            </li>
                        </c:if>

                        <li class="nav-item">
                            <a class="nav-link" href="customer">
                                <fmt:message key="navbar.customer" />
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="project">
                                <fmt:message key="navbar.project" />
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="task">
                                <fmt:message key="navbar.task" />
                            </a>
                        </li>
                </div>

                <div class="dropdown container d-inline-block align-right">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="navbar.language" />
                    </button>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                        <li>
                            <form class="dorpdown-item mb-1" action="home" method="get">
                                <input type="submit" class="btn btn-dark" name="actionEnglish" value="english" />
                                <img src="photos/flag-usa.png" width="40" height="20" alt="">
                            </form>
                        </li>
                        <li>
                            <form class="dorpdown-item mb-1" action="home" method="get">
                                <input type="submit" class="btn btn-dark" name="actionGerman" value="german" />
                                <img src="photos/flag-germany.png" width="40" height="20" alt="">
                            </form>
                        </li>
                        <li>
                            <form class="dorpdown-item mb-1" action="home" method="get">
                                <input type="submit" class="btn btn-dark" name="actionRomanian" value="romanian">
                                <img src="photos/flag-romania.png" width="40" height="20" alt=""></input>
                            </form>
                        </li>
                    </ul>
                </div>

                <a class="navbar-brand d-inline-block align-right" href="logout">
                    <img src="photos/logout.png" width="30" height="30" alt="">
                </a>
            </div>
        </nav>