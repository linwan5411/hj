package cn.jeefast.common.key;

/**
 * <pre>
 * <b>.</b>
 * <b>Description:</b>
 *
 * <b>Author:zhihang</b>
 * <b>Date: 2018/9/7 0007 15:09   </b>
 * <b>Copyright:</b> Copyright 2008-2026 http://www.jinvovo.com Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2018/9/7 0007 15:09          zhihang            new file.
 * <pre>
 */
public class KeyGeneratorFactory {
    public static KeyGenerator createKeyGenerator(Class<? extends KeyGenerator> keyGeneratorClass) {
        try {
            return (KeyGenerator)keyGeneratorClass.newInstance();
        } catch (IllegalAccessException | InstantiationException var2) {
            throw new IllegalArgumentException(String.format("Class %s should have public privilege and no argument constructor", keyGeneratorClass.getName()));
        }
    }

    private KeyGeneratorFactory() {
    }
}
