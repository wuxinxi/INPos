package com.szxb.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.szxb.PosApplication;
import com.szxb.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：Tangren on 2017/6/8 13:04
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class Util {

    private static final String TAG = "Util";

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 订单号
     * @return
     */
    public static String getOrderNo() {
        return format.format(new Date()) + buildRadom(5);
    }

    public static int buildRadom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1)
            random = random + 0.1;
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) (random * num);
    }

    public static Bitmap CreateCode(String str) {
        Bitmap bitmap = null;
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, 300, 300);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            // 二维矩阵转为一维像素数组,也就是一直横着排了
            int[] pix = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (matrix.get(x, y)) {
                        pix[y * width + x] = 0xff000000;
                    }
                }
            }
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pix, 0, width, 0, 0, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
            Log.e(TAG, "CreateCode: " + e.getMessage());
            return BitmapFactory.decodeResource(PosApplication.getInstance().getResources(), R.mipmap.load_fail);
        }

        return bitmap;

    }

    /**
     * 检查字符串是否可以转化成数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (Util.isEmpty(str)) {
            return false;
        } else {
            char[] chars = str.toCharArray();
            int sz = chars.length;
            boolean hasExp = false;
            boolean hasDecPoint = false;
            boolean allowSigns = false;
            boolean foundDigit = false;
            int start = chars[0] == 45 ? 1 : 0;
            int i;
            if (sz > start + 1 && chars[start] == 48 && chars[start + 1] == 120) {
                i = start + 2;
                if (i == sz) {
                    return false;
                } else {
                    while (i < chars.length) {
                        if ((chars[i] < 48 || chars[i] > 57) && (chars[i] < 97 || chars[i] > 102) && (chars[i] < 65 || chars[i] > 70)) {
                            return false;
                        }

                        ++i;
                    }

                    return true;
                }
            } else {
                --sz;

                for (i = start; i < sz || i < sz + 1 && allowSigns && !foundDigit; ++i) {
                    if (chars[i] >= 48 && chars[i] <= 57) {
                        foundDigit = true;
                        allowSigns = false;
                    } else if (chars[i] == 46) {
                        if (hasDecPoint || hasExp) {
                            return false;
                        }

                        hasDecPoint = true;
                    } else if (chars[i] != 101 && chars[i] != 69) {
                        if (chars[i] != 43 && chars[i] != 45) {
                            return false;
                        }

                        if (!allowSigns) {
                            return false;
                        }

                        allowSigns = false;
                        foundDigit = false;
                    } else {
                        if (hasExp) {
                            return false;
                        }

                        if (!foundDigit) {
                            return false;
                        }

                        hasExp = true;
                        allowSigns = true;
                    }
                }

                return i < chars.length ? (chars[i] >= 48 && chars[i] <= 57 ? true : (chars[i] != 101 && chars[i] != 69 ? (chars[i] == 46 ? (!hasDecPoint && !hasExp ? foundDigit : false) : (allowSigns || chars[i] != 100 && chars[i] != 68 && chars[i] != 102 && chars[i] != 70 ? (chars[i] != 108 && chars[i] != 76 ? false : foundDigit && !hasExp && !hasDecPoint) : foundDigit)) : false)) : !allowSigns && foundDigit;
            }
        }
    }


    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }
}
