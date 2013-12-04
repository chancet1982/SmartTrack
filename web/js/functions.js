/*------------General Functions------------*/
var mouse_is_inside = false;
var ulIndex;
var ulElement;
var temp = null;
var textAreaValue = $("#8 ul textarea").val();

serverError =   "#1,#2,#3,#4,#6,#7,#8,#0,#10";
frontEnd =      "#1,#2,#3,#4,#5,#7,#8,#0,#10";
textual =       "#1,#2,#3,#4,#9,#0,#10";
other =         "#1,#2,#3,#4,#6,#5,#7,#8,#0,#10";

//Input effects
function inputTitles() {
    $("input[type=text], input[type=password], input[type=file], textarea").each(function() {
        $(this).val($(this).attr("title"));
    });

    $("input[type=text], input[type=password], input[type=file],textarea").focus(function() {
        if ($(this).val()==$(this).attr("title")) { $(this).val(""); }
    }).blur(function() {
        if ($(this).val()=="") { $(this).val($(this).attr("title")); }
    });
}

//Readjust Leftovers from the Bug form
function adjustSteps() {
    $('li#8 > ul.step').each(function(){
        index = $(this).index();
        index++;
        $(this).attr( "id" , "step" + index )
        $(this).find("label").text("Step " + index );
        $(this).find("textarea").attr( "name" , "stepContent" + index );
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

//get URL parameters
function getUrlVar(key){
    var result = new RegExp(key + "=([^&]*)", "i").exec(window.location.search);
    return result && result[1] || "";
}

function validateForm() {
	$('form').each(function() {
		var $formValid = true;
        $(this).find('input.required').each(function (index, element) {
            if(! $(element).hasClass("valid")) $formValid = false;
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
    inputTitles();

    $(".site-footer .message").hide();
    if (getUrlVar('message-success')) {
        removeMessage();
        $("#site-footer .message").prepend('<p>'+decodeURIComponent(getUrlVar('message-success'))+'</p>').addClass("success").show();
    }

    if (getUrlVar('message-error')) {
        removeMessage();
        $("#site-footer .message").prepend('<p>'+decodeURIComponent(getUrlVar('message-error'))+'</p>').addClass("error").show();
    }

    if (getUrlVar('message-info')) {
        removeMessage();
        $("#site-footer .message").prepend('<p>'+decodeURIComponent(getUrlVar('message-info'))+'</p>').addClass("info").show();
    }

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
	/*$('input').each(function() {
		if (!$(this).val() && $(this).hasClass('required')) $(this).addClass('invalid');
	});  */
	
	//Initial form validation
	validateForm();

	//On every keypress validate the value of the input if required, validate the form as well
	$('input:focus').keyup(function() {
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

    // Message settings
    $('#site-footer .message a.close').click(function() {
        removeMessage();
    });

    function removeMessage() {
        $('#site-footer .message p').remove();
        $('#site-footer .message').removeClass("success").removeClass("error").removeClass("info").hide();
    }

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
            success: function(msg) {
                $("#site-footer .message p").remove();
                $("#site-footer .message").prepend('<p>Assignment Changes Saved!</p>').addClass("success").show();
            },
            error: function(msg) {
                $("#site-footer .message p").remove();
                $("#site-footer .message").prepend('<p>Error: Unable to save changes</p>').addClass("error").show();
            }
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
                    removeMessage();
                    $("#site-footer .message").prepend('<p>Value is unique</p>').addClass("success").show();
                }else{
                    thisInput.addClass("unique").removeClass("not-unique");
                    removeMessage();
                    $("#site-footer .message").prepend('<p>Error: Value is already being used</p>').addClass("error").show();
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            }
        });
    });
});