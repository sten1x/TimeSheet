<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <!doctype html>
        <html lang="en">

        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
                crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
                crossorigin="anonymous"></script>
            <title>Task</title>

            <script>
                function myFunction(element) {
                    document.getElementById("taskId").value = element.name;
                }
            </script>
        </head>

        <body>
            <c:import url="header.jsp" />

            <div class="container-fluid">
                <c:if test="${sessionScope.isAdmin}">
                    <button type="button" class="btn btn-dark mb-3" data-bs-toggle="modal"
                        data-bs-target="#addTaskModal">
                        <c:choose>
                            <c:when test="${'de'.equals(sessionScope.lang)}">
                                <fmt:message key="navbar.task" />
                                <fmt:message key="ad.title" />

                            </c:when>
                            <c:otherwise>
                                <fmt:message key="ad.title" />
                                <fmt:message key="navbar.task" />
                            </c:otherwise>
                        </c:choose>

                    </button>
                </c:if>

                <c:import url="modals/add/addTaskModal.jsp" />
                <c:import url="modals/edit/editTaskModal.jsp" />
                <div class="d-flex justify-content-center">
                    <table class="table table-sm table-dark table-striped text-center table-hover">
                        <c:choose>
                            <c:when test="${sessionScope.isAdmin}">
                                <thead>
                                    <tr>
                                        <th>
                                            <fmt:message key="table.task" />
                                        </th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody class="table-group-divider">
                                    <c:forEach items="${sessionScope.taskList}" var="t">
                                        <tr>
                                            <td>${t.getType()}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${t.getDisabled() == false}">
                                                        <button type="button" name="${t.getId()}"
                                                            class="btn btn-primary" onclick="myFunction(this)"
                                                            data-bs-toggle="modal" data-bs-target="#editTaskModal">
                                                            <fmt:message key="but.edit" />
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button type="button" name="${t.getId()}"
                                                            class="btn btn-primary" onclick="myFunction(this)"
                                                            data-bs-toggle="modal" data-bs-target="#editTaskModal"
                                                            disabled>
                                                            <fmt:message key="but.edit" />
                                                        </button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${t.getDisabled() == false}">
                                                        <form action="task" method="post">
                                                            <input type="hidden" name="${t.getId()}" value="disable" />
                                                            <input type="submit" class="btn btn-primary"
                                                                name="actionDisable" value="disable" />
                                                        </form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form action="task" method="post">
                                                            <input type="hidden" name="${t.getId()}" value="enable" />
                                                            <input type="submit" class="btn btn-primary"
                                                                name="actionEnable" value="enable" />
                                                        </form>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </c:when>
                            <c:otherwise>
                                <thead>
                                    <tr>
                                        <th>
                                            <fmt:message key="table.task" />
                                        </th>
                                    </tr>
                                </thead>
                                <tbody class="table-group-divider">
                                    <c:forEach items="${sessionScope.taskList}" var="t">
                                        <tr>
                                            <td>${t.getType()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </div>
        </body>

        </html>