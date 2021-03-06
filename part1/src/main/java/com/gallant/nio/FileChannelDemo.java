package com.gallant.nio;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileChannel exit from git server.
 * nio 中FileChannel可以理解为一个连接到文件的通道，可以通过FileChannel对文件进行读写；
 * FileChannel没有非阻塞模式，读写都只有阻塞的方式；
 * <p>
 * <p>
 * Created by huangjunhao on 19/11/15.
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
//        fileChannelTest();
       // transferFromTest();

        List<FutureTask<Boolean>> lst=new ArrayList<FutureTask<Boolean>>();
        long startTime=System.currentTimeMillis();   //获取开始时间
        for (int j = 0; j <= 200; j++) {
            Callable<Boolean> callTest = new test();
            FutureTask<Boolean> futureTest = new FutureTask<Boolean>(callTest);
            Thread threadTest = new Thread(futureTest, "线程" + j);
            threadTest.start();
            lst.add(futureTest);
        }

        //等待所有线程完成
        lst.stream().forEach(p -> {
            try {
                p.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("#######总耗时:" + (endTime - startTime) + "ms");
    }

    static class test implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            long startTime=System.currentTimeMillis();   //获取开始时间
            for (int i = 0; i <= 100000; i++) {
                isPhone("18701171838");
            }
            long endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println(Thread.currentThread().getName() + " 耗时:" + (endTime - startTime) + "ms");
            return true;
        }
    }

    public static boolean isPhone(String phone) {
        Pattern p = Pattern.compile("^1[3-9]{1}[0-9]{9}$");
        Matcher m = p.matcher(phone);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    static String[] users;

    static {
        users=new String[]{
                "111",
                "222",
                "333",
                "中国"
        };
    }

    private static void fileChannelTest() {
        try {
            RandomAccessFile in = new RandomAccessFile("/Users/huangjunhao/Desktop/work/git/HelloKitty/nio/src/main/resources/hello.txt", "rw");
            RandomAccessFile out = new RandomAccessFile("/Users/huangjunhao/Desktop/work/git/HelloKitty/nio/src/main/resources/hello-copy.txt", "rw");
            ByteBuffer bf = ByteBuffer.allocate(1024);
            FileChannel channelIn = in.getChannel();
            FileChannel channelOut = out.getChannel();
            int count;
            while ((count = channelIn.read(bf)) != -1) {
                //将缓存字节数组的指针设置为数组的开始序列即数组下标0。这样就可以从buffer开头，对该buffer进行遍历（读取）了
                bf.flip();
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

    /**
     * FileChannel 的 transferFrom() 方法可以将数据从源通道传输到 FileChannel
     * transferFrom 刚好相反
     * <p>
     * 方法的输入参数
     * position 表示从 position 处开始向目标文件写入数据，
     * count 表示最多传输的字节数。如果源通道的剩余空间小于 count 个字节，则所传输的字节数要小于请求的字节数
     *
     * @throws IOException
     */
    private static void transferFromTest() throws IOException {

        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            RandomAccessFile in = new RandomAccessFile("/Users/huangjunhao/Desktop/work/git/HelloKitty/nio/src/main/resources/hello.txt", "rw");
            RandomAccessFile out = new RandomAccessFile("/Users/huangjunhao/Desktop/work/git/HelloKitty/nio/src/main/resources/hello-copy.txt", "rw");
            inChannel = in.getChannel();
            outChannel = out.getChannel();
            long position = 0;
            long count = 2;
//            outChannel.transferFrom(inChannel, position, count);
            outChannel.transferTo(position,count,inChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inChannel.close();
            outChannel.close();
            System.out.println("complete.");
        }
    }
}
