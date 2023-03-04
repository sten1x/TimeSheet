<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <div class="modal fade" id="editTimeTrackingModal" tabindex="-1" role="dialog"
            aria-labelledby="editTimeTrackingTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTimeTrackingTitle">
                            <c:choose>
                                <c:when test="${'de'.equals(sessionScope.lang)}">
                                    <fmt:message key="navbar.timeTracking" />
                                    <fmt:message key="ed.title" />

                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="ed.title" />
                                    <fmt:message key="navbar.timeTracking" />
                                </c:otherwise>
                            </c:choose>

                        </h5>
                    </div>
                    <div class="modal-body">

                        <form action="home" method="post">

                            <input type="hidden" id="editId" name="tEditId">

                            <div class="form-floating mb-3">
                                <input class="form-control" type="text" value="${sessionScope.employeeName}"
                                    aria-label="${sessionScope.employeeName}" name="${sessionScope.employeeName}"
                                    disabled readonly>
                                <label for="${sessionScope.employeeName}">
                                    <fmt:message key="ed.employee" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <select class="form-select" aria-label="editProjectSelect" name="editProjectSelect">
                                    <c:forEach items="${sessionScope.projectList}" var="p" varStatus="loop">
                                        <c:if test="${!p.getDisabled()}">
                                            <c:choose>
                                                <c:when test="${loop.index == 0}">
                                                    <option selected>${p.getId()}. ${p.getProjectName()}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option>${p.getId()}. ${p.getProjectName()}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <label for="editProjectSelect">
                                    <fmt:message key="ed.project" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <select class="form-select" aria-label="editTaskSelect" name="editTaskSelect">
                                    <c:forEach items="${sessionScope.taskList}" var="t" varStatus="loop">
                                        <c:if test="${!t.getDisabled()}">
                                            <c:choose>
                                                <c:when test="${loop.index == 0}">
                                                    <option selected>${t.getId()}. ${t.getType()}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option>${t.getId()}. ${t.getType()}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <label for="editTaskSelect">
                                    <fmt:message key="ed.task" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <select class="form-select" aria-label="editStatusSelect" name="editStatusSelect">
                                    <c:forEach items="${sessionScope.statusList}" var="s" varStatus="loop">
                                        <c:choose>
                                            <c:when test="${loop.index == 0}">
                                                <option selected>${s.getId()}. ${s.getStatus()}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>${s.getId()}. ${s.getStatus()}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                                <label for="editStatusSelect">
                                    <fmt:message key="ed.task" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="editComment" name="editComment"
                                    placeholder="comment">
                                <label for="editComment">
                                    <fmt:message key="ed.comment" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" id="editWorkHours" name="editWorkHours"
                                    placeholder="Hours Worked" min="1" max="1000">
                                <label for="editWorkHours">
                                    <fmt:message key="ed.hours" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="date" class="form-control" id="editStartDate" name="editStartDate"
                                    placeholder="Start Date">
                                <label for="editStartDate">
                                    <fmt:message key="ed.start" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="date" class="form-control" id="editEndDate" name="editEndDate"
                                    placeholder="End Date">
                                <label for="editEndDate">
                                    <fmt:message key="ed.end" />
                                </label>
                            </div>

                            <button class="w-100 btn btn-lg btn-dark mb-3" type="submit" name="actionEdit" value="edit">
                                <fmt:message key="but.edit" />
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>