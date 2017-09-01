$(function() {
     $('.modal').modal({
        ready: function(modal, trigger) { // Callback for Modal open. Modal and trigger parameters available.
           $('#sympton_textarea').focus();
        }
     });

     $('ul.tabs').tabs({
        onShow: function(eltab){
            console.log(eltab)
        }
     });

     $('#savePatientHistory').on('click',function(){
        console.log("HICE CCLICK EN EL SAVE");
        $('#modalFormId').submit();
        console.log("Paso el submit")
     });

     $('select').material_select();
})