package index.inverted;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.classfile.Attribute;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShiXiQury extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShiXiQury() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");
		String recordId=request.getParameter("recordId");
		String timeString=request.getParameter("time");
		System.out.println("time:\t"+timeString);
		String attribute=request.getParameter("attribute");
		System.out.println("attribute:"+attribute);
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		Long time=null;
		try {
			time = myFormatter.parse(timeString).getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String sourceFileName=request.getSession().getServletContext().getRealPath("7w.csv");
		String indexFileName=request.getSession().getServletContext().getRealPath("7w.idx");
		String datasourceFileName=request.getSession().getServletContext().getRealPath("t_src_data.csv");
		String dataindexFileName=request.getSession().getServletContext().getRealPath("t_src_dataInvert.idx");
		
		SeaDataJson seaDataJson=new SeaDataJson();
		SeaWaterBean swb=InvertedIndex.getData(sourceFileName, indexFileName, new Integer(recordId), attribute,Long.MAX_VALUE);
		SeaWaterDataBean swdb=InvertedIndex.getData(datasourceFileName, dataindexFileName, new Integer(recordId));
		seaDataJson.setSevenWBean(swb);
		seaDataJson.setDataBean(swdb);
		
		Queue<SeaDataJson> nodeQueue=new LinkedList<SeaDataJson>();
		nodeQueue.offer(seaDataJson);
		while(!nodeQueue.isEmpty()){
			SeaDataJson sdjtemp=nodeQueue.poll();
			System.out.println("from:"+sdjtemp.getSevenWBean().getFrom());
			if(sdjtemp.getSevenWBean().getFrom().equals("")) continue;
			String []ids=sdjtemp.getSevenWBean().getFrom().split(",");
			ArrayList<SeaDataJson> children=new ArrayList<SeaDataJson>();
			for(int i=0;i<ids.length;++i){
				SeaDataJson sdj=new SeaDataJson();
				sdj.setSevenWBean(InvertedIndex.getData(sourceFileName, indexFileName, new Integer(ids[i]), attribute,sdjtemp.getSevenWBean().getWhen()));
				sdj.setDataBean(InvertedIndex.getData(datasourceFileName, dataindexFileName, new Integer(ids[i])));
				children.add(sdj);
				nodeQueue.offer(sdj);
			}
			if(ids.length>0)	sdjtemp.setChildren(children);
		}
		
		JSONObject jsons=JSONObject.fromObject(seaDataJson);
		System.out.println(jsons);
		PrintWriter out = response.getWriter();
		out.print(jsons);
		//out.flush();
		//out.close();//3,2014/12/22 18:19,temp,21,artificial value change,user4,t_src_data,3,四舍五入
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
