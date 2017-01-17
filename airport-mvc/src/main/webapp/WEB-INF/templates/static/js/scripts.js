$(document).ready(function(){
	$('.datetime').each(function() {
		var picker = $(this).datetimepicker({
	       format: 'YYYY-MM-DDTHH:mm',
		   sideBySide: true
	    });
	});
    $('.datatable').each(function() {
    	$(this).dataTable( {
		"columnDefs": [
				{ "orderable": false, "targets": $(this).data('disabled').split(',').map(function(item) { return parseInt(item, 10); }) }
			]
		} );
    });
	$('.single-checkbox input[type="checkbox"]').on('click', function() {
		console.log($(this));
        $('.single-checkbox input[type="checkbox"]').not(this).prop('checked', false);
    });
} );


/*$(document).ready(function ($) {

	$('.btn-update').each(function() {
		$(this).on('click', function() {
			var form = $('#update');

			if(form.length>0) {
				if(!form.hasClass('active')) {
					form.fadeIn('fast');
					form.addClass('active');
				}

				var row = $(this).closest('tr'),
					columns = row.find('td');
				columns.each(function() {
					var name = $(this).data('name'),
						input = form.find('#' + name),
						type = $(this).data('type');
					if(input.length > 0) {
						if(type!=undefined && type=="select") {
							var option = $(this).data('option');
							input.val(option);
						}
						else {
							input.val($(this).text().trim());
						}
					}
				});
			}
		});
	});

	$('.btn-cancel').each(function() {
		$(this).on('click', function() {
			var form = $(this).closest('form');
			form.find(".form-control").val("");
			$(this).closest('.hidden').fadeOut('fast').removeClass('active');
		});
	});

	$("#add-form").validate({
		submitHandler: function(form) {
			form.submit();
		}
	});

	$("#update-form").validate({
		submitHandler: function(form) {
			form.submit();
		}
	});

});
$(document).ready(function() {
    $('.datatable').each(function() {
    	$(this).dataTable( {
		"columnDefs": [
				{ "orderable": false, "targets": $(this).data('disabled').split(',').map(function(item) { return parseInt(item, 10); }) }
			]
		} );
    });
} );

    */