package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class FileController {

    /**
     * 中文名转换
     * @param filename
     * @param agent
     * @return
     * @throws IOException
     */
    public static String encodeDownloadFilename(String filename, String agent) throws IOException {
        if(agent.contains("Firefox")){ // 火狐浏览器
            filename = "=?UTF-8?B?"+new BASE64Encoder().encode(filename.getBytes("utf-8"))+"?=";
        }else{ // IE及其他浏览器
            filename = URLEncoder.encode(filename,"utf-8");
        }
        return filename;
    }

    public DemoClassEXCEL translate(DemoClassEXCEL c,String [] strArr) {
        if (strArr != null && strArr.length > 19) {
            c.setParam1(strArr[0]);
            c.setParam2(strArr[1]);
            c.setParam3(strArr[2]);
            c.setParam4(strArr[3]);
            c.setParam5(strArr[4]);
            c.setParam6(strArr[5]);
            c.setParam7(strArr[6]);
            c.setParam8(strArr[7]);
            c.setParam9(strArr[8]);
            c.setParam10(strArr[9]);
            c.setParam11(strArr[10]);
            c.setParam12(strArr[11]);
            c.setParam13(strArr[12]);
            c.setParam14(strArr[13]);
            c.setParam15(strArr[14]);
            c.setParam16(strArr[15]);
            c.setParam17(strArr[16]);
            c.setParam18(strArr[17]);
            c.setParam19(strArr[18]);
            c.setParam20(strArr[19]);
        } else {
            if (strArr.length > 0) {
                c.setParam1(strArr[0]);
            } else {
                return c;
            }
            if (strArr.length > 1) {
                c.setParam2(strArr[1]);
            } else {
                return c;
            }
            if (strArr.length > 2) {
                c.setParam3(strArr[2]);
            } else {
                return c;
            }
            if (strArr.length > 3) {
                c.setParam4(strArr[3]);
            } else {
                return c;
            }
            if (strArr.length > 4) {
                c.setParam5(strArr[4]);
            } else {
                return c;
            }
            if (strArr.length > 5) {
                c.setParam6(strArr[5]);
            } else {
                return c;
            }
            if (strArr.length > 6) {
                c.setParam7(strArr[6]);
            } else {
                return c;
            }
            if (strArr.length > 7) {
                c.setParam8(strArr[7]);
            } else {
                return c;
            }
            if (strArr.length > 8) {
                c.setParam9(strArr[8]);
            } else {
                return c;
            }
            if (strArr.length > 9) {
                c.setParam10(strArr[9]);
            } else {
                return c;
            }
            if (strArr.length > 10) {
                c.setParam11(strArr[10]);
            } else {
                return c;
            }
            if (strArr.length > 11) {
                c.setParam12(strArr[11]);
            } else {
                return c;
            }
            if (strArr.length > 12) {
                c.setParam13(strArr[12]);
            } else {
                return c;
            }
            if (strArr.length > 13) {
                c.setParam14(strArr[13]);
            } else {
                return c;
            }
            if (strArr.length > 14) {
                c.setParam15(strArr[14]);
            } else {
                return c;
            }
            if (strArr.length > 15) {
                c.setParam16(strArr[15]);
            } else {
                return c;
            }
            if (strArr.length > 16) {
                c.setParam17(strArr[16]);
            } else {
                return c;
            }
            if (strArr.length > 17) {
                c.setParam18(strArr[17]);
            } else {
                return c;
            }
            if (strArr.length > 18) {
                c.setParam19(strArr[18]);
            } else {
                return c;
            }
            if (strArr.length > 19) {
                c.setParam20(strArr[19]);
            } else {
                return c;
            }
        }
        System.out.println(JSONObject.toJSONString(c));
        return c;
    }

    public DemoClassEXCEL translateSql(DemoClassEXCEL c,String  strArr,Integer cityNum) {

        System.out.println(JSONObject.toJSONString(c));
        return c;
    }
    @RequestMapping(value = "/transLateWorld", method = RequestMethod.GET)
    public void transLateWorld(HttpServletResponse response, HttpServletRequest request) {
        try {
            DemoClassEXCEL c = new DemoClassEXCEL();
            List<DemoClassEXCEL> excelList = new ArrayList<DemoClassEXCEL>();

            // 文件夹路径
            String path = "F:/download/world.txt";
            List<String> scanListPath = readFile02(path);
            for (int i = 0; i < scanListPath.size(); i++) {
                String mytext = scanListPath.get(i);
                System.out.println("前：" + mytext);
                //替换所有制表符
                mytext = mytext.replaceAll("\t", ",");
                System.out.println("后：" + mytext);
                //每一行都转化为新的数组，根据下标去判断参数值对应的参数是什么
                String[] strArr = mytext.split(","); //注意分隔符是需要转译
                c = new DemoClassEXCEL();
                c=translate(c,strArr);
                excelList.add(c);
            }
            String returnName = encodeDownloadFilename("文字转换", request.getHeader("user-agent"));
            ExcelUtils.exportExcel(excelList, null, "文字转换", DemoClassEXCEL.class, returnName + ".xls", response);
            System.out.println("================= Success to export dealer balance Excel :文字转换");
        } catch (Exception e) {
            System.out.println("=================  Start to export empty dealer detail Excel :" + e);

        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str.charAt(0)+"");
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/transLateSql", method = RequestMethod.GET)
    public void transLateSql(HttpServletResponse response, HttpServletRequest request) {
        try {
            DemoClassEXCEL c = new DemoClassEXCEL();
            List<DemoClassEXCEL> sqlList = new ArrayList<DemoClassEXCEL>();

            // 文件夹路径
            String path = "F:/download/qu.txt";
            List<String> scanListPath = readFileSql(path);
            String sqlStr="";
            Integer city=null;
            int num=0;

            for (int i = 0; i < scanListPath.size(); i++) {
                String mytext = scanListPath.get(i);
                System.out.println("前：" + mytext);
                if (isNumeric(mytext)) {
                    mytext = mytext.substring(0, 6);
                    city = Integer.valueOf(mytext);
                    sqlList.add(new DemoClassEXCEL("-- ******************************** " + city + "****" + num+ "条***********"));
                    num=0;
                    continue;
                } else {
                    if ("{".equals(mytext)) {
                        sqlStr = "INSERT INTO `htmlOrg` (pid,name,id)VALUES ( " + city;
                        continue;
                    }
                    if (mytext.startsWith("city")) {
                        sqlStr += ",";
                        continue;
                    }
                    if (mytext.startsWith("text")) {
                        String text = mytext.substring(mytext.indexOf('\''), mytext.lastIndexOf('\''));
                        sqlStr += text + "',";
                        continue;
                    }
                    if (mytext.startsWith("value:")) {
                        String dempValue =mytext.substring(mytext.indexOf(':')+1, mytext.length());
                        sqlStr = sqlStr+dempValue;
                        continue;
                    }
                    if (mytext.startsWith("}")) {
                        sqlStr += ");";
                        sqlList.add(new DemoClassEXCEL(sqlStr));
                        sqlStr=null;
                        num++;
                        continue;
                    }
                }
            }
            String returnName = encodeDownloadFilename("文字转换", request.getHeader("user-agent"));
            ExcelUtils.exportExcel(sqlList, null, "文字转换", DemoClassEXCEL.class, returnName + ".xls", response);
            System.out.println("================= Success to export dealer balance Excel :文字转换");
        } catch (Exception e) {
            System.out.println("=================  Start to export empty dealer detail Excel :" + e);

        }
    }
    public static void main(String [] args)  {
        String aaa="abcdefghijkl,mnopqrst,uvwxyz,";
        String[] split = aaa.split(",");
        System.out.print("----------------"+split.length);
        System.out.print("----------------"+split[0]);
        System.out.print("----------------"+split[2]);
    }

    /**
     * 读取一个文本 一行一行读取
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readFile02(String path) throws IOException {
        // 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
        List<String> list = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(path);
        // 防止路径乱码  如果utf-8 乱码 改GBK   eclipse里创建的txt 用UTF-8，在电脑上自己创建的txt 用GBK
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            // 如果 t x t文件里的路径 不包含---字符串    这里是对里面的内容进行一个筛选
            if (line.lastIndexOf("---") < 0) {
                list.add(line);
            }
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }

    /**
     * 读取一个文本 一行一行读取
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readFileSql(String path) throws IOException {
        // 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
        List<String> list = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(path);
        // 防止路径乱码  如果utf-8 乱码 改GBK   eclipse里创建的txt 用UTF-8，在电脑上自己创建的txt 用GBK
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            // 如果 t x t文件里的路径 不包含---字符串    这里是对里面的内容进行一个筛选
            if (line.lastIndexOf("---") < 0) {
                list.add(line.trim());
            }
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }

}
