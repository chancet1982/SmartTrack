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
        <h1>Just fill in your name and password and you should be ready to go.</h1>
        <div class="box color-light round-corners shadow padding">
            <form action="UserServlet" method="POST">
                <ul>
                    <li>
                        <label>Type in your company name</label>
                        <input type="text" name="companyName">
                    </li>
                    <li>
                        <label>Your E-mail address</label>
                        <input type="text" name="userEmail">
                    </li>
                    <li>
                        <label>First name</label>
                        <input type="text" name="firstName" class="required">
                    </li>
                    <li>
                        <label>Last name</label>
                        <input type="text" name="lastName" class="required">
                    </li>
                    <li>
                        <label>Type in password</label>
                        <input type="password" name="userPassword" class="required password">
                    </li>
                    <li>
                        <label>Repeat password</label>
                        <input type="password" name="password2" class="required repeat-password">
                    </li>
                    <li>
                        <input type="submit" value="Submit">
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>

<%@include file="includes/footerMessage.jsp" %>

<script type="text/javascript">
    $(document).ready(function(){

        $.urlParam = function(name){
            var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href);
            return results[1] || 0;
        }

        $( "input[name='companyName']" ).val($.urlParam('companyName')).attr('readonly','readonly');
        $( "input[name='userEmail']" ).val($.urlParam('userEmail')).attr('readonly','readonly');
    });

</script>
</body>
</html>