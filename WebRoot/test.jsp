<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html {
            margin: 0;
            padding: 0;
            font-family: "微软雅黑";
        }

        #allmap {
            width: 650px;
            height: 665px;
            float: left;
        }

    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
    <title>世系系统</title>
</head>
<body>
<div id="allmap"></div>
<div id="r-result">

</div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(116.404, 39.915);
    map.centerAndZoom(point, 15);

    var marker = new BMap.Marker(new BMap.Point(116.404, 39.915)); // 创建点


    //添加覆盖物
    function add_overlay() {
        map.addOverlay(marker);            //增加点
    }
    //清除覆盖物
    function remove_overlay() {
        map.clearOverlays();
    }


    map.addEventListener("click", function (e) {
        //alert(e.point.lng + "," + e.point.lat);
        map.clearOverlays();
        marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));
        map.addOverlay(marker);
    });
</script>
