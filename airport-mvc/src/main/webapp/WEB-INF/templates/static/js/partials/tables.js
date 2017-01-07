$(document).ready(function() {
    $('.datatable').each(function() {
    	$(this).dataTable( {
		"columnDefs": [
				{ "orderable": false, "targets": $(this).data('disabled').split(',').map(function(item) { return parseInt(item, 10); }) }
			]
		} );
    });
} );