// Load the Visualization API and the corechart package.
google.charts.load('current', {
	'packages' : [ 'corechart' ]
});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {

	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Topping');
	data.addColumn('number', 'Slices');
	data.addRows([ [ 'Mushrooms', 3 ], [ 'Onions', 1 ], [ 'Olives', 1 ],
			[ 'Zucchini', 1 ], [ 'Pepperoni', 2 ] ]);

	// Set chart options
	var options = {
		'title' : 'How Much Pizza I Ate Last Night',
		'width' : 400,
		'height' : 300
	};

	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document
			.getElementById('chart_div'));
	chart.draw(data, options);
}

function hide(obj) {
	jQuery.ajax({
        type: "GET",
        url: "http://localhost:8080/rest/sales-by-price",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data, status, jqXHR) {
        	alert("Status: " + status + "\nItem[0]:" + data[0].name);
        },

        error: function (jqXHR, status) {
        	alert("Status: " + status + "\nError:" + jqXHR);
        }
	});
}