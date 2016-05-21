<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Positivniy repair - Welcome</title>
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

<s:if test="#session.role == 'ADMIN'">
    <c:import url="jsp/admin-header.jsp"/>
</s:if>
<s:elseif test="#session.role == 'MANAGER'">
    <c:import url="jsp/manager-header.jsp"/>
</s:elseif>
<s:elseif test="#session.role == 'MECHANIC'">
    <c:import url="jsp/mechanic-header.jsp"/>
</s:elseif>
<s:else>
    <c:import url="jsp/guest-header.jsp"/>
</s:else>

<div id="decorative1" style="position:relative">
    <div class="container">

        <div class="divPanel headerArea">
            <div class="row-fluid">
                <div class="span12">

                    <div id="headerSeparator"></div>

                    <div id="divHeaderText" class="page-content">
                        <div id="divHeaderLine1">Positivniy repair</div><br />
                        <div id="divHeaderLine2">We are trying to make not a simple lab for SPP</div><br />
                        <s:if test="#session.role != 'ADMIN' && #session.role != 'MANAGER' && #session.role != 'MECHANIC'">
                            <div id="divHeaderLine3"><a class="btn btn-large btn-primary" href="to_about_page.do">Read More</a></div>
                        </s:if>
                    </div>

                    <div id="headerSeparator2"></div>

                </div>
            </div>

        </div>

    </div>
</div>

<div id="contentOuterSeparator"></div>

<div class="container">

    <div class="divPanel page-content">

        <div class="row-fluid">

            <div class="span12" id="divMain">

                <h1>Welcome</h1>

                <p>Content on this page is for presentation purposes only. Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                    Lorem Ipsum has been the industry’s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                </p>

                <hr style="margin:45px 0 35px" />

                <div class="lead">
                    <h2>Lorem ipsum dolor sit amet.</h2>
                    <h3>Vivamus leo ante, consectetur sit amet vulputate vel, dapibus sit amet lectus.</h3>
                </div>
                <br />

                <div class="list_carousel responsive">
                    <ul id="list_photos">
                        <li><img src="img/Audi%20R8%20(2).jpg" class="img-polaroid">  </li>
                        <li><img src="img/Top%20Gear%20(1).jpg" class="img-polaroid">  </li>
                        <li><img src="img/Nissan%20GTR%20(3).jpg" class="img-polaroid">  </li>
                        <li><img src="img/Nissan%20Skyline%20GTR%20(10).jpg" class="img-polaroid">  </li>
                        <li><img src="img/Mitsubishi%20Lancer%20Evolution%20(46).jpg" class="img-polaroid">  </li>
                        <li><img src="img/Mitsubishi%20Lancer%20Evolution%20X%20(333).jpg" class="img-polaroid">  </li>
                        <li><img src="img/Audi%20R8%20(4).jpg" class="img-polaroid">  </li>
                        <li><img src="img/Dodge%20Charger%20RT%20and%20Dodge%20Charger%20Daytona.jpg" class="img-polaroid">  </li>
                        <li><img src="img/Audi.jpg" class="img-polaroid">  </li>
                        <li><img src="img/Top%20Gear%20(4).jpg" class="img-polaroid">  </li>
                    </ul>
                </div>

                <hr style="margin:45px 0 35px" />

                <div class="lead">
                    <h2>Featured Content.</h2>
                    <h3>Something which is not lorem ipsum</h3>
                </div>
                <br />

                <div class="row-fluid">
                    <div class="span8">

                        <h3>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</h3>

                        <p>
                            <img src="img/Audi%20R8%20(3).jpg" class="img-polaroid" style="margin:12px; :0px;">
                        </p>

                        <p>Content on this page is for presentation purposes only. Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                            Lorem Ipsum has been the industry’s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                            Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                        </p>

                    </div>
                    <div class="span4 sidebar">

                        <div class="sidebox">
                            <h3 class="sidebox-title">Sample Sidebar Content</h3>
                            <p>Lorem Ipsum is simply dummy text of the printing and <a href="#">typesetting industry</a>. Lorem Ipsum has been the industry’s standard dummy text ever since the 1500s.</p>
                        </div>

                        <br/>

                    </div>
                </div>

            </div>

        </div>

        <div id="footerInnerSeparator"></div>
    </div>

</div>

<div id="footerOuterSeparator"></div>

<div id="divFooter" class="footerArea">

    <div class="container">

        <div class="divPanel">

            <div class="row-fluid">
                <div class="span3" id="footerArea1">

                    <h3>About Company</h3>

                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry’s standard dummy text ever since the 1500s.</p>

                    <p>
                        <a href="#" title="Terms of Use">Terms of Use</a><br />
                        <a href="#" title="Privacy Policy">Privacy Policy</a><br />
                        <a href="#" title="FAQ">FAQ</a><br />
                        <a href="#" title="Sitemap">Sitemap</a>
                    </p>

                </div>

                <div class="span3" id="footerArea3">

                    <h3>Sample Content</h3>
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry’s standard dummy text ever since the 1500s.
                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry’s standard dummy text ever since the 1500s.
                    </p>

                </div>
                <div class="span3" id="footerArea4">

                    <h3>Get in Touch</h3>

                    <ul id="contact-info">
                        <li>
                            <i class="general foundicon-phone icon"></i>
                            <span class="field">Phone:</span>
                            <br />
                            (123) 456 7890 / 456 7891
                        </li>
                        <li>
                            <i class="general foundicon-mail icon"></i>
                            <span class="field">Email:</span>
                            <br />
                            <a href="mailto:info@yourdomain.com" title="Email">info@yourdomain.com</a>
                        </li>
                        <li>
                            <i class="general foundicon-home icon" style="margin-bottom:50px"></i>
                            <span class="field">Address:</span>
                            <br />
                            123 Street<br />
                            12345 City, State<br />
                            Country
                        </li>
                    </ul>

                </div>
            </div>

            <br/><br/>
            <div class="row-fluid">
                <div class="span12">
                    <p class="copyright">
                        Copyright © 2016 Positivniy repair. All Rights Reserved.
                    </p>

                    <p class="social_bookmarks">
                        <a href="#"><i class="social foundicon-facebook"></i> Facebook</a>
                        <a href=""><i class="social foundicon-twitter"></i> Twitter</a>
                        <a href="#"><i class="social foundicon-pinterest"></i> Pinterest</a>
                        <a href="#"><i class="social foundicon-rss"></i> Rss</a>
                    </p>
                </div>
            </div>
            <br />

        </div>

    </div>

</div>

<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/default.js" type="text/javascript"></script>


<script src="lib/carousel/jquery.carouFredSel-6.2.0-packed.js" type="text/javascript"></script>
<script type="text/javascript">
    $('#list_photos').carouFredSel({
        responsive: true, width: '100%',
        scroll: 2, items: {width: 320,visible: {min: 2, max: 6}}
    });
</script>


</body>
</html>
