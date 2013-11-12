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
        <h1>Create new user</h1>
        <div class="box color-light round-corners shadow padding">
            <form action="UserServlet" method="POST">
                <ul>
                    <li>
                        <label>First name</label>
                        <input type="text" name="firstName" class="required">
                    </li>
                    <li>
                        <label>Last name</label>
                        <input type="text" name="lastName" class="required">
                    </li>
                    <li>
                        <label>Type in E-mail address</label>
                        <input type="text" name="userEmail" class="required">
                    </li>
                    <li>
                        <label>Type in password</label>
                        <input type="password" name="userPassword" class="required">
                    </li>
                    <li>
                        <label>Repeat password</label>
                        <input type="password" name="password2" class="required">
                    </li>
                    <li>
                        <select id="selectCompany" name="companyName">
                        </select>
                        <button id="addCompany" type="button">add company</button>
                        <div id="addCompanyDialog"  title="Input new company name">
                            <input id="addCompanyName" >
                            <button id="okDialogCompany" type="button">OK</button>
                        </div>
                    </li>
                    <li>
                        <input type="submit" value="Submit">
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
//    $("#selectCompany").one(function(){
        $.ajax({
            type: "POST",
            url: "/CompanyServlet",
            success: function(data) {
                $('#selectCompany').show();
                $('#selectCompany').append(data);
                console.log("ajax");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            }
        });

//    });
    });
    $( "#addCompanyDialog" ).dialog({
        autoOpen: false
//        buttons: {
//            "OK": function() {
//                $( this ).dialog( "close" );
//                $( this ).click(function(){
//                    console.log("in the add dialg");
//                    newCompanyName = addCompanyName.val();
//                    $("#selectCompany").append("<option>"+ newCompanyName +"<option>");
//                });
//            }
        //}
    });
    $("#addCompany").click(function() {
        $( "#addCompanyDialog" ).dialog( "open" );
    });
    $("#okDialogCompany").click(function() {
        $( "#addCompanyDialog" ).dialog( "close" );
            console.log("in the add dialg");
            newCompanyName = $("#addCompanyName").val();
            $("#selectCompany").append("<option>"+ newCompanyName +"</option>")
    });
</script>
</body>
</html>