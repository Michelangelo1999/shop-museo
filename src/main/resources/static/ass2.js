$(document).ready(function() {
    var max_fields      = 200;
    var wrapper         = $(".row");
    var add_button      = $(".add_form_field");
 
    var x = 1;
    $(add_button).click(function(e){
        e.preventDefault();
        if(x < max_fields){
            x++;
            $(wrapper).append(
	
); //add input box
        }
  else
  {
  alert('')
  }
    });
 
    
});