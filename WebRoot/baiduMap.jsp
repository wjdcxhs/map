 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
<meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
<title>海洋水文数据世系管理系统</title>
<!--引用百度地图API-->
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.min.css">
<link href="http://cdn.bootcss.com/twitter-bootstrap/2.2.2/css/bootstrap-responsive.min.css" rel="stylesheet">
<script language="javascript" src="jquery-2.1.1.min.js"></script>
<script type="text/javascript" 	src="http://api.map.baidu.com/api?key=&v=1.2&services=true"></script>
<script type="text/javascript" src="d3/d3.js"></script>
<script type="text/javascript" src="d3/d3.layout.js"></script>
<script language="javascript" src="js/bootstrap.js"></script>
<script language="javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

</head>

<body id="body">
	<!--百度地图容器-->
	<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12"; style=" background-color: #009ACD; border:3px solid#C1CDC1">
            <h1 align="center" class="text-center" bgcolor="#20B2AA">
                <strong><font size="12" color="#FFFFFF">海洋水文数据世系管理系统</font></strong>
            </h1>
        </div>
        <div style="height:80px;">
        </div>
    </div>
    <div class="row-fluid" style="height:800px;">
		<div class="span5" >
		
		<div class = "row-fluid">
        
        <div class="input-append date form_datetime" data-date="2014-12-23T00:00:00Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                    <input size="16" type="text" value="2014/12/23 18:19" id = "time" readonly>
                    <!-- <span class="add-on"><i class="icon-remove"></i></span> -->
					<span class="add-on"><i class="icon-th"></i></span>
        </div>
      
        <div class ="row-fluid" id="map_canvas" style="height:600px;border:2px solid#ccc;float:left;"></div>        
        
        </div>
        </div>
        <div class="span7">
        

		<div class="row-fluid">
			<div class="row-fluid">
			<br />
					<h4 align="center" ><font face="宋体" size="5" >数据世系展示图<font/></h4>	
								<br />				
    				<!--<ul class="nytg-navigation clearfix" style="list-style-type:none">
        			<li id="温度世系" class="selected">温度世系</li>
        			<li id="盐度世系">盐度世系</li>
    				</ul>		  -->	
    		</div>
			<div class="row-fluid">
				<div id="tree"></div>
			</div>    
		</div>    
       

		</div>		
		<div class = "row-fluid">
            	<div id="tooltip" class = "tooltip" style="opacity:0; position: absolute;"><font size="3" color="#191970">
            		 <div class = "row" >
						<h7>&nbsp&nbsp&nbsp&nbsp该点的时空坐标</h7>
					</div>
					<div class = "row" >
						<h7>&nbsp&nbsp&nbsp&nbsp时间：</h7>
						<h7 id = "time_tip"></h7>
				</div>					
            		<div class = "row" >
						<h7>&nbsp&nbsp&nbsp&nbsp经度:</h7>
						<h7 id = "lng"></h7>
						<h7>&nbsp&nbsp&nbsp&nbsp纬度:</h7>
						<h7 id = "lat"></h7>
					</div>
					<div class = "row" >
						<h7>&nbsp&nbsp&nbsp&nbsp该点的世系信息</h7>
					</div>
					
					<div class = "row" ><font size="3">
					<h7>&nbsp&nbsp&nbsp&nbsp</h7>
					<h7 id = "who"></h7>
						<h7>根据</h7>
						<h7 id = "how"></h7>
						<h7>，利用</h7>
						<h7 id = "what"></h7>
					
						<br />
						<h7>&nbsp&nbsp&nbsp&nbsp在</h7>
						<h7 id = "when"></h7>
					
						<h7>时，得到的</h7>
						<h7 id = "why"></h7>
					</font></div>
					
					
					
				</div>
            </div>
		</div>
		<div hidden id = "infowindow" hidden>
			<dl class="dl-horizontal"><font size="3">
  				<dt>位&nbsp;置:</dt>
  				<dd id = "myLocation"></dd>
  				<dt>温&nbsp;度:</dt>
  				<dd id = "temp"></dd>
  				<dt>盐&nbsp;度:</dt>
  				<dd id = "pscal"></dd>
  				<dt>I&nbsp;D:</dt>
  				<dt hidden>id</dt>
  				<dd id = "recordId"></dd>
  				<dt>温度世系</dt>
  				<dd><input type ="radio" name="shixi" checked="checked" value="temp" /> </dd>
  				<dt>盐度世系</dt>
  				<dd><input type="radio" name="shixi" checked="checked" value="pscal" /> </dd>			
  				
  				
			</dl>
			<div style="width:400px; text-align:center;">
			<button id="pedigreeQury" onclick=shixiQury() name="click" align="center">世系查询</button>
			</div>
		</font>
		</div>

     </div>
