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
	var jsonData;
	var items = new Array();
	var prices = new Array();
	var rows = new Array();
	var item;
	var price;
	
	jQuery.ajax({
		type : "GET",
		url : "http://localhost:8080/rest/sales-by-price",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data, status, jqXHR) {
			/*alert("Status: " + status + "\nItem[0]:" + data[0].name);*/
			jsonData = data;
		},

		error : function(jqXHR, status) {
			alert("Status: " + status + "\nError:" + jqXHR);
		}
	});
	
	alert(jsonData.length);
	for(var i = 0; i < jsonData.length; i++){
		items[i] = jsonData[i].name;
		prices.push(jsonData[i].salesInPrice);
	}
	
	for(var i = 0; i < 10; i++){
		item = items[i];
		price = parseInt(prices[i]);
		rows.push([item, price]);
	}
	
	var data2 = new google.visualization.DataTable();
	data2.addColumn('string', 'Items');
	data2.addColumn('number', 'Sales');
	data2.addRows(rows);
	
	var options = {
		      title: 'Top 10 Sales In Price',
		      'width':600,
	           'height':300
		    };
	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    chart.draw(data2, options);

}

function hide(obj) {
	jQuery.ajax({
		type : "GET",
		url : "http://localhost:8080/rest/sales-by-price",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data, status, jqXHR) {
			alert("Status: " + status + "\nItem[0]:" + data[0].name);
		},

		error : function(jqXHR, status) {
			alert("Status: " + status + "\nError:" + jqXHR);
		}
	});
}