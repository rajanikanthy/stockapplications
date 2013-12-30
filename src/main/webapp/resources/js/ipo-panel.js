function IPOPanel(id) {

	this.panelId = id, this.show = function() {
		var id = this.panelId;
		var dg_rt_upcomingipos = id + "_dg_upcomingipos";
		

		$('#' + id).append($("<table></table>").attr({
			id : dg_rt_upcomingipos
		}));
		
		var columns = [ [ {
			field : 'companyName',
			title : 'Company Name',
			width : 200
		}, {
			field : 'symbol',
			title : 'Symbol',
			width : 200
		}, {
			field : 'market',
			title : 'Market',
			width : 200
		}, {
			field : 'price',
			title : 'Price',
			width : 200
		}, {
			field : 'shares',
			title : 'Shares',
			width : 200
		}, {
			field : 'expectedDate',
			title : 'ExpectedDate',
			width : 200
		}, ] ];

		$('#' + dg_rt_upcomingipos).datagrid({
			url : '/stockapp-frontend/ipo/upcoming',
			columns : columns,
			fitColumns : true,
			singleSelect : true,
			method : 'get',
			title : 'Upcoming IPOs'
		});

		$('#' + dg_rt_upcomingipos).datagrid('load', {});
	}

}