<!-- 时间选择器 -->
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
		format: "yyyy/mm/dd hh:ii",
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1,
        minuteStep: 1
    });
</script>	
	
	
	

	<script type="text/javascript">
		var buttonId = 0;
		function shixiQury() {
			buttonId = 0;
			var trNode = document.getElementById("quryol");
			if (trNode)
				document.getElementById("quryol").parentNode
						.removeChild(trNode);

			var recordId = document.getElementById("recordId").innerHTML;
			var attributeRadios = document.getElementsByName("shixi");
			var time = document.getElementById("time").value;
			var attribute = null;
			for ( var i = 0; i < attributeRadios.length; ++i) {
				if (attributeRadios.item(i).checked) {
					attribute = attributeRadios.item(i).getAttribute("value");
					break;
				}
			}
			createXMLHttpRequest();
			var queryString = "./ShiXiQury?";
			queryString = queryString + "&recordId=" + recordId + "&attribute="
					+ attribute + "&time=" + time;
			xmlHttp.onreadystatechange = showShixi;
			xmlHttp.open("GET", queryString, true);
			xmlHttp.send(null);
		}
		function showShixi() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var seaWaterBean = eval("(" + xmlHttp.responseText + ")");
					flare = seaWaterBean;
					if(document.getElementById("tree")){
						var tParent=document.getElementById("tree").parentNode;
						tParent.removeChild(document.getElementById("tree"));
						var tDiv=document.createElement("div");
						tDiv.id="tree";
						tParent.appendChild(tDiv);
					}
					buildTree(flare);
					var ol = document.getElementById("quryol");
					if (!ol) {
						var ol = document.createElement("ol");
						ol.setAttribute("id", "quryol");
						document.getElementById("body").appendChild(ol);
						ol.appendChild(document.createElement("li"));
						var parent = ol.lastChild;
						var value = seaWaterBean[0].value;
						return createQuryButton(buttonId, parent, value,
								seaWaterBean[0]);
					}
					var parent = ol.lastChild;
					var value = seaWaterBean[0].value;
					createQuryButton(++buttonId, parent, value, seaWaterBean[0]);
				}
			}
		}
		function createQuryButton(buttonId, parent, value, seaWaterBean) {
			var oldbutton = document.getElementById(buttonId);
			if (oldbutton && oldbutton.getAttribute('value') == value)
				return;
			//var label=document.createElement("label");
			//label.setAttribute(name, value);

			var button = document.createElement("input");
			button.setAttribute("type", "button");
			button.setAttribute('id', buttonId);
			button.setAttribute('value', value);
			button.setAttribute('class', 'circle');
			$("#recordId").html(seaWaterBean.from);
			button.name = "click";
			button.onclick = shixiQury2;
			parent.appendChild(button);
			var t;
			button.onmouseover = function() {
				t = setTimeout(
						function() {
							createXMLHttpRequest();
							var queryString = "./ShiXiQuryData?";
							queryString = queryString + "&seaDataId="
									+ seaWaterBean.id;
							xmlHttp.onreadystatechange = showLngLatTime;
							xmlHttp.open("GET", queryString, true);
							xmlHttp.send(null);
						}, 1200);
			};
			button.onmouseout = function() {
				clearTimeout(t);
			};
		}
		function showLngLatTime() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var seaDataBean = eval("(" + xmlHttp.responseText + ")");
					alert("经度：" + seaDataBean.lng + "\n纬度：" + seaDataBean.lat
							+ "\n时间：" + seaDataBean.time);
				}
			}
		}
		function shixiQury2(click) {
			if (this.name != "click")
				return;
			this.name = "unclick";
			var recordId = document.getElementById("recordId").innerHTML;
			var attributeRadios = document.getElementsByName("shixi");
			var attribute = null;
			for ( var i = 0; i < attributeRadios.length; ++i) {
				if (attributeRadios.item(i).checked) {
					attribute = attributeRadios.item(i).getAttribute("value");
					break;
				}
			}
			createXMLHttpRequest();
			var queryString = "./ShiXiQury?";
			queryString = queryString + "&recordId=" + recordId + "&attribute="
					+ attribute;
			xmlHttp.onreadystatechange = showShixi;
			xmlHttp.open("GET", queryString, true);
			xmlHttp.send(null);
		}

		//创建和初始化地图函数：
		function initMap() {
			createMap();//创建地图
			setMapEvent();//设置地图事件
			addMapControl();//向地图添加控件
			addMarker();//向地图中添加marker
		}

		//创建地图函数：
		function createMap() {
			var map = new BMap.Map("map_canvas");//在百度地图容器中创建一个地图
			var point = new BMap.Point(115.961885, 34.352024);//定义一个中心点坐标
			map.centerAndZoom(point, 5);//设定地图的中心点和坐标并将地图显示在地图容器中

			function showInfo(e) {
				alert(e.point.lng + ", " + e.point.lat);
				//map.clearOverlays();
				map.addOverlay(new BMap.Marker(new BMap.Point(e.point.lng,
						e.point.lat)));
			}

			map.addEventListener("dblclick", showInfo);

			window.map = map;//将map变量存储在全局
		}

		//地图事件设置函数：
		function setMapEvent() {
			map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
			map.disableScrollWheelZoom();//禁用地图滚轮放大缩小，默认禁用(可不写)
			map.disableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
			map.enableKeyboard();//启用键盘上下左右键移动地图
		}

		//地图控件添加函数：
		function addMapControl() {
			//向地图中添加缩放控件
			var ctrl_nav = new BMap.NavigationControl({
				anchor : BMAP_ANCHOR_TOP_LEFT,
				type : BMAP_NAVIGATION_CONTROL_LARGE
			});
			map.addControl(ctrl_nav);
			//向地图中添加缩略图控件
			var ctrl_ove = new BMap.OverviewMapControl({
				anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
				isOpen : 1
			});
			map.addControl(ctrl_ove);
			//向地图中添加比例尺控件
			var ctrl_sca = new BMap.ScaleControl({
				anchor : BMAP_ANCHOR_BOTTOM_LEFT
			});
			map.addControl(ctrl_sca);
		}
		var checkDate = function() {
			var datetime = (document.getElementById("time").value).split(" ");
			var date = datetime[0];
			var time = datetime[1];
			var DateForm = /^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$/;
			var TimeForm = /^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}$/;
			if (!DateForm.test(date) || !TimeForm.test(time)) {
				alert("日期时间输入格式输入错误！请重新输入。eg:2000/01/02 00:00");
				return;
			} else
				return 1;
		};
		//创建marker

		function addMarker() {
			var opts = {
	  			width : 400,     // 信息窗口宽度
	  			height: 200,     // 信息窗口高度
	  			opacity:0.5
			};
			var point = new BMap.Point(119.567756,38.048786);
			var marker = new BMap.Marker(point);
			marker.addEventListener("click", function(ee) {
				$("#myLocation").html("经度： " + ee.point.lng + "<br/>纬度： "+ ee.point.lat);
				var time = $("#time").val();
				if (checkDate()) {
					doRequestUsingGET(119.567756,38.048786, time);
				}    
				map.openInfoWindow(infoWindow,point); 
			});
			map.addOverlay(marker);
			
			var point = new BMap.Point(122.216966,32.500918);
			marker = new BMap.Marker(point);
			marker.addEventListener("click", function(ee) {
				$("#myLocation").html("经度： " + ee.point.lng + "纬度： "+ ee.point.lat);
				var time = $("#time").val();
				if (checkDate()) {
					doRequestUsingGET(122.216966,32.500918, time);
				}    
				map.openInfoWindow(infoWindow,point); 
			});
			map.addOverlay(marker);
			
			var point = new BMap.Point(112.79755,20.481977);
			marker = new BMap.Marker(point);
			map.addOverlay(marker);		
			var content = document.getElementById("infowindow");
			content.hidden=false;
			var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象 
			marker.addEventListener("click", function(ee){      
					$("#myLocation").html("经度： " + ee.point.lng + "纬度： "+ ee.point.lat);
				var time = $("#time").val();
				if (checkDate()) {
					doRequestUsingGET(112.79755,20.481977, time);
				}    
				map.openInfoWindow(infoWindow,point); //开启信息窗口
			});
			
			//        for (var i = 0; i < markerArr.length; i++) {
			//            var json = markerArr[i];
			//            var p0 = json.point.split("|")[0];
			//            var p1 = json.point.split("|")[1];
			//            var point = new BMap.Point(p0, p1);
			//            var iconImg = createIcon(json.icon);
			//            var marker = new BMap.Marker(point, {icon: iconImg});
			//            var iw = createInfoWindow(i);
			//            var label = new BMap.Label(json.title, {"offset": new BMap.Size(json.icon.lb - json.icon.x + 10, -20)});
			//            marker.setLabel(label);
			//            map.addOverlay(marker);
			//            label.setStyle({
			//                borderColor: "#808080",
			//                color: "#333",
			//                cursor: "pointer"
			//            });
			//
			//            (function () {
			//                var index = i;
			//                var _iw = createInfoWindow(index);
			//                var _marker = marker;
			//                _marker.addEventListener("click", function () {
			//                    this.openInfoWindow(_iw);
			//                });
			//                _marker.addEventListener("click", function () {
			//                    showMyLocation(i);
			//                });
			//                _iw.addEventListener("open", function () {
			//                    _marker.getLabel().hide();
			//                });
			//                _iw.addEventListener("close", function () {
			//                    _marker.getLabel().show();
			//                });
			//                label.addEventListener("click", function () {
			//                    _marker.openInfoWindow(_iw);
			//                });
			//                if (!!json.isOpen) {
			//                    label.hide();
			//                    _marker.openInfoWindow(_iw);
			//                }
			//            })();
			//        }
		}
		//    //创建InfoWindow
		//    function createInfoWindow(i) {
		//        var json = markerArr[i];
		//        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>" + json.content + "</div>");
		//        return iw;
		//    }
		//    //创建一个Icon
		//    function createIcon(json) {
		//        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w, json.h), {imageOffset: new BMap.Size(-json.l, -json.t), infoWindowOffset: new BMap.Size(json.lb + 5, 1), offset: new BMap.Size(json.x, json.h)});
		//        return icon;
		//    }
		//显示marker位置（经纬度）
		function showMyLocation(i) {
			var json = markerArr[i - 1];
			var p0 = json.point.split("|")[0];
			var p1 = json.point.split("|")[1];
			$("#myLocation").html("我的位置：" + "(" + p0 + "," + p1 + ")");
			$("#myContent").append(
					"<p style='color:#dd0000'>" + p0 + "/" + p1 + "</p>");

		}

		//创建xmlHttp
		function createXMLHttpRequest() {
			if (window.ActiveXObject) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			}
		}

		//使用get方式发送
		function doRequestUsingGET(lng, lat, time) {
			createXMLHttpRequest();
			var queryString = "./RTreeServlet?";
			queryString = queryString + "&lng=" + lng + "&lat=" + lat
					+ "&time=" + time;

			xmlHttp.onreadystatechange = handleStateChange;
			xmlHttp.open("GET", queryString, true);
			xmlHttp.send(null);
		}

		function handleStateChange() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var rpt = xmlHttp.responseText.split(";");
					var temperature = rpt[5];
					var salinity = rpt[7];
					$('#pscal').html(salinity);
					$('#temp').html(temperature);
					if (rpt == 'null') {
						$('#pscal').html("null");
						$('#temp').html("null");
					}
					$('#recordId').html(rpt[0]);
					$('#myContent').append(
							"<p style='color:#dd0000'>" + xmlHttp.responseText
									+ "</p>");
					document.getElementsByName("shixi")[0].checked = "checked";
					var trNode = document.getElementById("quryol");
					if (trNode)
						document.getElementById("quryol").parentNode
								.removeChild(trNode);
				}
			}
		}

		initMap();//创建和初始化地图
	</script>
	<script type="text/javascript">
		function buildTree(json) {
			var m = [ 20, 120, 20, 120 ], w = 1280 - m[1] - m[3], h = 400
					- m[0] - m[2], i = 0, root;

			var tree = d3.layout.tree().size([ h, w ]);

			var diagonal = d3.svg.diagonal().projection(function(d) {
				return [ d.y, d.x ];
			});

			var vis = d3.select("#tree").append("svg:svg").attr("width",
					w + m[1] + m[3]).attr("height", h + m[0] + m[2]).append(
					"svg:g").attr("transform",
					"translate(" + m[3] + "," + m[0] + ")");
			//alert(seaWaterJson.sevenWBean.value);
			var flare=json;
			root = flare;
			root.x0 = h / 2;
			root.y0 = 0;
			function toggleAll(d) {
				if (d.children) {
					d.children.forEach(toggleAll);
					toggle(d);
				}
			}

			// Initialize the display to show a few nodes.
			root.children.forEach(toggleAll);

			update(root);
            
           
            
			function update(source) {
				var duration = d3.event && d3.event.altKey ? 5000 : 500;

				// Compute the new tree layout.
				var nodes = tree.nodes(root).reverse();

				// Normalize for fixed-depth.
				nodes.forEach(function(d) {
					d.y = d.depth * 180;
				});

				// Update the nodes…
				var node = vis.selectAll("g.node").data(nodes, function(d) {
					return d.id || (d.id = ++i);
				});

				// Enter any new nodes at the parent's previous position.
				var nodeEnter = node.enter().append("svg:g").attr("class",
						"node").attr("transform", function(d) {
					return "translate(" + source.y0 + "," + source.x0 + ")";
				}).on("click", function(d) {
					toggle(d);
					update(d);
				});
				var tooltip = d3.select("#tooltip");
				var what = d3.select("#what");
				var when = d3.select("#when");
				var who = d3.select("#who");
				var why = d3.select("#why");
				nodeEnter.append("svg:circle")
						 .attr("r", 1e-6)
						 .on("mouseover",function(d){
						 	tooltip.transition().duration(200).style("opacity", ".9");
						 	
						 	what.text(d.sevenWBean.what);
						 	when.text(d.sevenWBean.whenString);
						 	who.text(d.sevenWBean.who);
						 	why.text(d.sevenWBean.why);
						 	d3.select("#how").text(d.sevenWBean.how);
						 	
						 	d3.select("#lng").text(d.dataBean.lng);
						   	d3.select("#lat").text(d.dataBean.lat);
						   
						    d3.select("#time_tip").text(d.dataBean.time);
						    d3.select("#quality").text(d.dataBean.quality);
							tooltip.style("left", (d3.event.pageX+ 15) + "px")
								   .style("top", (d3.event.pageY - 75) + "px");
								 
						 	})
						 .on("mouseout", function(d) {
							tooltip.transition().duration(500).style("opacity", "0");
							})
						 .style("fill",function(d) {
							return d._children ? "lightsteelblue" : "#fff";
						  });

				nodeEnter.append("svg:text").attr("x", function(d) {
					return d.children || d._children ? -10 : 10;
				}).attr("dy", ".35em").attr("text-anchor", function(d) {
					return d.children || d._children ? "end" : "start";
				}).text(function(d) {
					return d.sevenWBean.value.toString();
				}).style("fill-opacity", 1e-6);

				// Transition nodes to their new position.
				var nodeUpdate = node.transition().duration(duration).attr(
						"transform", function(d) {
							return "translate(" + d.y + "," + d.x + ")";
						});

				nodeUpdate.select("circle").attr("r", 4.5).style("fill",
						function(d) {
							return d._children ? "lightsteelblue" : "#fff";
						});

				nodeUpdate.select("text").style("fill-opacity", 1);

				// Transition exiting nodes to the parent's new position.
				var nodeExit = node.exit().transition().duration(duration)
						.attr(
								"transform",
								function(d) {
									return "translate(" + source.y + ","
											+ source.x + ")";
								}).remove();

				nodeExit.select("circle").attr("r", 1e-6);

				nodeExit.select("text").style("fill-opacity", 1e-6);

				// Update the links…
				var link = vis.selectAll("path.link").data(tree.links(nodes),
						function(d) {
							return d.target.id;
						});

				// Enter any new links at the parent's previous position.
				link.enter().insert("svg:path", "g").attr("class", "link")
						.attr("d", function(d) {
							var o = {
								x : source.x0,
								y : source.y0
							};
							return diagonal({
								source : o,
								target : o
							});
						}).transition().duration(duration).attr("d", diagonal);

				// Transition links to their new position.
				link.transition().duration(duration).attr("d", diagonal);

				// Transition exiting nodes to the parent's new position.
				link.exit().transition().duration(duration).attr("d",
						function(d) {
							var o = {
								x : source.x,
								y : source.y
							};
							return diagonal({
								source : o,
								target : o
							});
						}).remove();

				// Stash the old positions for transition.
				nodes.forEach(function(d) {
					d.x0 = d.x;
					d.y0 = d.y;
				});
			}
    
			// Toggle children.
			function toggle(d) {
				if (d.children) {
					d._children = d.children;
					d.children = null;
				} else {
					d.children = d._children;
					d._children = null;
				}
			}
			
		};
	</script>

</body>
</html>