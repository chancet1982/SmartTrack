<form action="ProjectServlet" method="POST">
    <h2>Add a New Project</h2>
    <p>We need to know what is the project name and version. The project will be created under your company </p>
    <ul>
        <li>
            <input type="text" title="Project Name" name="projectName" class="required">
        </li>
        <li>
            <input type="text" title="Project Version" name="projectVersion" class="required">
        </li>
        <li>
            <input type="submit" value="Create">
        </li>
    </ul>
</form>

<script type="text/javascript">
    $(document).ready(function() {
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
    });
</script>
