package com.example.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    /**
     * 进阶自定义导出方法 - 通过Response导出
     *
     * @param list
     * @param title
     * @param sheetName
     * @param pojoClass
     * @param fileName
     * @param response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    /**
     * 基本导出方法 - 通过Response导出
     *
     * @param list
     * @param fileName
     * @param response
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }

    /**
     * Response 输出流输出Excel
     *
     * @param fileName
     * @param response
     * @param workbook
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            logger.info("导出文件名称:["+fileName+"]");
            ServletOutputStream outputStream = response.getOutputStream();
            response.setHeader("Content-disposition", fileName);
            response.setContentType("application/vnd.ms-ExampleExcelHandler");
            response.setHeader("Content-disposition", "attachment;filename="+fileName);
            response.setHeader("Pragma", "No-cache");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Excel导出失败！错误原因[%s]");
        }
    }

    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (filePath==null || filePath.length()<1){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            System.out.println("Excel导入失败！错误原因[%s]");
        } catch (Exception e) {
            System.out.println("Excel导出失败！错误原因[%s]");
        }
        return list;
    }

    public static <T> List<T> importExcel(MultipartFile file, String[] title, Class<T> pojoClass,IExcelDataHandler<T> iExcelDataHandler){
        if (file == null){
            return null;
        }
        ImportParams importParams = new ImportParams();
        // 数据处理
        iExcelDataHandler.setNeedHandlerFields(title);// 注意这里对应的是excel的列名。也就是对象上指定的列名。
        importParams.setDataHanlder(iExcelDataHandler);
        // 需要验证
        importParams.setNeedVerfiy(true);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, importParams);
        }catch (NoSuchElementException e){
            System.out.println("Excel导入失败！错误原因excel文件不能为空");
        } catch (Exception e) {
            System.out.println("Excel导入失败！错误原因[%s]");
        }
        return list;
    }

}
