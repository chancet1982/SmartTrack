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
        <h1>Bugs...</h1>
        <div class="box color-light round-corners shadow padding clearfix">
            <table>
                <thead>
                <tr>
                    <th>Bug Id</th>
                    <th>Bug Title</th>
                    <th>Bug Description</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bugs}" var="bug">
                    <tr>
                        <td><c:out value="${bug.bugID}" /></td>
                        <td><c:out value="${bug.bugTitle}" /></td>
                        <td><c:out value="${bug.bugDescription}" /></td>
                        <td><a href="BugServlet?action=delete&bugID=<c:out value="${bug.bugID}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button name="createBug" id="createBug">Add bug</button>
        </div>
    </div>
</div>
<div id="createBugForm">
    <h1>Report Issue</h1>
    <form id="new-bug" action="BugServlet" method="POST">
        <ul id="bug-report">
            <li id="1">
                <label>What seems to be the problem?</label>
                <select id="bug-category" name="bugCategory">
                    <option value="no-value">-</option>
                    <option value="server-error">Something crashed</option>
                    <option value="front-end">It doesnt look correct</option>
                    <option value="textual">Change some texts</option>
                    <option value="other">I have no clue(other)</option>
                </select>
            </li>

            <li id="2" class="hidden">
                <label>Title</label>
                <input type="text" name="bugTitle" class="required">
            </li>

            <li id="3" class="hidden">
                <label>Short description of the issue</label>
                <textarea rows="4" cols="70" class="required" name="bugDescription"></textarea>
            </li>

            <li id="4" class="hidden">
                <label>URL for problem (the thing in the top of the browser)</label>
                <input type="text" name="bugURL">
            </li>

            <li id="5" class="hidden">
                <label>Screenshot</label>
                <input type="file" name="screenshotURL" size="40">
            </li>

            <li id="6" class="hidden">
                <label>Timestamp(when did the error happen?)</label>
                <input type="text" name="timeStamp" >
            </li>

            <li id ="7" class="hidden">
                <label>Did it happen on while using this local PC?</label>
                <input type="radio" name="localPC" value="true">Yes
                <input type="radio" name="localPC" value="false">No
            </li>
            <li class="hidden"><p>Steps for re-creating the error</p></li>
            <li id="8" class="hidden">
                <ul class="step" id="step1">
                    <li><div class="remove-step"></div><label>Step 1</label></li>
                    <li><textarea name="stepContent1" rows="2" cols="65"></textarea></li>

                </ul>
                <a id="addStep" class="button" style="color:white ">Add step...</a>
                <a id="undoDelete" class="button inactive" style="color:white ">undo delete</a>

            </li>

            <li id="9" class="hidden">
                <p>Specify change</p>
                <label>From</label>
                <input type="text" name="textFrom" size="40">
                <label>To</label>
                <input type="text" name="textTo" size="40">
            </li>

            <li id="0">
                <input type="submit" value="Submit">
            </li>
        </ul>
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $( "#createBugForm" ).dialog({
            autoOpen: false,
            modal: true,
            width: 600,
            height: $(window).height() * 0.9,
            draggable: false
        });
        $( "#createBug" )
            .button()
            .click(function() {
                $( "#createBugForm" ).dialog( "open" );
        });
    });
</script>
</body>
</html>
