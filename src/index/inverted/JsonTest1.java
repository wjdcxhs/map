package index.inverted;

import java.util.ArrayList;

import net.sf.json.JSONObject;

import org.junit.Test;

public class JsonTest1 {
	@Test
	public void testAdd(){
		PersonNode pn1=new PersonNode();
		pn1.setId(1);
		pn1.setName("hailu1");
		PersonNode pn2=new PersonNode();
		pn2.setId(2);
		pn2.setName("hailu2");
		PersonNode pn3=new PersonNode();
		pn3.setId(3);
		pn3.setName("hailu3");
		ArrayList<PersonNode> pNodes=new ArrayList<PersonNode>();
		pNodes.add(pn1);
		pNodes.add(pn2);
		//pn3.setpNodes(pNodes);
		JSONObject psJsonObject=JSONObject.fromObject(pn3);
		System.out.println(psJsonObject);
	}
	@Test
	public void test1(){
		SeaDataJson sdJson=new SeaDataJson();
		SeaWaterBean seaWaterBean=new SeaWaterBean();
		sdJson.setSevenWBean(seaWaterBean);
		sdJson.getSevenWBean().getFrom();
	}
}
