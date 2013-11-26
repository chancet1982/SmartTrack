<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="includes/documentHead.jsp" %>

<body>
<style>
    .project {display:block;}
    .project span{ display:block; float:left; }
    .project .project-name {background:#ffffff;  width:10%; }
    .project .project-users {background:#f0f0f0; width:90%; height:76px; }
    .project .project-users ul { height:100%; }
    .project .project-users ul li { margin: 3px 3px 3px 0; padding: 1px; float: left;  }

    #site-content li.user {
        display: inline-block;
        width: 64px;
        line-height: 64px;
        height: 64px;
        cursor: pointer;
        position: relative;
    }
    #site-content li.user .icon {
        margin-top: 24px;
        margin-right: 0px;
        margin-left: 8px;
    }
    #site-content li.user { background: #a6e866; }
</style>
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
        <h1>Assign users to projects</h1>
        <div class="box color-light round-corners shadow padding" style="padding:8px;">
            <h2>users</h2>
                <ul class="users">

                    <c:forEach items="${users}" var="user">
                        <li class="user ico" data-user-id="${user.userid}" >
                            <span class="icon user"></span><c:out value="${user.firstname}" />
                        </li>
                    </c:forEach>
                </ul>
            <h2>Projects</h2>
                <ul class="projects">
                    <c:forEach items="${projects}" var="project">
                        <li class="project clearfix" data-project-id='<c:out value="${project.projectID}"/>' >
                            <span class="project-name"><c:out value="${project.projectName}" /></span>
                            <span class="project-users">
                                <ul></ul>
                            </span>
                        </li>
                    </c:forEach>
                </ul>
        </div>
    </div>
</div>
<script type="text/javascript">

    $(document).ready(function(){

        $("ul.projects li.project").each(function(){
            projectID = $(this).attr("data-project-id");
            this2 = $(this);


            $.ajax({
                type: "GET",
                url: "/ProjectServlet?action=getAssigned",
                data:{ "projectID" : projectID },
                dataType: "text",
                async: false,
                success: function(data) {
                    this2.find(".project-users ul").append(data);
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.status);
                    console.log(thrownError);
                }
            });
        });

    });

    draggable= ".users li";
    sortable = ".projects .project-users ul";

    $(draggable).draggable({
        helper: "clone",
        revert: false,
        connectToSortable: $(sortable)
    });

    $(sortable).sortable({
        revert: "100",
       // cancel: "li",
        helper: "original",
        connectWith: '.delete-sortable',
        over: function () {
            removeIntent = false;
        },
        out: function () {
            removeIntent = true;
        },
        beforeStop: function (event, ui) {
            if(removeIntent == true){
                ui.item.remove();
            }
            projectID = $(this).parent().parent().attr("data-project-id");
            assignedUsers = "";
            $(this).find("li").each(function(){
                thisUserId = $(this).attr("data-user-id");
                assignedUsers = assignedUsers + ":" +thisUserId;
            });

            $.ajax({
                type: "GET",
                url: "/ProjectServlet?action=setAssigned",
                data:{ "projectID" : projectID , "assignedUsers" : assignedUsers },
                success: function(data) {},
                error: function (xhr, ajaxOptions, thrownError) {console.log(xhr.status+ " " +thrownError);}
            });
        },

        update: function( event, ui ) {


        },

        receive: function( event, ui ) {
            removeDuplicateUsers($(this));

            projectID = $(this).parent().parent().attr("data-project-id");
            assignedUsers = "";
            $(this).find("li").each(function(){
                thisUserId = $(this).attr("data-user-id");
                assignedUsers = assignedUsers + ":" +thisUserId;
            });

            $.ajax({
                type: "GET",
                url: "/ProjectServlet?action=setAssigned",
                data:{ "projectID" : projectID , "assignedUsers" : assignedUsers },
                success: function(data) {},
                error: function (xhr, ajaxOptions, thrownError) {console.log(xhr.status+ " " +thrownError);}
            });

        }
    });

    function removeDuplicateUsers(obj){
        var seen = {};
        obj.find("li").each(function(){
            id = $(this).attr("data-user-id");
            if (seen[id]){$(this).remove();}else{seen[id] = true;}
        });
    }

</script>
</body>
</html>