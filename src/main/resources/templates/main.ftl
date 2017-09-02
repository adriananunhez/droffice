<#import "/spring.ftl" as spring />
<#macro html_head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="<@spring.url '/css/materialize.min.css'/>"  media="screen,projection"/>
</#macro>

<#macro script_javascript>
    <script type="text/javascript" src="<@spring.url '/js/jquery-3.2.1.min.js'/>"></script>
    <script type="text/javascript" src="<@spring.url '/js/materialize.min.js'/>"></script>
    <script type="text/javascript" src="<@spring.url '/js/main.js'/>"></script>
</#macro>

<#macro header_nav>
    <div id="datePickerRoot"></div>
    <span id="mensajedemesesi18n" class="hide">
        <@spring.message code='button.default.cancel'/>
    </span>
    <span id="mensajedemeses" class="hide">
        <@spring.message code='date.picker.meses'/>
    </span>
    <nav class="nav-extended blue darkten-1">
        <div id="nav-content" class="nav-wrapper">
            <ul>
              <li><img src="<@spring.url '/images/medical_logo.png'/>" alt="" width="70" class="img-logo"></li>
            </ul>

            <ul class="left">
                <li>
                    <h5>
                        <span>Dr. Raul Oscar Portillo</span>
                    </h5>
                    <h6>
                        <span>Pediatra - Medicina General</span>
                    </h6>
                </li>
            </ul>
        </div>
    </nav>
</#macro>