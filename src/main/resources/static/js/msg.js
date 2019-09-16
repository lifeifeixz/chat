/* 按回车的时候 */
function getData(key) {
	if (event.keyCode == 13) {
		if (document.getElementById("msg").value.trim() == "") {
			msg.value = "";
		}

		send();
	}
}

/* @ 功 能 */
function selected(user) {
	document.getElementById("msg").value = document.getElementById("msg").value
			+ "<span style='color:red;font-weight:900;'>@" + user + " </span>";
	document.getElementById('msg').focus();
}

/* 私聊窗口 */
function selfSay(u) {

	// 1.打开新的窗口
	// window.open("selfSay.html","newwindow","height=430, width=500, toolbar
	// =no,top=100,left=380,
	// menubar=no,scrollbars=no,resizable=no,location=no,status=no");
}

/* 打开表情窗口 */
function openExpressoin() {
	document.getElementById("expression").style.display = "block";
}

/* 选择表情 */
function getE(i) {

	var msg = document.getElementById("msg");
	/*
	 * msg.value=msg.value+"<img
	 * src='http://flysli.eicp.net/WebSocket_01/expression/"+i+".gif'>";
	 */
	msg.value = msg.value
			+ "<img style='vertical-align: middle;' src='expression/"
			+ i + ".gif'>";
	document.getElementById('msg').focus();
	document.getElementById("expression").style.display = "none";

}

/* 发送 */
function send() {
	var msg = document.getElementById("msg");
	if (msg.value.trim() == "" || msg.value.trim() == null) {
		return;
	}

	/* var json="{\"msg\":\""+msg.value+"\",\"sayType\":\"0\"}"; */
	ws.send(msg.value);
	msg.value = "";

}