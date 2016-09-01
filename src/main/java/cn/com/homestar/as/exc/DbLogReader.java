package cn.com.homestar.as.exc;

import cn.com.homestar.as.common.LogConstants;
import cn.com.homestar.as.utils.DateUtils;
import cn.com.homestar.as.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 读取日志文件
 */
public class DbLogReader {

    private static final Logger logger = LoggerFactory.getLogger(DbLogWriter.class);

    public static List<String> readLog(File file) {
            String tempString;
            //读取日志文件
            List<String> list = new LinkedList<>();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))) {
                while ((tempString = bufferedReader.readLine()) != null) {
                    list.add(tempString);
                }
                writeReadLog("读到文件:"+ file.getParent() + file.getName());

            } catch (IOException e) {
                logger.error("警告：读取文件出错啦：{},文件名:{}",e.getMessage(),file.getName());
                e.printStackTrace();
                return list;
            }
        return list;
    }

    public static File[] getYesterdayLogFiles(){
        //获取到日志路径
        String logPath = PropertiesUtil.get(LogConstants.LOG_PATH_KEY);
        File rootDirFile = new File(logPath);
        if (logPath == null || !rootDirFile.isDirectory()) {
            logger.error("警告：日志文件路径不存在");
            return null;
        }
        String dateString = DateUtils.formatDate(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
        File yesterdayDir = new File(logPath + dateString);
        if (!yesterdayDir.isDirectory() || yesterdayDir.listFiles().length == 0) {
            logger.error("警告：昨日{}没有生成日志啊", dateString);
            return null;
        }
        return yesterdayDir.listFiles();
    }

    private static boolean writeReadLog(String readString) {
        File readLog = new File(PropertiesUtil.get(LogConstants.LOG_PATH_KEY) + LogConstants.READ_LOG_FILE);

        if (!readLog.exists()) {
            try {
                readLog.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter writer = new FileWriter(readLog, true)) {//
            writer.write(readString + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        File[] logFiles = getYesterdayLogFiles();
        for (File file:logFiles){
            List<String> strings = readLog(file);
            System.out.println(strings.size());
        }

    }
}
