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
		<div class="items-row cols-1 clearfix">
            <div id="createBug" class="box color-light round-corners shadow padding">
				<h1>Report Issue</h1>
				<form id="new-bug" action="BugServlet" method="POST">

                <ul id="bug-report">
                    <li id="11" class="">
                        <label>Project Name</label>
                        <select id="projectName" name="reportedPriority">
                            <option value="no-value">-</option>
                        </select>
                    </li>

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

                    <li id="10" class="hidden">
                        <label>Bug Priority</label>
                        <select id="reportedPriority" name="reportedPriority">
                            <option value="no-value">Who Cares</option>
                            <option value="Minor">Minor</option>
                            <option value="Average">Average</option>
                            <option value="Major">Major</option>
                            <option value="Chuck Norris">Chuck Norris</option>
                        </select>
                    </li>

					<li id="4" class="hidden">
						<label>URL for problem (the thing in the top of the browser)</label>
						<input type="text" name="bugURL">
					</li>

					<li id="5" class="hidden">
						<label>Screenshot</label>
						<input type="file" name="screenshotURL" size="40">
                        <a class="button"><span class="icon upload"></span> Upload</a>
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
						<li>
                            <label>Step 1</label>
							<textarea name="stepContent1" rows="2" cols="65"></textarea>
                            <a class="remove-step button"><span class="icon remove"></span></a>
                        </li>
							
						</ul>
						<a id="addStep" class="button"><span class="icon add"></span></a>
						<a id="undoDelete" class="button inactive" style="color:white "><span class="icon undo"></span></a>

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
		</div>
	</div>
</div>
<script type="text/javascript">

</script>
</body>
</html> 