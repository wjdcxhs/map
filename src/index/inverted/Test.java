package index.inverted;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Test {
	
    public static void main(String[] args) {  
        try{  
            RandomAccessFile rafi = new RandomAccessFile("E:\\win7 32+64\\boost_1_55_0.zip", "r");  
            RandomAccessFile rafo = new RandomAccessFile("E:\\win7 32+64\\abcd.data", "rw");  
            FileChannel fci = rafi.getChannel();  
            FileChannel fco = rafo.getChannel();  
            long size = fci.size();  
            System.out.println(size);
            MappedByteBuffer mbbi = fci.map(FileChannel.MapMode.READ_ONLY, 0, size);  
            MappedByteBuffer mbbo = fco.map(FileChannel.MapMode.READ_WRITE, 0, size);  
            long start = System.currentTimeMillis();  
            for (int i = 0; i < size; i++) {  
                byte b = mbbi.get(i);  
                mbbo.put(i, b);  
            }  
            fci.close();  
            fco.close();  
            rafi.close();  
            rafo.close();  
            System.out.println("Spend: "+(double)(System.currentTimeMillis()-start) / 1000 + "s");            
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }
}
