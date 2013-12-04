<%@include file="includes/documentHead.jsp" %>

<body>
<div id="site-header">
    <div class="site-width clearfix">
        <div id="site-logo"></div>
        <div id="site-navigation" class="clearfix">
        </div>
    </div>
</div>
<div id="site-content">
    <div class="site-width clearfix">
        <h1>Login to our system</h1>
        <div class="box color-light round-corners shadow padding">
            <div class="items-row cols-2 clearfix">
                <div class="item column-1">
                    <h2>Already have an Account?</h2>
                    <form method="POST" action="Login">
                        <ul>
                            <li><input type="text" name="userEmail" title="E-mail" class="required"></li>
                            <li><input type="password" name="userPassword" title="Password" class="required"></li>
                            <li><input type="submit" value="Login"></li>
                        </ul>
                    </form>
                </div>
                <div class="item column-2">
                    <h2>Don't have an Account?</h2>
                    <a id="createUser" class="button round-corners"><span class="icon add-user"></span>Create new user</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dialog" title="Create New User"></div>
<script type="text/javascript">
    $(document).ready(function(){

        $.ajax({
            url: "createUserForm.jsp",
            success: function(data){
                $("#dialog").html(data);
                inputTitles()
            }
        });

        $("#createUser").click(function () {
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