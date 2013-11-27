/*------------General Functions------------*/
var mouse_is_inside = false;
var ulIndex;
var ulElement;
var temp = null;
var textAreaValue = $("#8 ul textarea").val();

serverError = " #2 , #3 , #4 , #6 , #7 , #8";
frontEnd = " #2 , #3 , #4 , #5 , #7 , #8";
textual = " #2 , #3 , #4 , #9";
other = " #2 , #3 , #4 , #6 , #5 , #7 , #8";

//Readjust steps
function adjustSteps() {
    $('li#8 > ul.step').each(function(){
        index = $(this).index();
        index++;
        $(this).attr( "id" , "step" + index )
        $(this).find("label").text("Step " + index );
        console.log('adjusted');
    });
}
function disableUndo(){
    temp = null;
    $("#undoDelete").addClass("inactive").attr("disabled", "disabled");
}
function enableUndo(){
    $("#undoDelete").removeClass("inactive").removeAttr("disabled");
}

function validateForm() {
	$('form').each(function() {
		var $formValid = true;
		$(this).find('input.required').each(function (index, element) {
			if($(element).hasClass("invalid")) $formValid = false;
		});		
		if (!$formValid) {
            $(this).find('input[type="submit"]').addClass('inactive').bind('click');
        } else {
            $(this).find('input[type="submit"]').removeClass('inactive').unbind('click');
        }
	});
}

/*------------Do things on window resize------------*/
$(window).resize(function() {
});

