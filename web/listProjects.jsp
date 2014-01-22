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
                <div id="projectPipeline"> </div>

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
                                <td>
                                    <a class="button" href="BugServlet?action=listBugsForProject&projectID=<c:out value="${project.projectID}"/>">List Bugs</a>
                                    <% if(isManager == true){%>
                                    <a class="button" href="ProjectServlet?action=delete&projectID=<c:out value="${project.projectID}"/>">Delete</a>
                                    <% } %>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${fn:length(projects) lt 1}">
                <h2>You dont have any active projects... how about creating some?</h2>
            </c:if>
            <% if(isManager == true){%>
            <a id="createProject" class="button round-corners"><span class="icon add-project"></span>Create new project</a>
            <%}%>
        </div>
    </div>
</div>
<div id="dialog" title="Create New Project"></div>
<script type="text/javascript" src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1','packages':['timeline']}]}"></script>
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


    google.setOnLoadCallback(drawChart);

    function drawChart() {
        var container = document.getElementById('projectPipeline');

        var chart = new google.visualization.Timeline(container);

        var dataTable = new google.visualization.DataTable();

        dataTable.addColumn({ type: 'string', id: 'projectName' });
        dataTable.addColumn({ type: 'date', id: 'startDate' });
        dataTable.addColumn({ type: 'date', id: 'endDate' });

        <c:forEach items="${projects}" var="project">
            var startDateFromJSTL = "${project.startDate}";
            var endDateFromJSTL = "${project.endDate}";

            var startDateMonth = startDateFromJSTL.substring(0,2);
            var startDateDay = startDateFromJSTL.substring(3,5);
            var startDateYear = startDateFromJSTL.substring(6,10);

            var endDateMonth = endDateFromJSTL.substring(0,2);
            var endDateDay = endDateFromJSTL.substring(3,5);
            var endDateYear = endDateFromJSTL.substring(6,10);

            dataTable.addRow(['${project.projectName}  ${project.projectVersion}', new Date(startDateYear, startDateMonth, startDateDay),new Date(endDateYear, endDateMonth, endDateDay)]);
        </c:forEach>

        var options = {
            backgroundColor: '#d0dadf'
        };

        chart.draw(dataTable);
    }
</script>
<%@include file="includes/footerMessage.jsp" %>
</body>
</html>
