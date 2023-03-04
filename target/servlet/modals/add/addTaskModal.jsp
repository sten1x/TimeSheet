<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <div class="modal fade" id="addTaskModal" tabindex="-1" role="dialog" aria-labelledby="addTaskModalTitle"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addTaskModalTitle">
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

                        </h5>
                    </div>
                    <div class="modal-body">

                        <form action="task" method="post">

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="addTask" name="addTask" placeholder="Type">
                                <label for="addTask">
                                    <fmt:message key="ad.type" />
                                </label>
                            </div>

                            <button class="w-100 btn btn-lg btn-dark mb-3" name="actionEdit" type="submit" value="add">
                                <fmt:message key="but.add" />
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>