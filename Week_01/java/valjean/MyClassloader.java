package valjean;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

public class MyClassloader extends ClassLoader {

    public static void main(String[] args) {
        MyClassloader entity = new MyClassloader();
        try {
            Class<?> hello = entity.findClass("Hello");
            if (hello == null) {
                System.out.println("解析class失败！");
                return;
            }
            Method method = hello.getMethod("hello");
            method.invoke(hello.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = getByteContent();

        if (bytes.length == 0) {
            System.out.println("获取文件内容失败!");
            return null;
        }

        byte[] newBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            newBytes[i] = (byte) (255 - bytes[i]);
        }

        return defineClass(name, newBytes, 0, newBytes.length);
    }

    public byte[] getByteContent() {
        String filepath = System.getProperty("user.dir") + "/Week_01/java/Hello.xlass";
        byte[] contents = new byte[0];
        try {
            File file = new File(filepath);
            if (!file.exists()) return contents;

            long length = file.length();
            contents = new byte[(int) length];

            FileInputStream fileInputStream = new FileInputStream(filepath);
            int read = fileInputStream.read(contents);
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return contents;
    }

}
