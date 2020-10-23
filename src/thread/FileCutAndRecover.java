package thread;


import java.io.*;

/**
 * FileCutAndRecover通过实现Runnable接口创建线程类，用于完成文件的切割和合并
 */
public class FileCutAndRecover implements Runnable {
    //使用cut流来完成文件的切割；使用转换流构造器中可以指定字符集，防止乱码问题。
    static private InputStreamReader cut;
    //使用recover流来完成文件的合并
  static   private  OutputStreamWriter recover;

    private static class Inner{
        private static final FileCutAndRecover instance = new FileCutAndRecover();
    }

    private FileCutAndRecover(){}

    public static final FileCutAndRecover getInstance(File src, File des) throws IOException {
         cut = new InputStreamReader(new FileInputStream(src),"utf-8");
         recover = new OutputStreamWriter(new FileOutputStream(des),"utf-8");
         return Inner.instance;
    }
    @Override
    public void run() {
        //让线程一直读取和合并文件，知道读取文件为空时使用return结束线程，让该线程死亡。
        while (true) {
                try {
                    char[] chars = new char[64];
                    int len = cut.read(chars);//读取数据
                    if (len == -1) break;
                    recover.write("--&&&&&&&"+Thread.currentThread().getName() + "&&&&&&&--");
                    recover.write(chars,0,len);//合并数据
                    recover.flush();//每次写完数据刷新缓冲区
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
