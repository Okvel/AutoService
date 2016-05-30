<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Detail orders</title>
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
                                    <li class="dropdown"><a href="show_register">Register new</a></li>
                                    <li class="dropdown active">
                                        <a href="" class="dropdown-toggle">Dropdown Item &nbsp;&raquo;</a>
                                        <ul class="dropdown-menu sub-menu">
                                            <li class="active"><a href="">Acts</a></li>
                                            <li><a href="to_repair_reports">Repair reports</a></li>
                                            <li class="active"><a href="to_detail_orders">Detail orders</a></li>
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

<div id="contentOuterSeparator"></div>

<div class="container">
    <div class="sidebox">
        <h3 class="sidebox-title">Please choose action</h3>
        <form action="show_detail_orders">
            <button class="pr-button-dark" type="submit">Show detail orders</button>
        </form>
        <s:if test="%{detail_orders != null}">
            <table class="pr-table">
                <thead>
                <th class="pr-table-cell">ID</th>
                <th class="pr-table-cell">Mechanic</th>
                <th class="pr-table-cell">More</th>
                <th class="pr-table-cell">Download</th>
                </thead>
                <tbody>
                <s:iterator value="detail_orders" var="detail_order">
                    <tr class="pr-table-cell">
                        <td class="pr-table-cell"><s:property value="#detail_order.id"/></td>
                        <td class="pr-table-cell"><s:property value="#detail_order.mechanic.personInfo.lastName"/> <s:property value="#detail_order.mechanic.personInfo.firstName"/></td>
                        <td class="pr-table-cell">
                            <form action="show_detail_order" method="post">
                                <input type="hidden" name="id" value="<s:property value="#detail_order.id"/>"/>
                                <button class="pr-table-button" type="submit">Show</button>
                            </form>
                        </td>
                        <td class="pr-table-cell">
                            <form action="download_detail_order" method="post">
                                <input type="hidden" name="id" value="<s:property value="#detail_order.id}"/>"/>
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
        <s:if test="%{detail_order != null}">
            <table class="pr-table">
                <thead>
                <th class="pr-table-cell">Mechanic</th>
                <th class="pr-table-cell">Detail</th>
                <th class="pr-table-cell">Count</th>
                <th class="pr-table-cell">More</th>
                </thead>
                <tbody>
                <tr class="pr-table-cell">
                    <td class="pr-table-cell"><s:property value="detail_order.mechanic.personInfo.lastName"/> <s:property value="detail_order.mechanic.personInfo.firstName"/></td>
                    <td class="pr-table-cell"><s:property value="detail_order.detail.name"/></td>
                    <td class="pr-table-cell"><s:property value="detail_order.count"/></td>
                    <td class="pr-table-cell">
                        <form action="save_detail_invoice" method="post">
                            <input type="hidden" name="id" value="<s:property value="detail_order.id}"/>"/>
                            <button class="pr-table-button" type="submit">Create invoice</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </s:if>
    </div>
</div>

<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/default.js" type="text/javascript"></script>
</body>
</html>
