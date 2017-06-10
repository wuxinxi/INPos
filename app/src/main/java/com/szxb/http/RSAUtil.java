package com.szxb.http;

import com.szxb.util.Config;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/**
 * 公钥加解密参考
 */
public class RSAUtil {


    /**
     * 算法名称
     */
    private static final String ALGORITHOM = "RSA";
    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    /**
     * 默认的安全服务提供者
     */
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    public void main1(String[] args) throws Exception {

//        BigInteger modulus = new BigInteger(
//                "94d2d4bd317da870b4623d7cd6e94c2740a4ed51d816d8c4569bef0970a6cd14a0d2b586eaae0a31533bb5a959a036659168dbb49f4840e87c54602420d5453ba4e33e8236c1f0dd08fa362183659a11dc6e439306ac7e4f425feaa7aa5cd72214cbb8f1b5e19b8c2db78cf79a826430962ce21e61ea2a4a7c4c6cf11adfbfeb",
//                16);
//        BigInteger publicExponent = new BigInteger("10001", 16);
//        // 根据给定的系数和专用指数构造一个RSA专用的公钥对象
//        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus,
//                publicExponent);
//
//        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM,
//                DEFAULT_PROVIDER);
//        RSAPublicKey publicKey = (RSAPublicKey) keyFactory
//                .generatePublic(publicKeySpec);
//        // 公钥加密
//        String a = "{ \"mid\":\"100105100000173\",  \"pay_channel\":\"A\", \"out_trade_no\":\"201511171720395557\",  \"total_fee\":1, \"version\":\"1.2\"}";
//        String es = encryptString(a);
//        System.out.println(es);
//        // 公钥解密
//
//        String ds = decryptStringByPublicKey(
//                publicKey,
//                "1290b994fc0a1178db5e1224b16cf78d3ce7c86322e15187cab32a8226e13955e703e1bd9e08f622054502e1df0d5bf16ec14bd6033a39c4b65c19abeeed14e33c2db9c3f0d779f4ac4f2ec47256b0d04561b0e012d49b1ebd66244fe8360ea081458bce80cdf950eda51682ca818d19f31d668ea04d6ac240200b5455e4c0018da812c366de3976d045ee7d64d1ce1f056fa9bc2c6728c908d4c5b840b9d658e7f86025fd4b8e10ac286b625aad6b12c8332ff4f771cce1151d4b86a1d9ecd043e3ea94a67fbe24c6cdf21669b6711761a12b022bacd1f73b6deddf43c8a7cc753f45408f5f35c7fd523a063107e5c40c0f752be4cd93409f1780c1ed0d6fd5");
//
//        System.out.println(ds);


    }


    public static RSAPublicKey getRSAPublicKey() {
        BigInteger modulus = new BigInteger(Config.key, 16);
        BigInteger publicExponent = new BigInteger("10001", 16);
        RSAPublicKey publicKey = null;
        try {
            // 根据给定的系数和专用指数构造一个RSA专用的公钥对象
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus,
                    publicExponent);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM,
                    DEFAULT_PROVIDER);
            publicKey = (RSAPublicKey) keyFactory
                    .generatePublic(publicKeySpec);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }


    /*****************************************
     * Description：使用给定的公钥加密给定的字符串<br/>
     *
     * @param plaintext 字符串。
     * @return 给定字符串的密文。
     *******************************************/
    public static String encryptString(String plaintext) {
        PublicKey publicKey = getRSAPublicKey();
        if (publicKey == null || plaintext == null) {
            return null;
        }
        try {
            byte[] data = plaintext.getBytes("utf-8");

            byte[] en_data = encrypt(publicKey, data);
            return new String(Hex.encodeHex(en_data));
        } catch (Exception ex) {
        }
        return null;
    }

    /*****************************************
     * Description：使用指定的公钥加密数据<br/>
     *
     * @param publicKey 给定的公钥。
     * @param data      要加密的数据。
     * @return 加密后的数据。
     *******************************************/
    public static byte[] encrypt(PublicKey publicKey, byte[] data)
            throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.ENCRYPT_MODE, publicKey);

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = ci.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = ci.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();

        return encryptedData;
    }

    /**
     * 指定的公钥解密
     *
     * @param encrypttext
     * @return
     */
    public static String decryptStringByPublicKey(String encrypttext) {
        PublicKey publicKey = getRSAPublicKey();
        if (publicKey == null) {
            return null;
        }
        try {
            byte[] en_data = Hex.decodeHex(encrypttext.toCharArray());
            byte[] data = decryptByPublicKey(publicKey, en_data);
            return new String(data);
        } catch (Exception ex) {
        }
        return null;
    }

    public static byte[] decryptByPublicKey(PublicKey publicKey, byte[] data)
            throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE, publicKey);

        int inputLen = data.length;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = ci.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = ci.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();

        return decryptedData;
    }
}
