<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- Charset -UTF8 -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <!-- Supporting responsiveness -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

    <!-- Remove IE quirks mode -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <!-- Remove Cache -->
    <meta http-equiv="cache-control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="pragma" content="no-cache" />

    <!-- CSS -->
    <link media="all" href="css/reset.css" rel="stylesheet" type="text/css" />
    <link media="all" href="css/jquery-ui/jquery-ui-1.10.3.custom.css" rel="stylesheet">
    <link media="screen" href="css/main.css" rel="stylesheet" type="text/css" /> <!-- To be used in the future production version -->

    <!-- LESS -->
    <link rel="stylesheet/less" type="text/css" href="less/main.less">
    <script type="text/javascript" src="js/less.min.js"></script>

    <!-- Web Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Montserrat+Alternates' rel='stylesheet' type='text/css'>

    <!-- JavaScript -->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/functions.js"></script>

    <!-- Browser Support -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="js/html5shiv.js"></script>
    <script type="text/javascript" src="js/html5shiv-printshiv.js"></script>
    <![endif]-->

    <!--[if (gte IE 6)&(lte IE 8)]>
    <script type="text/javascript" src="js/selectivizr.js"></script>
    <![endif]-->

    <title>Title of the document</title>

    <style type="text/css">

    .step {margin-top:10px;}
    .step li label,
    .step li .remove-step{float:left;}
    .step li:first-child{height:20px;}

    .remove-step {
        height:20px;
        width:20px;
        background-color:#00afba;
        background-image:url(images/ico-sprites.png);
        background-position:22px -50px;
        margin-right:5px
    }

    textarea {margin-bottom:10px}
    .step label {cursor:move}

    /*--- assign user to project page ---*/
    .project {display:block;}
    .project span{ display:block; float:left; }
    .project .project-name {background:#ffffff;  width:10%; }
    .project .project-users {background:#f0f0f0; width:90%; height:76px; }
    .project .project-users ul { height:100%; }
    .project .project-users ul li { margin: 3px 3px 3px 0; padding: 1px; float: left;  }

    #site-content li.user {
        display: inline-block;
        width: 64px;
        line-height: 64px;
        height: 64px;
        cursor: pointer;
        position: relative;
    }
    #site-content li.user .icon {
        margin-top: 24px;
        margin-right: 0px;
        margin-left: 8px;
    }
    #site-content li.user { background: #a6e866; }

    </style>
</head>