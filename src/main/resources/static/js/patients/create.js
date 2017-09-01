$(function() {
    /*$('#opening_date').pickadate({
        container: "#datePickerRoot",
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 25, // Creates a dropdown of 15 years to control year,
        today: 'Today',
        clear: 'Clear',
        close: 'Ok',
        closeOnSelect: false // Close upon selecting a date,
    });*/

  $("#formValidate").validate({
        rules: {
            name: {
                required: true
            },

            birthday: {
                required: true
            },
            address: {
                required: true
            },
            sex: {
                required: true
            },
            opening_date: {
                required: true
            }
       },
        errorElement : 'div',
        errorPlacement: function(error, element) {
          var placement = $(element).data('error');

          if (element.attr("name") == "sex") {
            error.insertAfter($("#femenino").next());
          }else if (placement) {
            $(placement).append(error)
          } else {
            error.insertAfter(element);
          }
        },
        messages: {
                    name: $('#errori18n').text(),
                    birthday: $('#errori18n').text(),
                    address: $('#errori18n').text(),
                    sex: $('#errori18n').text(),
                    opening_date: $('#errori18n').text()
                }
  });

    $("#buttonSave").on('click', function (event){
         event.preventDefault();
        if($("#formValidate").validate().form() == true){
            $("#formValidate")[0].submit();
        }
    })
});