/**позначаємо вибрані користувачем відповіді */
$(".question").on('change',function(){
    $(this).siblings(".question").removeClass("selected");
    $(this).addClass("selected");
});
/**створюємо масив з відповідями користувача та правильними відповідями,
 * а також лічильники */
var rightans=[
"title","p","link","div","button","table","ul","strong",
"value","class","#element",".element","div","padding:20px","margin:20px"];
var userans = [];
var result = 0; 
var point = 0.8;
/**Робимо наступні дії при настисненні кнопки відправити*/
$(".button").on("click",function(){
/**перевіряємо тест на заповнення */
$(".borders").removeClass("selected");
for (var n=0;n<$(".borders").length;n++){
if($(".borders").eq(n).val() !==""){
$(".borders").eq(n).addClass("selected");
}};
   
    if($(".selected").length < rightans.length ){
        alert("Дайте відповідь на усі питання")
    }
    else{
/** додаємо в масив відповіді користувача */
for(var k=0;k<rightans.length;k++){
userans.push($(".selected").eq(k).val());
console.log(userans);
}
/** робимо підрахунки балів */


for (var i=0;i<userans.length;i++){

    if( userans[i]==rightans[i]){
        result = result+point;
        $(".your-answers").append("<span class='fa fa-check fa-lg'>"+" "+userans[i]+"</span>"+"<br>")
    }
    else{
        result =result;
          $(".your-answers").append("<span class='fa fa-times fa-lg'>"+" "+userans[i]+"</span>"+"<br>")
    }
};

/** видалення сторінки з тестами  */
$(".quest-block").remove();
/** створюємо  блок з результатом*/ 
$(".res").removeClass("hider");
$(".your-answers").removeClass("hider");
$(".point").append("<span >"+"<font class='grade' size='6'>"+Math.floor(result)+"</font>"+"</span>")
}; 
});

/**При натисненні enter не відправляємо відповіді*/
$(window).keydown(function(){
if (event.keyCode == 13){
    event.preventDefault();
    return false;}  
});
/** Створюємо кнопку перепроходження тесту*/
$(".restart").click(function(){
 location.reload();
});

