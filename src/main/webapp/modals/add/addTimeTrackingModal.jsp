<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <fmt:setLocale value="${sessionScope.lang}" />
        <fmt:setBundle basename="messages" />
        <div class="modal fade" id="addTimeTrackingModal" tabindex="-1" role="dialog"
            aria-labelledby="addTimeTrackingTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addTimeTrackingTitle">
                            <c:choose>
                                <c:when test="${'de'.equals(sessionScope.lang)}">
                                    <fmt:message key="navbar.timeTracking" />
                                    <fmt:message key="ad.title" />

                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="ad.title" />
                                    <fmt:message key="navbar.timeTracking" />
                                </c:otherwise>
                            </c:choose>
                        </h5>
                    </div>
                    <div class="modal-body">

                        <form action="home" method="post">

                            <div class="form-floating mb-3">
                                <input class="form-control" type="text" value="${sessionScope.employeeName}"
                                    aria-label="${sessionScope.employeeName}" name="${sessionScope.employeeName}"
                                    disabled readonly>
                                <label for="${sessionScope.employeeName}">
                                    <fmt:message key="ad.employee" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <select class="form-select" aria-label="addProjectSelect" name="addProjectSelect">
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
                                <label for="addProjectSelect">
                                    <fmt:message key="ad.project" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <select class="form-select" aria-label="addTaskSelect" name="addTaskSelect">
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
                                <label for="addTaskSelect">
                                    <fmt:message key="ad.task" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <select class="form-select" aria-label="addStatusSelect" name="addStatusSelect">
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
                                <label for="addStatusSelect">
                                    <fmt:message key="ad.status" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="addComment" name="addComment"
                                    placeholder="comment">
                                <label for="comment">
                                    <fmt:message key="ad.comment" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" id="addWorkHours" name="addWorkHours"
                                    placeholder="Hours Worked" min="1" max="1000">
                                <label for="addWorkHours">
                                    <fmt:message key="ad.hours" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="date" class="form-control" id="addStartDate" name="addStartDate"
                                    placeholder="Start Date">
                                <label for="addStartDate">
                                    <fmt:message key="ad.start" />
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="date" class="form-control" id="addEndDate" name="addEndDate"
                                    placeholder="End Date">
                                <label for="addEndDate">
                                    <fmt:message key="ad.end" />
                                </label>
                            </div>

                            <button class="w-100 btn btn-lg btn-dark mb-3" type="submit" name="actionAdd" value="add">
                                <fmt:message key="but.add" />
                            </button>

                        </form>
                    </div>
                </div>
            </div>
        </div>