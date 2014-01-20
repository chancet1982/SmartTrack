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
    $.cookie("pid", "1");
    menuHighlight();
</script>

</body>
</html>