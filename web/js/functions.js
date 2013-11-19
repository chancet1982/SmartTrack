/*------------General Functions------------*/
var mouse_is_inside = false;

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