package cn.com.homestar.test;

import cn.com.homestar.as.model.BookStatis;
import cn.com.homestar.as.model.UserActionLog;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lx199 on 2016/8/25.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class WriteJsonTest{

    private static final Logger logger = LoggerFactory.getLogger(WriteJsonTest.class);

    private BookStatis bookStatis;
    private UserActionLog userActionLog;
    private static String LOG_PATH = "d:/logs/";

    @Before
    public void setUp() {
        userActionLog = new UserActionLog();
        userActionLog.setCity("北京");
        userActionLog.setCountry("中国");
        userActionLog.setIp("124.66.88.98");
        userActionLog.setOperatorTime(new Date());
        userActionLog.setRequestUri("dtts.soyinke.conm/user/doLogin.sx");
        userActionLog.setTitle("用户登陆");
        userActionLog.setRegion("北京");
        userActionLog.setUserAgent("chrome");
    }

    @Test
    public void testWriteJson() {
        System.out.println(true);
////        FileOutputStream outputStream = null;
//        int count = 10000;//记录数
//        File dir = new File(LOG_PATH);
//        if (!dir.exists()) dir.mkdirs();
//        String writePath = LOG_PATH + "write/";
//        String userJson = JSON.toJSONString(userActionLog);
//
//        long time3 = System.currentTimeMillis();
//
//        for (int i = 0;i<count;i++){
//            ///################
//            DbLogWriter.wtriteLog(userJson);
//            ///###############
//        }
//        long time4 = System.currentTimeMillis();
//        System.out.println("time:"+(time4-time3));
//
//        File writePathFile = new File(writePath);
//        writePathFile.mkdirs();
//        FileWriter writer = null;
//        long time1 = System.currentTimeMillis();
//        try {
//            writer = new FileWriter(new File(writePath+"test.json"));
//            for (int i = 0 ;i<count;i++){
////                writer.write(userJson+"\r\n");
//                if(i>0&&i%10000==0){
//                    writer.flush();
//                    writer = new FileWriter(new File(writePath+"test"+i+".json"));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            this.close(writer);
//        }
//        long time2 = System.currentTimeMillis();
//        System.out.println(time2 - time1);
    }

    @Test
    public void testReader() {
        File dir = new File(LOG_PATH + "/write");
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            int line = 1;
            long time1 = System.currentTimeMillis();
            for (File file : files) {
                List<UserActionLog> list = new LinkedList<>();
                if (file.isFile() && file.getName().endsWith(".json")) {

                    try (FileReader reader = new FileReader(file);
                         BufferedReader bufferedReader = new BufferedReader(reader)) {
                        String tempString = null;
                        while ((tempString = bufferedReader.readLine()) != null) {
//                            UserActionLog userActionLog = JSON.parseObject(tempString, UserActionLog.class);
                            System.out.println("第" + line + "数据:" + tempString);
                            logger.error(tempString);
//                            System.out.println("解析数据对象:"+userActionLog.getCountry());
                            //保存日志对象
                            UserActionLog userActionLog = JSON.parseObject(tempString,UserActionLog.class);
                            list.add(userActionLog);
//                            userActionMapper.insertSelective(userActionLog);
                            line++;
                        }
                        writeReadLog("读到文件:" + file.getName());
//                        userActionMapper.insertSelectiveBatch(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            long time2 = System.currentTimeMillis();
            System.out.println("耗时" + (time2 - time1));
        }

    }

    private boolean writeReadLog(String readString) {
        File readLog = new File(LOG_PATH + "read.log");

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

    @Test
    public void test() {
        File dir = new File(LOG_PATH + "/write");
        File[] files = dir.listFiles();
        if (files != null) {
            File file = files[0];
            getTrySources(file);
        }
    }

    private void getTrySources(File file) {


        try (FileReader reader = new FileReader(file); BufferedReader bReader = new BufferedReader(reader)) {
            String readString = null;
            if ((readString = bReader.readLine()) != null) {
                System.out.println(readString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void close(Closeable closable){
        if (closable != null) {
            try {
                closable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
