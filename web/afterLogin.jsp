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
        <h1>SmartTrack. Simply Smart!</h1>
        <div class="box color-light round-corners shadow padding" style="padding:8px;">
            <h2>Tasks, ideas, and requests are all around you. Use SmartTrack to capture and organize your companies' issues, take action on what's important, all in an easy and efficient way.</h2>
        </div>
    </div>
</div>

<%@include file="includes/footerMessage.jsp" %>

<script type="text/javascript">

    $.cookie("pid", "1");
    menuHighlight();

    $(document).ready(function(){
        $('a.css').on('click', function (e) {
            $('#oldstyle').remove();
            if ($(this).hasClass("default")){
                $('head').append('<link href="/css/default.css" rel="stylesheet" id="currentStyle" />');
                $.cookie("css", "default");
            } else if($(this).hasClass("blue")){
                $('head').append('<link href="/css/blue.css" rel="stylesheet" id="currentStyle" />');
                $.cookie("css", "blue");
            } else if($(this).hasClass("dark")){
                $('head').append('<link href="/css/dark.css" rel="stylesheet" id="currentStyle" />');
                $.cookie("css", "dark");
            } else if($(this).hasClass("sand")){
                $('head').append('<link href="/css/sand.css" rel="stylesheet" id="currentStyle" />');
                $.cookie("css", "sand");
            }
        });
    });
</script>

</body>
</html>