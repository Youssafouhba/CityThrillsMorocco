(function ($) {
	'use strict';
	// Preloader
	$(window).on('load', function() {
		
		if($('.loader-wrap').length){
			$('.loader-wrap').delay(1000).fadeOut(500);
		}
		
	
		if ($(".preloader-close").length) {
			$(".preloader-close").on("click", function(){
				$('.loader-wrap').delay(200).fadeOut(500);
			})
		}
	});

	jQuery(document).ready(function () {


        // Add Menu Item Current Class Auto
		function dynamicCurrentMenuClass(selector) {
			let FileName = window.location.href.split("/").reverse()[0];

			selector.find("li").each(function () {
				let anchor = $(this).find("a");
				if ($(anchor).attr("href") == FileName) {
					$(this).addClass("active");
				}
			});
			// if any li has .current elmnt add class
			selector.children("li").each(function () {
				if ($(this).find(".active").length) {
					$(this).addClass("active");
				}
			});
			// if no file name return
			if ("" == FileName) {
				selector.find("li").eq(0).addClass("active");
			}
		}
		
		if ($('.vacasky-vertical-nav .vacasky-navmenu .main-menu').length) {
			dynamicCurrentMenuClass($('.vacasky-vertical-nav .vacasky-navmenu .main-menu'));
		}

		// Add Class in body tag
		$('.vacasky-vertical-nav-toggle').on('click', function(e) {
			$('body').toggleClass('vacasky-vertical-nav--collapse');
			e.stopPropagation();
			e.preventDefault();
		});
	
		$('.vacasky-mobile-menu-opener').on('click', function(e) {
			$('body').toggleClass('vacasky-vertical-nav--active');
			e.stopPropagation();
			e.preventDefault();
		});

        // Sub-Menu Open On-Click
        $('.vacasky-navmenu ul.main-menu li.menu-item-has-children > a, .vacasky-navmenu ul.main-menu li.menu-item-has-children .submenu-opener').on("click", function(e){
            $(this).parent().toggleClass('nav-open');
            $(this).siblings('ul').slideToggle();
            e.stopPropagation();
            e.preventDefault();
        });


		// Datepickr / Flatpicker
		$(".flatpickr").flatpickr({
			// mode: "range",
			dateFormat: "d-M-Y",
			defaultDate: ["08-Aug-2023"],
			minDate: "today",
			// enableTime: true,
		});

		// Sticky Header
        var header = $("header");
        $(window).scroll(function() {
            var scroll = $(window).scrollTop();

            if (scroll >= 50) {
                header.addClass("sticky");
            } else {
                header.removeClass("sticky");
            }
        });

	});	
})(jQuery);

// Invoice Print
function printDiv(printCard) {
	var printContents = document.getElementById(printCard).innerHTML;
	var originalContents = document.body.innerHTML;

	document.body.innerHTML = printContents;

	window.print();

	document.body.innerHTML = originalContents;
}

// Hide header on scroll down
const nav = document.querySelector(".header");
// const horizontalnav = document.querySelector(".vacasky-horizontal-nav");
const scrollUp = "top-up";
let lastScroll = 500;

window.addEventListener("scroll", () => {
    const currentScroll = window.pageYOffset;
    if (currentScroll <= 500) {
        nav.classList.remove(scrollUp);
        // horizontalnav.classList.remove(scrollUp);
        return;
    }
    
    if (currentScroll > lastScroll) {
        // down
        nav.classList.add(scrollUp);        
        // horizontalnav.classList.add(scrollUp);  

    } else if (currentScroll < lastScroll) {
        // up
        nav.classList.remove(scrollUp);        
        // horizontalnav.classList.remove(scrollUp); 
    }
    lastScroll = currentScroll;
});