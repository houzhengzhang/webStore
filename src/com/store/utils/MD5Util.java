package com.store.utils;


import com.ndktools.javamd5.Mademd5;

/**
 * @ Date: 2018/12/1 21:26
 * @ Description:
 */
public class MD5Util {
    private static Mademd5 md = new Mademd5();

    public static String toMd5(String str) {
        return md.toMd5(str);
    }

    public static void main(String[] args) {
        String password = "123456";
        System.out.println(md.toMd5(password));
    }
//
//    /**
//     * MD5验证方法
//     *
//     * @param text 明文
//     * @param key 密钥
//     * @param md5 密文
//     * @return true/false
//     * @throws Exception
//     */
//    public static boolean verify(String text, String key, String md5) throws Exception {
//        //根据传入的密钥进行验证
//        String md5Text = md5(text, key);
//        if(md5Text.equalsIgnoreCase(md5))
//        {
//            System.out.println("MD5验证通过");
//            return true;
//        }
//
//        return false;
//    }
}
