package com.neu;

import spatialindex.spatialindex.Point;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RTreeServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            //long time = 1417401382;
        	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
            Date time = null;
            try
            {
                time = myFormatter.parse(req.getParameter("time"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            double lng = Double.parseDouble(req.getParameter("lng"));
            double lat = Double.parseDouble(req.getParameter("lat"));
            System.out.println(time.getTime()/1000 + ":" + lng + ":" + lat);
            String realIndexPath = req.getSession().getServletContext().getRealPath("tree");//索引文件绝对路径
            String realDataPath = req.getSession().getServletContext().getRealPath("t_src_data.csv");//原始数据绝对路径
            //将查询结果返回
            PrintWriter out = resp.getWriter();
            out.print(BuildTree.getData(realIndexPath, realDataPath, new Point(new double[]{time.getTime()/1000, lng, lat})));
            

        }catch (NumberFormatException e)
        {
            e.printStackTrace();
        }

    }
}
