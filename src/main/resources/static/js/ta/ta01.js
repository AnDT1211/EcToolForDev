/**
 * 
 */




$(document).ready(function() {
	$('#a5_result_with_tab').focusout(async function() {
		const a5Input = getA5Input();
        const mdResult = await getMdResultFromA5Format(a5Input); 
		
		console.log(mdResult);
        setValueToMD(mdResult);
	});
});

function getA5Input() {
	return $('#a5_result_with_tab').val().toString();
}

function setValueToMD(a5Input) {
	$("#md_result").text(a5Input);
}

async function getMdResultFromA5Format(a5Input) {
	$.post("ta01/api/convert", 
	    {
	        a5Input: a5Input
	    },
	    function(response) {
	        console.log("Response:", response);
			return response.filter('#one').text();;
	    }
	).fail(function(error) {
	    console.error("Error:", error);
		return "";
	});
}

