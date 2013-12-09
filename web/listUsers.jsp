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
        <h1>User Management</h1>
        <div class="box color-light round-corners shadow padding clearfix">
            <table>
                <thead>
                <tr>
                    <th>User Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><c:out value="${user.userid}" /></td>
                        <td><c:out value="${user.firstname}" /></td>
                        <td><c:out value="${user.lastname}" /></td>
                        <td><c:out value="${user.useremail}" /></td>
                        <td><a href="UserServlet?action=edit&userID=<c:out value="${user.userID}"/>">Edit</a></td>
                        <td><a href="UserServlet?action=delete&userID=<c:out value="${user.userID}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a id="inviteUser" class="button round-corners"><span class="icon add-user"></span>Invite Users</a>
        </div>
    </div>
</div>
<div id="dialog" title="invite Users"></div>
<script type="text/javascript">
    $(document).ready(function(){

        $.ajax({
            url: "inviteUsersForm.jsp",
            beforeSend: function(){
                $("#ajax-loader").show();
            },
            success: function(data){
                $("#ajax-loader").hide();
                $("#dialog").html(data);
                inputTitles()
            }
        });

        $("#inviteUser").click(function () {
            $("#dialog").dialog("open");
        });

        $("#dialog").dialog({
            bgiframe: true,
            autoOpen: false,
            width: (0.7*$( window ).width()),
            modal: true
        });
    });
</script>
<%@include file="includes/footerMessage.jsp" %>
</body>
</html>
