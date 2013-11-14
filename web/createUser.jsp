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
                        <input type="text" name="userEmail" class="required unique">
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
                        <select id="selectCompany" name="companyInfo" class="unique">
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
    $(".unique").on("keyup" , function(){
        email = $(this).val();
        $.ajax({
            type: "GET",
            url: "/UserServlet?action=verifyUnique",
            data:{ "email" : email } ,
            dataType: "json",
            success: function(data) {
                console.log(data.isUnique);

//                if(data=="false"){
//                    console.log("data is false");
//                }else{
//                    console.log("data is true");
//                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            }
        });
    });
});

    //create dialog
    $( "#addCompanyDialog" ).dialog({
        autoOpen: false,
        modal: true,
        draggable: false
    });

    //open dialog
    $("#addCompany").click(function() {
        $( "#addCompanyDialog" ).dialog( "open" );
    });

    //press OK in the dialog (verify if )
    $("#okDialogCompany").click(function() {
        var unique = true;
        for( i=0 ; i< $('#selectCompany option').length ; i++){
            if( $('#selectCompany').val() == $("#addCompanyName").val() && $('#selectCompany option').length > 0){
                unique = false;}else{
                unique = true;}
        }
        if(unique == true){
            $( "#addCompanyDialog" ).dialog( "close" );
            console.log("in the add dialg");
            newCompanyName = $("#addCompanyName").val();
            $("#selectCompany").append("<option>"+ newCompanyName +"</option>")
            $("#selectCompany option:last").attr("selected","selected");
        }else{
            console.log("not unique");
        }
    });
</script>
</body>
</html>