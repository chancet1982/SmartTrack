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
        <div id="viewBugForm" class="box color-light round-corners shadow padding clearfix">
            <ul>
                <li id="1" class="clearfix">
                    <p class="small-text no-padding">
                        <span class="title">ID: </span><c:out value="${bug.bugID}"/>
                        <span class="title">Project ID: </span><c:out value="${bug.projectID}"/>
                    </p>
                    <p class="small-text no-padding"><span class="title">Created: </span><c:out value="${bug.created}"/></p>
                    <p class="small-text no-padding"><span class="title">Modified: </span><c:out value="${bug.modified}"/></p>
                </li>
                <li id="4" class="clearfix">
                    <label>Title: </label>
                    <span><c:out value="${bug.bugTitle}"/></span>
                </li>
                <li id="5" class="clearfix">
                    <label>Description: </label>
                    <p class="no-padding"><c:out value="${bug.bugDescription}"/></p>
                </li>
                <li id="3" class="clearfix">
                    <span><span class="title">Category: </span><c:out value="${bug.bugCategory}"/></span>
                </li>
                <li id="6" class="clearfix">
                    <span class="title">Status: </span>
                    <select id="bugStatus" name="bugStatus">
                        <c:choose>
                            <c:when test="${bug.bugStatus eq 'unassigned'}">
                                <option value="unassigned" selected="selected">Unassigned</option>
                            </c:when>
                            <c:otherwise>
                                <option value="unassigned" >Unassigned</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${bug.bugStatus eq 'pending'}">
                                <option value="pending" selected="selected">Pending</option>
                            </c:when>
                            <c:otherwise>
                                <option value="pending" >Pending</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${bug.bugStatus eq 'inProgress'}">
                                <option value="inProgress" selected="selected">In-Progress</option>
                            </c:when>
                            <c:otherwise>
                                <option value="inProgress" >In-Progress</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${bug.bugStatus eq 'readyForTest'}">
                                <option value="readyForTest" selected="selected">Ready for Test</option>
                            </c:when>
                            <c:otherwise>
                                <option value="readyForTest" >Ready for Test</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${bug.bugStatus eq 'Resolved'}">
                                <option value="Resolved" selected="selected">Resolved</option>
                            </c:when>
                            <c:otherwise>
                                <option value="Resolved" >Resolved</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </li>
                <% if(isManager == true){%>
                <li id="7" class="clearfix">
                    <span class="title">Priority: </span>
                    <select id="bugPriority" name="bugPriority">
                    <c:choose>
                        <c:when test="${bug.bugPriority eq 'Minor'}">
                            <option value="no-value">unprioritized</option>
                            <option value="Minor" selected="selected">Minor</option>
                            <option value="Average">Average</option>
                            <option value="Major">Major</option>
                            <option value="Blocker">Blocker</option>
                        </c:when>
                        <c:when test="${bug.bugPriority eq 'Average'}">
                            <option value="no-value">unprioritized</option>
                            <option value="Minor">Minor</option>
                            <option value="Average" selected="selected">Average</option>
                            <option value="Major">Major</option>
                            <option value="Blocker">Blocker</option>
                        </c:when>
                        <c:when test="${bug.bugPriority eq 'Major'}">
                            <option value="no-value">unprioritized</option>
                            <option value="Minor">Minor</option>
                            <option value="Average">Average</option>
                            <option value="Major" selected="selected">Major</option>
                            <option value="Blocker">Blocker</option>
                        </c:when>
                        <c:when test="${bug.bugPriority eq 'Blocker'}">
                            <option value="no-value">unprioritized</option>
                            <option value="Minor">Minor</option>
                            <option value="Average">Average</option>
                            <option value="Major">Major</option>
                            <option value="Blocker" selected="selected">Blocker</option>
                        </c:when>
                        <c:otherwise>
                            <option value="no-value" selected="selected">unprioritized</option>
                            <option value="Minor">Minor</option>
                            <option value="Average">Average</option>
                            <option value="Major">Major</option>
                            <option value="Blocker">Blocker</option>
                        </c:otherwise>
                    </c:choose>
                    </select>
                </li>
                <%} else {%>
                <li id="7" class="clearfix">
                    <span><c:out value="${bug.bugPriority}"/></span>
                </li>
                <%}%>
                <c:if test="${not empty bug.reportedPriority}">
                <li id="8" class="clearfix">
                    <span><span class="title">Reported Priority: </span><c:out value="${bug.reportedPriority}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.bugURL}">
                <li id="9" class="clearfix">
                    <span><span class="title">URL: </span><c:out value="${bug.bugURL}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.screenshotURL}">
                <li id="10" class="clearfix">
                    <label>Screenshot: </label>
                    <span><c:out value="${bug.screenshotURL}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.bugPCInfo}">
                <li id="11" class="clearfix">
                    <span><span class="title">PC information: </span><c:out value="${bug.bugPCInfo}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.bugErrorCode}">
                <li id="12" class="clearfix">
                    <span><span class="title">Error Code: </span><c:out value="${bug.bugErrorCode}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.textFromTo}">
                <li id="13" class="clearfix">
                    <label>Change text From To: (Make it nicer)</label>
                    <span><c:out value="${bug.textFromTo}"/></span>
                </li>
                </c:if>

                <c:if test="${not empty bug.bugTimeStamp}">
                <li id="13" class="clearfix">
                    <span><span class="title">TimeStamp: </span><c:out value="${bug.bugTimeStamp}"/></span>
                </li>
                </c:if>

                <li id="14" class="clearfix">
                    <label>steps To Recreate: </label>

                    <ul id="stepsToRecreate">
                    <script type="text/javascript">
                        var JSTLString = "<c:out value="${bug.stepsToRecreate}"/>";
                        var strings = JSTLString.split("~");
                        for (i = 1; i < strings.length; ++i) {
                            $("ul#stepsToRecreate").append("<li>" + strings[i] + "</li>");
                            console.log(strings[i]);
                        }
                    </script>
                    </ul>


                </li>

                <li id="16" class="clearfix">
                    <span>Active: </span>
                    <c:choose>
                        <c:when test="${bug.active}">
                            <span>Yes</span>
                            <a class="button" id="closeBug" href="#">Close Bug</a>
                        </c:when>
                        <c:otherwise>
                            <span>No</span>
                            <a class="button" id="openBug" href="#">Re-open Bug</a>
                        </c:otherwise>
                    </c:choose>
                </li>

                <li id="17" class="clearfix">
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

                    $('select#bugStatus').on('change', function (e) {
                        var selectedOption = $("option:selected", this);
                        var bugStatus = this.value;

                        $.ajax({
                            type: "GET",
                            url: "/BugServlet?action=changeBugStatus&bugID=<c:out value="${bug.bugID}"/>&bugStatus="+ bugStatus +"",
                            beforeSend: function(){
                                $("#ajax-loader").show();
                            },
                            success: function(data) {
                                $("#ajax-loader").hide();
                                $('select#bugStatus').removeAttr('selected').val(bugStatus);
                                $("#site-footer .message p").remove();
                                $("#site-footer .message").prepend('<p>Success: Bug status changed</p>').addClass("success").show();
                            },
                            error: function () {
                                $("#ajax-loader").hide();
                                $("#site-footer .message p").remove();
                                $("#site-footer .message").prepend('<p>Error: Cannot change bug status</p>').addClass("error").show();
                            }
                        });
                    });

                    $('select#bugPriority').on('change', function (e) {
                        var selectedOption = $("option:selected", this);
                        var bugPriority = this.value;

                        $.ajax({
                            type: "GET",
                            url: "/BugServlet?action=changeBugPriority&bugID=<c:out value="${bug.bugID}"/>&bugPriority="+ bugPriority +"",
                            beforeSend: function(){
                                $("#ajax-loader").show();
                            },
                            success: function(data) {
                                $("#ajax-loader").hide();
                                $('select#bugPriority').removeAttr('selected').val(bugPriority);
                                $("#site-footer .message p").remove();
                                $("#site-footer .message").prepend('<p>Success: Bug priority changed</p>').addClass("success").show();
                            },
                            error: function () {
                                $("#ajax-loader").hide();
                                $("#site-footer .message p").remove();
                                $("#site-footer .message").prepend('<p>Error: Cannot change bug priority</p>').addClass("error").show();
                            }
                        });
                    });

                    $('a#closeBug').click(function () {
                        $.ajax({
                            type: "GET",
                            url: "/BugServlet?action=close&bugID=<c:out value="${bug.bugID}"/>",
                            beforeSend: function(){
                                $("#ajax-loader").show();
                            },
                            success: function(data) {
                                $("#ajax-loader").hide();
                                $('select#bugPriority').removeAttr('selected').val(bugPriority);
                                $("#site-footer .message p").remove();
                                $("#site-footer .message").prepend('<p>Bug closed</p>').addClass("success").show();
                            },
                            error: function () {
                                $("#ajax-loader").hide();
                                $("#site-footer .message p").remove();
                                $("#site-footer .message").prepend('<p>Error: Bug cannot be closed</p>').addClass("error").show();
                            }
                        });
                    });

                    $('a#openBug').click(function () {
                        $.ajax({
                            type: "GET",
                            url: "/BugServlet?action=open&bugID=<c:out value="${bug.bugID}"/>",
                            beforeSend: function(){
                                $("#ajax-loader").show();
                            },
                            success: function(data) {
                                $("#ajax-loader").hide();
                                $('select#bugPriority').removeAttr('selected').val(bugPriority);
                                $("#site-footer .message p").remove();
                                $("#site-footer .message").prepend('<p>Bug Re-opened</p>').addClass("success").show();
                            },
                            error: function () {
                                $("#ajax-loader").hide();
                                $("#site-footer .message p").remove();
                                $("#site-footer .message").prepend('<p>Error: Bug cannot be re-opened</p>').addClass("error").show();
                            }
                        });
                    });

                });
            </script>
        </div>
    </div>
</div>

<%@include file="includes/footerMessage.jsp" %>
</body>
</html>
