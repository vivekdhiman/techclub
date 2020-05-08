(function($){

$(document).ready(function ($) {

     jQuery('#map').CustomMap();


     ////------- Testimonials Carousel
    
    var testimonial = $(".testimonial-wrapper");
    testimonial.owlCarousel({
        pagination: false,
        navigation : true,
        slideSpeed : 1000,
        stopOnHover: true,
        autoPlay: 3000,
        singleItem: true,
        transitionStyle : "fade",
        navigationText: ['<i class="fa fa-chevron-left"></i>', '<i class="fa fa-chevron-right"></i>']
    });
    
    /*----------------------------------------------------*/
	/*	Nav Menu & Search
	/*----------------------------------------------------*/
	
	$(".nav > li:has(ul)").addClass("drop");
	$(".nav > li.drop > ul").addClass("dropdown");
	$(".nav > li.drop > ul.dropdown ul").addClass("sup-dropdown");
    
    
    
    
    
    /*---------------------------------------------------*/
    /* Progress Bar
    /*---------------------------------------------------*/
    $(document).ready(function($) {
	"use strict";

    
    
        $('.skill-shortcode').appear(function() {
            $('.progress').each(function(){ 
                $('.progress-bar').css('width',  function(){ return ($(this).attr('data-percentage')+'%')});
            });
        },{accY: -100});
        
        var get_path = window.location.pathname;
        $('.navbar-default .navbar-nav > li > a').each(function(){
            var get_anc = $(this).attr('href');
            if(get_anc == get_path){
                $(this).parents('ul').find('a').removeClass('active');
                $(this).addClass('active');
            }
        });


        var get_path = window.location.pathname;
        $('.navbar-default .navbar-nav > li > a').each(function(){
            var get_anc = $(this).attr('href');
            if(get_anc == get_path){
                $(this).parents('ul').find('a').removeClass('active');
                $(this).addClass('active');
            }
        });

    });    
    
    
    /*--------------------------------------------------*/
    /* Counter*/
    /*--------------------------------------------------*/ 
    $('.timer').countTo();

    $('.counter-item').appear(function() {
        $('.timer').countTo();
    },{accY: -100});
    
    
});

}(jQuery));