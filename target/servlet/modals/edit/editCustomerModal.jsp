<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <div class="modal fade" id="editCustomerModal" tabindex="-1" role="dialog"
            aria-labelledby="editCustomerModalTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editCustomerModalTitle">
                            <c:choose>
                                <c:when test="${'de'.equals(sessionScope.lang)}">
                                    <fmt:message key="navbar.customer" />
                                    <fmt:message key="ed.title" />

                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="ed.title" />
                                    <fmt:message key="navbar.customer" />
                                </c:otherwise>
                            </c:choose>

                        </h5>
                    </div>
                    <div class="modal-body">

                        <form action="customer" method="post">

                            <input type="hidden" id="cusId" name="cusEditId">

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="editCusName" name="editCusName"
                                    placeholder="Name">
                                <label for="editCusName">
                                    <fmt:message key="ed.name" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="editCusPhone" name="editCusPhone"
                                    placeholder="Phone">
                                <label for="editCusPhone">
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