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
        <h1>Bugs...</h1>
        <div class="box color-light round-corners shadow padding clearfix">
            <c:if test="${fn:length(bugs) gt 0}">
                <table>
                    <thead>
                    <tr>
                        <th>Bug ID</th>
                        <th>P. ID</th>
                        <th>Bug Title</th>
                        <th>Bug Description</th>
                        <th>Bug Status</th>
                        <th>Is open</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${bugs}" var="bug">
                        <tr>
                            <td><c:out value="${bug.bugID}" /></td>
                            <td><c:out value="${bug.projectID}" /></td>
                            <td><c:out value="${bug.bugTitle}" /></td>
                            <td><c:out value="${bug.bugDescription}" /></td>
                            <td><c:out value="${bug.bugStatus}" /></td>
                            <td>
                                <c:choose>
                                    <c:when test="${bug.active}">Yes</c:when>
                                    <c:otherwise>No</c:otherwise>
                                </c:choose>
                            </td>
                            <td><a href="BugServlet?action=edit&bugID=<c:out value="${bug.bugID}"/>">Edit</a></td>
                            <td><a href="BugServlet?action=close&bugID=<c:out value="${bug.bugID}"/>">Close</a></td>
                            <td><a href="BugServlet?action=delete&bugID=<c:out value="${bug.bugID}"/>">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a id="listOpenBugs" class="button round-corners" href="BugServlet?action=listOpenBugsForProject"><span class="icon bug"></span>Show only Open Bugs</a>
            </c:if>
            <c:if test="${fn:length(bugs) lt 1}">
                <h2>There are no bugs in the system... would you like to report one?</h2>
            </c:if>
            <a id="createBug" class="button round-corners"><span class="icon add-bug"></span>Add Bugs</a>
        </div>
    </div>
</div>
<div id="dialog" title="add a new Bug"></div>
<script type="text/javascript">
    $(document).ready(function(){

        $("#dialog").dialog({
            bgiframe: true,
            autoOpen: false,
            width: (0.7*$( window ).width()),
            modal: true,
            height:(0.9*$( window ).height())
        });

        $("#createBug").click(function () {

            $.ajax({
                url: "createBugForm.jsp",
                beforeSend: function(){
                    $("#ajax-loader").show();
                },
                success: function(data){
                    $("#ajax-loader").hide();
                    $("#dialog").html(data);
                    inputTitles();
                }
            });

            $("#dialog").dialog("open");
        });

    });
</script>

<%@include file="includes/footerMessage.jsp" %>
</body>
</html>
