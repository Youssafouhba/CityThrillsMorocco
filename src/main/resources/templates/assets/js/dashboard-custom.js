/* ----------------- Start Document ----------------- */
(function($){
"use strict";
$(document).ready(function(){

/*----------------------------------------------------*/
 /*Mobile slick nav
/*----------------------------------------------------*/


/*----------------------------------------------------*/
    /*  Counters
/*----------------------------------------------------*/
    $(window).on('load resize', function() {
		$('.dashboard-stat-content h5').counterUp({
	        delay: 100,
	        time: 800
	    });
    });

    $(function () {
      $('[data-toggle="tooltip"]').tooltip()
    })
// ------------------ End Document ------------------ //
});

})(this.jQuery);
