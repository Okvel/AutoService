<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Acceptance act</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
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

    <link href="../lib/carousel/style.css" rel="stylesheet" type="text/css" />

    <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Palatino+Linotype" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">

    <link href="../css/custom.css" rel="stylesheet" type="text/css" />
</head>
<body id="pageBody">
<c:import url="manager-header.jsp"/>
<div id="contentOuterSeparator"></div>

<div class="container">
    <div class="sidebox">
        <h3 class="sidebox-title">Please choose action</h3>
        <form action="show_acceptance_act_list.do">
            <button class="btn" type="submit">Show acceptance acts</button>
        </form>
        <form action="add_acceptance_act.do">
            <button class="btn" type="submit">Add acceptance act</button>
        </form>
        <c:if test="${not empty acts}">
        <table>
            <thead>
                <th>Date</th>
                <th>Car</th>
                <th>Client</th>
                <th></th>
            </thead>
            <tbody>
                <c:forEach items="${acts}" var="act">
                    <tr>
                        <td>${act.date}</td>
                        <td>${act.car.model.name} ${act.car.model.vendor}</td>
                        <td>${act.client.personInfo.lastName} ${act.client.personInfo.firstName}</td>
                        <td>
                            <form action="show_act.do" method="post">
                                <input type="hidden" value="${act.id}"/>
                                <button class="btn" type="submit">Show</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:if>
        <c:if test="${not empty act}">
            <table>
                <thead>
                <th>Date</th>
                <th>Car</th>
                <th>Client</th>
                <th>Manager</th>
                <th>Type</th>
                <th>Description</th>
                </thead>
                <tbody>
                    <tr>
                        <td>${act.date}</td>
                        <td>${act.car.model.name} ${act.car.model.vendor}</td>
                        <td>${act.client.personInfo.lastName} ${act.client.personInfo.firstName}</td>
                        <td>${act.manager.personInfo.lastName} ${act.manager.personInfo.firstName}</td>
                        <td>${act.type}</td>
                        <td>${act.description}</td>
                    </tr>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
