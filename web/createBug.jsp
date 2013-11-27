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
            <div class="box color-light round-corners shadow padding">
				<h1>Report Issue</h1>
				<form id="new-bug" action="BugServlet" method="POST">
				<ul id="bug-report">
					<li id="1">
						<label>What seems to be the problem?</label>
						<select id="bug-category">
						  <option value="no-value">-</option>				
						  <option value="server-error">Something crashed</option>
						  <option value="front-end">It doesnt look correct</option>
						  <option value="textual">Change some texts</option>				  
						  <option value="other">I have no clue(other)</option>
						</select> 	
					</li>
					
					<li id="2" class="hidden">
						<label>Title</label>
						<input type="text" name="bugCategory" >
					</li>
					
					<li id="3" class="hidden">
						<label>Short description of the issue</label>
						<textarea rows="4" cols="70"></textarea>
					</li>					
						
					<li id="4" class="hidden">
						<label>URL for problem (the thing in the top of the browser)</label>
						<input type="text" name="bugUrl">
					</li>

					<li id="5" class="hidden">
						<label>Screenshot</label>
						<input type="file" name="datafile" size="40">
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
					<li><p>Steps for re-creating the error</p></li>
					<li id="8" class="hidden">
						<ul class="step" id="step1">
							<li><div class="remove-step"></div><label>Step 1</label></li>
							<li><textarea rows="2" cols="65"></textarea></li>
							
						</ul>
						<a id="addStep" class="button" style="color:white ">Add step...</a>
						<a id="undoDelete" class="button inactive" style="color:white ">undo delete</a>
						<!--
						<button type="button" id="addStep" class="button">Add step...</button> 
						<button type="button" id="undoDelete" class="button">Undo last action</button> 
						-->
					</li>
					
					<li id="9" class="hidden">
						<p>Specify change</p>
						<label>From</label>
						<input type="text" name="from-text" size="40">
						<label>To</label>
						<input type="text" name="to-text" size="40">
					</li>
					
				</ul>
				</form>			
			</div>
		</div>
	</div>
</div>
</body>
</html> 