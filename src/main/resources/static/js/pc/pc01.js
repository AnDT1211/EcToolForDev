



function generateBtnClick() {
	console.log("hello");

	generate();
}


function generate() {

	const button_name_input_str = getButton_name_input();
	const sub_dialog_name_input_str = getSub_dialog_name_input();
	const focus_out_field_name_input_str = getFocus_out_field_name_input();
	
	$.ajax({
		url: 'pc01/api/generate',
		type: 'POST',
		data: {
			have_check: $('#have_check').is(":checked"),
			button_name_input_str: button_name_input_str,
			sub_dialog_name_input_str: sub_dialog_name_input_str,
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


function getButton_name_input() {
	return $('#button_name_input').val().toString();
}


function getSub_dialog_name_input() {
	return $('#sub_dialog_name_input').val().toString();
}


function getFocus_out_field_name_input() {
	return $('#focus_out_field_name_input').val().toString();
}









