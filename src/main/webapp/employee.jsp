<%@ page import="java.util.List" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <fmt:setLocale value="${sessionScope.lang}" />
            <fmt:setBundle basename="messages" />
            <!doctype html>
            <html lang="en">

            <head>
                <title>Employees</title>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
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
                    <c:if test="${sessionScope.isAdmin}">
                        <div class="container-fluid">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm">
                                        <button type="button" class="btn btn-dark mb-3" data-bs-toggle="modal"
                                            data-bs-target="#addEmployeeModal">
                                            <c:choose>
                                                <c:when test="${'de'.equals(sessionScope.lang)}">
                                                    <fmt:message key="navbar.employee" />
                                                    <fmt:message key="ad.title" />

                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="ad.title" />
                                                    <fmt:message key="navbar.employee" />
                                                </c:otherwise>
                                            </c:choose>

                                        </button>
                                    </div>
                                    <div class="col-sm">
                                        <c:if test="${requestScope.error_message != null}">
                                            <div class="alert alert-danger" role="alert">
                                                ${ requestScope.error_message }
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>

                            <c:import url="modals/add/addEmployeeModal.jsp" />
                            <c:import url="modals/edit/editEmployeeModal.jsp" />
                    </c:if>


                    <table class="table table-sm table-dark table-striped text-center table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <fmt:message key="table.employeeName" />
                                </th>
                                <th>
                                    <fmt:message key="table.employeeAge" />
                                </th>
                                <th>
                                    <fmt:message key="table.employeePhone" />
                                </th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <c:forEach items="${sessionScope.employeeList}" var="e">
                                <tr>
                                    <td>${e.getName()}</td>
                                    <td>${e.getAge()}</td>
                                    <td>${e.getPhone()}</td>
                                    <c:if test="${sessionScope.isAdmin}">
                                        <td>
                                            <button type="button" name="${e.getId()}" onclick="myFunction(this)"
                                                class="btn btn-primary" data-bs-toggle="modal"
                                                data-bs-target="#empEditModal">
                                                <fmt:message key="but.edit" />
                                            </button>
                                        </td>
                                        <td>
                                            <form action="employee" method="post">
                                                <input type="hidden" name="${e.getId()}" value="delete" />
                                                <input type="submit" class="btn btn-primary" name="actionDelete"
                                                    value="delete" />
                                            </form>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </body>

            </html>