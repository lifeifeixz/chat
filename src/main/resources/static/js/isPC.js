
/*
 * 此方法监检查登录者设备是否是PC设备
 * 然后根据设备选取不同的聊天页
 * */
function IsPC() {
			var userAgentInfo = navigator.userAgent;
			var Agents = ["Android", "iPhone",
						"SymbianOS", "Windows Phone",
						"iPad", "iPod"];
			var flag = true;
			for (var v = 0; v < Agents.length; v++) {
				if (userAgentInfo.indexOf(Agents[v]) > 0) {
					flag = false;
					break;
				}
			}
			return flag;
		}
