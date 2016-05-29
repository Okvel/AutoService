<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Add passing act</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../lib/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="../css/bootstrap.min.css">

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
    <link rel="stylesheet" href="../lib/fontawesome/css/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="../lib/fontawesome/css/font-awesome-ie7.min.css">
    <![endif]-->

    <link href="lib/carousel/style.css" rel="stylesheet" type="text/css" />

    <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Palatino+Linotype" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">

    <link href="css/custom.css" rel="stylesheet" type="text/css" />
</head>
<body id="pageBody">
<c:import url="admin-header.jsp"/>
<div id="contentOuterSeparator"></div>

<div class="container">
    <div class="sidebox">
        <h3 class="sidebox-title">Please dismissed employee</h3>
        <form action="save_act.do" method="post">

            <input type="hidden" name="act_type" value="passing"/>
            <input class="form-control" name="date" type="date"/>
            <input class="form-control" type="text" name="description"/>

            <input class="form-control" type="text" name="last_name"/>
            <input class="form-control" type="text" name="first_name"/>
            <input class="form-control" type="text" name="patronymic"/>
            <input class="form-control" type="text" name="country"/>
            <input class="form-control" type="text" name="city"/>
            <input class="form-control" type="text" name="street"/>
            <input class="form-control" type="text" name="building"/>
            <input class="form-control" type="text" name="room"/>
            <input class="form-control" type="text" name="phone"/>
            <input class="form-control" type="text" name="passport"/>

            <input class="form-control" type="text" name="registration_number"/>
            <input class="form-control" type="text" name="vin"/>
            <input class="form-control" type="text" name="model"/>
            <input class="form-control" type="text" name="vendor"/>

            <button class="btn" type="submit">Add</button>
        </form>
    </div>
</div>
</body>
</html>