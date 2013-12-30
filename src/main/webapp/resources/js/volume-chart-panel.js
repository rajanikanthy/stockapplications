function VolumeChartPanel(id) {
	this.panelId = id, this.topQuotes = new Array(),

	this.hidePlot = function() {
		var cp = $("#" + this.panelId);
		if (cp != undefined) {
			cp.empty();
		}
	}, this.ajaxRenderer = function(url, plot, options) {
		var volumedata = [ [], [], [] ];
		$.ajax({
			type : 'GET',
			url : url,
			async : false,
			dataType : 'json',
			success : function(stats) {
				if (stats == undefined || stats.length == 0) {
					volumedata[0].push([ 0, 0 ]);
					volumedata[1].push([ 0, 0 ]);
					volumedata[2].push([ 0, 0 ]);
				}
				$.each(stats, function(index, stat) {
					volumedata[0].push([ stat.fetchDate, stat.dayClose]);
					volumedata[1].push([ stat.fetchDate, stat.dayOpen ]);
					volumedata[2].push([ stat.fetchDate, stat.dayHigh ]);
					// console.log(stat);
				});

			}
		});
		console.log(volumedata);
		return volumedata;
	}, this.volumeDataRenderer = function(url, plot, options) {
		var volumedata = [ [] ];
		$.ajax({
			type : 'GET',
			url : url,
			async : false,
			dataType : 'json',
			success : function(stats) {
				if (stats == undefined || stats.length == 0) {
					volumedata[0].push([ 0, 0 ]);
				}
				$.each(stats, function(index, stat) {
					volumedata[0].push([ stat.fetchDate, stat.volume]);
					// console.log(stat);
				});

			}
		});
		console.log(volumedata);
		return volumedata;
	}, this.showPlot = function() {
		var me = this;
		$.ajax({
			type : "GET",
			url : "/stockapp-frontend/realtime/lessthandollar",
			async : false,
			success : function(data) {
				$.each(data, function(index, symbol) {
					var priceChart = $("<div></div>").attr({
						id : symbol.symbol + "_pricechart",
						style : "width:1024px;height:300px;background:#fafafa;",
						display : 'inline',
						"data-options" : "iconCls:'icon-save',closable:true,collapsible:true,minimizable:true,maximizable:true"
					});
					var volumeChart = $("<div></div>").attr({
						id : symbol.symbol + "_volumechart",
						style : "width:1024px;height:300px;background:#fafafa;",
						display : 'inline',
						"data-options" : "iconCls:'icon-save',closable:true,collapsible:true,minimizable:true,maximizable:true"
					});
					
					$('#' + me.panelId).append([priceChart, volumeChart]);
					
					var plot1 = $.jqplot(symbol.symbol + "_pricechart", "/stockapp-frontend/history/quotes/" + symbol.symbol
							+ "/30", {
						dataRenderer : me.ajaxRenderer,
						title : "SYMB:" + symbol.symbol,
						axes : {
							xaxis : {
								renderer : $.jqplot.DateAxisRenderer
							}
						},
						series : [ {label : 'Day Close'}, {label : 'Day Open'}, {label : 'Day High'}],
						legend : { show : true }
							
					});
					
					var plot2 = $.jqplot(symbol.symbol + "_volumechart", "/stockapp-frontend/history/quotes/" + symbol.symbol
							+ "/30", {
						dataRenderer : me.volumeDataRenderer,
						title : "SYMB:" + symbol.symbol,
						axes : {
							xaxis : {
								renderer : $.jqplot.DateAxisRenderer
							}
						},
						series : [ {label : 'Volume', renderer:$.jqplot.EnhancedLegendRenderer }],
						legend : { show : true },
						seriesDefaults: {
				            renderer:$.jqplot.BarRenderer,
				            pointLabels: { show:true } 
				        }
							
					});
					
					
				});
			}
		});
	};

}