<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>arrow</title>
<script language="javascript" src="arrow.js"></script>
</head>
<body>
	<canvas width=800 height=600 id="arrowLine">请使用支持HTML5的浏览器</canvas>
	<input type="button" id="testButton1" />
	<input type="button" id="testButton2" />
	<script type="text/javascript">
		/* 	function getpos(e) {  
				var t=e.offsetTop;  
				var l=e.offsetLeft;  
				var height=e.offsetHeight;  
			    while(e=e.offsetParent) {  
			        t+=e.offsetTop;  
			        l+=e.offsetLeft;  
			    }  
			} *//* 
				var cont = document.getElementById("arrowLine").getContext('2d');
				var a1 = new window.mapleque.arrow();
				a1.set({
					x : 350,
					y : 200
				}, {
					x : 350,
					y : 100
				});
				a1.paint(cont);
				
				var a2 = new window.mapleque.arrow();
				a2.set({
					x : 350,
					y : 200
				}, {
					x : 350,
					y : 200
				});
				a2.paint(cont); */
		var cont = document.getElementById("arrowLine").getContext('2d');
		var a1 = new window.mapleque.arrow();
		a1.set({
			x : 100,
			y : 100
		}, {
			x : 100,
			y : 200
		});
		a1.paint(cont);
		var a2 = new window.mapleque.arrow();
		a2.set({
			x : 100,
			y : 100
		}, {
			x : 200,
			y : 200
		});
		a2.paint(cont);
		var a3 = new window.mapleque.arrow();
		a3.set({
			x : 100,
			y : 100
		}, {
			x : 0,
			y : 200
		});
		a3.paint(cont);
		var a3 = new window.mapleque.arrow();
		a3.set({
			x : 100,
			y : 100
		}, {
			x : -100,
			y : 200
		});
		a3.paint(cont1);

		//document.getElementById("arrowLine").onmouseover=function (){alert("Good!");};
	</script>
</body>
</html>








