<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <div class="modal fade" id="addEmployeeModal" tabindex="-1" role="dialog"
            aria-labelledby="addEmployeeModalTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addEmployeeModalTitle">
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

                        </h5>
                    </div>
                    <div class="modal-body">

                        <form action="employee" method="post">

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="addEmpName" name="addEmpName"
                                    placeholder="Name">
                                <label for="addEmpName">
                                    <fmt:message key="ad.name" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" id="addEmpAge" name="addEmpAge"
                                    placeholder="Age">
                                <label for="addEmpAge">
                                    <fmt:message key="ad.age" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="addEmpPhone" name="addEmpPhone"
                                    placeholder="Phone">
                                <label for="addEmpPhone">
                                    <fmt:message key="ad.phone" />
                                </label>
                            </div>

                            <button class="w-100 btn btn-lg btn-dark mb-3" name="actionAdd" type="submit" value="add">
                                <fmt:message key="but.add" />
                            </button>

                        </form>
                    </div>
                </div>
            </div>
        </div>