<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Positivniy repair</title>
    <link href="../css/reg.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="../js/reg.js"></script>

    <link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../lib/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Icons -->
    <link href="../img/icons/general/stylesheets/general_foundicons.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="../img/icons/social/stylesheets/social_foundicons.css" media="screen" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
    <link href="../img/icons/general/stylesheets/general_foundicons_ie7.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="../img/icons/social/stylesheets/social_foundicons_ie7.css" media="screen" rel="stylesheet" type="text/css" />
    <![endif]-->
    <link rel="stylesheet" href="../lib/fontawesome/css/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="../lib/fontawesome/css/font-awesome-ie7.min.css">
    <![endif]-->

    <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Palatino+Linotype" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">

    <link href="../css/custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="decorative2">
        <div class="container">

            <div class="divPanel topArea notop nobottom">
                <div class="row-fluid">
                    <div class="span12">

                        <div id="divLogo" class="pull-left">
                            <a href="to_home_page.do" id="divSiteTitle">Positivniy repair</a><br />
                            <a href="to_home_page.do" id="divTagLine">Easy to find</a>
                        </div>

                        <div id="divMenuRight" class="pull-right">
                            <div class="navbar">
                                <button type="button" class="btn btn-navbar-highlight btn-large btn-primary" data-toggle="collapse" data-target=".nav-collapse">
                                    NAVIGATION <span class="icon-chevron-down icon-white"></span>
                                </button>
                                <div class="nav-collapse collapse">
                                    <ul class="nav nav-pills ddmenu">
                                        <li class="dropdown"><a href="">Home</a></li>
                                        <li class="dropdown"><a href="show_dismiss.do">Dismiss</a></li>
                                        <li class="dropdown active"><a href="show_register.do">Register new</a></li>
                                        <li class="dropdown"><a href="">Acts</a></li>
                                        <li class="dropdown"><a href="logout.do">Log Out</a></li>
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
        <form action="registration_user.do" method="POST" id="form">
            <div id="form-fields-container">
                <label for="name">First Name*</label>
                <input class="form-control pr-input" type="text" name="name" id="name" required>
                <label for="surname">Last Name*</label>
                <input class="form-control pr-input" type="text" name="surname" id="surname" required>
                <label for="phone">Phone number*</label>
                <input class="form-control pr-input" type="text" name="phone" id="phone" required>
                <br/><br/>
                <label for="email">Email*</label>
                <input class="form-control pr-input" type="email" name="email" id="email" required>
                <label for="pass">Password*</label>
                <input class="form-control pr-input" type="password" name="pass" id="pass" required>
                <label>Role:</label>
                <select class="date form-control pr-input" name="role_id">
                    <c:forEach items="${roles}" var="element">
                        <option value="${element.id}">${element.name}</option>
                    </c:forEach>
                </select>
                <div class="pr-div">
                    <button class="pr-button">Submit</button>
                    <span id="message"></span>
                </div>
            </div><!-- end form-fields-container -->
        </form>
    </div><!-- end wrapper -->
</body>
</html>
