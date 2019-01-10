package cn.jeefast.utls;

/**
 * <pre>
 * <b>.</b>
 * <b>Description:</b>
 *
 * <b>Author:zhihang</b>
 * <b>Date: 2018/12/19 0019 18:10   </b>
 * <b>Copyright:</b> Copyright 2008-2026 http://www.jinvovo.com Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2018/12/19 0019 18:10          zhihang            new file.
 * <pre>
 */
public class A {

    public static void main(String[] args) {
        String x = "0717-4351768\t;\t07174286556\t;";

        x = x.replace("\t","");
        x = x.replace(";",",");

        x = x.trim();
        System.out.println(x);
    }
}
