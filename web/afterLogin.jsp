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
        <h1>Welcome to Smarttrack</h1>
        <div class="box color-light round-corners shadow padding" style="padding:8px;">
            <p>Active Projects</p>
            <div id="projectPipeline"> </div>
        </div>
    </div>
</div>

<%@include file="includes/footerMessage.jsp" %>
<script type="text/javascript">
    google.setOnLoadCallback(drawChart);

    function drawChart() {
        var container = document.getElementById('projectPipeline');

        var chart = new google.visualization.Timeline(container);

        var dataTable = new google.visualization.DataTable();

        dataTable.addColumn({ type: 'string', id: 'projectName' });
        dataTable.addColumn({ type: 'date', id: 'startDate' });
        dataTable.addColumn({ type: 'date', id: 'endDate' });

        dataTable.addRows([
            [ 'Project 1', new Date(1789, 3, 29), new Date(1797, 2, 3) ],
            [ 'Project 2', new Date(1797, 2, 3),  new Date(1801, 2, 3) ],
            [ 'Project 3',  new Date(1801, 2, 3),  new Date(1809, 2, 3) ]
        ]);

        var options = {
            backgroundColor: '#d0dadf'
        };

        chart.draw(dataTable);
    }
</script>
<div id="example1" style="width: 900px; height: 180px;"></div>
</body>
</html>