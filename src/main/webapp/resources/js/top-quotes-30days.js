function TopQuote30Days(id) {

	this.panelId = id, this.show = function() {
		var id = this.panelId;
		var dg_rt_topquotes = id + "dg_topquotes";
		

		$('#' + id).append($("<table></table>").attr({
			id : dg_rt_topquotes
		}));
		
		$.ajax({
			type : 'GET',
			url : '/stockapp-frontend/home/topquotes/30',
			async : false
		}).done(function(data){
			var currentDate;
			var columns = new Array();
			$.each(data, function(index, element){
				currentDate = element.fetchDate;
				if ( $.inArray( currentDate, columns) == -1 ) columns.push(currentDate);
			});
			console.log(columns);
		});
		
	};

}
