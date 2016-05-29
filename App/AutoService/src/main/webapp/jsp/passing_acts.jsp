<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Passing acts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
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

    <link href="lib/carousel/style.css" rel="stylesheet" type="text/css" />

    <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Palatino+Linotype" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">

    <link href="css/custom.css" rel="stylesheet" type="text/css" />
</head>
<body id="pageBody">
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
                                    <li class="dropdown"><a href="to_home_page.do">Home</a></li>
                                    <li class="dropdown"><a href="to_acceptance_acts.do">Acceptance acts</a></li>
                                    <li class="dropdown active"><a href="to_passing_acts.do">Passing acts</a></li>
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

<div id="contentOuterSeparator"></div>

<div class="container">
    <div class="sidebox centered_menu">
        <c:if test="${empty acts}">
            <h3 class="sidebox-title">Please choose action</h3>
            <form action="show_passing_act_list.do">
                <button class="pr-button-dark" type="submit">Show passing acts</button>
            </form>
        </c:if>
        <form action="TO_ADD_PASSING_ACT_PAGE.do">
            <button class="pr-button-dark" type="submit">Add passing act</button>
        </form>
        <c:if test="${not empty acts}">
            <table class="pr-table">
                <thead>
                <th class="pr-table-cell">Date</th>
                <th class="pr-table-cell">Car</th>
                <th class="pr-table-cell">Client</th>
                <th class="pr-table-cell">More</th>
                <th class="pr-table-cell">Download</th>
                </thead>
                <tbody>
                <c:forEach items="${acts}" var="act">
                    <tr class="pr-table-cell">
                        <td class="pr-table-cell">${act.date}</td>
                        <td class="pr-table-cell">${act.car.model.vendor} ${act.car.model.name}</td>
                        <td class="pr-table-cell">${act.client.personInformation.lastName} ${act.client.personInformation.firstName}</td>
                        <td class="pr-table-cell">
                            <form action="show_act.do" method="post">
                                <input type="hidden" value="${act.id}"/>
                                <button class="pr-table-button" type="submit">Show</button>
                            </form>
                        </td>
                        <td class="pr-table-cell">
                            <form action="download_act.do" method="post">
                                <input type="hidden" value="${act.id}"/>
                                <select name="format" class="form-control pr-table-select">
                                    <option value="CSV">CSV</option>
                                    <option value="XLSX">XLSX</option>
                                    <option value="PDF">PDF</option>
                                </select>
                                <button class="pr-table-button" type="submit">Download</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty act}">
            <table class="pr-table">
            <thead>
                <th class="pr-table-cell">Date</th>
                <th class="pr-table-cell">Car</th>
                <th class="pr-table-cell">Client</th>
                <th class="pr-table-cell">Manager</th>
                <th class="pr-table-cell">Type</th>
                <th class="pr-table-cell">Description</th>
                </thead>
                <tbody>
                <tr class="pr-table-cell">
                    <td class="pr-table-cell">${act.date}</td>
                    <td class="pr-table-cell">${act.car.model.name} ${act.car.model.vendor}</td>
                    <td class="pr-table-cell">${act.client.personInfo.lastName} ${act.client.personInfo.firstName}</td>
                    <td class="pr-table-cell">${act.manager.personInfo.lastName} ${act.manager.personInfo.firstName}</td>
                    <td class="pr-table-cell">${act.type}</td>
                    <td class="pr-table-cell">${act.description}</td>
                </tr>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
