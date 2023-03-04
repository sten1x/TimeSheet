<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <div class="modal fade" id="empEditModal" tabindex="-1" role="dialog" aria-labelledby="empEditModalTitle"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="empEditModalTitle">
                            <c:choose>
                                <c:when test="${'de'.equals(sessionScope.lang)}">
                                    <fmt:message key="navbar.employee" />
                                    <fmt:message key="ed.title" />

                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="ed.title" />
                                    <fmt:message key="navbar.employee" />
                                </c:otherwise>
                            </c:choose>

                        </h5>
                    </div>
                    <div class="modal-body">

                        <form action="employee" method="post">

                            <input type="hidden" id="editId" name="empEditId">

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="editEmpName" name="editEmpName"
                                    placeholder="Name">
                                <label for="editEmpName">
                                    <fmt:message key="ed.name" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" id="editEmpAge" name="editEmpAge"
                                    placeholder="Age">
                                <label for="editEmpAge">
                                    <fmt:message key="ed.age" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="editEmpPhone" name="editEmpPhone"
                                    placeholder="Phone">
                                <label for="editEmpPhone">
                                    <fmt:message key="ed.phone" />
                                </label>
                            </div>

                            <button class="w-100 btn btn-lg btn-dark mb-3" name="actionEdit" type="submit" value="edit">
                                <fmt:message key="but.edit" />
                            </button>

                        </form>
                    </div>
                </div>
            </div>
        </div>