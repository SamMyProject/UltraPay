package com.example.sam.order_sound;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public AES(){}

    public String encrypt(String content, SecretKey secretKey, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte [] temp = cipher.doFinal( content.getBytes() );
        return Base64.encodeToString(temp, Base64.NO_WRAP);
    }

    public String decrypt(String content, SecretKey secretKey, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte [] temp = Base64.decode(content, Base64.NO_WRAP);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte [] decodeBytes = cipher.doFinal(temp);
        return new String(decodeBytes);
    }

    public IvParameterSpec genIV() throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte [] iVAES = new byte[ cipher.getBlockSize() ];
        new SecureRandom().nextBytes(iVAES);
        return new IvParameterSpec(iVAES);
    }
    public SecretKey genAESKey() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException {
        // AES key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256, new SecureRandom());
        return keyGen.generateKey();
    }

    public String secretKeyToBase64(SecretKey secretKey){
        return Base64.encodeToString(secretKey.getEncoded(), Base64.NO_WRAP);
    }
    public String ivToBase64(IvParameterSpec iv){
        return Base64.encodeToString(iv.getIV(), Base64.NO_WRAP);
    }

    public SecretKey base64ToSecretKey(String base64Key){
        byte [] key = Base64.decode(base64Key, Base64.NO_WRAP);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
}
