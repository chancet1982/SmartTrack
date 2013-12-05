<style>
.autocompleteContainter li{padding:5px;}
.autocompleteContainter li:hover{background:#D6D6D6;cursor:pointer;}
.autocompleteContainter{
    float:left;
    width:100%;
    border:1px solid #000000;
    position:absolute;
    top:30px;
    background:#f0f0f0;
}
</style>
<form id="new-bug" action="BugServlet" method="POST">
    <h2>Report New Issue</h2>
    <p>In order to create an effective report please fill in the following:</p>
    <ul id="bug-report">

        <li id="11" class="">
            <select title="Project Name" id="projectName" name="projectID">
                <option value="no-value">Project Name</option>
            </select>
        </li>

        <li id="1">
            <select id="bug-category" name="bugCategory">
              <option value="no-value">What seems to be the problem?</option>
              <option value="server-error">Something crashed</option>
              <option value="front-end">It doesnt look correct</option>
              <option value="textual">Change some texts</option>
              <option value="other">I have no clue(other)</option>
            </select>
        </li>

        <li id="2" class="hidden">
            <input type="text" title="Title" name="bugTitle" class="required">
        </li>

        <li id="3" class="hidden">
            <textarea title="Short description of the issue" class="required" name="bugDescription"></textarea>
        </li>

        <li id="12" class="hidden" style="position:relative">
            <input type="text" title="Assign a user to this issue" name="assignUserToBug" class="searchFor" autocomplete="off">
            <input type="text" name="userID" class="hidden userID">
            <ul class="autocompleteContainter hidden"></ul>
        </li>

        <li id="10" class="hidden">
            <select id="reportedPriority" name="reportedPriority">
                <option value="no-value">Bug Priority</option>
                <option value="Minor">Minor</option>
                <option value="Average">Average</option>
                <option value="Major">Major</option>
                <option value="Chuck Norris">Chuck Norris</option>
            </select>
        </li>

        <li id="4" class="hidden">
            <input type="text" title="URL for problem (the thing in the top of the browser)" name="bugURL">
        </li>

        <li id="5" class="hidden">
            <input id="file" title="Screenshot" type="file" name="screenshotURL" size="40">
            <a id="uploadFile" class="button"><span class="icon upload"></span> Upload</a>
        </li>

        <li id="6" class="hidden">
            <input type="text" title="When did you encounter the error?" name="timeStamp" id="datepicker">
        </li>

        <li id ="7" class="hidden">
            <label>Did it happen on while using this local PC?</label>
            <input type="radio" name="localPC" value="true">Yes
            <input type="radio" name="localPC" value="false">No
        </li>

        <li id="8" class="hidden">
            <label>Steps for re-creating the error</label>
            <ul class="steps">
                <li class="step" id="step1">
                    <textarea name="stepContent1" title="Step 1"></textarea>
                    <a class="remove-step button"><span class="icon delete"></span></a>
                </li>
            </ul>
            <a id="addStep" class="button"><span class="icon add"></span></a>
            <a id="undoDelete" class="button inactive" style="color:white "><span class="icon undo"></span></a>
        </li>

        <li id="9" class="hidden">
            <label>Specify change</label>
            <input type="text" title="From" name="textFrom" size="40">
            <input type="text" title="To" name="textTo" size="40">
        </li>

        <li id="0">
            <input type="submit" value="Create">
        </li>
    </ul>
</form>

<script type="text/javascript">

    //AUTOCOMPLETE
    $(document).on("mousedown",".autocompleteContainter li",function(){
        $(".searchFor").val($(this).text());
        $(".userID").val($(this).attr("data-user-id"));
    });

    $(".searchFor").on("blur", function(){
        $(".autocompleteContainter").addClass("hidden");
    });

    $(".searchFor").on("keyup", function(){
        searchFor = $(this).val();
        if(searchFor == "" || searchFor==null){
            $(".autocompleteContainter").addClass("hidden");
        }else{
            $(".autocompleteContainter").removeClass("hidden");
            $.ajax({
                type: "GET",
                url: "/BugServlet?action=autocompleteUsers",
                data:{ "searchFor" : searchFor },
                success: function(data) {
                    if(data==""){
                        $(".autocompleteContainter").addClass("hidden");
                    }
                    $(".autocompleteContainter").html(data);
                },
                error: function (xhr, ajaxOptions, thrownError) {console.log(xhr.status);console.log(thrownError);}
            });
        }
    });
    //autocomplete end


    $(document).ready(function(){
        $.ajax({
            type: "GET",
            url: "/BugServlet?action=getProjectsInDropdown",
            dataType: "text",
            async: false,
            success: function(data) {
                $("#projectName").append(data);
            },
            error: function () {
                $("#site-footer .message p").remove();
                $("#site-footer .message").prepend('<p>Error: Cannot get Project Names</p>').addClass("error").show();
            }
        });

        $('#datepicker').datetimepicker({
            controlType: 'select',
            timeFormat: 'hh:mm tt'
        });
    });

    //Step Addition
    $("#addStep").click(function(){
        adjustSteps();
        stepID =  $('li#8 > li.step').size();
        stepID++;
        step = $('<li class="step" id="step' + stepID + '">'+
                '<textarea name="stepContent'+ stepID +'" title="Step'+ stepID +'" rows="2" cols="65"></textarea>' +
                '<a class="remove-step button"><span class="icon remove"></span></a>' +
                '</li>');

        $(step).appendTo("li#8 ul.steps");
        temp = null;
        disableUndo();
    });

    //Step removal
    $(document).on("click","a.remove-step",function(){
        ulIndex = $(this).parent().index() ;
        ulIndex++;
        ulElement = $(this).parent();
        temp = null;

        if ( temp == null ){
            temp = $( ulElement ).detach();
            adjustSteps();
        }
        enableUndo();
        stepID--;
    });

    //UNDO delete
    $("#undoDelete").click(function(){
        console.log(ulIndex);
        if ( temp != null ) {
            if(ulIndex <= $("#8").children().length - 1){
                temp.insertBefore( "li#8 > ul.steps > li.step:nth-child(" + ulIndex  + ")" );
            }else{
                temp.appendTo("li#8 > ul.steps");
            }
            disableUndo();
            console.log(ulIndex);
            adjustSteps();
        }
    });

    //Category select
    $("#new-bug #bug-category").change(function(){
        selectedOption = $("#new-bug #bug-category option:selected" );
        console.log(selectedOption.val());
        $(this).parent().parent().children().not(":first-child").addClass("hidden");

        if (selectedOption.val() == 'server-error'){
            $(serverError).removeClass("hidden");
        }else if (selectedOption.val() == 'front-end'){
            $(frontEnd).removeClass("hidden");
        }else if (selectedOption.val() == 'textual'){
            $(textual).removeClass("hidden");
        }else if (selectedOption.val() == 'other'){
            $(other).removeClass("hidden");
        }
    });
    $('.step textarea').on('blur' , function(){
        textAreaValue = $(this).val();
    });
    $( "#8" ).sortable({
        revert: false,
        stop: function() { adjustSteps(); },
        start: function() {  }
    });
    $( ".step" ).draggable({
        connectToSortable: "#8",
        revert: false,
        cursor: "move",
        containment: "parent",
        snap: "#8",
        delay: 100,
        //stack: ".step",
        axis: "y"
    });

    //File Upload
    $('#uploadFile').click(function() {
        $.ajax({
            type: "POST",
            url: "/UploadServlet",
            async: true,
            data: $("#file").serialize(),
            contentType: 'multipart/form-data',
            success: function(msg) {
                removeMessage();
                $("#site-footer .message").prepend('<p>File Upload Success!</p>').addClass("success").show();
            },
            error: function(msg) {
                removeMessage();
                $("#site-footer .message").prepend('<p>Upload Error!</p>').addClass("error").show();
            }
        });
    });

    //Text area axpander
    $("textarea").keyup(function(e) {
        while($(this).outerHeight() < this.scrollHeight + parseFloat($(this).css("borderTopWidth")) + parseFloat($(this).css("borderBottomWidth"))) {
            $(this).height($(this).height()+1);
        };
    });
</script>