$(document).ready(function(){
	$('.datetime').each(function() {
		var picker = $(this).datetimepicker({
	       format: 'YYYY-MM-DDTHH:mm',
		   sideBySide: true
	    });
	});
	
    $('.datatable').each(function() {
		var target = $(this).data('disabled').split(',').map(function(item) { return parseInt(item, 10); });
    	$(this).dataTable( {
		"columnDefs": [
				{ "orderable": false, "targets": target }
			]
		} );
    });
	
	$('.single-checkbox input[type="checkbox"]').on('click', function() {
		console.log($(this));
        $('.single-checkbox input[type="checkbox"]').not(this).prop('checked', false);
    });
	
	$('.btn-remove-disabled').on('click', function(e) {
		e.preventDefault();
		$(this).parent().find('.error-msg').fadeIn('fast');
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
} );