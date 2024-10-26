/**
 * 
 */


$(document).ready(function() {
	$('#table_csv').hide();
	$('#back_button').hide();

	$('#show_button').on('click', function() {
		var csvHeaderPhysical = getCsvHeaderInput();
		var csvContent = getCsvContentInput();

		$.ajax({
			type: 'POST',
			url: 'cs01/api/show',
			data: {
				csv_csvContent_content_header: $('#header_in_content').is(":checked"),
				csv_physical_header: csvHeaderPhysical,
				csv_content: csvContent
			},
			success: function(response) {
				console.log(response);
				updateTable(response);
			},
			error: function(error) {
				console.error('Error:', error);
			}
		});

		showHide();
	});


	$('#back_button').click(function() {
		backHide();

		var tableData = [];

		// Đọc header
		if (!$(this).val()) {
			$(this).parents('p').addClass('warning');
		}
		var headers = [];
		$('#table_csv thead tr:eq(1) th').each(function() {
			headers.push($(this).text().trim());
		});
		tableData.push(headers);  // Thêm headers vào mảng dữ liệu

		// Đọc nội dung (body)
		$('#table_csv tbody tr').each(function() {
			var rowData = [];
			$(this).find('td').each(function() {
				rowData.push($(this).text().trim());
			});
			tableData.push(rowData);  // Thêm từng dòng vào mảng dữ liệu
		});

		$.ajax({
			url: 'cs01/api/convert',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(tableData),
			success: function(response) {
				updateCsvContent(response.csvContent);
				console.log("Data sent successfully:", response);
			},
			error: function(xhr, status, error) {
				console.error("Error sending data:", error);
			}
		});
	});
});

function updateCsvContent(data) {
	$('#csv_content_input').val(data);
}

function updateTable(data) {
	$('#header_row_physical').empty();
	$('#header_row').empty();

	if (data.csvHeaderPhysical?.length != 1) {
		data.csvHeaderPhysical.forEach(header => {
			$('#header_row_physical').append(`<th>${header}</th>`);
		});
		$('#header_row_physical').show();
	} else {
	$('#header_row_physical').hide();
		
	}
	if ($('#header_in_content').is(":checked")) {
		// second header
		data.csvHeader.forEach(line => {
			$('#header_row').append(`<th>${line}</th>`);
		});
	}

	$('#table_csv tbody').empty();
	data.csvContent.forEach(row => {
		let tds = row.map(cell => `<td contenteditable='true'>${cell}</td>`).join('');
		$('#table_csv tbody').append(`<tr>${tds}</tr>`);
	});
}

function backHide() {
	$('#form_input').show();
	$('#table_csv').hide();
	$('#back_button').hide();
	$('#show_button').show();
}

function showHide() {
	$('#form_input').hide();
	$('#table_csv').show();
	$('#back_button').show();
	$('#show_button').hide();
}

function getCsvHeaderInput() {
	return $('#csv_physical_header_input').val().toString();
}

function getCsvContentInput() {
	return $('#csv_content_input').val().toString();
}
