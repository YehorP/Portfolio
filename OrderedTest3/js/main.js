/** Створюємо масив із запитаннями */
var array=[
{question:"<div class='form-group q-block' >\
<img src='images/number1.jpg' class='question-img'>\
<p class='quest'><font size='3'>Виберіть потрібну фігуру з чотирьох пронумерованих.</font></p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

{question:"<div class='form-group' >\
<p class='quest'><font size='3'>Вставте слово, яке служило б закінченням першого слова і початком другого</font></p>\
<p>ОБЫ ( . . . ) КА</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'><font size='3'>Розв'яжіть анаграми і назвіть зайве слово.</font></p>\
<p>ААЛТЕРК</p>\
<p>КОЖАЛ</p>\
<p>ДМОНЧЕА</p>\
<p>ШКААЧ</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<img src='images/number2.jpg' class='question-img'>\
<p class='quest'><font size='3'>Вставте відсутнє число</font></p>\
<input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'><font size='3'>Вставте пропущене слово.</font></p>\
<p>БАГОР (РОСА) ТЕСАК</p>\
<p>ГАРАЖ ( . . . . ) ТАБАК</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'><font size='3'>Вставте пропущене число.</font></p>\
<p>196  (25)  324</p>\
<p>325  (  )  137</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

{question:"<div class='form-group' >\
<p class='quest'>Продовжіть ряд чисел</p>\
<p>18  10  6  4  ?</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

{question:"<div class='form-group' >\
<p class='quest'>Розв'яжіть  анаграми і назвіть зайве слово.</p>\
<p>НИАВД</p>\
<p>СЕОТТ</p>\
<p>СЛОТ</p>\
<p>ЛЕКСОР</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<img src='images/number3.jpg' class='question-img'>\
<p class='quest'><font size='3'>Виберіть потрібну фігуру з пронумерованих</font></p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
  <img src='images/number4.jpg' class='question-img'>\
<p class='quest'><font size='3'>Виберіть потрібну фігуру з шести пронумерованих</font></p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'>Вставте відсутню букву</p>\
<p>Щ  Ц  Т  П  Л  ?</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'>Вставте слово, яке служило б закінченням першого слова і початком другого</p>\
<p>ME ( . . . ) ОЛАД</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
  <img src='images/number5.jpg' class='question-img'>\
<p class='quest'>Вставте пропущене число</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'>Вставте відсутнє число</p>\
<p>4 9 20</p>\
<p>8 5 14</p>\
<p>10 3?</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'>Вставте відсутнє число</p>\
<p>16  (27)  43</p>\
<p>29  (  )  56</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
  <img src='images/number6.jpg' class='question-img'>\
<p class='quest'>Вставте відсутні літери без пробілів в будь-якій послідовності</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
    <img src='images/number7.jpg' class='question-img'>\
<p class='quest'>Виберіть потрібну фігуру з шести пронумерованих</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
     <img src='images/number8.jpg' class='question-img'>\
<p class='quest'>Виберіть потрібну фігуру з пронумерованих</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'>Вставте пропущене число</p>\
<p>6  11  ?  27</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'>Вставте пропущене число</p>\
<p>12  (56)  16</p>\
<p>17  (  )  21</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'>Вставте пропущене слово</p>\
<p>ФЛЯГА (АЛЬТ) ЖЕСТЬ</p>\
<p>КОСЯК ( . . . . ) МИРАЖЬ</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'> Вставте слово, яке служило б закінченням першого слова і початком другого</p>\
<p>ПРИК ( . . . ) ЬЯ</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  {question:"<div class='form-group' >\
<p class='quest'>Розв'яжіть  анаграми і назвіть зайве слово</p>\
<p>ЖААРБ</p>\
<p>ТЯХА</p>\
<p>НУССК</p>\
<p>КОДАЛ</p>\
<input  class=' borders userinput borders' >\
  </div>\
  "},

 {question:"<div class='form-group' >\
<p class='quest'>Вставте слово, яке означало б те саме, що і слова, що стоять поза дужками</p>\
<p>РУКА ( . . . . . ) ГРОЗДЬ</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

   {question:"<div class='form-group' >\
<p class='quest'>Вставте пропущену букву</p>\
<p>А  Г  Ж</p>\
<p>Г  З  Л</p>\
<p>З  М  ?</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

   {question:"<div class='form-group' >\
   <img src='images/number9.jpg' class='question-img'>\
<p class='quest'>Вставте пропущені букви без пробілів в будь-якій послідовності</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

   {question:"<div class='form-group' >\
   <img src='images/number10.jpg' class='question-img'>\
<p class='quest'>Виберіть потрібну фігуру з шести пронумерованих</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

   {question:"<div class='form-group' >\
   <img src='images/number11.jpg' class='question-img'>\
<p class='quest'>Виберіть потрібну фігуру з пронумерованих</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

   {question:"<div class='form-group' >\
      <img src='images/number12.jpg' class='question-img'>\
<p class='quest'>Виберіть потрібну фігуру з шести пронумерованих</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

   {question:"<div class='form-group' >\
<p class='quest'>Вставте пропущене слово</p>\
<p>КНИГА (АИСТ) САЛАТ</p>\
<p>ПОРОГ ( . . . . ) ОМЛЕТ</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

 {question:"<div class='form-group' >\
<p class='quest'>Вставте слово, яке означало б те саме, що і слова, що стоять поза дужками</p>\
<p>КАРТОЧНАЯ ИГРА ( . . . . ) СТЕРЖЕНЬ С РЕЗЬБОЙ</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

   {question:"<div class='form-group' >\
<p class='quest'>Вставте пропущене число</p>\
<p>1 8 27 ?</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

   {question:"<div class='form-group' >\
<p class='quest'>Вставте пропущене слово</p>\
<p>ЛОТОК (КЛАД) ЛОДКА</p>\
<p>ОЛИМП ( . . . . ) КАТЕР</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

   {question:"<div class='form-group' >\
<p class='quest'>Розв'яжіть  анаграми і назвіть зайве слово</p>\
<p>АТСЕН</p>\
<p>ТИВОНКР</p>\
<p>РАКЫШ</p>\
<p>КООН</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

   {question:"<div class='form-group' >\
   <img src='images/number13.jpg' class='question-img'>\
<p class='quest'>Вставте пропущену букву і пропущене число без пробілів в будь-якій послідовності</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},
  

   {question:"<div class='form-group' >\
<p class='quest'>Вставте слово, яке означало б те саме, що і слова, що стоять поза дужками</p>\
<p>ЗАЛИВ ( . . . . ) ЧАСТЬ ЛИЦА</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  
   {question:"<div class='form-group' >\
<p class='quest'>Вставте пропущене слово</p>\
<p>ПИРОГ (ПОЛЕ) СЛЕЗА</p>\
<p>РЫНОК ( . . . . ) ОСАДА</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  
   {question:"<div class='form-group' >\
<img src='images/number14.jpg' class='question-img'>\
<p class='quest'> Виберіть потрібну фігуру з шести пронумерованих</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  
   {question:"<div class='form-group' >\
   <img src='images/number15.jpg' class='question-img'>\
<p class='quest'> Виберіть потрібну фігуру з шести пронумерованих</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},

  
   {question:"<div class='form-group' >\
   <img src='images/number16.jpg' class='question-img'>\
<p class='quest'>Виберіть потрібну фігуру з чотирьох пронумерованих</p>\
    <input  class=' borders userinput borders' >\
  </div>\
  "},
  
];
/***************************************************************/
$(window).keydown(function(){
if (event.keyCode == 13){
    event.preventDefault();
    return false;}  
});
/**Правила тесту та вставлення першого питання у html документ*/
 var i = 0;
 var indexing = 0;
 $(".accept").click(function(){
   $(".counter").removeClass("hider");
    $(".counter").empty;
     $(".counter").html((i+1)+" "+"з 40");
$("form").append(array[i].question);  
$(".rules").remove();
$(".test").removeClass("hider");
 });


/***************************************************************/


/**Створюємо масив з відповідями користувача , та масив з правильними відповідями*/
 var rightans=["4","чай","чемодан","11","жаба","21","3","тесто","6","5"
 ,"и","шок","54","11","27","ми","2","2","18","76"
 ,"кожа","лад","скунс","кисть","с","ее","2","1","1","грот"
 ,"винт","64","порт","вторник","ё7","губа","роса","1","6","1"];
 var userans=[];
/***************************************************************/


/**При натисненні на кнопку Далі перевіряємо чи заповнив користувач поле з відповіддю,
 *та зберігаємо його відповідь у масив*/
$(".next").click(function(){
if ($("input").val() == ""){
 alert("Дайте відповідь на поставлене питання");
}
else{
    userans.push($("input").val());
    $("form").empty();  
    i++;
      $(".counter").empty;
     $(".counter").html((i+1)+" "+"з 40");
    $("form").append(array[i].question); 
   $(".prev").removeClass("hider");
   if(i == (rightans.length-1)){
$(".next").addClass("hider");
$(".submit").removeClass("hider");
    }
   }


 return i,userans;
});
/***************************************************************/

/**При натисненні на кнопку назад повертаэмось до попереднього питання*/
$(".prev").click(function(){
$("form").empty();
$(".next").removeClass("hider");
$(".submit").addClass("hider");
$(this).addClass("hider");
i--;
$("form").append(array[i].question); 
 $(".counter").html((i+1)+" "+"з 40");
userans.pop();
 return userans;
});
/***************************************************************/

/**При натисненні на кнопку Відправити порівнюємо відповіді користувача з правильними відповідями 
 * та віводимо відповіді користувача познечені (красним - не правильні , зелені - правильні)*/
$(".submit").click(function(){
if ($("input").val() == ""){
 alert("Дайте відповідь на поставлене питання");
}

 else{
    $(".counter").remove();
  userans.push($("input").val());
   var questionIq = 4.5;
   var counter=0;
for(var e=0;e<userans.length;e++){
    if(userans[e]==rightans[e]){
$(".yourans").append((e+1)+" "+"<span class='cor'>"+" "+userans[e]+"</span>"+"<br>")
counter = counter+questionIq;  
    }
else{
    counter=counter;
    $(".yourans").append((e+1)+" "+"<span class='inc'>"+" "+userans[e]+"</span>"+"<br>")
}

};
 $(".test").empty();
$(".test2").show();
$(".result").append("<span class='iq'>"+"<font size='4'>"+"<strong>"+counter+"</strong>"+" "+"iq"+"</font>"+"</span>")
$('.cor').css("background-color","lightgreen");
$('.inc').css("background-color","#ff7f7f");


};
}
);
/***************************************************************/

/** При натисненні на кнопку рестарт починаємо тест знову*/
$(".restart").click(function(){
 location.reload();
});
/***************************************************************/
