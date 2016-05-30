<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration new employee</title>
    <link href="css/reg.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="js/reg.js"></script>

    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="lib/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Icons -->
    <link href="img/icons/general/stylesheets/general_foundicons.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="img/icons/social/stylesheets/social_foundicons.css" media="screen" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
    <link href="img/icons/general/stylesheets/general_foundicons_ie7.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="img/icons/social/stylesheets/social_foundicons_ie7.css" media="screen" rel="stylesheet" type="text/css" />
    <![endif]-->
    <link rel="stylesheet" href="lib/fontawesome/css/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="lib/fontawesome/css/font-awesome-ie7.min.css">
    <![endif]-->

    <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Palatino+Linotype" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">

    <link href="css/custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="decorative2">
    <div class="container">

        <div class="divPanel topArea notop nobottom">
            <div class="row-fluid">
                <div class="span12">

                    <div id="divLogo" class="pull-left">
                        <a href="to_home_page" id="divSiteTitle">Positivniy repair</a><br />
                        <a href="to_home_page" id="divTagLine">Easy to find</a>
                    </div>

                    <div id="divMenuRight" class="pull-right">
                        <div class="navbar">
                            <button type="button" class="btn btn-navbar-highlight btn-large btn-primary" data-toggle="collapse" data-target=".nav-collapse">
                                NAVIGATION <span class="icon-chevron-down icon-white"></span>
                            </button>
                            <div class="nav-collapse collapse">
                                <ul class="nav nav-pills ddmenu">
                                    <li class="dropdown"><a href="to_home_page">Home</a></li>
                                    <li class="dropdown"><a href="show_dismiss">Dismiss</a></li>
                                    <li class="dropdown active"><a href="show_register">Register new</a></li>
                                    <li class="dropdown">
                                        <a href="" class="dropdown-toggle">Dropdown Item &nbsp;&raquo;</a>
                                        <ul class="dropdown-menu sub-menu">
                                            <li><a href="to_repair_reports">Repair reports</a></li>
                                            <li><a href="to_detail_orders">Detail orders</a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown"><a href="logout">Log Out</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>
    <div class="wrapper">
        <form action="registration_user" method="POST" id="form">
            <div id="form-fields-container">
                <label for="name">First Name*</label>
                <input class="form-control pr-input" type="text" name="firstName" id="name" required>
                <label for="surname">Last Name*</label>
                <input class="form-control pr-input" type="text" name="lastName" id="surname" required>
                <label for="phone">Phone number*</label>
                <input class="form-control pr-input" type="text" name="phone" id="phone" required>
                <br/><br/>
                <label for="email">Email*</label>
                <input class="form-control pr-input" type="email" name="login" id="email" required>
                <label for="pass">Password*</label>
                <input class="form-control pr-input" type="password" name="password" id="pass" required>
                <label>Role:</label>
                <select class="date form-control pr-input" name="roleId">
                    <s:iterator value="roles">
                        <option value="<s:property value="id"/>"><s:property value="name"/></option>
                    </s:iterator>
                </select>
                <label class="pr-error-label"><s:property value="message"/></label>
                <div class="pr-div">
                    <button class="pr-button">Submit</button>
                    <span id="message"></span>
                </div>
            </div><!-- end form-fields-container -->
        </form>
    </div><!-- end wrapper -->
</body>
</html>
