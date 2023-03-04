<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <div class="modal fade" id="editTaskModal" tabindex="-1" role="dialog" aria-labelledby="editTaskModalTitle"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTaskModalTitle">
                            <c:choose>
                                <c:when test="${'de'.equals(sessionScope.lang)}">
                                    <fmt:message key="navbar.task" />
                                    <fmt:message key="ed.title" />
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="ed.title" />
                                    <fmt:message key="navbar.task" />
                                </c:otherwise>
                            </c:choose>

                        </h5>
                    </div>
                    <div class="modal-body">

                        <form action="task" method="post">

                            <input type="hidden" id="taskId" name="taskEditId">

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="editTask" name="editTask"
                                    placeholder="Type">
                                <label for="editTask">
                                    <fmt:message key="ed.type" />
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