

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * FileChannel
 * NIO 中FileChannel可以理解为一个连接到文件的通道，可以通过FileChannel对文件进行读写；
 * FileChannel没有非阻塞模式，读写都只有阻塞的方式；
 *
 *
 * Created by huangjunhao on 19/11/15.
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {

        try {
            RandomAccessFile in=new RandomAccessFile("/Users/huangjunhao/Desktop/work/git/HelloKitty/NIO/src/main/resources/hello.txt","rw");
            RandomAccessFile out=new RandomAccessFile("/Users/huangjunhao/Desktop/temp/hello-copy.txt","rw");
            ByteBuffer bf = ByteBuffer.allocate(1024);
            FileChannel channelIn=in.getChannel();
            FileChannel channelOut=out.getChannel();
            int count;
            while ((count = channelIn.read(bf)) != -1) {
                //将缓存字节数组的指针设置为数组的开始序列即数组下标0。这样就可以从buffer开头，对该buffer进行遍历（读取）了
                bf.flip();
                //对bf读取的数据进行读取操作,写入另一个文件
                channelOut.write(bf);
                //强制讲内存刷新到硬盘,boolean 代表是否刷新属性
                channelOut.force(true);
                //对bf读取的数据进行读取操作,写入另一个文件
                channelOut.write(bf);
                //强制讲内存刷新到硬盘,boolean 代表是否刷新属性
                channelOut.force(true);
                bf.clear();
            }
            channelOut.close();
            channelIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
