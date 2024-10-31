


function converting() {
	showHide();
	
	const a5Input = getA5Input();
	console.log(a5Input);
	$.ajax({
		type: 'POST',
		url: 'ta01/api/convert',
		data: { a5Input: a5Input },
		success: function(response) {
			updateTable(response);
		},
		error: function(error) {
			console.error('Error:', error);
		}
	});
}

function showHide() {
	
	if(!$('#form_input').is(':visible')) {
	    $('#form_input').show();
	} else {
		$('#form_input').hide();
	}

	if(!$('#table_a5').is(':visible')) {
	    $('#table_a5').show();
	} else {
		$('#table_a5').hide();
	}
}

function updateTable(data) {
	$('#header_row').empty();
	data.header.forEach(line => {
		$('#header_row').append(`<th>${line}</th>`);
	});


	$('#table_a5 tbody').empty();
	data.body.forEach(row => {
		let tds = row.map(cell => `<td>${cell}</td>`).join('');
		$('#table_a5 tbody').append(`<tr>${tds}</tr>`);
	});
}

function getA5Input() {
	return $('#a5_input_with_tab').val().toString();
}
