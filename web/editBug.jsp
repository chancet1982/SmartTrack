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
            <form action="UserServlet" method="POST">
                <input name="bugID" value="<c:out value="${bug.bugID}"/>" class="hidden" />
                <ul>
                    <li>
                        <label>Bug ID</label>
                        <input name="userEmail" value="<c:out value="${bug.bugID}"/>" class="required email" readonly="true" />
                    </li>
                    <li>
                        <label>Bug Title</label>
                        <input name="firstName" value="<c:out value="${bug.bugTitle}"/>" class="required"/>
                    </li>
                    <li>
                        <label>Bug description</label>
                        <input name="lastName" value="<c:out value="${bug.bugDescription}"/>" class="required" />
                    </li>
                    <li>
                        <label>Comments</label>
                        <ul id="comments">
                        </ul>
                        <a id="addComment" class="button round-corners"><span class="icon add-bug"></span>Add Comment</a>
                    </li>
                    <li>
                        <input type="submit" value="Submit">
                    </li>
                </ul>
            </form>

            <script type="text/javascript">

                $(document).ready(function(){
                    $.ajax({
                        type: "GET",
                        url: "/CommentServlet?action=getCommentsForBug&bugID=<c:out value="${bug.bugID}"/>",
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
