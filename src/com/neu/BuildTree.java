package com.neu;

import spatialindex.rtree.RTree;
import spatialindex.spatialindex.*;
import spatialindex.storagemanager.*;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

/**
 * read data from "data.cvs" and build r-tree
 * Created by ubuntu on 14-12-1.
 */
public class BuildTree
{
    public static final int BUFFER_SIZE = 100;
    public static final String DATEFORMAT_STRING = "yyyy/MM/dd hh:mm";

    public static void buildTree(String dataFileName,String indexFileName) throws IOException
    {
        /********************init R-tree**********************/
        // Create a disk based storage manager.
        PropertySet ps = new PropertySet();

        ps.setProperty("Overwrite", true);
        //overwrite the file if it exists.

        ps.setProperty("FileName", indexFileName);
        // .idx and .dat extensions will be added.

        ps.setProperty("PageSize", 4096);
        // specify the page size. Since the index may also contain user defined data
        // there is no way to know how big a single node may become. The storage manager
        // will use multiple pages per node if needed. Of course this will slow down performance.

        IStorageManager diskfile = new DiskStorageManager(ps);

        IBuffer file = new RandomEvictionsBuffer(diskfile, 10, false);

        // Create a new, empty, RTree with dimensionality 3, minimum load 70%, using "file" as
        // the StorageManager and the RSTAR splitting policy.
        PropertySet ps2 = new PropertySet();

        Double f = 0.7;
        ps2.setProperty("FillFactor", f);


        ps2.setProperty("IndexCapacity", 4);
        ps2.setProperty("LeafCapacity", 4);
        // Index capacity and leaf capacity may be different.

        ps2.setProperty("Dimension", 3);

        ISpatialIndex tree = new RTree(ps2, file);


        /********************insert into R-tree**********************/
        RandomAccessFile randomFile = null;

        System.out.println("build tree:");
        randomFile = new RandomAccessFile(dataFileName, "r");

        byte[] buffer = new byte[BUFFER_SIZE];


        int readNumber = 0;
        String data = "0";

        int id;
        double lng, lat;
        String datetime;
        double[] pointData = new double[3];

        while(readNumber < randomFile.length())
        {
            int index = readLine(randomFile, buffer);
            String line = new String(buffer,0,index);
            readNumber += index+1;
            StringTokenizer st = new StringTokenizer(line, ";");
            id = new Integer(st.nextToken());
            datetime = st.nextToken().trim();
            lng = new Double(st.nextToken());
            lat = new Double(st.nextToken());

            SimpleDateFormat myFormatter = new SimpleDateFormat(DATEFORMAT_STRING);
            java.util.Date date = null;
            try
            {
                date = myFormatter.parse(datetime);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            long time = 0;
            if (date != null)
            {
                time = date.getTime() / 1000;//second since January 1, 1970, 00:00:00 GMT
            }

            pointData[0] = time;
            pointData[1] = lng;
            pointData[2] = lat;


            Point point = new Point(pointData);

            tree.insertData(data.getBytes(), point, id);
            data = String.valueOf(readNumber);
//            System.out.println(index);
            System.out.println(readNumber);
//            System.out.println(line);
            System.out.println("id:" + id + ",datetime:" + time + ",lng:" + lng + ",lat:" + lat);
        }
        randomFile.close();
        System.err.println(tree);
        Integer indexID = (Integer) ps2.getProperty("IndexIdentifier");
        System.err.println("Index ID: " + indexID);

        boolean ret = tree.isIndexValid();
        if (!ret) System.err.println("Structure is INVALID!");

        // flush all pending changes to persistent storage (needed since Java might not call finalize when JVM exits).
        tree.flush();

    }


    public static ISpatialIndex getTree(String indexFileName)
    {
        // Create a disk based storage manager.
        PropertySet ps1 = new PropertySet();

        ps1.setProperty("FileName", indexFileName);
        // .idx and .dat extensions will be added.

        IStorageManager diskfile1 = null;
        try
        {
            diskfile1 = new DiskStorageManager(ps1);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        IBuffer file1 = new RandomEvictionsBuffer(diskfile1, 10, false);
        // applies a main memory random buffer on top of the persistent storage manager
        // (LRU buffer, etc can be created the same way).

        PropertySet ps2 = new PropertySet();

        // If we need to open an existing tree stored in the storage manager, we only
        // have to specify the index identifier as follows
        Integer i = new Integer(1); // INDEX_IDENTIFIER_GOES_HERE (suppose I know that in this case it is equal to 1);
        ps2.setProperty("IndexIdentifier", i);
        // this will try to locate and open an already existing r-tree index from file manager file.

        return new RTree(ps2, file1);
    }

    public static int readLine(RandomAccessFile randomFile,byte[] buffer) throws IOException
    {
        byte c = -1;
        boolean eol = false;
        int index = 0;

        while (!eol)
        {
            switch (c = (byte)randomFile.read())
            {
                case -1:
                case '\n':
                    eol = true;
                    break;
                case '\r':
                    eol = true;
                    long cur = randomFile.getFilePointer();
                    if ((randomFile.read()) != '\n')
                    {
                        randomFile.seek(cur);
                    }
                    break;
                default:
                    buffer[index++] = c;
                    break;
            }
        }

        return index;
    }

    public static String readLine(RandomAccessFile randomFile) throws IOException
    {
        byte[] buffer = new byte[BUFFER_SIZE];
        byte c = -1;
        boolean eol = false;
        int index = 0;

        while (!eol)
        {
            switch (c = (byte)randomFile.read())
            {
                case -1:
                case '\n':
                    eol = true;
                    break;
                case '\r':
                    eol = true;
                    long cur = randomFile.getFilePointer();
                    if ((randomFile.read()) != '\n')
                    {
                        randomFile.seek(cur);
                    }
                    break;
                default:
                    buffer[index++] = c;
                    break;
            }
        }

        return new String(buffer,0,index);
    }


    /**
     * 从R树索引中读取记录的位置（行开始位置），然后读取一行返回
     * @param indexFilename R树索引名称
     * @param dataFilename 原始文件名称
     * @param point 索引点
     * @return 索引对应的一行原始数据
     */
    public static String getData(String indexFilename,String dataFilename, Point point)
    {
        //先查看是否索引已经建立，如果没有，则需要先建立
        File indexFile = new File(indexFilename + ".idx");
        File dataFile = new File(indexFilename + ".dat");
        if(!indexFile.exists() || !dataFile.exists())
        {
            try
            {
                BuildTree.buildTree(dataFilename,indexFilename);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //读取索引
        ISpatialIndex tree = BuildTree.getTree(indexFilename);

        MyVisitor vis = new MyVisitor(dataFilename);//新建一个遍历容器
        tree.intersectionQuery(point,vis);

        return vis.getReadData();

    }



    public static void main(String[] args)
    {
        try
        {
            BuildTree.buildTree("t_src_data.csv","tree");

            ISpatialIndex tree = BuildTree.getTree("tree");
            double[] data1 = {1419837840,113.018318,19.018783};
            double[] data2 = {1419837840,113.081359,19.054061};
            double[] data3 = {70.64253,41.26596,1417225399};
            double[] data4 = {70.64253,41.26596,123};

            Point point1 = new Point(data1);
            Point point2 = new Point(data2);
            Point point3 = new Point(data3);
            Point point4 = new Point(data4);

            MyVisitor vis = new MyVisitor("t_src_data.csv");
            tree.intersectionQuery(point1,vis);
            tree.intersectionQuery(point2,vis);
            tree.intersectionQuery(point3,vis);
            tree.intersectionQuery(point4,vis);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}


class MyVisitor implements IVisitor
{
    private String filename;//原始文件名
    private String readData;//读取的一行数据

    public MyVisitor(String filename)
    {
        this.filename = filename;
    }

    public String getReadData()
    {
        return this.readData;
    }
    //public static final String FILENAME = "data.csv";

    public void visitNode(final INode n) {}

    public void visitData(final IData d)
    {
        System.out.print("query result:");
        System.out.println(d.getIdentifier());
        System.out.println(d.getShape().toString());

        byte[] bytes = d.getData();
        if(bytes != null)
        {
            int position = Integer.parseInt(new String(bytes));
            RandomAccessFile randomFile = null;
            try
            {
                System.out.println("readFileByRandomAccess:");
                randomFile = new RandomAccessFile(filename, "r");

                randomFile.seek(position);

                readData = BuildTree.readLine(randomFile);
                System.out.println(readData);

            } catch (IOException e)
            {
                e.printStackTrace();
            } finally
            {
                if (randomFile != null)
                {
                    try
                    {
                        randomFile.close();
                    } catch (IOException ignored)
                    {
                    }
                }
            }
        }
        else
        {
            System.out.println("null");
        }
    }

}