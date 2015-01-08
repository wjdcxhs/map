package index.inverted;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

public class TestRandomAccessFileReadLine {
    public void testRandomAccessFile() throws IOException { 

		BufferedRandomAccessFile raf=new BufferedRandomAccessFile("D:\\GCC编程\\Exact geo+text search\\data_1000w.txt","r");
		RandomAccessFile rafw=new RandomAccessFile("D:\\GCC编程\\Exact geo+text search\\data_1000w.txt.idx","rw");
		//BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\GCC编程\\Exact geo+text search\\data_100w.txt.idx"));
		int i=0;
		byte[] buff=new byte[1024*10];
		while((i=raf.read(buff))!=-1){
			rafw.write(buff, 0, i);
		}
    }
	@Test
	public void testBufferedReadLine() throws IOException{
		BufferedRandomAccessFile raf=new BufferedRandomAccessFile("D:\\GCC编程\\Exact geo+text search\\data_10w.txt","r");
		String line=null;
		System.out.println(raf.length());
		while((line=raf.bufferedReadLine())!=null){
			System.out.println(line);
		}
	}
}