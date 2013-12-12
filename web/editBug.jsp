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
                    <form action="CommentServlet" method="POST">
                        <input title="bug ID" class="hidden" name="bugID" value="<c:out value="${bug.bugID}"/>">
                        <input title="user ID" id="userID" class="hidden" name="userID" value="">
                        <input title="comment Content" class="" name="commentContent" value="">
                        <input type="submit" value="Create">
                        <a id="createComment" class="button round-corners"><span class="icon add-comment"></span>Add Comment</a>
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
