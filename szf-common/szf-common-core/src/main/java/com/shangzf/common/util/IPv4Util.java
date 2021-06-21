package com.shangzf.common.util;

import com.shangzf.common.constant.StringConstant;

import java.net.InetAddress;

public class IPv4Util {

    private static final int INADDRSZ = 4;

    /**
     * 把IP地址转化为字节数组
     *
     * @param ipAddr IP地址
     * @return byte[]
     */
    public static byte[] ipToBytesByInet(String ipAddr) {
        try {
            return InetAddress.getByName(ipAddr).getAddress();
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    /**
     * 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
     */
    public static long ipToLong(String strIp) {
        long[] ip = new long[4];
        // 先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf(StringConstant.DOT);
        int position2 = strIp.indexOf(StringConstant.DOT, position1 + 1);
        int position3 = strIp.indexOf(StringConstant.DOT, position2 + 1);
        // 将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    /**
     * 将十进制整数形式转换成127.0.0.1形式的ip地址
     */
    public static String longToIp(long longIp) {
        // 直接右移24位
        return (longIp >>> 24) + StringConstant.DOT +
                // 将高8位置0，然后右移16位
                ((longIp & 0x00FFFFFF) >>> 16) + StringConstant.DOT +
                // 将高16位置0，然后右移8位
                ((longIp & 0x0000FFFF) >>> 8) + StringConstant.DOT +
                // 将高24位置0
                (longIp & 0x000000FF);
    }
}
