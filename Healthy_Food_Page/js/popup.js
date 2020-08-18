 $(document).ready(function(){
for(var i=0;i<$(".food-list").length;i++){
$(".food-list img").eq(i).attr("src",array[i].photo);
$(".food-list h4").eq(i).html(array[i].title);
$(".food-list").eq(i).data("id",i);
}
  });
  var array = [
     {title:'Салат з тунцем і авокадо',
     photo:"receips-images/salat-ztuncem.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>1 авокадо</li>\
     <li>2 середніх помідора</li>\
     <li>2 банки тунця у воді</li>\
     <li>2 ст. л. кедрових горішків;</li>\
     <li>2 ст. л. оливкової олії</li>\
     <li>сік половини лайма (або лимона)</li>\
     <li>кінза</li>\
     <li>морська сіль</li>\
     <li>мелений перець.</li>\
     </ul>"
    },

     {title:'Літній овочевий салат',
     photo:"receips-images/salat-summer.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>1 молодий кабачок</li>\
     <li>1 болгарський перець</li>\
     <li> 1 цибулина</li>\
     <li>2 помідори</li>\
     <li>2 зубчики часнику</li>\
     <li>2 ст. л. яблучного оцту</li>\
     <li>2 ст. л. рослинного масла</li>\
     <li>щіпку солі і чорного меленого перцю</li>\
     </ul>"
    },

        {title:'Салат з кабачків і капусти',
     photo:"receips-images/salat-zkabachkv.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>1 молодий кабачок</li>\
     <li>200 г молодої капусти</li>\
     <li> невелика цибулина</li>\
     <li>середній пучок кропу</li>\
     <li>щіпка солі</li>\
     <li>2 ст. л. лимонного соку</li>\
     <li>щіпка свіжомеленого чорного перцю</li>\
     </ul>"
    },
      {title:'Панцанела',
     photo:"receips-images/salat-italyskiy.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>1 огірок</li>\
     <li>1 помідор</li>\
     <li>1 скибочка білого хліба</li>\
     <li> 1 невелика цибулина</li>\
     <li>невеликий шматочок бринзи</li>\
     <li>1/3 банки оливок</li>\
     <li>1 ст. л. оливкової олії</li>\
     <li>гілочка базиліку</li>\
     <li>кілька крапель бальзамічного оцту</li>\
     </ul>"
    },
    {title:'Томатний суп Гаспачо',
     photo:"receips-images/sup-gaspacho.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>помідори - 15 шт. (соковиті стиглі і м'ясисті) </li>\
     <li>огірок - 4 великі шт</li>\
     <li>болгарський перець - 3 шт. (червоний)</li>\
     <li>часник - 4 великих зубчики (краще молодий)</li>\
     <li>хліб - 3 великих шматки (білий або з висівками, черствий) </li>\
     <li>цибуля кримська - 1 шт. або шалот - 5-6 шт. </li>\
     <li>рослинна олія оливкова - 125 мл </li>\
     <li>оцет бальзамічний або червоний винний - 4 ст. л.</li>\
     <li>морська сіль - 1 ст. л. (без гірки)</li>\
     <li>зелень - петрушка - 1 пучок </li>\
      <li> табаско гострий соус - кілька крапель за бажанням. </li>\
      </ul>"
    },

     {title:'Курячий бульйон',
     photo:"receips-images/sup_bulyon.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>морква, цибуля - по 1/2-1 шт</li>\
     <li>часник - 2-3 зубчики</li>\
     <li>корінь селери (або стебла) - 20-30 г</li>\
     <li>спагетті (або інші макаронні вироби) - 50 г</li>\
     <li>зелень - 1/2 пучка</li>\
     <li>чилі (за бажанням) - 2-3 кільця </li>\
     <li>сіль, перець, чебрець.</li>\
     <li>частина курячої тушки (або стегна, крила і т. д.) - 400-500 г</li>\
     </ul>"
    },
    
    {title:'Суп з фрикадельками',
     photo:"receips-images/supz-frikadelkami.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>м'ясний бульйон (або вода) - 1,5 л</li>\
     <li>цибуля - 1 шт. + 0,5 шт</li>\
     <li>морква - 1 шт</li>\
     <li>перець болгарський - 0,5 шт</li>\
     <li>картопля - 2-3 шт</li>\
     <li>олія соняшникова - 2 ст.л</li>\
     <li>фарш м'ясний - 300 г</li>\
     <li>яйця курячі - 1 шт</li>\
     <li>яйця курячі - 1 шт</li>\
     <li>петрушка (зелень), сіль, перець чорний (мелений) - за смаком</li>\
     </ul>"
     },
    
     {title:'Сирний суп',
     photo:"receips-images/suchiy-sup.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>Куряче філе 300 г</li>\
     <li>Цибуля ріпчаста 1 шт</li>\
     <li>Петрушка корінь 100 г</li>\
     <li>Морква 1 шт</li>\
     <li>Картопля 3 шт</li>\
     <li>Сир плавлений 400 г</li>\
     <li>Масло вершкове 50 г</li>\
     <li>Вермішель дрібна 4 ст. л</li>\
     <li>Лавровий лист 3 шт</li>\
     <li>Базилік сухий 1 ч. л</li>\
     <li>Перець чорний мелений 1 щіпка</li>\
     <li>Сіль 1 ст. л</li>\
     <li>Вода 4 л.</li>\
     </ul>"
     },
   
    {title:'Овочеве рагу зі свининою',
     photo:"receips-images/ragoo.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>500 г молодої картоплі</li>\
     <li>1 велика цибулина (теж з нового врожаю)</li>\
     <li>1 молоденька морква</li>\
     <li>200 г стручкової квасолі</li>\
     <li>2-3 зубчики часнику</li>\
     <li>трохи рослинного масла</li>\
     <li>2 лаврових листочка</li>\
     <li>по дрібці солі і перцю</li>\
     <li>близько 200 г свинячої м'якоті (досить пісної)</li>\
     <li>1 неповний стакан зеленого горошку (в очищеному вигляді)</li>\
      </ul>"
     },
     {title:'Картопляна запіканка з грибами і куркою',
     photo:"receips-images/zapikanka.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>картопля — 3-4 шт</li>\
     <li>куряче філе — 1 шт</li>\
     <li>яйця — 1 шт</li>\
     <li>борошно — 2 ст. л</li>\
     <li>помідори — 1-2 шт</li>\
     <li>печериці — 5-7 шт</li>\
     <li>сир твердий — 100 г</li>\
     <li>соняшникова олія</li>\
     <li>сіль, спеції.</li>\
     </ul>"
    }, 
      
      {title:'Спагеті з тунцем',
     photo:"receips-images/spageti-ztuncem.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4> <ul class='foodingredients'>\
     <li>300-350 г свіжого або мороженого філе тунця</li>\
     <li>340 г спагеті</li>\
     <li>1 лимон</li>\
     <li>3 зубчики часнику</li>\
     <li>1/4 чайної ложки шматочків червоного перцю</li>\
     <li>1 столова ложка оливкової олії</li>\
     <li>1/4 склянки Савіньон Блан</li>\
     <li>1/4 склянки петрушки</li>\
     <li>1/4 або 1/2 склянки води, в якій варилися спагеті</li>\
     <li>сіль і чорний перець за смаком</li>\
     <li>пармезан, пекарино або інший твердий сир з овечого молока.</li>\
     </ul>"
    },

    {title:'Плов',
     photo:"receips-images/plow.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4> <ul class='foodingredients'>\
     <li>свинина - 250 г</li>\
     <li>цибуля ріпчаста - 1 шт</li>\
     <li>морква - 1 шт</li>\
     <li>рис довгозернистий - 2 склянки</li>\
     <li>часник - 2-3 зубчики</li>\
     <li>приправа - за смаком</li>\
     <li>перець мелений і сіль - за смаком</li>\
     <li>кетчуп для шашлику - 2-3 ст. л</li>\
     <li>вода - 3 ст.</li>\
     </ul>"
    }, 
    {title:'Яблучний штрудель',
     photo:"receips-images/apple-strudel.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li style='text-decoration:underline;'>Для тіста:</li>\
     <li>250 г борошна</li>\
     <li>жовток 1 яйця</li>\
     <li>3 ст. ложки рослинної олії</li>\
     <li>130 мл теплої кип'яченої води</li>\
     <li>1 ст. ложка свіжевіджатого лимонного соку</li>\
     <li>8-10 ст. топленого масла</li>\
     <li>щіпка солі.</li>\
     <li style='text-decoration:underline;'>Для начинки:</li>\
     <li>1 кг яблук (соковиті, кисло-солодкі)</li>\
     <li>3-4 столові ложки цукру</li>\
     <li>1 здобна булочка</li>\
     <li>топлене масло для змащування форми.</li>\
     </ul>"
    }, 

     {title:'Шоколадні мафіни',
     photo:"receips-images/muffins.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>борошно пшеничне – 150 г</li>\
     <li>цукровий пісок – 60 г</li>\
     <li>какао-порошок - 1 ст. ложка</li>\
     <li>шоколад молочний – 100 г</li>\
     <li>молоко - 50 мл</li>\
     <li>розпушувач тіста - 1 ч. л</li>\
     <li>куряче яйце - 1шт</li>\
     <li>масло вершкове – 60 г</li>\
     </ul>"
    }, 
    
    {title:'Класична шарлотка з яблуками',
     photo:"receips-images/Sharlotka-z-yablukami.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4> <ul class='foodingredients'>\
     <li>яблука — 1 кг</li>\
     <li>цукор — 1 стакан</li>\
     <li>яйця — 4 шт</li>\
     <li>борошно — 1 склянку</li>\
     <li>розпушувач тіста — 0,5 ч. л</li>\
     <li>сіль — щіпка</li>\
     <li>манна крупа — 1 ст. л</li>\
    </ul>"
    }, 

   {title:'Фруктовий салат',
     photo:"receips-images/fruit_salad.jpg",
     info:"<h4 style='margin-top:0px;'>Інгредієнти</h4><ul class='foodingredients'>\
     <li>1 чашка полуниці</li>\
     <li>1 чашка черешні</li>\
     <li>1/2 чашки чорниці</li>\
     <li>1/2 червоного яблука</li>\
     <li>1/2 персика</li>\
     <li>1 ківі</li>\
     <li>2 ст. л. лимонного соку</li>\
     </ul>"
    },  
  ];
 
$(".food-list").click(function() {
var foodIndex=$(this).data("id");
$(".foodlist").removeAttr("data-toggle");  
$(".foodlist").removeAttr("data-target").removeData("target"); 
$(".food-photo").removeAttr("src");  
$(".modal-title").empty();
$(".modal-body").empty(); 
$(this).attr("data-toggle","modal");
$(this).attr("data-target","#myModal");
$(".food-photo").attr("src",array[foodIndex].photo);
$(".modal-title").html(array[foodIndex].title);
$(".modal-body").html(array[foodIndex].info);

});

$(".fa-navicon").click(function(){
  $(".hidden-content").toggle("fast");
  });
