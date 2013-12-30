<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="resources/jquery-easyui-1.3.2/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="resources/jquery-easyui-1.3.2/themes/icon.css" />
<script type="text/javascript" src="resources/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="resources/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>

<div id="body" style="font-size: 12px;">
	<table>
		<tr>
			<td>
				<table class="easyui-datagrid" style="width: 690px; height: 320px; font-family: verdana;" data-options="singleSelect:true" title="Top Quotes - NASDAQ">
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
			</td>
			<td>
				<table class="easyui-datagrid" style="width: 690px; height: 320px; font-family: verdana;" data-options="singleSelect:true" title="Top Quotes - NASDAQ">
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
			</td>
		</tr>

	</table>


</div>