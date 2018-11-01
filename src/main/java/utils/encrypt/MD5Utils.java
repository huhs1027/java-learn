package utils.encrypt;

import utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;


/**
 * md5工具类
 *
 * @author huhongsen
 */
public class MD5Utils {

    /**
     * 首先初始化一个字符数组，用来存放每个16进制字符
     */
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 获得一个字符串的MD5值
     *
     * @param input 输入的字符串
     * @return 输入字符串的MD5值
     */
    public static String md5(String input) {
        // 异常处理
        if (StringUtils.isBlank(input)) {
            // 中断流程
            return null;
        }
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes("utf-8");
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取文件的MD5值
     *
     * @param file
     * @return
     */
    public static String md5(File file) {
        try {
            // 异常判断
            if (!file.isFile()) {
                // 中断流程
                return null;
            }
            // 取得文件流
            FileInputStream in = new FileInputStream(file);
            // 获取流MD5加密值
            String result = md5(in);
            // 关闭流
            in.close();
            // 返回结果
            return result;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 获取流MD5加密值
     *
     * @param in
     * @return
     */
    private static String md5(InputStream in) {
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            // 构造比特数组
            byte[] buffer = new byte[1024];
            // 读取索引
            int read = 0;
            // 循环处理
            while ((read = in.read(buffer)) != -1) {
                // 字节数组设定
                messagedigest.update(buffer, 0, read);
            }
            // 关闭流
            in.close();
            // 字符数组转换成字符串返回
            return byteArrayToHex(messagedigest.digest());
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 字符数组转换成字符串返回
     *
     * @param byteArray
     * @return
     */
    private static String byteArrayToHex(byte[] byteArray) {
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        // 循环处理
        for (byte b : byteArray) {
            resultCharArray[index++] = HEX_DIGITS[b >>> 4 & 0xf];
            resultCharArray[index++] = HEX_DIGITS[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }

}
