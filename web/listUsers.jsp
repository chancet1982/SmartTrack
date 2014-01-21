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
                    <th>Reporter</th>
                    <th>Handler</th>
                    <th>Manager</th>
                    <th></th>
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
                        <td>
                            <c:choose>
                                <c:when test="${user.reporter}">
                                    <span userID="<c:out value="${user.userid}" />" id="isReporter" class="icon role valid"></span>
                                </c:when>
                                <c:otherwise>
                                    <span userID="<c:out value="${user.userid}" />" id="isReporter" class="icon role invalid"></span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${user.handler}">
                                    <span userID="<c:out value="${user.userid}" />" id="isHandler" class="icon role valid"></span>
                                </c:when>
                                <c:otherwise>
                                    <span userID="<c:out value="${user.userid}" />" id="isHandler" class="icon role invalid"></span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${user.manager}">
                                    <span userID="<c:out value="${user.userid}" />" id="isManager" class="icon role valid"></span>
                                </c:when>
                                <c:otherwise>
                                    <span userID="<c:out value="${user.userid}" />" id="isManager" class="icon role invalid"></span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><a href="UserServlet?action=edit&userID=<c:out value="${user.userid}"/>">Edit</a></td>
                        <td><a href="UserServlet?action=delete&userID=<c:out value="${user.userid}"/>">Delete</a></td>
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

        $.cookie("pid", "2");
        menuHighlight();

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

        $('span#isReporter').on('click', function (e) {
            var isReporter = false;
            var userID = $(this).attr('userID');

            if ($(this).hasClass('valid')) {
                $(this).removeClass('valid').addClass('invalid');
                isReporter = false;
            } else {
                $(this).removeClass('invalid').addClass('valid');
                isReporter = true;
            }

            $.ajax({
                type: "GET",
                url: "/UserServlet?action=changeReporter&userID="+userID+"&isReporter="+isReporter,
                beforeSend: function(){
                    $("#ajax-loader").show();
                },
                success: function(data) {
                    $("#ajax-loader").hide();
                    $("#site-footer .message p").remove();
                    $("#site-footer .message").prepend('<p>User role changed</p>').addClass("success").show();
                },
                error: function () {
                    $("#ajax-loader").hide();
                    $("#site-footer .message p").remove();
                    $("#site-footer .message").prepend('<p>Error: Cannot change user role</p>').addClass("error").show();
                }
            });
        });

        $('span#isHandler').on('click', function (e) {
            var isHandler = false;
            var userID = $(this).attr('userID');

            if ($(this).hasClass('valid')) {
                $(this).removeClass('valid').addClass('invalid');
                isHandler = false;
            } else {
                $(this).removeClass('invalid').addClass('valid');
                isHandler = true;
            }

            $.ajax({
                type: "GET",
                url: "/UserServlet?action=changeHandler&userID="+userID+"&isHandler="+isHandler,
                beforeSend: function(){
                    $("#ajax-loader").show();
                },
                success: function(data) {
                    $("#ajax-loader").hide();
                    $("#site-footer .message p").remove();
                    $("#site-footer .message").prepend('<p>User role changed</p>').addClass("success").show();
                },
                error: function () {
                    $("#ajax-loader").hide();
                    $("#site-footer .message p").remove();
                    $("#site-footer .message").prepend('<p>Error: Cannot change user role</p>').addClass("error").show();
                }
            });
        });

        $('span#isManager').on('click', function (e) {
            var isManager = false;
            var userID = $(this).attr('userID');

            if ($(this).hasClass('valid')) {
                $(this).removeClass('valid').addClass('invalid');
                isManager = false;
            } else {
                $(this).removeClass('invalid').addClass('valid');
                isManager = true;
            }

            $.ajax({
                type: "GET",
                url: "/UserServlet?action=changeManager&userID="+userID+"&isManager="+isManager,
                beforeSend: function(){
                    $("#ajax-loader").show();
                },
                success: function(data) {
                    $("#ajax-loader").hide();
                    $("#site-footer .message p").remove();
                    $("#site-footer .message").prepend('<p>User role changed</p>').addClass("success").show();
                },
                error: function () {
                    $("#ajax-loader").hide();
                    $("#site-footer .message p").remove();
                    $("#site-footer .message").prepend('<p>Error: Cannot change user role</p>').addClass("error").show();
                }
            });
        });
    });
</script>
<%@include file="includes/footerMessage.jsp" %>
</body>
</html>
