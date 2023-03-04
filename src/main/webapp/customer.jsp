<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <!doctype html>
        <html lang="en">

        <head>
            <title>Customers</title>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
                crossorigin="anonymous"></script>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
                crossorigin="anonymous">

            <script>
                function myFunction(element) {
                    document.getElementById("cusId").value = element.name;
                }
            </script>
        </head>

        <body>

            <c:import url="header.jsp" />

            <div class="container-fluid">
                <c:if test="${sessionScope.isAdmin}">
                    <button type="button" class="btn btn-dark mb-3" data-bs-toggle="modal"
                        data-bs-target="#addCustomerModal">
                        <c:choose>
                            <c:when test="${'de'.equals(sessionScope.lang)}">
                                <fmt:message key="navbar.customer" />
                                <fmt:message key="ad.title" />

                            </c:when>
                            <c:otherwise>
                                <fmt:message key="ad.title" />
                                <fmt:message key="navbar.customer" />
                            </c:otherwise>
                        </c:choose>

                    </button>

                    <c:import url="modals/add/addCustomerModal.jsp" />
                    <c:import url="modals/edit/editCustomerModal.jsp" />
                </c:if>

                <table class="table table-sm table-dark table-striped text-center table-hover">
                    <c:choose>
                        <c:when test="${sessionScope.isAdmin}">
                            <thead>
                                <tr>
                                    <th>
                                        <fmt:message key="table.customerName" />
                                    </th>
                                    <th>
                                        <fmt:message key="table.customerPhone" />
                                    </th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody class="table-group-divider">

                                <c:forEach items="${sessionScope.customerList}" var="cus">
                                    <tr>
                                        <td>${cus.getName()}</td>
                                        <td>${cus.getPhone()}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${cus.getDisabled() == false}">
                                                    <button type="button" name="${cus.getId()}" class="btn btn-primary"
                                                        onclick="myFunction(this)" data-bs-toggle="modal"
                                                        data-bs-target="#editCustomerModal">
                                                        <fmt:message key="but.edit" />
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="button" name="${cus.getId()}" class="btn btn-primary"
                                                        onclick="myFunction(this)" data-bs-toggle="modal"
                                                        data-bs-target="#editCustomerModal" disabled>
                                                        <fmt:message key="but.edit" />
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${cus.getDisabled() == false}">
                                                    <form action="customer" method="post">
                                                        <input type="hidden" name="${cus.getId()}" value="disable" />
                                                        <input type="submit" class="btn btn-primary"
                                                            name="actionDisable" value="disable" />
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <form action="customer" method="post">
                                                        <input type="hidden" name="${cus.getId()}" value="enable" />
                                                        <input type="submit" class="btn btn-primary" name="actionEnable"
                                                            value="enable" />
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
                                        <fmt:message key="table.customerName" />
                                    </th>
                                    <th>
                                        <fmt:message key="table.customerPhone" />
                                    </th>
                                </tr>
                            </thead>
                            <tbody class="table-group-divider">
                                <c:forEach items="${sessionScope.customerList}" var="cus">
                                    <c:if test="${!cus.getDisabled()}">
                                        <tr>
                                            <td>${cus.getName()}</td>
                                            <td>${cus.getPhone()}</td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </tbody>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </body>

        </html>