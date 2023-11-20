package com.example.xinhua.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Md5Util {
    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
     */
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String MD5Result = "";

    protected static MessageDigest messagedigest = null;
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(Md5Util.class.getName()
                    + "初始化失败，MessageDigest不支持MD5Util。");
            nsaex.printStackTrace();
        }
    }

    /**
     * 生成字符串的md5校验值
     * 
     * @param s
     * @return
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * 生成字符串的md5校验值并清除公共String
     * 
     * @param s
     * @return
     */
    public static String getMD5StringClean(String s) {
        String result = getMD5String(s.getBytes());
        MD5Result = "";
        return result;
    }

    /**
     * 判断字符串的md5校验码是否与一个已知的md5码相匹配
     * 
     * @param password  要校验的字符串
     * @param md5PwdStr 已知的md5校验码
     * @return
     */
    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    /**
     * 生成文件的md5校验值
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        InputStream fis;
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        while ((numRead = fis.read(buffer)) > 0) {
            messagedigest.update(buffer, 0, numRead);
        }
        fis.close();
        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    /**
     * 生成目录下所有文件的MD5累积码
     * 
     * @param file
     * @param supportType 对指定类型的文件进行加密
     *                    support为空，则对所有文件进行处理
     * @return
     * @throws Exception
     */

    public static String makeMD5String(File file, String supportType)
            throws Exception {

        if (file.isDirectory()) {// 是不是目录

            String[] files = file.list();// 返回该目录下所有文件及文件夹数组

            Arrays.sort(files); // 排序

            for (int i = 0; i < files.length; i++) {
                makeMD5String(new File(file, files[i]), supportType); // 递归(File
                                                                      // parent,
                                                                      // String
                                                                      // child)
            }

        } else {
            if (null == supportType || supportType.equals("")) {
                System.out.println(file.getParent() + "/" + file.getName());// 目录/文件名
                String md5 = getFileMD5String(file);
                MD5Result += md5;
            } else {
                String[] type = supportType.split(";");
                if (type != null && type.length != 0) {
                    for (int i = 0; i < type.length; i++) {
                        if (file.getPath().endsWith("." + type[i])) {
                            System.out.println(file.getParent() + "/"
                                    + file.getName());// 目录/文件名
                            String md5 = getFileMD5String(file);
                            MD5Result += md5;

                        }
                    }
                }
            }
        }
        return MD5Result;
    }

    /**
     * 获取目录下文件列表
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public static String[] getDirectory(File file) throws Exception {

        String[] files = {};

        if (file.isDirectory()) {// 是不是目录

            files = file.list();// 返回该目录下所有文件及文件夹数组

            Arrays.sort(files); // 排序

            return files;
        } else {
            return files;
        }

    }

    /**
     * 输出文件内容
     * 
     * @param content
     * @param path
     * @throws Exception
     */
    public static void outputFile(String content, String path) throws Exception {
        byte[] buff = new byte[] {};
        try {
            buff = content.getBytes();
            FileOutputStream out = new FileOutputStream(path);
            out.write(buff, 0, buff.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        long begin = System.currentTimeMillis();
        // 生成文件MD5码
        try {
            String[] files = getDirectory(new File(args[0]));
            for (int i = 0; i < files.length; i++) {
                // 排除.db文件
                if (!files[i].endsWith(".db")) {
                    String result = getMD5String(makeMD5String(new File(args[0] + "/" + files[i]), args[2]));
                    System.out.println("文件夹" + files[i] + ":" + result);
                    outputFile(result, args[1] + "/" + files[i]);
                    MD5Result = "";
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("time:" + ((end - begin) / 1000) + "s");
    }
}
