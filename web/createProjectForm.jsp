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
            <input type="text" title="Project Start Date" name="startDate" class="required datepicker">
        </li>
        <li>
            <input type="text" title="Project End Date" name="endDate" class="required datepicker">
        </li>
        <li>
            <input type="submit" value="Create">
        </li>
    </ul>
</form>

<script>
    $(function() {
        $( ".datepicker" ).datepicker();
    });
</script>
