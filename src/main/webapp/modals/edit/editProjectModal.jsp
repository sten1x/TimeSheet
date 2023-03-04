<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <div class="modal fade" id="editProjectModal" tabindex="-1" role="dialog"
            aria-labelledby="editProjectModalTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editProjectModalTitle">
                            <c:choose>
                                <c:when test="${'de'.equals(sessionScope.lang)}">
                                    <fmt:message key="navbar.project" />
                                    <fmt:message key="ed.title" />

                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="ed.title" />
                                    <fmt:message key="navbar.project" />
                                </c:otherwise>
                            </c:choose>

                        </h5>
                    </div>
                    <div class="modal-body">

                        <form action="project" method="post">

                            <input type="hidden" id="proId" name="proEditId">

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="editProName" name="editProName"
                                    placeholder="Edit Name">
                                <label for="editProName">
                                    <fmt:message key="ed.name" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="editProOwner" name="editProOwner"
                                    placeholder="Add project owner">
                                <label for="editProOwner">
                                    <fmt:message key="ed.owner" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <select class="form-select" aria-label="customerSelect" name="editCustomerSelect">
                                    <c:forEach items="${sessionScope.customerList}" var="cus" varStatus="loop">
                                        <c:if test="${!cus.getDisabled()}">
                                            <c:choose>
                                                <c:when test="${loop.index == 0}">
                                                    <option selected>${cus.getId()}. ${cus.getName()}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option>${cus.getId()}. ${cus.getName()}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <label for="customerSelect">
                                    <fmt:message key="ed.customer" />
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