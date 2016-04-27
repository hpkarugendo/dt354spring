function charts(data, ChartType) {
	var c = ChartType;
	var jsonData = data;
	google.load("visualization", "1", {
		packages : [ "corechart" ],
		callback : drawVisualization
	});
	function drawVisualization() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Item');
		data.addColumn('number', 'Revenue');
		$.each(jsonData, function(i, jsonData) {
			var value = jsonData.salesInPrice;
			var name = jsonData.name;
			data.addRows([ [ name, value ] ]);
		});

		var options = {
			title : "Top 5 Selling Items In Revenue",
			animation : {
				duration : 3000,
				easing : 'out',
				startup : true
			},
			colorAxis : {
				colors : [ '#54C492', '#cc0000' ]
			},
			datalessRegionColor : '#dedede',
			defaultColor : '#dedede'
		};

		var chart;
		if (c == "ColumnChart") // Column Charts
			chart = new google.visualization.ColumnChart(document
					.getElementById('chart_div'));
		else if (c == "PieChart") // Pie Charts
			chart = new google.visualization.PieChart(document
					.getElementById('piechart_div'));
		else if (c == "BarChart") // Bar Charts
			chart = new google.visualization.BarChart(document
					.getElementById('bar_div'));
		else if (c == "GeoChart") // Geo Charts
			chart = new google.visualization.GeoChart(document
					.getElementById('regions_div'));

		chart.draw(data, options);
	}
}

/*function hide(obj) {
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
}*/