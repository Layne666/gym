package site.layne666.gym.utils;

import java.util.UUID;

/**
 * @author layne666
 * @date 2019/04/27
 */
public class UUIDUtil {
    /**
     * 产生唯一的字符串
     * @return
     */
    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
