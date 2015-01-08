package index.inverted;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BufferedRandomAccessFile extends RandomAccessFile {

	private  final int Buffer_SIZE = 8*1024;
	private  byte[] buff = new byte[Buffer_SIZE];
	private  int index=0;
	private  long filePoint=0;
	public long getFilePoint() {
		return filePoint;
	}
	private void setFilePoint(long filePoint) {
		this.filePoint = filePoint;
	}
	public BufferedRandomAccessFile(String fileName, String mode)
			throws FileNotFoundException {
		super(fileName, mode);
	}
	public String bufferedReadLine() throws IOException{
		
		StringBuffer line = new StringBuffer();
		int c = -1;
		boolean eol = false;
		int num=0;

		while(!eol){
			if(filePoint%Buffer_SIZE==0){
				num=this.read(buff);
				index=0;
				if(num<Buffer_SIZE)	buff[num]=-1;
			}
			switch(c=buff[index]){
				case -1: return null;
				case '\r':++index;++filePoint;break;
				case '\n':
					++index;
					++filePoint;
					eol=true;break;
				default:
					line.append((char)c);
					++index;
					++filePoint;
					break;				
			}
		}
		this.setFilePoint(filePoint);
		return line.toString();
	}	
}