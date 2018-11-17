package cn.jeefast.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <pre>
 * <b>计算经纬度之间的距离.</b>
 * <b>Description:</b>
 *
 * <b>Author:zhihang</b>
 * <b>Date: 2018/11/17 0017 12:10   </b>
 * <b>Copyright:</b> Copyright 2008-2026 http://www.jinvovo.com Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2018/11/17 0017 12:10          zhihang            new file.
 * <pre>
 */
public class LocationUtils {

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米),返回整数去掉小数位
     * @param lat1 纬度 1
     * @param lng1 经度 1
     * @param lat2 纬度 2
     * @param lng2 经度 2
     * @return
     */
    public static int getDistanceToM(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS * 1000;
        return new BigDecimal(s).setScale(2, RoundingMode.UP).intValue();
    }

    /**
     * 通过经纬度获取距离(单位：千米),保留2位小数
     * @param lat1 纬度 1
     * @param lng1 经度 1
     * @param lat2 纬度 2
     * @param lng2 经度 2
     * @return
     */
    public static double getDistanceToKM(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return new BigDecimal(s).setScale(2, RoundingMode.UP).doubleValue();
    }

    /**
     * 通过经纬度获取距离,不足1000M按 m返回，超过按km返回;超过5000KM 按照超过5000km
     * @param lat1 纬度 1
     * @param lng1 经度 1
     * @param lat2 纬度 2
     * @param lng2 经度 2
     * @return
     */
    public static String getDistanceToString(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        System.out.println(s);
        if(s >= 5000){
            return "超过5000km";
        }else if(s >= 1){
            return new BigDecimal(s).setScale(2, RoundingMode.UP).doubleValue()+"km";
        }else if(s > 0){
            return new BigDecimal(s*1000).setScale(2, RoundingMode.UP).doubleValue()+"m";
        }else{
            return "未知";
        }
    }


    public static void main(String[] args) {
        System.out.println(getDistanceToM(40.0497810000, 116.3424590000, 40.009561, 116.36718));
        System.out.println(getDistanceToString(0, 0, 1, 1));
        System.out.println(getDistanceToKM(40.0497810000, 116.3424590000, 40.009561, 116.36718));
        System.out.println(getDistanceToString(40.0497810000, 116.3424590000, 40.009561, 116.36718));
    }

}
