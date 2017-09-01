<#import "/spring.ftl" as spring />
<#include "/main.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <@html_head/>
    <link type="text/css" rel="stylesheet" href="<@spring.url '/css/main.css'/>"/>
    <link type="text/css" rel="stylesheet" href="<@spring.url '/css/patients/show.css'/>"/>
    <title><@spring.message code='patient.medical.history'/></title>
</head>
<header>
    <@header_nav/>
</header>
<body>
    <!-- Modal Structure -->
    <div id="patient_history_modal" class="modal modal-fixed-footer">
        <div class="modal-content" style="color:black;">
            <form id="modalFormId" action="/patients/savePatientHistory" method="post">
                <input id="patientId" name="patientId" value="${patientModel.id}" class="hide"/>
                <div class="row">
                    <div class="col s4">
                        <h4><@spring.message code='patient.create.history'/></h4>
                    </div>
                    <div class="col s4">
                        <div class="input-field">
                            <input id="history_date" name="history_date" type="text" class="datepicker" value="12/09/2017">
                            <label for="history_date"><span class="header-form-label"><@spring.message code='patient.history.date'/></span></label>
                        </div>
                    </div>
                    <div class="input-field col s4">
                        <select>
                            <option value="" disabled selected>Elige una opcion</option>
                            <#list placeAttentionList as placeAttention>
                                <option value="${placeAttention.id}">${placeAttention.description}</option>
                            </#list>
                        </select>
                        <label><span class="header-form-label"><@spring.message code='patient.history.place.attention'/></span></label>
                    </div>
                </div>
                <ul id="patient_history_tabs" class="tabs tabs-fixed-width">
                    <li class="tab col s3"><a href="#sympton_tab"><@spring.message code='patient.history.sympton'/></a></li>
                    <li class="tab col s3"><a href="#diagnostic_tab"><@spring.message code='patient.history.diagnostic'/></a></li>
                    <li class="tab col s3"><a href="#prescription_tab"><@spring.message code='patient.history.prescription'/></a></li>
                </ul>
                <div id="sympton_tab" class="col s12 grey lighten-3 div-textarea">
                    <div class="input-field col s12">
                        <textarea id="sympton_textarea" name="sympton_textarea" class="materialize-textarea textarea-modal"></textarea>
                        <label for="sympton_textarea"></label>
                    </div>
                </div>
                <div id="diagnostic_tab" class="col s12 grey lighten-3 div-textarea">
                    <div class="input-field col s12">
                        <textarea id="diagnostic_textarea" name="diagnostic_textarea" class="materialize-textarea textarea-modal"></textarea>
                        <label for="diagnostic_textarea"></label>
                    </div>
                </div>
                <div id="prescription_tab" class="col s12 grey lighten-3 div-textarea">
                    <div class="input-field col s12">
                        <textarea id="prescription_textarea" name="prescription_textarea" class="materialize-textarea textarea-modal"></textarea>
                        <label for="prescription_textarea"></label>
                    </div>
                </div>
            </form>
        </div>

        <div class="modal-footer">
            <a id="savePatientHistory" href="#!" class="modal-action modal-close waves-effect waves-green btn-flat "><@spring.message code='button.default.save'/></a>
            <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat "><@spring.message code='button.default.cancel'/></a>
        </div>
    </div>

    <div class="row">
        <div class="col s12 m12 l12">
            <!-- Este div no tiene contenido, ya que la clase row aplica a su primer hijo
                pone por defecto un estilo centrado,entonces este sirve para que al resto de los hijos
                no queden asignados con dicho estilo-->
        </div>
        <div class="col offset-s1 s12  offset-m1 m10  offset-l1 l10">
            <a id="addPatientHistory" class="waves-effect waves-light btn blue darken-4 modal-trigger" href="#patient_history_modal">
                <i class="material-icons left">add</i><@spring.message code='add.patient.history'/>
            </a>
            <!--<a id="savePatient" class="waves-effect waves-light btn blue darken-4">-->
                <!--<i class="material-icons left">save</i>Guardar-->
            <!--</a>-->
            <!--<a id="cancelPatient" class="waves-effect waves-light btn blue darken-4">-->
                <!--<i class="material-icons left">cancel</i>Cancelar-->
            <!--</a>-->
        </div>
        <form id="formValidate" class="" action="/patients/savePatient" method="post">
            <div class="col s12 offset-m1 m10 offset-l1 l10 z-depth-3 patient-form-header">
                <div class="col s12 center-align">
                    <h4>
                        <span><@spring.message code='patient.medical.history'/></span>
                        <div class="input-field right">
                            <input placeholder="" id="opening_date" name="opening_date" type="text" class="datepicker" value="${patientModel.openingDateString}" required disabled>
                            <label><span class="header-form-label"><@spring.message code='patient.openingDate'/></span></label>
                        </div>
                    </h4>
                </div>
                <div class="col s12 m12 l12 patient-form-data">
                    <div class="col s12 m12 l12">
                        <div class="input-field col s6">
                            <input placeholder="" id="name" name="name" type="text" class="validate" value="${patientModel.name}" required disabled>
                            <label for="name"><span class="header-form-label"><@spring.message code='patient.name'/></span></label>
                        </div>
                        <div class="input-field col s6">
                            <input placeholder="" id="document_number" name="document_number" type="text" class="validate" value="${patientModel.documentNumber}" disabled>
                            <label for="document_number"><span class="header-form-label"><@spring.message code='patient.documentNumber'/></span></label>
                        </div>
                    </div>
                    <div class="col s12 m12 l12">
                        <div class="input-field col s6">
                            <input placeholder="" id="birthday" name="birthday" type="text" class="datepicker" value="${patientModel.birthdayString}" required disabled>
                            <label for="birthday"><span class="header-form-label"><@spring.message code='patient.birthday'/></span></label>
                        </div>
                        <div class="input-field col s6">
                            <input placeholder="" id="address" name="address" type="text" class="validate" value="${patientModel.address}" required disabled>
                            <label for="address"><span class="header-form-label"><@spring.message code='patient.address'/></span></label>
                        </div>
                    </div>
                    <div class="col s12 m12 l12">
                        <div class="input-radio-field col s6">
                            <label><span class="header-form-label" id="patient-sex"><@spring.message code='patient.sex'/></span></label>
                            <#if patientModel.sexInt == 1>
                                <input id="masculino" type="radio" name="sex" value="M" class="validate with-gap" required disabled checked>
                                <label class="radio-label" for="masculino"><@spring.message code='patient.sex.male'/></label>

                                <input id="femenino" type="radio" name="sex" value="F" class="validate with-gap" required disabled>
                                <label class="radio-label" for="femenino"><@spring.message code='patient.sex.female'/></label>

                                <#else>
                                    <input id="masculino" type="radio" name="sex" value="M" class="validate with-gap" required disabled>
                                    <label class="radio-label" for="masculino"><@spring.message code='patient.sex.male'/></label>

                                    <input id="femenino" type="radio" name="sex" value="F" class="validate with-gap" required disabled checked>
                                    <label class="radio-label" for="femenino"><@spring.message code='patient.sex.female'/></label>
                            </#if>
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
    <script type="text/javascript" src="<@spring.url '/js/patients/show.js'/>"></script>
</body>
</html>