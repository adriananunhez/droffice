<#import "/spring.ftl" as spring />
<#include "/main.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <@html_head/>
    <link type="text/css" rel="stylesheet" href="<@spring.url '/css/main.css'/>"/>
    <link type="text/css" rel="stylesheet" href="<@spring.url '/css/style.css'/>"/>
    <title>Ficha medica</title>
</head>
<header>
    <@header_nav/>
</header>
<body>

<div class="row">
    <div class="col s12 m12 l12">
        <!-- Este div no tiene contenido, ya que la clase row aplica a su primer hijo
      pone por defecto un estilo centrado,entonces este sirve para que al resto de los hijos
    no queden asignados con dicho estilo-->
    </div>
    <div class="col s12 offset-m1 m10 offset-l1 l10">
        <a id="buttonSave" class="waves-effect waves-light btn blue darken-4">
            <i class="material-icons left">save</i>Guardar
        </a>
        <a class="waves-effect waves-light btn modal-trigger blue darken-4" href="">
            <i class="material-icons left">cancel</i>Cancelar
        </a>
    </div>
    <form id="formValidate" class="" action="/patients/savePatient" method="post">
        <div class="col s12 offset-m1 m10 offset-l1 l10 z-depth-3 patient-form-header">
            <div class="col s12 center-align">
                <h4>
                    <span>Ficha Clinica</span>
                    <div class="input-field right">
                        <input id="opening_date" name="opening_date" type="text" class="datepicker" value="">
                        <label><span class="header-form-label">Fecha de apertura</span></label>
                    </div>
                </h4>
            </div>
            <div class="col s12 m12 l12 patient-form-data">
                    <div class="col s12 m12 l12">
                        <div class="input-field col s6">
                            <input placeholder="" id="name" name="name" type="text" class="validate" value="" required>
                            <label for="name"><span class="header-form-label">Nombre y Apellido:</span></label>
                        </div>
                        <div class="input-field col s6">
                            <input placeholder="" id="document_number" name="document_number" type="text" class="validate" value="">
                            <label for="document_number"><span class="header-form-label">Nro de Documento:</span></label>
                        </div>
                    </div>
                    <div class="col s12 m12 l12">
                        <div class="input-field col s6">
                            <input id="birthday" name="birthday" type="text" class="datepicker" value="" required>
                            <label for="birthday"><span class="header-form-label">Fecha de nacimiento:</span></label>
                        </div>
                        <div class="input-field col s6">
                            <input placeholder="" id="address" name="address" type="text" class="validate" value="" required>
                            <label for="address"><span class="header-form-label">Domicilio:</span></label>
                        </div>
                    </div>
                    <div class="col s12 m12 l12">
                        <div class="input-radio-field col s6">
                            <label><span class="header-form-label" id="patient-sex">Sexo:</span></label>

                            <input id="masculino" type="radio" name="sex" value="M" class="validate with-gap" required>
                            <label class="radio-label" for="masculino">Masculino</label>

                            <input id="femenino" type="radio" name="sex" value="F" class="validate with-gap" required>
                            <label class="radio-label" for="femenino">Femenino</label>

                        </div>
                        <div class="input-field col s6">
                        </div>
                    </div>
            </div>
        </div>
    </form>
</div>
<@script_javascript/>
<script type="text/javascript" src="<@spring.url '/js/jquery.validate.min.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/js/patients/create.js'/>"></script>
</body>
</html>