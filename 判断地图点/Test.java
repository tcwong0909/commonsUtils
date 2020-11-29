package com.tcwong.pattern.Test;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/11/29
 * Since 1.8
 */
public class Test {

    public static void main(String[] args) {
        //北京
        System.out.println("北京"+PolygonUtil.isInPolygon(116.23128, 40.22077));
        //哈尔滨
        System.out.println("哈尔滨"+PolygonUtil.isInPolygon(126.95717, 45.54774));
        //西安
        System.out.println("西安"+PolygonUtil.isInPolygon(108.93425, 34.23053));
        //南京
        System.out.println("南京"+PolygonUtil.isInPolygon(118.8921, 31.32751));
        //深圳
        System.out.println("深圳"+PolygonUtil.isInPolygon(113.88308, 22.55329));
        //澳门
        System.out.println("澳门"+PolygonUtil.isInPolygon(113.54913, 22.19875));
        //台北
        System.out.println("台北"+PolygonUtil.isInPolygon(121.520076, 25.030724));
        //东京
        System.out.println("东京"+PolygonUtil.isInPolygon(139.44, 35.41));
        //平壤
        System.out.println("平壤"+PolygonUtil.isInPolygon(125.30, 39.0));

    }
}