<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <div class="modal fade" id="addCustomerModal" tabindex="-1" role="dialog"
            aria-labelledby="addCustomerModalTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addCustomerModalTitle">
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

                        </h5>
                    </div>
                    <div class="modal-body">

                        <form action="customer" method="post">

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="addCusName" name="addCusName"
                                    placeholder="Name">
                                <label for="addCusName">
                                    <fmt:message key="ad.name" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="addCusPhone" name="addCusPhone"
                                    placeholder="Phone">
                                <label for="addCusPhone">
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