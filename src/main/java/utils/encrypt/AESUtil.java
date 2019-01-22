package utils.encrypt;

import com.alibaba.fastjson.JSON;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * Created by huhongsen on 2017/9/25.
 */
public class AESUtil extends BASE64Util {
    /**
     * ALGORITHM 算法 <br>
     * 可替换为以下任意一种算法，同时key值的size相应改变。
     * <p>
     * <pre>
     * DES                  key size must be equal to 56
     * DESede(TripleDES)    key size must be equal to 112 or 168
     * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
     * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
     * RC2                  key size must be between 40 and 1024 bits
     * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
     * </pre>
     * <p>
     * 在Key toKey(byte[] key)方法中使用下述代码
     * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code> 替换
     * <code>
     * DESKeySpec dks = new DESKeySpec(key);
     * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
     * SecretKey secretKey = keyFactory.generateSecret(dks);
     * </code>
     */
    private static final String ALGORITHM = "AES";

    private String aesKey;

    public String getAesKey() {
        return aesKey;
    }

    /**
     * 加密
     *
     * @param data 明文
     * @return
     * @throws Exception
     */
    public String encryptData(Object data) throws Exception {
        String sourceData = JSON.toJSONString(data);
        byte[] encrypt = encrypt(sourceData.getBytes(), aesKey);
        String encryptData = parseByte2HexStr(encrypt);
        return encryptData;
    }


    /**
     * 加密 需要传入秘钥
     *
     * @param data 数据
     * @return
     * @throws Exception
     */
    public String encryptDataNeedKey(Object data, String key) throws Exception {
        String sourceData = JSON.toJSONString(data);
        byte[] encrypt = encrypt(sourceData.getBytes(), key);
        //将二进制转换成16进制
        return parseByte2HexStr(encrypt);
    }

    /**
     * 加密 兼容c#解密
     */
    public String encryptComp(Object data, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        java.security.SecureRandom random = java.security.SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        kgen.init(128, random);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();

        //如下代码是标准的AES加密处理，C#可以实现
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        String content = JSON.toJSONString(data);
        byte[] byteContent = content.getBytes("utf-8");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        BASE64Encoder base64Decoder = new BASE64Encoder();
        return base64Decoder.encode(cipher.doFinal(byteContent));
    }

    /**
     * 解密
     *
     * @param encrypt 密文
     * @return
     */
    public String decryptData(String encrypt) throws Exception {
        byte[] bytes = parseHexStr2Byte(encrypt);
        byte[] decrypt = decrypt(bytes, aesKey);
        return new String(decrypt);
    }

    /**
     * 转换密钥<br>
     *
     * @param key
     * @return
     * @throws Exception
     */
    private Key toKey(byte[] key) throws Exception {
        /*DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(dks);*/

        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);

        return secretKey;
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private byte[] decrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);

        return cipher.doFinal(data);
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private byte[] encrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);

        return cipher.doFinal(data);
    }

    /**
     * 生成密钥
     *
     * @return
     * @throws Exception
     */
    private String initKey() throws Exception {
        return initKey(null);
    }

    /**
     * 生成密钥
     *
     * @param seed
     * @return
     * @throws Exception
     */
    private String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;

        if (seed != null) {
            secureRandom = new SecureRandom(decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }

        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(secureRandom);

        SecretKey secretKey = kg.generateKey();

        return encryptBASE64(secretKey.getEncoded());
    }

    public static void main(String[] args) throws Exception {
        String s = javaKeyToCSharpKey("u06csMYzhU3ojX5koBf28g==");
        System.out.println(s);
    }

    /**
     * java的key转换为c#的key
     *
     * @param key
     * @return
     */
    public static String javaKeyToCSharpKey(String key) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        java.security.SecureRandom random = java.security.SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        kgen.init(128, random);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        BASE64Encoder coder = new BASE64Encoder();

        return coder.encode(enCodeFormat);
    }
}
