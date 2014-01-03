function TopQuote30Days(id) {

	this.panelId = id, this.show = function() {
		var id = this.panelId;
		var dg_rt_topquotes = id + "dg_topquotes";
		var dataMap = {};
		$('#' + id).append($("<div></div>").attr({
			id : dg_rt_topquotes
		}));
		
		$.ajax({
			type : 'GET',
			url : '/stockapp-frontend/home/topquotes/30',
			async : false
		}).done(function(data){
			
			$.each(data, function(index, element){
				if ( dataMap[element.fetchDate] == undefined ) {
					dataMap[element.fetchDate] = element.symbol + "(" + element.changePercentage + ")";
				} else {
					var symbollist = dataMap[element.fetchDate];
					symbollist = symbollist + "," + element.symbol + "(" + element.changePercentage + ")";
					dataMap[element.fetchDate] =symbollist; 
				}
			});
			console.log(dataMap);
			for(var fetchDate in dataMap) {
				var content = $('<ul class="dateul"></ul>');
				content.append($('<li class="symbolli">' + fetchDate + '</li>'));
				var symbols = dataMap[fetchDate].split(",");
				for(var i = 0; i < symbols.length ; i++) {
					content.append($('<li class="symbolli">' + symbols[i] + '</li>'));
				}
				content.append("<br/>");
				$('#' + dg_rt_topquotes).append(content); 
			}
		});
		
	};

}
