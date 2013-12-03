<form action="UserServlet" method="POST">
    <h2>Tell Us a little about yourself</h2>
    <p>You just need to fill-in a couple of details and we could get started</p>
    <ul>
        <li>
            <input type="text" title="First name" name="firstName" class="required">
        </li>
        <li>
            <input type="text" title="Last name" name="lastName" class="required">
        </li>
        <li>
            <input type="text" title="E-mail address" name="userEmail" data-servlet="/UserServlet?action=verifyUnique" class="required unique invalid email">
            <span class="message box error hidden">Email is already registered and must be unique</span>
        </li>
        <li>
            <input type="text" title="Password" name="userPassword" class="required password">
        </li>
        <li>
            <input type="text" title="Repeat password" name="password2" class="required repeat-password">
        </li>
        <li>
            <input type="text" title="Company name" name="companyName" data-servlet="/UserServlet?action=verifyUnique" class="required unique invalid">
            <span class="message box error hidden">Company already exists, you cannot add a user to an existing company</span>
        </li>
        <li>
            <input type="submit" value="Submit">
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
