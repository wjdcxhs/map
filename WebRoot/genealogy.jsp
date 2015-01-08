<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html
        {
            margin: 0;
            padding: 0;
            font-family: "微软雅黑";
        }

        #allmap
        {
            width: 650px;
            height: 665px;
            float: left;
            border:#ccc solid 1px;
        }

    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>

    <title>世系系统</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(109.854086, 29.937542);//定义一个中心点坐标
    map.centerAndZoom(point, 5);//设定地图的中心点和坐标并将地图显示在地图容器中
    map.enableScrollWheelZoom();
    map.disableDoubleClickZoom();

    map.addEventListener("dblclick", function (e)
    {
        //alert(e.point.lng + "," + e.point.lat);
        map.clearOverlays();
        marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));
        marker.addEventListener("click", function (ee)
        {
            alert(ee.point.lng + "," + ee.point.lat);
            doRequestUsingGET(ee.point.lng,ee.point.lat);
        });
        map.addOverlay(marker);

    });

//    //向地图中添加缩放控件
//    var ctrl_nav = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT, type: BMAP_NAVIGATION_CONTROL_LARGE});
//    map.addControl(ctrl_nav);
//    //向地图中添加缩略图控件
//    var ctrl_ove = new BMap.OverviewMapControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, isOpen: 1});
//    map.addControl(ctrl_ove);
//    //向地图中添加比例尺控件
//    var ctrl_sca = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});
//    map.addControl(ctrl_sca);

    //创建xmlHttp
    function createXMLHttpRequest()
    {
        if(window.ActiveXObject)
        {
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        else if(window.XMLHttpRequest)
        {
            xmlHttp=new XMLHttpRequest();
        }
    }

    //使用get方式发送
    function doRequestUsingGET(lng,lat)
    {
        createXMLHttpRequest();
        var queryString="./RTreeServlet?";
        queryString=queryString + "&lng=" + lng + "&lat=" + lat;
        xmlHttp.onreadystatechange=handleStateChange;
        xmlHttp.open("GET",queryString,true);
        xmlHttp.send(null);
    }

    function handleStateChange()
    {
        if(xmlHttp.readyState==4)
        {
            if(xmlHttp.status==200)
            {
                alert("aa");
            }
        }

    }


</script>
