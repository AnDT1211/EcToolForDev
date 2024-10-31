



function generateBtnClick() {
	generate();
}


function generate() {

	const focus_out_field_name_input_str = getFocus_out_field_name_input();
	
	$.ajax({
		url: 'pc02/api/generate',
		type: 'POST',
		data: {
			have_check: $('#have_check').is(":checked"),
			focus_out_field_name_input_str: focus_out_field_name_input_str
		},
		success: function(response) {
			$('#txt_output').val(response.data);
		},
		error: function(xhr, status, error) {
			console.error("Error sending data:", error);
		}
	});
}

function getFocus_out_field_name_input() {
	return $('#focus_out_field_name_input').val().toString();
}
