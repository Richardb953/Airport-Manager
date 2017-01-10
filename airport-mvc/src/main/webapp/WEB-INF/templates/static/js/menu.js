/**
 * Created by richa on 8.1.2017.
 * funkcia na ofarbovanie menu podla aktualnej stranky
 */

$(function() {
    var pgurl = window.location.href.substr(window.location.href.lastIndexOf("pa165/")-1); //URL za pa165/

    $("#nav ul li a").each(function(){
        if($(this).attr("href") == pgurl || $(this).attr("href") == '' )
            $(this).parent().addClass("active");
    })
});