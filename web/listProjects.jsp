<%@include file="includes/validateCookie.jsp" %>
<%@include file="includes/documentHead.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <h1>Project Management</h1>
        <div class="box color-light round-corners shadow padding clearfix">
            <c:if test="${fn:length(projects) gt 0}">
            <table>
                <thead>
                <tr>
                    <th>Project Id</th>
                    <th>Project Name</th>
                    <th>Project Version</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${projects}" var="project">
                        <tr>
                            <td><c:out value="${project.projectID}" /></td>
                            <td><c:out value="${project.projectName}" /></td>
                            <td><c:out value="${project.projectVersion}" /></td>
                            <td><a href="ProjectServlet?action=delete&projectId=<c:out value="${project.projectID}"/>">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </c:if>
            <c:if test="${fn:length(projects) lt 1}">
                <h2>You dont have any active projects... how about creating some?</h2>
            </c:if>
            <a id="createProject" class="button round-corners"><span class="icon add-project"></span>Create new project</a>
        </div>
    </div>
</div>
<div id="dialog" title="Create New Project"></div>
<script type="text/javascript">

    $.cookie("pid", "3");
    menuHighlight();

    $(document).ready(function(){

        $.ajax({
            url: "createProjectForm.jsp",
            beforeSend: function(){
                $("#ajax-loader").show();
            },
            success: function(data){
                $("#ajax-loader").hide();
                $("#dialog").html(data);
                inputTitles()
            }
        });

        $("#createProject").click(function () {
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
