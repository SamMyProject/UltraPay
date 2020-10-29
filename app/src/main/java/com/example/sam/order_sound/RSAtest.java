package com.example.sam.order_sound;


import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RSAtest {
    private static final String ClientpublicKey = "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgG4G/zzyy8OJ+fRm0VKktVRI0VCL" +
            "UTXLMlYCpdFG56/qgDUJa1s+jOhADFWkNwusUZXt9WT5W1rBA5jg9cuLeQjfFVTN" +
            "fSc98LkBHGbF9ahY6zozePb6G6h7SZXnR1NCIfym+GHB8RzlLffkOQ9cvqtL2Fle" +
            "8/vX8qhwPM49QiydAgMBAAE=" ;

    private static final String ClientprivateKey = "MIICXAIBAAKBgG4G/zzyy8OJ+fRm0VKktVRI0VCLUTXLMlYCpdFG56/qgDUJa1s+" +
            "jOhADFWkNwusUZXt9WT5W1rBA5jg9cuLeQjfFVTNfSc98LkBHGbF9ahY6zozePb6" +
            "G6h7SZXnR1NCIfym+GHB8RzlLffkOQ9cvqtL2Fle8/vX8qhwPM49QiydAgMBAAEC" +
            "gYBBAqtfFMqm3+irqvC1lxJ5GYlrwNWcJlK4lVnqfK26uKFGNSmUwTG6rNVc6OmJ" +
            "YSjXoMFlBx0eU3tPomQ4/1Dk0Tapn2S1Xyas3oqPU+bV+fs0sTXNVhXDHgZx1i9N" +
            "KnexB+sr5fIRxTpnVaBDzxKIgctckd2dOcXUZ7lLvCtrdQJBALPEcw8bGGtUkVwc" +
            "N1sWjT2yv1IX7TydrgDkovxGTdO+3+I6HUFquDsfOmjZvnAGGkWyByEK5JQo9SDg" +
            "X0EV53MCQQCcr5EEA98oa9PwxGit7Se6YXG8CwteBWr3dxjf4G0nU2oYEwQVR9Wr" +
            "947d/fw4ud1zydEiCy754ewTU/mW4/evAkEAnQ7jn60ClrNaFGmqi3CWkfMbaHPz" +
            "F2SD48/d/h7GXFuFazdu2/qLX0wXUgf7dBBA3+d8hwfnUPK/x6uATXL/tQJAQx8m" +
            "5WxvLda7bVoGafO9YCecXGzyyq1RfH+FpWpz+DPQsE19I0ypqUic6FaEtRxSVObw" +
            "8z+NOTjiNAl2U4CHOwJBAIjjMx1vw23bNQmKKQqaeLiaYEB8LNAa/5Vs2TCDJGzI" +
            "5mF4F2G06keQl/OGzg+2pgblCZMyp8piZkIYEmqKV2Y=" ;

    private static final String ServerpublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIbMCktXcts5G1qdz6RjwzPiuQR5ZObX0c9Py/l2rV+xhcxnlhnUx84B4kG0TtY+EfHNQUfTAgAROY6tdwfDRZ7MRXjQX/a9oTjRSH0Atcu0W6ziJzrRnALy+yGOJkFHRqlyJxti/+daGPs+zR2Pim7YtAEv87OfW/fM3IXQ3fCQIDAQAB" ;
    //    private static final String ServerprivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMhswKS1dy2zkbWp3PpGPDM+K5BHlk5tfRz0/L+XatX7GFzGeWGdTHzgHiQbRO1j4R8c1BR9MCABE5jq13B8NFnsxFeNBf9r2hONFIfQC1y7RbrOInOtGcAvL7IY4mQUdGqXInG2L/51oY+z7NHY+Kbti0AS/zs59b98zchdDd8JAgMBAAECgYEAhHvm57mQWyRdCAGNsd5lgtXwxAGeKJ+VWWPUAu2bUHEXjJQhvlXVLqKTKfjgmi8wq3lJ83VBL5amlql92bwweaeFFaERxxCHaRqZY8HxBTm5hj1USms6OPkbMTe++xVd6VLoznUOQTyVf0ogXCAaugPpw6f4DMysyRPIQj5mtHUCQQD9Qdq5+L6eB/c5NB6HsaW2Nz3NGT/htPnnWFb/R4G8nk2F5oMgVa8BVHVojcbON8bbcnuzateIf1mWCdo3BRm/AkEAyphsHJ7+yFuQmzk1leB4p1pme/ozLUdVNW68AJbm+Vhz2PheMMxbSq7bOFDLTP4Scb9HL1HMTXXqOghIqhppNwJAKfOGr9ese7EGGsuvYvriYFS2QAs2scSwwMm5X4AQ1ZXDcbLFdc27t5St/iu+CtribeeA5i+ElG/D/z6SjxqhMQJAQKbXiWKrx+CAiyh8kzGpDy7oGZRElKR46q5FzKJbXyL9BTZtSIFOeMzIJHaUCiPiWu5BlzYxTl7culw+ZBo9jQJBAITV1Gy7pmXnlskfkmBOvhLEJvBw0NGTvUHBtB9e02HGwD+8tuI8W/c5wEgcvCraAoogXU2cD9LxNzdo4WNH7dA=" ;
    public static final String RSA = "RSA";
    /** 加密填充方式*/
    public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
    /** 秘钥默认长度*/
    public static final int DEFAULT_KEY_SIZE = 2048;
    public static final byte[] DEFAULT_SPLIT = "#PART#".getBytes();
    public static final int DEFAULT_BUFFERSIZE = (DEFAULT_KEY_SIZE / 8) - 11;

    private String ivRSAencryptedData ;
    private String AESkeyRSAencryptedData ;
    private String AESencryptedData ;
    private  AES aes = new AES() ;
    public RSAtest() {
    }

    //    public SecretKey secretKey2;
//    public IvParameterSpec iv2 ;
//    public String swapSecre ;
    public RSA rsa = new RSA() ;
    public void AES_RSA_DataEncrypted( String _data ) throws Exception {
        String data = _data ;
        ///////////////// AES 加密 ////////////////////
        SecretKey secretKey = aes.genAESKey();
        IvParameterSpec iv = aes.genIV();
//        secretKey2 = secretKey ;
//        iv2 = iv ;
        AESencryptedData = aes.encrypt(data, secretKey, iv);
        String AESkey = aes.secretKeyToBase64( secretKey ) ;

        String ivContext = aes.ivToBase64( iv ) ;
//        String AESkey  = aes.secretKeyToBase64( secretKey ) ;
//        swapSecre = AESkey ;
        ///////////////// AES 加密 ////////////////////
        ///////////////// RSA IV 加密 ////////////////////
//        byte[] encodeData = null;
        ivRSAencryptedData = ivContext ;

        ///////////////// RSA IV 加密 ////////////////////

        ///////////////// RSA AESkey 加密 ////////////////////
        RSAPublicKey publicKey = (RSAPublicKey) getPublicKey( Base64.decode(ServerpublicKey,Base64.NO_WRAP));
// 私钥
//        RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey( Base64.decode(ClientprivateKey,Base64.NO_WRAP)) ;
        try {
//            公钥分段加密
            byte[] encryByte =
                    RSA_java.encryptByPublicKeyForSpilt(AESkey.getBytes(),
                            publicKey.getEncoded());
            String encryBase64 = Base64.encodeToString(encryByte,Base64.NO_WRAP);
            this.AESkeyRSAencryptedData = encryBase64 ;
            Log.e("Lking","RSA公钥加密---->"+encryBase64);
            Log.e("Lking","RSA公钥加密---->"+ AESkey);
            Log.d("space", "AES: " + AESkey.length());
//            byte[] encryBytepri =
//                    RSA_java.decryptByPrivateKeyForSpilt(Base64.decode(this.AESkeyRSAencryptedData,Base64.NO_WRAP),
//                            privateKey.getEncoded());
//            String ans = new String(encryBytepri);
//            Log.e("Lking","RSA私钥解密---->"+ans);
//            SecretKey aeskey = aes.base64ToSecretKey(ans) ;
//            IvParameterSpec ivv = new IvParameterSpec(Base64.decode( ivRSAencryptedData,Base64.NO_WRAP)) ;
//            Log.d("finalANS", "decode:  "  + aes.decrypt(AESencryptedData, aeskey,ivv ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        ///////////////// RSA AESkey 加密 ////////////////////

        Log.d("check", "Data: " + data );
        Log.d("check", "IV: "  + ivContext );
        Log.d("check", "IV?" + ivRSAencryptedData);
        Log.d("check", "AESkey: "  + AESkey );
        Log.d("check", "RSAAESkey: "  + AESkeyRSAencryptedData );
        Log.d("check", "msg:  "  + AESencryptedData );
    }

    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException
    {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException
    {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }


    public String AES_RSA_DataDecrypted( String RSAiv, String  RSA_AES_key, String AES_data ) throws Exception {



        String IV = RSAiv ;
        Log.d("AES","decodeIV: " + IV );
        String AES_key = "" ;

        ///////////////// RSA key 解密 ////////////////////
// 公钥
//        RSAPublicKey publicKey = (RSAPublicKey) getPublicKey( Base64.decode(ServerpublicKey,Base64.DEFAULT));
// 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey( Base64.decode(ClientprivateKey,Base64.DEFAULT)) ;
        try {
//            byte[] encryByte =
//                    RSA_java.encryptByPublicKeyForSpilt("大于秘钥支持加密的最大字节数...".getBytes(),
//                            publicKey.getEncoded());
//            String encryBase64 = Base64.encodeToString(encryByte,Base64.DEFAULT);
//            Log.e("Lking","RSA公钥加密---->"+encryBase64);

            byte[] encryBytepri =
                    RSA_java.decryptByPrivateKeyForSpilt(Base64.decode(RSA_AES_key,Base64.DEFAULT),
                            privateKey.getEncoded());
            AES_key = new String(encryBytepri);
            Log.e("Lking","RSA私钥解密---->"+AES_key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ///////////////// RSA key 解密 ////////////////////
//

        SecretKey aeskey = aes.base64ToSecretKey( AES_key) ;
//        SecretKeySpec secretKeySpec = new SecretKeySpec(decodeRSA_AES_key, "AES");
//
//        String se = aes.secretKeyToBase64( secretKeySpec ) ;
//        Log.d("key","base64: " + se ) ;
        IvParameterSpec ivv = new IvParameterSpec(Base64.decode( IV,Base64.DEFAULT)) ;
//        Log.d("AES","decodeStr" + ivv );
        Log.d("key","decode:   " + aes.decrypt( AES_data, aeskey,ivv));
        return   aes.decrypt( AES_data, aeskey,ivv);


    }

    public String RSAencode( String _data ) {

        try {
            RSAPublicKey publicKey = (RSAPublicKey) getPublicKey( Base64.decode(ServerpublicKey,Base64.NO_WRAP));
            byte[] encryByte =
                    RSA_java.encryptByPublicKeyForSpilt( _data.getBytes(),
                            publicKey.getEncoded());

            String encryBase64 = Base64.encodeToString(encryByte,Base64.NO_WRAP);
            return encryBase64 ;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  "404" ;
    }


    public String getIvRSAencryptedData() {
        return ivRSAencryptedData;
    }


    public String getAESkeyRSAencryptedData() {
        return AESkeyRSAencryptedData;
    }

    public String getAESencryptedData() {
        return AESencryptedData ;
    }

}
