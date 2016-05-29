<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Add acceptance act</title>
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
        <h3 class="sidebox-title">Fill acceptance act</h3>
        <form action="save_act.do" method="post">

            <input type="hidden" name="act_type" value="acceptance"/>
            <input class="form-control" name="date" type="date"/><br/>
            <input class="form-control" type="text" name="description" placeholder="description"/><br/>
            <input class="form-control" type="text" placeholder="last_name" name="lastName"/><br/>
            <input class="form-control" type="text" placeholder="first_name" name="firstName"/><br/>
            <input class="form-control" type="text" placeholder="patronymic" name="patronymic"/><br/>
            <input class="form-control" type="text" placeholder="country" name="country"/><br/>
            <input class="form-control" type="text" placeholder="city" name="city"/><br/>
            <input class="form-control" type="text" placeholder="street" name="street"/><br/>
            <input class="form-control" type="text" placeholder="building" name="building"/><br/>
            <input class="form-control" type="text" placeholder="room" name="room"/><br/>
            <input class="form-control" type="text" placeholder="phone" name="phone"/><br/>
            <input class="form-control" type="text" placeholder="passport" name="passport"/><br/>
            <input class="form-control" type="text" placeholder="registration_number" name="registrationNumber"/><br/>
            <input class="form-control" type="text" placeholder="vin" name="vin"/><br/>
            <input class="form-control" type="text" placeholder="model" name="model"/><br/>
            <input class="form-control" type="text" placeholder="vendor" name="vendor"/><br/>
            <br/>
            <button class="btn" type="submit">Add</button>
        </form>
    </div>
</div>
</body>
</html>