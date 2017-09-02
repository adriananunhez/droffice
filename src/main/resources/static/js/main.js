$(function() {
    $('.datepicker').pickadate({
      container: "#datePickerRoot",
      labelMonthNext: 'Next month',
      labelMonthPrev: 'Previous month',
      labelMonthSelect: 'Select a month',
      labelYearSelect: 'Select a year',
      monthsFull: [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ],
      monthsShort: [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ],
      weekdaysFull: [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday' ],
      weekdaysShort: [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ],
      weekdaysLetter: [ 'S', 'M', 'T', 'W', 'T', 'F', 'S' ],
      today: 'Today',
      clear: $('#mensajedemesesi18n').text(),//'Clear',
      close: 'Ok',
      format: 'dd/mm/yyyy',
      selectMonths: true, // Creates a dropdown to control month
      selectYears: 100 // Creates a dropdown of 15 years to control year,
    });
//
//    $('.datepicker').pickadate('picker').get('highlight', 'yyyy');
//    $('.datepicker').pickadate('picker').get('highlight', 'dd');
//    $('.datepicker').pickadate('picker').get('highlight', 'mm');
});