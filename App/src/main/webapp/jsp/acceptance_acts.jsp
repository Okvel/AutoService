<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Acceptance act</title>
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
                                    <li class="dropdown active"><a href="to_acceptance_acts_page">Acceptance acts</a></li>
                                    <li class="dropdown"><a href="to_passing_acts_page">Passing acts</a></li>
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
<div id="contentOuterSeparator"></div>

<div class="container">
    <div class="sidebox centered_menu">
        <s:if test="%{acts == null}">
            <h3 class="sidebox-title">Please choose action</h3>
            <form action="show_acceptance_acts">
                <button class="pr-button-dark" type="submit">Show acceptance acts</button>
            </form>
        </s:if>
        <form action="to_add_acceptance_act_page">
            <button class="pr-button-dark" type="submit">Add acceptance act</button>
        </form>
        <s:if test="%{acts != null}">
            <table class="pr-table">
                <thead>
                    <th class="pr-table-cell">Date</th>
                    <th class="pr-table-cell">Car</th>
                    <th class="pr-table-cell">Client</th>
                    <th class="pr-table-cell">More</th>
                    <th class="pr-table-cell">Download</th>
                </thead>
                <tbody>
                    <s:iterator value="acts" var="act">
                        <tr class="pr-table-cell">
                            <td class="pr-table-cell"><s:property value="#act.date"/></td>
                            <td class="pr-table-cell"><s:property value="#act.car.model.vendor"/> <s:property value="#act.car.model.name"/></td>
                            <td class="pr-table-cell"><s:property value="#act.client.personInformation.lastName"/> <s:property value="#act.client.personInformation.firstName"/></td>
                            <td class="pr-table-cell">
                                <form action="show_acceptance_act" method="post">
                                    <input name="actId" type="hidden" value="<s:property value="id"/>"/>
                                    <button class="pr-table-button" type="submit">Show</button>
                                </form>
                            </td>
                            <td class="pr-table-cell">
                                <form action="download_acceptance_act" method="post">
                                    <input type="hidden" value="<s:property value="id"/>"/>
                                    <select name="format" class="form-control pr-table-select">
                                        <option value="CSV">CSV</option>
                                        <option value="XLSX">XLSX</option>
                                        <option value="PDF">PDF</option>
                                    </select>
                                    <button class="pr-table-button" type="submit">Download</button>
                                </form>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:if test="%{act != null}">
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
                        <td class="pr-table-cell" class="pr-table-cell"><s:property value="act.date"/></td>
                        <td class="pr-table-cell" class="pr-table-cell"><s:property value="act.car.model.vendor"/> <s:property value="act.car.model.name"/></td>
                        <td class="pr-table-cell" class="pr-table-cell"><s:property value="act.client.personInformation.lastName"/> <s:property value="act.client.personInformation.firstName"/></td>
                        <td class="pr-table-cell" class="pr-table-cell"><s:property value="act.manager.personInfo.lastName"/> <s:property value="act.manager.personInfo.firstName"/></td>
                        <td class="pr-table-cell" class="pr-table-cell"><s:property value="act.type"/></td>
                        <td class="pr-table-cell" class="pr-table-cell"><s:property value="act.description"/></td>
                    </tr>
                </tbody>
            </table>
        </s:if>
    </div>
</div>
</body>
</html>
