
//**if (typeof attr !== typeof undefined && attr == false) {}*\




$('.calc').on('click',function(){
var regex = new RegExp("^\\d+$");
var kg = $('.kilo').val();
var cal = $('.calories').val();

if ($('.kilo').hasClass("vis") == true &&  $('.calories').hasClass("vis") == false){
if(regex.test(kg) && !kg == ' '){
     parseInt(kg);
      var res = Math.floor(kg*7.7)+' '+'кал';
    $('.calories').val(res);
 }
}

   else if ($('.kilo').hasClass("vis") == false && $('.calories').hasClass("vis") == true){
    if(regex.test(cal) && !cal == ' '){
        parseInt(cal);
      var res =Math.floor(cal*0.13)+' '+'г';
    $('.kilo').val(res);
   }
  }
});


$(".swap").on('click',function(){
  /*var fplaceholder =  $('.input-fs').attr('placeholder');
  var splaceholder = $('.input-s').attr('placeholder');*/
  var fplaceholder =  "Грами";
  var splaceholder = "Калорії";
$('.input-fs').val('');
$('.input-s').val('');
if ($('.kilo').hasClass("vis") == true){
 $('.input-fs').attr('placeholder',splaceholder);
 $('.input-s').attr('placeholder',fplaceholder);
  $('.input-fs').addClass('calories').removeClass('kilo');
  $('.input-s').addClass('kilo').removeClass('calories');
}
else{ 
  $('.input-fs').attr('placeholder',fplaceholder);
 $('.input-s').attr('placeholder',splaceholder);
  $('.input-fs').addClass('kilo').removeClass('calories');
  $('.input-s').addClass('calories').removeClass('kilo');
}
});