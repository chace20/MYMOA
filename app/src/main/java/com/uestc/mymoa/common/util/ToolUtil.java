package com.uestc.mymoa.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chao on 2015/7/20.
 */

public class ToolUtil {
    public static final int TIME_STAMP = 0x2325;
    public static final int TIME_COMMON = 0x1242;

    /**
     * 根据mode获取当前时间,
     * TIME_STAMP 获取时间戳,
     * TIME_COMMON 获取普通时间
     *
     * @param mode
     * @return time
     */
    public static String getCurrentTime(int mode) {
        long time = System.currentTimeMillis();
        if (mode == TIME_STAMP)
            return time + "";
        return getCommonTimeByStamp(time + "");
    }

    /**
     * 通过时间戳获取正常时间
     *
     * @param stamp
     * @return time
     */
    public static String getCommonTimeByStamp(String stamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long stampL = Long.parseLong(stamp);
        String d = format.format(new Date(stampL));
        return d;
    }
}

