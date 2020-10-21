package thread;

import java.io.File;
import java.io.IOException;

/**
 * 用于测试FileCutAndRecover文件切割和合并的类。
 */
public class Test {
    public static void main(String[] args) throws IOException {
        FileCutAndRecover fileCutAndRecover = new FileCutAndRecover(new File("target.txt"), new File("a.txt"));
        new Thread(fileCutAndRecover, "线程1").start();
        new Thread(fileCutAndRecover,"线程2").start();
        new Thread(fileCutAndRecover,"线程3").start();
    }
}
