/**при натисненні на варіант відповіді позначаємо її як вибрану користувачем */
$("input").on('change',function(){
 $(this).siblings(".question").removeClass("selected");
$(this).addClass("selected");
var findSubmit = $(this).parent().siblings(".button");

/** якщо кнопка вже з'явилася просто позначаємо відповідь користувача */
if (findSubmit.hasClass("invisible") == false){
$(this).siblings("input").removeClass("selected");
$(this).addClass("selected");   
}
/** при натисненні radiobutton зїявляється кнопка */
else{
$(this).siblings("input").removeClass("selected");
$(this).addClass("selected");
findSubmit.removeClass("invisible");
}
});

/** створюємо масив з відповідями користувача*/
var questionsNumber = $(".quest-block").length;
var userans =[];
var temperamentValue = 0;
var counter = 0;
/**При натисненні на кнопку виконуємо дії які прописані нижче */

$(".button").on("click",function(){
/**Додаємо відповіді користувача у масив  */
var answer = $(this).siblings("form").children(".selected").val();
userans.push(answer);
/** видаляємо питання на яке ми вже відповіли*/
var btnindex=($(this).parent().index()-1);
$(".quest-block").eq(btnindex).slideUp('slow', function(){$(".quest-block").eq(btnindex).remove(); });

/** робимо підрахунки кількості балів*/
if(userans.length == questionsNumber){
for (var i=0;i<userans.length;i++){
   temperamentValue = temperamentValue +  Number(userans[i]);
}


/** видаляємо сторінку з тестом */
$(".test").remove();
/** створюємо  блок з результатом */
$(".res").removeClass("hider");
/** Дізнаємось темперамент користувача */
if (temperamentValue >= 40 && temperamentValue <= 60){
$(".res").append("<span >"+"<font class='grade' size='6'>"+"Флігматик"+"</font>"+"</span>")
}
else if (temperamentValue > 60 && temperamentValue <= 80){
$(".res").append("<span >"+"<font class='grade' size='6'>"+"Меланхолік"+"</font>"+"</span>")
}
else if (temperamentValue > 80 && temperamentValue <= 100){
$(".res").append("<span >"+"<font class='grade' size='6'>"+"Холерик"+"</font>"+"</span>")
} 

else if (temperamentValue > 100 && temperamentValue <= 120){
$(".res").append("<span >"+"<font class='grade' size='6'>"+"Cангвінік"+"</font>"+"</span>")
}
};
});

/**Не відправляти відповідь при натисненні enter */
$(window).keydown(function(){
if (event.keyCode == 13){
    event.preventDefault();
    return false;}  
});