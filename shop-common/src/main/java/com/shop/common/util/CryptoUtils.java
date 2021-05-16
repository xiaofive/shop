package com.shop.common.util;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.google.common.hash.Hashing;
import com.shop.common.constant.SymbolConstant;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by ThomasYu on 2019-07-21
 */
public abstract class CryptoUtils {

    public static String sha256(Object... params) {
        if (ArrayUtils.isEmpty(params)) {
            return SymbolConstant.NONE;
        }
        String origin = Arrays.stream(params).map(Objects::toString).collect(Collectors.joining());
        return Hashing.sha256().hashString(origin, StandardCharsets.UTF_8).toString();
    }

    public static byte[] sha256Bytes(Object... params) {
        if (ArrayUtils.isEmpty(params)) {
            return null;
        }
        String origin = Arrays.stream(params).map(Objects::toString).collect(Collectors.joining());
        return Hashing.sha256().hashString(origin, StandardCharsets.UTF_8).asBytes();
    }

    private static final int DEFAULT_LENGTH = 6;

    public static String generateSalt() {
        return generateSalt(DEFAULT_LENGTH);
    }

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;

    public static String generateSalt(int length) {
        if (length < 1) throw new IllegalArgumentException();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rndCharAt = ThreadLocalRandom.current().nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }

    // 密码 最少8位 必须包含字母 数字 大小写
    public static void main(String[] args) {
        String s = generateSalt();
        System.out.println(s);
        System.out.println(sha256("Cy123456", s));
    }
}
