package cn.com.homestar.as.exc;

import cn.com.homestar.as.common.LogConstants;
import cn.com.homestar.as.model.UserActionLog;
import cn.com.homestar.as.utils.DateUtils;
import cn.com.homestar.as.utils.PropertiesUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;

/**
 * 写入日志工具
 */
public class DbLogWriter {

    private static final Logger logger = LoggerFactory.getLogger(DbLogWriter.class);

    public static void wtriteLog(String jsonString, String flag) {
        if (jsonString == null || "".equals(jsonString.trim())) {//空字符串，不符合写入要求
            logger.error("日志字符串为空");
            return;
        }
        if (JSON.parse(jsonString) == null) {
            logger.error("日志字符串不符合要求");
            return;
        }
        String rootLogPath = PropertiesUtil.get(LogConstants.LOG_PATH_KEY);
        String lineNumStr = PropertiesUtil.get(LogConstants.LOG_TOTAL_LINE_KEY);
        if (rootLogPath == null || lineNumStr == null) {
            logger.error("警告：没有配置日志路径或者日志行数");
            return;
        }
        int lineNum = Integer.valueOf(lineNumStr);
        //写入文件夹
        String today = DateUtils.formatDate(new Date());

        //获取当前应写入文件
        File fileNow = getFileNow(rootLogPath + today + "/", lineNum);
        //获取上一行
        String lastLine = readLastLine(fileNow, "UTF-8");
        int index = getIndex(lastLine);
        if(index==0){
            try {
                fileNow.createNewFile();
            } catch (IOException e) {
                logger.error("新建文件失败："+e.getMessage());
                e.printStackTrace();
            }
        }
        jsonString = LogConstants.LOG_LINE_PREFIX + (index + 1) + flag + jsonString;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileNow, true), "UTF-8"));) {
            bufferedWriter.write(jsonString + "\r\n");
        } catch (IOException e) {
            logger.error("警告：写入日志文件失败啦:{},文件名：{}", e.getMessage(), fileNow.getName());
            e.printStackTrace();
        }
    }

//    private static String getPrefix(String jsonString) {
//        if(jsonString==null||"".equals(jsonString.trim())) return "";
//        String u = Constants.
//        return null;
//    }

    private static File getFileNow(String dir, int lineNum) {
        File dirFile = new File(dir);
        if (!dirFile.exists()) {//今天还没创建日志
            dirFile.mkdirs();
            return new File(dir + LogConstants.LOG_FILE_PREFIX + "1");
        }
        //获取最后一个文件
        File[] files = dirFile.listFiles();
        if (files == null || files.length == 0) return new File(dir + LogConstants.LOG_FILE_PREFIX + "1");
        int[] indexs = new int[files.length];
        int i = 0;
        for (File file : files) {
            if (file.isFile()) {
                int index = file.getName().lastIndexOf("_");
                if (index < 1) continue;//文件名不合法
                String num = file.getName().substring(index + 1);
                if (num == null || "".equals(num.trim())) continue;//文件名不合法
                indexs[i] = Integer.valueOf(num);
                i++;
            }
        }
        sort(indexs);
        File file = new File(dir + LogConstants.LOG_FILE_PREFIX + indexs[0]);
        String lastLine = readLastLine(file, "UTF-8");
        int line = getIndex(lastLine);
        if (line < lineNum) return file;
        return new File(dir + "logFile_" + (indexs[0] + 1));
    }


//    private static FileWriter getFileWrite(String dir,int lineNum) throws IOException {
//        File dirFile = new File(dir);
//        if (!dirFile.exists()) {//今天还没创建日志
//            dirFile.mkdirs();
//            return new FileWriter(new File(dir + "logFile_1"));
//        }
//        //获取最后一个文件
//        File[] files = dirFile.listFiles();
//        if(files==null||files.length==0) return new FileWriter(new File(dir+"logFile_1"));
//        int[] indexs = new int[files.length];
//        int i = 0;
//        for (File file : files) {
//            if (file.isFile()) {
//                int index = file.getName().lastIndexOf("_");
//                if (index < 1) continue;//文件名不合法
//                String num = file.getName().substring(index+1);
//                if (num == null || "".equals(num.trim())) continue;//文件名不合法
//                indexs[i] = Integer.valueOf(num);
//                i++;
//            }
//        }
//        sort(indexs);
//        File file = new File(dir + "logFile_" + indexs[0]);
//        try {
//            String lastLine = readLastLine(file, "UTF-8");
//            int line = getIndex(lastLine);
//            if(line<lineNum) return new FileWriter(file);
//            return new FileWriter(new File(dir+"logFile_"+(indexs[0]+1)));
//        } catch (IOException e) {
//            logger.error("获取文件最后一行失败:{},文件名:{}",e.getMessage(),file.getName());
//            e.printStackTrace();
//            return new FileWriter(new File(dir+"logFile_"+(indexs[0]+1)));
//        }
//    }

    /**
     * 获取当前行索引号
     *
     * @param lastLine
     * @return
     */
    private static int getIndex(String lastLine) {
        if (lastLine == null) return 0;
        String sub = lastLine.substring(0, lastLine.indexOf("/"));
        String[] str = sub.split(";");
        int index = Integer.valueOf(str[1]);
        return index;
    }

    private static int getMiddle(int[] list, int low, int high) {
        int tmp = list[low];
        while (low < high) {
            while (low < high && list[high] <= tmp) {
                high--;
            }
            list[low] = list[high];
            while (low < high && list[low] >= tmp) {
                low++;
            }
            list[high] = list[low];
        }
        list[low] = tmp;
        return low;
    }

    private static void sort(int[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);
            sort(list, low, middle - 1);
            sort(list, middle + 1, high);
        }

    }

    private static void sort(int[] list) {
        if (list != null && list.length > 0) {
            sort(list, 0, list.length - 1);
        }
    }

    /**
     * 读取最后一行数据
     *
     * @param file
     * @param charset
     * @return
     * @throws IOException
     */
    public static String readLastLine(File file, String charset) {
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            long len = raf.length();
            if (len == 0L) {
                return "";
            } else {
                long pos = len - 1;
                while (pos > 0) {
                    pos--;
                    raf.seek(pos);
                    if (raf.readByte() == '\n') {
                        break;
                    }
                }
                if (pos == 0) {
                    raf.seek(0);
                }
                byte[] bytes = new byte[(int) (len - pos)];
                raf.read(bytes);
                if (charset == null) {
                    return new String(bytes);
                } else {
                    return new String(bytes, charset);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (Exception e2) {
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        UserActionLog userActionLog = new UserActionLog();
        userActionLog.setRequestUri("哈哈哈哈哈哈哈哈");
        wtriteLog(JSON.toJSONString(userActionLog), LogConstants.USER_SEARCH_FLAG);
    }
}

