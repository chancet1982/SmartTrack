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
            <p>Change style here:</p>
            <ul>
                <li><a href="#" class="default css">default.css</a></li>
                <li><a href="#" class="blue css">blue.css</a></li>
                <li><a href="#" class="dark css">dark.css</a></li>
                <li><a href="#" class="sand css">sand.css</a></li>
            </ul>

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