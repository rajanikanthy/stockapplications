function RealTimePanel(id) {
	var me = this;
	this.panelId = id, this.hideTopQuotes = function() {
		$('#' + this.panelId).empty();
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
	}, this.showTopQuotes = function() {
		var me = this;
		var id = this.panelId;
		var dg_rt_morethandollar = id + "_dg_realtime_morethandollar";
		var dg_rt_lessthandollar = id + "_dg_realtime_lessthandollar";
		var dg_rt_top_losers = id + "_dg_realtime_toplosers";

		$('#' + id).append($("<table></table>").attr({
			id : dg_rt_morethandollar
		}));
		$('#' + id).append($("<br/>"));
		$('#' + id).append($("<br/>"));

		$('#' + id).append($("<table></table>").attr({
			id : dg_rt_lessthandollar
		}));
		
		$('#' + id).append($("<br/>"));
		$('#' + id).append($("<br/>"));

		$('#' + id).append($("<table></table>").attr({
			id : dg_rt_top_losers
		}));

		var columns = [ [ {
			field : 'symbol',
			title : 'Symbol',
			width : 100,
			formatter : function(val) {
				var p = $("<ul></ul>").attr({
					id : 'details',
					style : 'list-style-type:none;margin:0px;padding:2px;font-weight:bold;color:blue;text-decoration:underline;',
				}).append($("<li></li>").text("5d").attr({
					id : val + '_5',
					style : 'display:inline;padding:2px;cursor:pointer;'
				})).append($("<li></li>").text("30d").attr({
					id : val + '_30',
					style : 'display:inline;padding:2px;cursor:pointer;'
				})).append($("<li></li>").text("60d").attr({
					id : val + '_60',
					style : 'display:inline;padding:2px;cursor:pointer;'
				}));
				//console.dir(p.clone().wrap('<p>').parent().html());
				return val + p.clone().wrap('<p>').parent().html();
			}
		}, {
			field : 'currentPrice',
			title : 'Current Price',
			width : 75
		}, {
			field : 'dollarChange',
			title : 'Dollar Change',
			width : 75
		}, {
			field : 'percentChange',
			title : 'Percent Change',
			width : 75
		}, {
			field : 'fiftyTwoWeekHigh',
			title : '52 Week High',
			width : 75
		}, {
			field : 'fiftyTwoWeekLow',
			title : '52 Week Low',
			width : 75
		}] ];

		$('#' + dg_rt_morethandollar).datagrid({
			url : '/stockapp-frontend/realtime/morethandollar',
			columns : columns,
			fitColumns : true,
			singleSelect : true,
			method : 'get',
			title : 'Top Quotes More Than Dollar'
		});

		$('#' + dg_rt_lessthandollar).datagrid({
			url : '/stockapp-frontend/realtime/lessthandollar',
			columns : columns,
			fitColumns : true,
			singleSelect : true,
			method : 'get',
			title : 'Top Quotes Less Than Dollar'
		});
		
		$('#' + dg_rt_top_losers).datagrid({
			url : '/stockapp-frontend/realtime/toplosers',
			columns : columns,
			fitColumns : true,
			singleSelect : true,
			method : 'get',
			title : 'Top Losers',
			onLoadSuccess: function(){
				$('ul#details > li').click(function(event) {
					$("div#chartsWin").empty();
					$('div#chartsWin').window({
						width: 600,
						height : 600,
						title : event.target.id,
						modal : true,
						onOpen : function() {
							var symbol = event.target.id.split('_')[0];
							var duration = event.target.id.split('_')[1];
							var priceChart = $("<div></div>").attr({
								id : symbol + "_pricechart",
								style : "width:500;height:200px;background:#fafafa;",
								display : 'inline',
								"data-options" : "iconCls:'icon-save',closable:true,collapsible:true,minimizable:true,maximizable:true"
							});
							var volumeChart = $("<div></div>").attr({
								id : symbol + "_volumechart",
								style : "width:500;height:200px;background:#fafafa;",
								display : 'inline',
								"data-options" : "iconCls:'icon-save',closable:true,collapsible:true,minimizable:true,maximizable:true"
							});
							
							$('div#chartsWin').append([priceChart, volumeChart]);
							var plot1 = $.jqplot(symbol + "_pricechart", "/stockapp-frontend/history/quotes/" + symbol
									+ "/" + duration, {
								dataRenderer : me.ajaxRenderer,
								title : "SYMB:" + symbol,
								axes : {
									xaxis : {
										renderer : $.jqplot.DateAxisRenderer
									}
								},
								series : [ {label : 'Day Close'}, {label : 'Day Open'}, {label : 'Day High'}],
								legend : { show : true }
									
							});
							
							var plot2 = $.jqplot(symbol + "_volumechart", "/stockapp-frontend/history/quotes/" + symbol
									+ "/" + duration, {
								dataRenderer : me.volumeDataRenderer,
								title : "SYMB:" + symbol,
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
						}
					});
				});
			}
		});

		$('#' + dg_rt_morethandollar).datagrid('load', {});

		$('#' + dg_rt_lessthandollar).datagrid('load', {});
		
		$('#' + dg_rt_top_losers).datagrid('load', {});

	}

}
