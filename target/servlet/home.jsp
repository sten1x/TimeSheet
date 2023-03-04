<%@ page import="java.util.List" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <fmt:setLocale value="${sessionScope.lang}" />
            <fmt:setBundle basename="messages" />
            <!doctype html>
            <html lang="en">

            <head>
                <title>Home</title>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
                    crossorigin="anonymous"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
                    integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
                    crossorigin="anonymous"></script>

                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
                    rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
                    crossorigin="anonymous">
                <script>
                    function myFunction(element) {
                        document.getElementById("editId").value = element.name;
                    }
                </script>
            </head>

            <body>

                <c:import url="header.jsp" />

                <div class="container-fluid">
                    <button type="button" class="btn btn-dark mb-3" data-bs-toggle="modal"
                        data-bs-target="#addTimeTrackingModal">
                        <c:choose>
                            <c:when test="${'de'.equals(sessionScope.lang)}">
                                <fmt:message key="navbar.timeTracking" />
                                <fmt:message key="ad.title" />

                            </c:when>
                            <c:otherwise>
                                <fmt:message key="ad.title" />
                                <fmt:message key="navbar.timeTracking" />
                            </c:otherwise>
                        </c:choose>

                    </button>

                    <c:import url="modals/add/addTimeTrackingModal.jsp" />
                    <c:import url="modals/edit/editTimeTrackingModal.jsp" />

                    <table class="table table-sm table-dark table-striped text-center table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <fmt:message key="table.employeeName" />
                                </th>
                                <th>
                                    <fmt:message key="table.projectName" />
                                </th>
                                <th>
                                    <fmt:message key="table.task" />
                                </th>
                                <th>
                                    <fmt:message key="table.hours" />
                                </th>
                                <th>
                                    <fmt:message key="table.start" />
                                </th>
                                <th>
                                    <fmt:message key="table.end" />
                                </th>
                                <th>
                                    <fmt:message key="table.comment" />
                                </th>
                                <th>
                                    <fmt:message key="table.status" />
                                </th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <c:choose>
                                <c:when test="${sessionScope.isAdmin}">
                                    <c:forEach items="${sessionScope.timeTrackingList}" var="t">
                                        <tr>
                                            <c:choose>
                                                <c:when test="${sessionScope.employeeId == t.getEmployee().getId()}">
                                                    <td><b>${t.getEmployee().getName()}</b></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>${t.getEmployee().getName()}</td>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>${t.getProject().getProjectName()}</td>
                                            <td>${t.getTask().getType()}</td>
                                            <td>${t.getWorkHours()}</td>
                                            <td>${t.getStartDate().toString()}</td>
                                            <td>${t.getEndDate().toString()}</td>
                                            <td>${t.getComment()}</td>
                                            <td>${t.getStatus().getStatus()}</td>
                                            <c:choose>
                                                <c:when test="${sessionScope.employeeId == t.getEmployee().getId()}">
                                                    <td>
                                                        <button type="button" name="${t.getId()}"
                                                            onclick="myFunction(this)" class="btn btn-primary"
                                                            data-bs-toggle="modal"
                                                            data-bs-target="#editTimeTrackingModal">
                                                            <fmt:message key="but.edit" />
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <form action="home" method="post">
                                                            <input type="hidden" name="${t.getId()}"
                                                                value="delete" />
                                                            <input type="submit" class="btn btn-primary"
                                                                name="actionDelete" value="delete" />
                                                        </form>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td></td>
                                                    <td></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${sessionScope.timeTrackingList}" var="t">
                                        <c:if test="${sessionScope.employeeId == t.getEmployee().getId()}">
                                            <tr>
                                                <td>${t.getEmployee().getName()}</td>
                                                <td>${t.getProject().getProjectName()}</td>
                                                <td>${t.getTask().getType()}</td>
                                                <td>${t.getWorkHours()}</td>
                                                <td>${t.getStartDate().toString()}</td>
                                                <td>${t.getEndDate().toString()}</td>
                                                <td>${t.getComment()}</td>
                                                <td>${t.getStatus().getStatus()}</td>

                                                <td>
                                                    <button type="button" name="tEdit${t.getId()}"
                                                        onclick="myFunction(this)" class="btn btn-primary"
                                                        data-bs-toggle="modal" data-bs-target="#editTimeTrackingModal">
                                                        <fmt:message key="but.edit" />
                                                    </button>
                                                </td>
                                                <td>
                                                    <form action="home" method="post">
                                                        <input type="hidden" name="tDelete${t.getId()}"
                                                            value="delete" />
                                                        <input type="submit" class="btn btn-primary" name="actionDelete"
                                                            value="delete" />
                                                    </form>
                                                </td>

                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </body>

            </html>