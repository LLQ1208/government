package com.acsm.training.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.acsm.training.vo.CourseMember;
import org.apache.poi.ss.usermodel.Workbook;

import com.acsm.training.vo.CourseMember;

public class DownloadUtil {
    public static <T> void download(HttpServletResponse response, String fileName, Object[] title,
            String[] propertyNames, List<T> list) {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/octet-stream; charset=utf-8");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            Object[][] sheetData = ObjectExtractUtil.extractSheetData(title, propertyNames, list);
//            ObjectExtractUtil.extractSheetDataForVertical(title, propertyNames, list, 6);
            String sheetName = "Data List";
            Workbook wb = ExcelUtils.write(sheetData, sheetName);
            wb.write(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static <T> void download2(HttpServletResponse response, String fileName, List<CourseMember> list, Integer rowSize) {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/octet-stream; charset=utf-8");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            Object[][] sheetData = ObjectExtractUtil.extractSheetDataForVertical(list, rowSize);
            String sheetName = "Data List";
            Workbook wb = ExcelUtils.write(sheetData, sheetName);
            wb.write(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
