$(function() {

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
        }
  });

    $("#buttonSave").on('click', function (event){
         event.preventDefault();
        $("#formValidate").validate().form();
        $("#formValidate")[0].submit();
    })


});