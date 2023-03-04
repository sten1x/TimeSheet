<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <div class="modal fade" id="addProjectModal" tabindex="-1" role="dialog" aria-labelledby="addProjectModalTitle"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addProjectModalTitle">
                            <c:choose>
                                <c:when test="${'de'.equals(sessionScope.lang)}">
                                    <fmt:message key="navbar.project" />
                                    <fmt:message key="ad.title" />

                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="ad.title" />
                                    <fmt:message key="navbar.project" />
                                </c:otherwise>
                            </c:choose>

                        </h5>
                    </div>
                    <div class="modal-body">

                        <form action="project" method="post">

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="addProName" name="addProName"
                                    placeholder="Name">
                                <label for="addProName">
                                    <fmt:message key="ad.name" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="addProOwner" name="addProOwner"
                                    placeholder="Project owner">
                                <label for="addProOwner">
                                    <fmt:message key="ad.owner" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <select class="form-select" aria-label="customerSelect" name="addCustomerSelect">
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
                                <label for="addCustomerSelect">
                                    <fmt:message key="ad.customer" />
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