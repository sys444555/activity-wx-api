package com.activity.common.utils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @����: Charles
 * @ʱ��: 2017��6��7������7:05:14
 * @TODO�� ��������ַ����Ĺ����� uuid
 */
public class UUIDUtils {

    public static String getUUID(){
         return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println("��ʽǰ��UUID �� " + UUID.randomUUID().toString());
        System.out.println("��ʽ�����UUID ��" + new Date());
    }

    //获取随机字符串
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
