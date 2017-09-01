$(function() {
    console.log(patientAutocompleteJSON)
      $('input.autocomplete').autocomplete({
        data:patientAutocompleteJSON,
        limit: 20, // The max amount of results that can be shown at once. Default: Infinity.
        onAutocomplete: function(val) {
          // Callback function when value is autcompleted.
        },
        minLength: 1, // The minimum length of the input for the autocomplete to start. Default: 1.
      });

      $("#search").click(function () {
         $(this).select();
      });

      $("#addPatientButton").click(function (){
           $("#addPattientForm").submit();
      });

//      $('.clickable').click(function(){
//            console.log($(this))
//      })

      $('.clickable').click(function() {
        var userId = $(this).attr('id');
        $("a[href='/patients/show/"+userId)+"']"[0].click();
      });
})