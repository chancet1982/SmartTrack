<%@include file="includes/validateCookie.jsp" %>
<%@include file="includes/documentHead.jsp" %>

<body>

<div id="site-header">
    <div class="site-width clearfix">
        <div id="site-logo"></div>
        <div id="site-navigation" class="clearfix">
            <%@include file="includes/navigation.jsp" %>
        </div>
    </div>
</div>
<div id="site-content">
    <div class="site-width clearfix">
        <h1>Edit Bug Details</h1>
        <div class="box color-light round-corners shadow padding clearfix">
            <ul>
                <li>
                    <label>Bug ID</label>
                    <span><c:out value="${bug.bugID}"/></span>
                </li>
                <li>
                    <label>Project ID</label>
                    <span><c:out value="${bug.projectID}"/></span>
                </li>
                <li>
                    <label>Bug Category</label>
                    <span><c:out value="${bug.bugCategory}"/></span>
                </li>
                <li>
                    <label>Bug Title</label>
                    <span><c:out value="${bug.bugTitle}"/></span>
                </li>
                <li>
                    <label>Bug Description</label>
                    <span><c:out value="${bug.bugDescription}"/></span>
                </li>

                <li>
                    <label>Bug Status(TODO change to dropdown)</label>
                    <span><c:out value="${bug.bugStatus}"/></span>
                </li>

                <c:if test="${not empty bug.bugPriority}">
                <li>
                    <label>Priority</label>
                    <span><c:out value="${bug.bugPriority}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.reportedPriority}">
                <li>
                    <label>Reported Priority</label>
                    <span><c:out value="${bug.reportedPriority}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.bugURL}">
                <li>
                    <label>Bug URL</label>
                    <span><c:out value="${bug.bugURL}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.screenshotURL}">
                <li>
                    <label>Screenshot</label>
                    <span><c:out value="${bug.screenshotURL}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.bugPCInfo}">
                <li>
                    <label>Additional PC information</label>
                    <span><c:out value="${bug.bugPCInfo}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.bugErrorCode}">
                <li>
                    <label>Error Code</label>
                    <span><c:out value="${bug.bugErrorCode}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.textFromTo}">
                <li>
                    <label>Change text From To</label>
                    <span><c:out value="${bug.textFromTo}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.bugTimeStamp}">
                <li>
                    <label>Bug TimeStamp</label>
                    <span><c:out value="${bug.bugTimeStamp}"/></span>
                </li>
                </c:if>

                <li>
                    <label>steps To Recreate (TODO - make it nicer)</label>
                    <span><c:out value="${bug.stepsToRecreate}"/></span>
                </li>

                <li>
                    <label>Created Date</label>
                    <span><c:out value="${bug.created}"/></span>
                </li>
                <li>
                    <label>Modified Date</label>
                    <span><c:out value="${bug.modified}"/></span>
                </li>
                <li>
                    <label>Active ?</label>
                    <c:choose>
                        <c:when test="${bug.active}">
                            <span>Yes</span>
                        </c:when>
                        <c:otherwise>
                            <span>No</span>
                        </c:otherwise>
                    </c:choose>
                </li>

                <li>
                    <label>Comments</label>
                    <ul id="comments">
                    </ul>
                    <form action="CommentServlet" method="POST">
                        <input title="bug ID" class="hidden" name="bugID" value="<c:out value="${bug.bugID}"/>">
                        <input title="user ID" id="userID" class="hidden" name="userID" value="">
                        <input title="comment Content" class="" name="commentContent" value="">
                        <input type="submit" value="add">
                    </form>

                </li>
                <li>
                    <input type="submit" value="Submit">
                </li>
            </ul>

            <script type="text/javascript">
                $(document).ready(function(){
                    $("input#userID").val($.cookie("uid"));

                    $.ajax({
                        type: "GET",
                        url: "/CommentServlet?action=listComments&bugID=<c:out value="${bug.bugID}"/>",
                        dataType: "text",
                        async: false,
                        success: function(data) {
                            $("#comments").append(data);
                        },
                        error: function () {
                            $("#site-footer .message p").remove();
                            $("#site-footer .message").prepend('<p>Error: Cannot get Comments, are you sure these exist?</p>').addClass("error").show();
                        }
                    });
                });
            </script>
        </div>
    </div>
</div>

<%@include file="includes/footerMessage.jsp" %>
</body>
</html>
