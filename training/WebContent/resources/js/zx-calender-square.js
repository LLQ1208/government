(function(a) {
	var defaults = {
		maxDate: new Date(),
		currentDate: new Date(),
		minDate: new Date(1970, 0, 1),
		callBackFn: function() {},
		addDates:[],
		initCallBack: false
	};

	//通用函数
	var F = {
		//计算某年某月有多少天
		getDaysInMonth: function(year, month) {
			return new Date(year, month + 1, 0).getDate();
		},
		//计算某月1号是星期几
		getWeekInMonth: function(year, month) {
			return new Date(year, month, 1).getDay();
		},
		getMonth: function(m) {
			return ['01','02','03','04','05','06','07','08','09','10','11','12'][m];
		},
		//计算年某月的最后一天日期
		getLastDayInMonth: function(year, month) {
			return new Date(year, month, this.getDaysInMonth(year, month));
		}
	};

	var natsume = {
		value: {
			year: '',
			month: '',
			date: ''
		},
		ctx:'',
		currentDate: '',
		config: {},
		lastCheckedDate: {
			year: '',
			month: '',
			date: ''
		},
		init: function(e) {
			config = a.extend({}, defaults, e);
			config.obj = a(this);
			currentDate = e.currentDate;
			ctx = e.ctx;
			natsume.value.year = currentDate.getFullYear();
			natsume.value.month = currentDate.getMonth();
			natsume.value.date = currentDate.getDate();
			natsume.renderHtml();
			natsume.refreshView();
			natsume.initListeners();
		},
		renderHtml: function() {
			var $html = $('<div class="md_panel">' + '<div class="md_head"><span class="iconfont icon-pre" ><</span><span class="md_headtext">2016年08月</span> <span class="iconfont icon-next">></span></div>' + '<div class="md_body">' + '<ul class="md_weekarea"><li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li>六</li></ul>' + '<ul class="md_datearea in"></ul>' + '</div>' + '</div>');
			config.obj.append($html);
			return $html;
		},
		_changeMonth: function(add, checkDate) { //1或-1，config.value.date
			//先把已选择的日期保存下来
			this.saveCheckedDate();
			var headText = config.obj.find('.md_headtext');
			var num = Number(headText.data('month')) + add;

			//月份变动发生了跨年
			if (num > 11) {
				num = 0;
				this.value.year++;
			} else if (num < 0) {
				num = 11;
				this.value.year--;
			}
			headText.attr("data-date",this.value.year+"-"+F.getMonth(num));
			headText.text(this.value.year + '年' + F.getMonth(num) + '月').data('year', this.value.year).data('month', num);
			this.value.month = num;

			if (checkDate) {
				this.value.date = checkDate;
			} else {
				//如果有上次选择的数据，则进行赋值
				this.setCheckedDate();
			}

			this.updateDate(add);
		},
		//保存上一次选择的数据
		saveCheckedDate: function() {
			if (this.value.date) {
				this.lastCheckedDate = {
					year: this.value.year,
					month: this.value.month,
					date: this.value.date
				}
			}
		},
		//将上一次保存的数据恢复到界面
		setCheckedDate: function() {
			if (this.lastCheckedDate && this.lastCheckedDate.year == this.value.year && this.lastCheckedDate.month == this.value.month) {
				this.value.date = this.lastCheckedDate.date;
			} else {
				this.value.date = '';
			}
		},
		//根据日期得到渲染天数的显示的HTML字符串
		getDateStr: function(y, m, d) { //this.value.year, this.value.month, this.value.date
			var dayStr = '';
			var currentMonthDays = F.getDaysInMonth(y, m);
			//判断是否超出允许的日期范围
			var startDay = 1,
				endDay = currentMonthDays,
				thisDate = new Date(y, m, d),
				firstDate = new Date(y, m, 1),
				lastDate = new Date(y, m, currentMonthDays),
				minDateDay = config.minDate.getDate();

			if (config.minDate > lastDate) {
				startDay = currentMonthDays + 1;
			} else if (config.minDate >= firstDate && config.minDate <= lastDate) {
				startDay = minDateDay;
			}

			if (config.maxDate) {
				var maxDateDay = config.maxDate.getDate();
				if (config.maxDate < firstDate) {
					endDay = startDay - 1;
				} else if (config.maxDate >= firstDate && config.maxDate <= lastDate) {
					endDay = maxDateDay;
				}
			}

			//计算1号是星期几，并补上上个月的末尾几天
			var week = F.getWeekInMonth(y, m);
			var lastMonthDays = F.getDaysInMonth(y, m - 1);
			//小于minDate之后的日期 统一添加disabled的class名
			var minDsbClass = startDay > 1 ? 'disabled' : '';

			for (var j = week - 1; j >= 0; j--) {
				dayStr += '<li class="prevdate ' + minDsbClass + '" data-day="' + (lastMonthDays - j) + '">' + (lastMonthDays - j) + '</li>';
			}

			//再补上本月的所有天;

			//将日期按允许的范围分三段拼接
			for (var i = 1; i < startDay; i++) {
				dayStr += '<li class="disabled" data-day="' + i + '">' + i + '</li>';
			}

			for (var j = startDay; j <= endDay; j++) {
				var current = '';
				var addFlag='';
				if($.inArray(j,config.addDates)!=-1){
					addFlag='<i class="tips">已添加</i>';
				}
				if (y == this.value.year && m == this.value.month && d == j) {
					current = 'today';
				}
				dayStr += '<li class="' + current + '" data-day="' + j + '">' + j +addFlag+ '</li>';
			}

			for (var k = endDay + 1; k <= currentMonthDays; k++) {
				dayStr += '<li class="disabled" data-day="' + k + '">' + k + '</li>';
			}

			//再补上下个月的开始几天
			var nextMonthStartWeek = (currentMonthDays + week) % 7;
			var fillTotal = (week + currentMonthDays) < 35 ? 14 : 7;
			//超过maxDate之后的日期 统一添加disabled的class名
			/*var maxDsbClass = endDay < currentMonthDays ? 'disabled' : '';

			for (var i = 1; i <= fillTotal - nextMonthStartWeek; i++) {
				dayStr += '<li class="nextdate ' + maxDsbClass + '" data-day="' + i + '">' + i + '</li>';
			}*/
			//pre next 按钮显隐控制
			//natsume.showIcon(startDay > 1, endDay < currentMonthDays);

			return dayStr;
		},
		updateDate: function(add) {
			var dateArea = config.obj.find('.md_datearea.in');
			var newDateArea = $('<ul class="md_datearea in"></ul>');

			newDateArea.html(natsume.getDateStr(this.value.year, this.value.month, this.value.date));
			$('.md_body').append(newDateArea);
			dateArea.remove();
		},
		refreshView: function() {
			var date = currentDate;
			var y = this.value.year = date.getFullYear(),
				m = this.value.month = date.getMonth(),
				d = this.value.date = date.getDate();
			config.obj.find('.md_headtext').attr("data-date",y+"-"+F.getMonth(m));
			config.obj.find('.md_headtext').text(y + '年' + F.getMonth(m) + '月').data('year', y).data('month', m);
			var dayStr = natsume.getDateStr(y, m, d);
			config.obj.find('.md_datearea').html(dayStr);

			if (config.initCallBack === true) {
				var _m = this.value.month + 1 < 10 ? '0' + (this.value.month + 1) : (this.value.month + 1);
				var _day = Number(config.obj.find('.current').text()) < 10 ? '0' + config.obj.find('.current').text() : config.obj.find('.current').data("day");
				var _type = Number(config.obj.find('.current').text()) < 10 ? '0' + config.obj.find('.current').text() : config.obj.find('.current').data("type");
				config.callBackFn('' + this.value.year +'-'+ _m +'-'+ _day +'/'+ _type);
			}
		},
		initListeners: function() {
			var _this = this;
			var _mouseDate=0;
//			var date = new Date();
			var date = currentDate;
			var d = this.value.date = date.getDate();
			var EventHappe=0;
			config.obj.find('.icon-pre').on('click', function() {
				if (!$(this).hasClass('disabled')) {
					var y = parseInt(natsume.value.year);
					var m = parseInt(natsume.value.month);
					if(m==0){
						m = 12;
						y = y-1;
					}
					var dateStr = y+"-"+m;
					var courseTypeId = $("#courseType").val();
					$.ajax({
						url: ctx+"/courseContent/getAddDateArr",
						type: 'GET',
						dataType:'json',
						data:{'date':dateStr,'courseTypeId':courseTypeId},
						async:false,
						success: function(result){
							config.addDates = result;
						},
						error:function(XMLHttpRequest){
							console.log('服务器异常');
						}
					});
					natsume._changeMonth(-1);
				}
			});
			config.obj.find('.icon-next').on('click', function() {
				if (!$(this).hasClass('disabled')) {
					var y = parseInt(natsume.value.year);
					var m = parseInt(natsume.value.month);
					if(m==11){
						m = 1;
						y = y+1;
					}else{
						m = m+2;
					}
					var dateStr = y+"-"+m;
                    var courseTypeId = $("#courseType").val();
					$.ajax({
						url: ctx+"/courseContent/getAddDateArr",
						type: 'GET',
						dataType:'json',
						data:{'date':dateStr,'courseTypeId':courseTypeId},
						async:false,
						success: function(result){
							config.addDates = result;
						},
						error:function(XMLHttpRequest){
							console.log('服务器异常');
						}
					});
					natsume._changeMonth(1);
				}
			});
			config.obj.delegate('.md_datearea li', 'mouseenter', function(e) {
				if(EventHappe==1) return false;
				EventHappe=1;
				_mouseDate=$(this).html();
				if(_mouseDate.indexOf('已添加')!=-1){
					$(this).addClass('edit').html("编辑");
					$(this).addClass('edit').attr("data-type","edit");
				}else{
					$(this).addClass('current').html("添加");
					$(this).addClass('current').attr("data-type","add");
				}
			})
			config.obj.delegate('.md_datearea li', 'mouseleave', function(e) {
				EventHappe=0;
				$(this).removeClass('current').removeClass('edit').html(_mouseDate);
			})
			config.obj.delegate('.md_datearea li', 'click', function() {
				var $this = $(this);
				if ( $this.hasClass('disabled')) {
					return;
				}
				_this.value.date = $this.data('day');

				//判断是否点击的是前一月或后一月的日期
				var add = 0;
				if ($this.hasClass('nextdate')) {
					add = 1;
				} else if ($this.hasClass('prevdate')) {
					add = -1;
				}

				if (add !== 0) {
					natsume._changeMonth(add, _this.value.date);
				} else {
					$this.addClass('current').siblings('.current').removeClass('current');
				}
				if (!$this.hasClass('nextdate') && !$this.hasClass('predate')) {
					var _type = Number($this.text()) < 10 ? '0' + $this.text() : $this.data("type");
					var _day = Number($this.text()) < 10 ? '0' + $this.text() : $this.data("day");
					var _m = (_this.value.month + 1) < 10 ? '0' + (_this.value.month + 1) : (_this.value.month + 1);
					config.callBackFn('' + _this.value.year +'-'+ _m +'-'+ _day +'/'+ _type);
				}
			});
			//touch
			var touchStartX=0,thouchMoveX=0, moveX=0; 
			config.obj.find('.md_panel').off().on('touchstart',function (e) {
				touchStartX = e.touches[0].clientX;
			}).on('touchmove',function (e) {
				thouchMoveX = e.touches[0].clientX;
				moveX = touchStartX - thouchMoveX;
			}).on('touchend',function (e) {
				if (moveX < -10 && !config.obj.find('.icon-pre').hasClass('disabled')) {
					natsume._changeMonth(-1);
				}else if (moveX > 10 && !config.obj.find('.icon-next').hasClass('disabled')) {
					natsume._changeMonth(1);
				}
				moveX = 0;
				
			});
		},
		showIcon: function(boolPre, boolNext) {
			config.obj.find('.iconfont').removeClass('disabled');
			if (boolPre) {
				config.obj.find('.icon-pre').addClass('disabled');
			}
			if (boolNext) {
				config.obj.find('.icon-next').addClass('disabled');
			}
		}
	};

	a.fn.zxCalenderSquare = function(f) {
		return natsume.init.apply(this, arguments);
	};

})(window.Zepto || window.jQuery);