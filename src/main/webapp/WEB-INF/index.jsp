<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>

<link rel="stylesheet" type="text/css" href="resources/jquery-easyui-1.3.2/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="resources/jquery-easyui-1.3.2/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="resources/jquery-plot/jquery.jqplot.css" />
<script type="text/javascript" src="resources/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="resources/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/jquery-plot/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="resources/jquery-plot/plugins/jqplot.dateAxisRenderer.min.js"></script>
<script type="text/javascript" src="resources/jquery-plot/plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="resources/js/realtime-quote-panel.js"></script>
<script type="text/javascript" src="resources/js/otc-realtime-quote-panel.js"></script>
<script type="text/javascript" src="resources/js/volume-chart-panel.js"></script>
<script type="text/javascript" src="resources/js/otc-volume-chart-panel.js"></script>
<script type="text/javascript" src="resources/js/ipo-panel.js"></script>



<style type="text/css">
	.datagrid .datagrid-cell {
		font-family : verdana;
		font-size : 11px;
	}
	
	.panel-title {
		font-family : verdana;
		font-size : 11px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var panel = new RealTimePanel("volume-panel");
		panel.showTopQuotes();
		var vPanel = new VolumeChartPanel("volume-panel");
		console.log('>>>>> in document ready function <<<<<<<<');
		var otcPanel = new OTCRealTimePanel("volume-panel");
		otcvPanel = new OTCVolumeChartPanel("volume-panel");
		var ipoPanel = new IPOPanel("volume-panel");
		$('#tt').tree({
			onSelect : function(node) {
				$("#volume-panel").empty();
				if ( node.text == 'Top Quotes') {
					panel.showTopQuotes();
				} else if ( node.text == "Top Quotes Less Than Dollar - Price Past 30 days") {
					vPanel.showPlot();
				} else if ( node.text == "Top OTC Quotes") {
					otcPanel.showTopQuotes();
				} else if ( node.text == "Top OTC Quotes - Price Past 30 Days") {
					otcvPanel.showPlot();
				} else if ( node.text == "IPOs") {
					ipoPanel.show();
				}
			}
		})
	});
</script>

<body class="easyui-layout">
	<div data-options="region:'west',title:'Navigation',split:true" style="width: 250px;">
		<ul id="tt" class="easyui-tree">
			<li><span id="1">Top Quotes</span></li>
			<li><span id="3">Top OTC Quotes</span></li>
			<li><span id="5">IPOs</span></li>
		</ul>
	</div>
	<!-- div id="data-panel" data-options="region:'center',title:'DashBoard'" style="padding: 5px; background: #eee;">
	<br/>
	<br/>
		<table class="easyui-datagrid" style="font-family: verdana;" data-options="fitColumns:true,singleSelect:true" title="Top 10 Quotes - NASDAQ">
			<thead>
				<tr>
					<th field='symbol' width="100%">Symbol</th>
					<th field="currentPrice" width="100%">Current Price</th>
					<th field="dollarChange" width="100%">Dollar Change</th>
					<th field="percentChange" width="100%">Percent Change</th>
					<th field="52WeekHigh" width="100%">52 Week High</th>
					<th field="52WeekLow" width="100%">52 Week Low</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="topQuote" items="${topQuotes}">
					<tr>
						<td><c:out value="${topQuote.symbol}" /></td>
						<td><c:out value="${topQuote.currentPrice}" /></td>
						<td><c:out value="${topQuote.dollarChange}" /></td>
						<td><c:out value="${topQuote.percentChange}" /></td>
						<td><c:out value="${topQuote.fiftyTwoWeekHigh}" /></td>
						<td><c:out value="${topQuote.fiftyTwoWeekLow}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br/>
		<br/>
		<table class="easyui-datagrid" style="font-family: verdana;" data-options="fitColumns:true,singleSelect:true" title="Top 20 Quotes Less Than Dollar - NASDAQ">
			<thead>
				<tr>
					<th field='symbol' width="80">Symbol</th>
					<th field="currentPrice" width="120">Current Price</th>
					<th field="dollarChange" width="120">Dollar Change</th>
					<th field="percentChange" width="120">Percent Change</th>
					<th field="52WeekHigh" width="120">52 Week High</th>
					<th field="52WeekLow" width="120">52 Week Low</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="topQuote" items="${topQuotesLessThanDollar}">
					<tr>
						<td><c:out value="${topQuote.symbol}" /></td>
						<td><c:out value="${topQuote.currentPrice}" /></td>
						<td><c:out value="${topQuote.dollarChange}" /></td>
						<td><c:out value="${topQuote.percentChange}" /></td>
						<td><c:out value="${topQuote.fiftyTwoWeekHigh}" /></td>
						<td><c:out value="${topQuote.fiftyTwoWeekLow}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div -->
	<div id="volume-panel" data-options="region:'center',title:'DashBoard'" style="padding: 5px; background: #eee;">
		<!--  div id="chartdiv" style="height:400px;width:300px; "></div -->
		
	</div>
	<div id="chartsWin" style='align:center;'></div>
</body>

</html>
