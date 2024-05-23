(function ($) {
	'use strict';

	jQuery(document).ready(function () {

        var cleave_card = new Cleave('.cleave_card', {
            creditCard: true,
            onCreditCardTypeChanged: function (type) {

            }
        });

        var cleave_date = new Cleave('.cleave_date', {
            date: true,
            delimiter: '-',
            datePattern: ['Y', 'm', 'd']
        });

        // var cleave_time = new Cleave('.cleave_time', {
        //     time: true,
        //     delimiter: ' : ',
        //     timePattern: ['h', 'm']
        // });

        // var cleave_time_second = new Cleave('.cleave_time_second', {
        //     time: true,
        //     delimiter: ' : ',
        //     timePattern: ['h', 'm', 's']
        // });

        // var cleave_neumeral = new Cleave('.cleave_neumeral', {
        //     numeral: true,
        //     numeralThousandsGroupStyle: 'thousand'
        // });

        // var cleave_block = new Cleave('.cleave_block', {
        //     blocks: [4, 3, 3, 4],
        //     uppercase: true,

        // });

        // var cleave = new Cleave('.cleave_delimiter', {
        //     delimiter: ' · ',
        //     blocks: [3, 3, 3],
        //     uppercase: true
        // });

        // var cleave = new Cleave('.cleave_delimiters', {
        //     delimiters: [' . ', ' . ', ' - '],
        //     blocks: [3, 3, 3, 2],
        //     uppercase: true
        // });

        // var cleave = new Cleave('.cleave_prefix', {
        //     prefix: 'PREFIX',
        //     delimiter: ' - ',
        //     blocks: [6, 4, 4, 4],
        //     uppercase: true
        // });

        // var cleave_phone = new Cleave('.cleave_phone', {
        //     phone: true,
        //     phoneRegionCode: '{country}'
        // });

    });

})(jQuery);
