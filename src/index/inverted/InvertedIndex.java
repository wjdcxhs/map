package index.inverted;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class InvertedIndex {
	private static String delim;
	
	public static String getDelim() {
		return delim;
	}

	public static void setDelim(String delim) {
		InvertedIndex.delim = delim;
	}

	public  static HashMap<Integer,ArrayList<Long>> buildInvertedIndex(String fileName,String indexFileName){
		HashMap<Integer, ArrayList<Long>> hm=new HashMap<Integer,ArrayList<Long>>();
		Integer key=null;
		ArrayList<Long> value=null;
		try {
			BufferedRandomAccessFile braf=new BufferedRandomAccessFile(fileName,"r");
			long index=braf.getFilePointer();
			String line=null;
			while((line=braf.bufferedReadLine())!=null){
				StringTokenizer st=new StringTokenizer(line,InvertedIndex.getDelim());
				key=new Integer(st.nextToken());	//id
				value=(ArrayList<Long>) hm.get(key);
				if(value==null){
					value=new ArrayList<Long>();
				}
				value.add(index);
				hm.put(key, value);
				index=braf.getFilePoint();
			}
			BufferedWriter bw=new BufferedWriter(new FileWriter(indexFileName));
			Iterator<?> iter=hm.entrySet().iterator();
			long count=0;
			while(iter.hasNext()){		//把索引写到文件里；
				@SuppressWarnings("rawtypes")
				Map.Entry entry = (Map.Entry) iter.next();
				Object k = entry.getKey();
				@SuppressWarnings("unchecked")
				ArrayList<Long> val = (ArrayList<Long>) entry.getValue();
				bw.write(k.toString());
				bw.write(",");
				for(int i=0;i<val.size();++i){
					bw.write(val.get(i).toString()+" ");
				}
				bw.write("\n");
				if((count%10000)==0)bw.flush();
			}
			bw.close();
			return hm;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HashMap<Integer,ArrayList<Long>> getIndex(String sourceFileName,String indexFileName){
		HashMap<Integer, ArrayList<Long>> hm=new HashMap<Integer,ArrayList<Long>>();
		File indexFile=new File(indexFileName);
		if(!indexFile.exists()){
			return InvertedIndex.buildInvertedIndex(sourceFileName,indexFileName);
		}
		try {
			BufferedReader br=new BufferedReader(new FileReader(indexFileName));
			String line=null;
			while((line=br.readLine())!=null){
				StringTokenizer st=new StringTokenizer(line,",");
				Integer key=new Integer(st.nextToken());
				ArrayList<Long> value=new ArrayList<Long>();
				StringTokenizer stval=new StringTokenizer(st.nextToken()," ");
				while(stval.hasMoreTokens()){
					value.add(new Long(stval.nextToken()));
				}
				hm.put(key, value);
			}
			br.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hm;
	}
	public static SeaWaterBean getData(String sourceFileName,String indexFileName,Integer recordId,String attribute,long time) throws IOException{
    	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		RandomAccessFile randomFile=new RandomAccessFile(sourceFileName,"r");
		ArrayList<SeaWaterBean> swbeans=new ArrayList<SeaWaterBean>();
		InvertedIndex.setDelim(",");
		ArrayList<Long> idOffset=InvertedIndex.getIndex(sourceFileName, indexFileName).get(recordId);
		String line=null;
		if(idOffset==null)	return null;
		for(int i=0;i<idOffset.size();++i){
			SeaWaterBean swb=new SeaWaterBean();
			randomFile.seek(idOffset.get(i));
			line =new String(randomFile.readLine().getBytes("8859_1"),"gbk");
			StringTokenizer stringTokenizer=new StringTokenizer(line,",");
			swb.setId(new Integer(stringTokenizer.nextToken()));
			try {
				String lineTime=stringTokenizer.nextToken();
				swb.setWhen(myFormatter.parse(lineTime).getTime()/1000);
				if(swb.getWhen()>=time)	continue;
				swb.setWhenString(lineTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			swb.setAttribute(stringTokenizer.nextToken());
			if(!swb.getAttribute().equals(attribute))	continue;
			swb.setValue(new Double(stringTokenizer.nextToken()));
			swb.setWhat(stringTokenizer.nextToken());
			swb.setWho(stringTokenizer.nextToken());
			swb.setWhere(stringTokenizer.nextToken());
			String str=stringTokenizer.nextToken("$");
			if(str.startsWith(",\"")){		
				String []fw=str.split("\"");
				swb.setFrom(fw[1]);	//第一个元素是空；
				swb.setWhy(fw[2].substring(1).split(",")[0]);	//“，”要去掉;
				if(fw[2].substring(1).split(",").length==2)	swb.setHow(fw[2].substring(1).split(",")[1]);
			}else{		
				String []fw=str.split(",");
				swb.setFrom(fw[1]);
				swb.setWhy(fw[2]);
				if(fw.length==4)swb.setHow(fw[3]);
			}
			swbeans.add(swb);
		}
		if(swbeans.size()<=0||swbeans==null) return null;
		return Collections.max(swbeans, new Comparator<SeaWaterBean>(){
			@Override
			public int compare(SeaWaterBean o1, SeaWaterBean o2) {
				return (o1.getWhen() < o2.getWhen() ? 0 : 1);
			}
		});
	}
	
	public static SeaWaterDataBean getData(String sourceFileName,String indexFileName,Integer seaDataId) throws IOException{
		RandomAccessFile randomFile=new RandomAccessFile(sourceFileName,"r");
		SeaWaterDataBean swdbean=new SeaWaterDataBean();
		InvertedIndex.setDelim(";");
		ArrayList<Long> idOffset=InvertedIndex.getIndex(sourceFileName, indexFileName).get(seaDataId);
		if(idOffset==null)	return null;
		randomFile.seek(idOffset.get(0));
		String line =new String(randomFile.readLine().getBytes("8859_1"),"gbk");
		String[] strs=line.split(";");
		{
			swdbean.setId(new Integer(strs[0]));
			swdbean.setTime(strs[1]);
			swdbean.setLng(new Double(strs[2]));
			swdbean.setLat(new Double(strs[3]));
			swdbean.setSourceFileName(strs[4]);
			swdbean.setTemperature(new Double(strs[5]));
			swdbean.setTempQuality(new Double(strs[6]));
			swdbean.setPscal(new Double(strs[7]));
			swdbean.setPsaclQuality(new Double(strs[8]));	
		}
		return swdbean;
	}
	public static void main(String [] args) throws IOException{
		HashMap<Integer, ArrayList<Long>> hm=InvertedIndex.getIndex("H:\\map\\src\\index\\inverted\\7w.csv", "H:\\map\\src\\index\\inverted\\7w.csv.idx");
		RandomAccessFile randomFile=new RandomAccessFile("H:\\map\\src\\index\\inverted\\7w.csv","r");
		for(int i=0;i<hm.get(3).size();++i){
			randomFile.seek(hm.get(3).get(i));	
			String line=new String(randomFile.readLine().getBytes("8859_1"),"gbk");
			System.out.println(line);
		}
		System.out.println(randomFile.length());
	}
}


























