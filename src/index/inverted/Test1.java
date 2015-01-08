package index.inverted;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test1 {

    public static void main(String[] args) throws IOException { 
    	FileReader fr=new FileReader("D:\\GCC编程\\Exact geo+text search\\data_10000w.txt");
    	File file=new File("D:\\GCC编程\\Exact geo+text search\\data_10000w.txt");
    	long fsize=file.length();
    	System.out.println(fsize);
    	BufferedReader bf=new BufferedReader(fr,8*1024);
    	String line=null;
    	long index=0;
    	while((line=bf.readLine())!=null){
    		//System.out.println(index);
    		index+=line.length()+1;
    	}
    	System.out.println(index);
    }
}