/*------------Do things on document ready------------*/
$(document).ready(function() {

    /*--- assignUsersToProjects.jsp ---*/

    //Load assigned users from database
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
            error: function (xhr, ajaxOptions, thrownError) {console.log(xhr.status+ " " +thrownError);}
        });
    });

    //create dragables and sortables
    draggable= "ul.users li.user";
    sortable = "ul.projects li.project span.project-users ul";

    $(draggable).draggable({
        helper: "clone",
        revert: false,
        connectToSortable: $(sortable)
    });

    $(sortable).sortable({
        revert: "100",
        helper: "original",
        over: function () {removeIntent = false;},
        out:  function () {removeIntent = true;},
        beforeStop: function (event, ui) {
            if(removeIntent == true){ui.item.remove();}
            assignUsers($(this));
        },
        receive: function( event, ui ) {
            removeDuplicateUsers($(this));
            assignUsers($(this));
        }
    });

    //Step Addition
    $("#addStep").click(function(){
        adjustSteps();
        stepID =  $('li#8 > ul.step').size();
        stepID++;
        step = $('<ul class="step" id="step' + stepID + '"> '+
            '<li><div class="remove-step"></div><label>Step '+ stepID +'</label></li> '+
            '<li><textarea rows="2" cols="65"></textarea></li></ul>');

        $(step).insertBefore(this);
        temp = null;
        disableUndo();
    });

    //Step removal
    $(document).on("click","div.remove-step",function(){
        ulIndex = $(this).parent().parent().index() ;
        ulIndex++;
        ulElement = $(this).parent().parent();
        temp = null;

        if ( temp == null ){
            temp = $( ulElement ).detach();
            adjustSteps();
        }
        enableUndo();
    });

    //UNDO delete
    $("#undoDelete").click(function(){
        if ( temp != null ) {
            if(ulIndex == 1){
                temp.insertBefore( "li#8 > ul.step:eq(0)" );
            }else{
                temp.insertAfter( "li#8 > ul.step:nth-child(" + ulIndex  + ")" );
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
    $('textarea').on('blur' , function(){
        textAreaValue = $(this).val();console.log('blur')
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

	//toggling things
	$('.toggleable .toggle').on( 'click', function() {
		if ( $(this).hasClass('collapse')) {
			$(this).hide().closest('content').hide();
			$(this).parent().switchClass('expanded', 'collapsed', 300);
			$(this).removeClass('collapse').addClass('expand');			
			$(this).show().closest('content').show();
		} else {
			$(this).hide().closest('content').hide();
			$(this).parent().switchClass('collapsed', 'expanded', 300);
			$(this).removeClass('expand').addClass('collapse');			
			$(this).show().closest('content').show();			
		}
	});

    $('.toggleable').hover(function(){ 
        mouse_is_inside=true; 
    }, function(){ 
        mouse_is_inside=false; 
    });

    $("body").mouseup(function(){ 
		if($('.toggleable').hasClass('expanded')) {
			if(! mouse_is_inside){
				$('.toggleable .toggle').trigger('click');
			}
		}
    });

	//Add invalid to empty required inputs
	$('input').each(function() {
		if (!$(this).val() && $(this).hasClass('required')) $(this).addClass('invalid');
	});
	
	//Initial form validation
	validateForm();

	//On every keypress validate the value of the input if required, validate the form as well
	$('input').keyup(function() {
		if ($(this).hasClass('required')) {
			if ($(this).hasClass('password')) {//Password validation
				if($(this).val().length > 4) {
					$(this).removeClass('invalid').addClass('valid');	
				} else {
					if(!$(this).hasClass('invalid')) $(this).removeClass('valid').addClass('invalid');
				}

			} else if($(this).hasClass('repeat-password')) {//Password(Repeat) validation
				var $pwdvalue = $('input.password').val();
				if($(this).val() == $pwdvalue) {
					$(this).removeClass('invalid').addClass('valid');	
				} else {
					if(!$(this).hasClass('invalid')) $(this).removeClass('valid').addClass('invalid');
				}
				
			} else if ($(this).hasClass('email')) {//Email validation
				var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
				if(emailReg.test($(this).val()) && $(this).val()) {
					$(this).removeClass('invalid').addClass('valid');
				} else {
					if(!$(this).hasClass('invalid')) $(this).removeClass('valid').addClass('invalid');
				}

			} else if($(this).val()) {//General validation
				$(this).removeClass('invalid').addClass('valid');		
			} else {
				if(!$(this).hasClass('invalid')) $(this).removeClass('valid').addClass('invalid');			
			}	
		}
		validateForm();
	});

	//Add invalid to empty required inputs
	
	//Deactivate inactive links
	$('.inactive').click(function(e) {
		e.preventDefault();
	});

	//Highlight selected
	$('#site-navigation a').click(function() {
		if (!$(this).hasClass('inactive')) {
			$(this).parent().parent().each(function(){
				$('li').removeClass("active");
			});
			$(this).parent().addClass('active')
		}
	});

	//Input effects
	$('input[type="text"] , input[type="password"]').addClass('normal');	
	$('input[type="text"] , input[type="password"]').hover(function(){ 
		$(this).stop(true, true).switchClass('normal', 'hover', 300);
	},function(){
		if (!$(this).is(':focus')) $(this).stop(true, true).switchClass('hover', 'normal', 300);
	});

	$('input[type="text"] , input[type="password"]').focusin(function(){ 
		$(this).stop(true, true).switchClass('normal', 'hover', 300);
	});

	$('input[type="text"] , input[type="password"]').focusout(function(){ 
		$(this).stop(true, true).switchClass('hover', 'normal', 300);
	});

    /*---------AJAX SECTION---------*/

    //assignUsersToProjects.jsp
    function removeDuplicateUsers(obj){
        var seen = {};
        obj.find("li").each(function(){
            id = $(this).attr("data-user-id");
            if (seen[id]){$(this).remove();}else{seen[id] = true;}
        });
    }

    function assignUsers(obj){
        projectID = obj.parent().parent().attr("data-project-id");
        assignedUsers = "";
        obj.find("li").each(function(){
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

    //unique check
    $(".unique").on("keyup" , function(){
        inputValue = $(this).val();
        inputName = $(this).attr("name");
        servletName = $(this).attr("data-servlet");
        thisInput = $(this);
        $.ajax({
            type: "GET",
            url: servletName,
            data:{ "inputValue" : inputValue , "inputName" : inputName},
            dataType: "json",
            success: function(data) {
                console.log(data.isUnique);
                if(data.isUnique == "true"){
                    thisInput.removeClass("not-unique").addClass("unique");
                    thisInput.next().addClass("hidden");
                }else{
                    thisInput.addClass("unique").removeClass("not-unique");
                    thisInput.next().removeClass("hidden");
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            }
        });
    });


});