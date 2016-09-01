package cn.com.homestar.as.common;

import cn.com.homestar.as.utils.PropertiesUtil;

/**
 * Created by lx199 on 2016/9/1.
 */
public class LogConstants {
    //日志路径
    public static String LOG_PATH_KEY = "log.file.path";
    //单个日志文件行数
    public static String LOG_TOTAL_LINE_KEY = "log.line.num";
    public static String LOG_FILE_PREFIX = "log_";
    //日志行前缀
    public static String LOG_LINE_PREFIX = "#log;";
    //用户日志文件标记
    public static String USER_ACTION_FLAG = ";ua/";
    //搜索日志文件标记
    public static String USER_SEARCH_FLAG = ";us/";

    public static String USER_VIEW_FLAG = ";uv/";

    public static String USER_DOWN_FLAG=";ud/";

    //读取日志文件
    public static String READ_LOG_FILE ="/read.log";
}
