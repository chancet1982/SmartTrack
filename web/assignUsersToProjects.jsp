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
                        <li class="project clearfix">
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
    draggable= ".users li";
    sortable = ".projects .project-users ul";

    $(draggable).draggable({
        helper: "clone",
        revert: false,
        connectToSortable: $(sortable)
    });

    $(sortable).sortable({
        revert: "100",
        cancel: "li",
        helper: "original",
        receive: function( event, ui ) {
            removeDuplicateUsers($(this));
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