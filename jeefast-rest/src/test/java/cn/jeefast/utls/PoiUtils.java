package cn.jeefast.utls;

import cn.jeefast.common.utils.HttpClientUtils;
import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.common.utils.KeyGeneratorUtils;
import cn.jeefast.entity.HjHaciendaInfo;
import cn.jeefast.entity.HjServerInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * <pre>
 * <b>.</b>
 * <b>Description:</b>
 *
 * <b>Author:zhihang</b>
 * <b>Date: 2018/11/12 0012 10:24   </b>
 * <b>Copyright:</b> Copyright 2008-2026 http://www.jinvovo.com Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2018/11/12 0012 10:24          zhihang            new file.
 * <pre>
 */
public class PoiUtils{

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     * @throws Exception
     */
    public static void checkExcelVaild(File file) throws Exception{
        if(!file.exists()){
            throw new Exception("文件不存在");
        }
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){
            throw new Exception("文件不是Excel");
        }
    }

    public static List<HjHaciendaInfo> landList(){
        List<HjHaciendaInfo> list = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File("d:/hjn/l.xlsx"); // 创建文件对象
            FileInputStream in = new FileInputStream(excelFile); // 文件流
            checkExcelVaild(excelFile);
            Workbook workbook = getWorkbok(in,excelFile);
            //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的

            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            Sheet sheet = workbook.getSheetAt(0);   // 遍历第一个Sheet
            //Sheet sheet = workbook.getSheetAt(2);   // 遍历第三个Sheet

            // 为跳过第一行目录设置count
            int count = 0;
            for (Row row : sheet) {
                try {
                    // 跳过第一
                    if(count < 1 ) {
                        count++;
                        continue;
                    }

                    //如果当前行没有数据，跳出循环
                    if(row.getCell(0) == null){
                        continue;
                    }

                    //获取总列数(空格的不计算)
                    int columnTotalNum = row.getPhysicalNumberOfCells();
                    //System.out.println("总列数：" + columnTotalNum);

                    // System.out.println("最大列数：" + row.getLastCellNum());

                    HjHaciendaInfo h = new HjHaciendaInfo();

                    int end = row.getLastCellNum();

                    Object obj0 = getValue(row.getCell(1));
                    if(obj0 != null && StringUtils.isNotBlank(obj0.toString())){
                        if("1".equals(obj0.toString())){
                            h.setHaciendaName(KeyGeneratorUtils.getLongValue()+"");
                            h.setHaciendaType(1);
                        }else {
                            h.setHaciendaName(obj0.toString());
                            h.setHaciendaType(2);
                        }
                    }else{
                        h.setHaciendaName(KeyGeneratorUtils.getLongValue()+"");
                        h.setHaciendaType(1);
                    }

                    Object obj1 = getValue(row.getCell(2));
                    if(obj1 != null){
                        h.setDetailAddr(obj1.toString());
                    }

                    Object obj2 = getValue(row.getCell(3));
                    if(obj2 != null){
                        h.setLinkName(obj2.toString());
                    }

                    Object obj3 = getValue(row.getCell(4));
                    if(obj3 != null){
                        String obj = obj3.toString();
                        obj = obj.replace("\t","");
                        obj = obj.replace(";",",");
                        obj = obj.replace("；",",");
                        obj = obj.trim();
                        h.setLinkPhone(obj);
                    }

                    Object obj5 = getValue(row.getCell(5));
                    if(obj5 != null){
                        h.setServerMax(Double.valueOf(obj5.toString()));
                        h.setServerUseMax(Double.valueOf(obj5.toString()));
                    }

                    Object obj6 = getValue(row.getCell(6));
                    if(obj6 != null){
                        h.setHaciendaLand(obj6.toString());
                    }

                    Object obj7 = getValue(row.getCell(7));
                    if(obj7 != null){
                        h.setNeedServerName(obj7.toString());
                    }

                    Object obj8 = getValue(row.getCell(8));
                    if(obj8 != null){
                        h.setHaciendaScope(obj8.toString());
                    }
                    list.add(h);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010
     */
    public static List<HjServerInfo> serverList(){
        List<HjServerInfo> list = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File("d:/hjn/s.xlsx"); // 创建文件对象
            FileInputStream in = new FileInputStream(excelFile); // 文件流
            checkExcelVaild(excelFile);
            Workbook workbook = getWorkbok(in,excelFile);
            //Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的

            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            Sheet sheet = workbook.getSheetAt(0);   // 遍历第一个Sheet
            //Sheet sheet = workbook.getSheetAt(2);   // 遍历第三个Sheet

            // 为跳过第一行目录设置count
            int count = 0;
            for (Row row : sheet) {
                try {
                    // 跳过第一
                    if(count < 1 ) {
                        count++;
                        continue;
                    }

                    //如果当前行没有数据，跳出循环
                    if(row.getCell(0) == null){
                        continue;
                    }

                    //获取总列数(空格的不计算)
                    int columnTotalNum = row.getPhysicalNumberOfCells();
                    //System.out.println("总列数：" + columnTotalNum);

                   // System.out.println("最大列数：" + row.getLastCellNum());

                    HjServerInfo h = new HjServerInfo();

                    int end = row.getLastCellNum();

                    Object obj0 = getValue(row.getCell(0));
                    if(obj0 != null){
                        h.setCompanyName(obj0.toString());
                    }

                    Object obj1 = getValue(row.getCell(1));
                    if(obj1 != null){
                        h.setLinkName(obj1.toString());
                    }

                    Object obj2 = getValue(row.getCell(2));
                    if(obj2 != null){
                        h.setServerRegistration(obj2.toString());
                    }

                    Object obj3 = getValue(row.getCell(3));
                    if(obj3 != null){
                        h.setServerRegTime(obj3.toString());
                    }

                    Object obj5 = getValue(row.getCell(5));
                    if(obj5 != null){
                        h.setServerRegistration(obj5.toString());
                    }

                    Object obj6 = getValue(row.getCell(6));
                    if(obj6 != null){
                        String obj = obj6.toString();
                        obj = obj.replace("\t","");
                        obj = obj.replace(";",",");
                        obj = obj.replace("；",",");
                        obj = obj.trim();
                        h.setLinkPhone(obj);
                    }

                    Object obj7 = getValue(row.getCell(7));
                    if(obj7 != null){
                        h.setDetailAddr(obj7.toString());
                    }

                    Object obj8 = getValue(row.getCell(8));
                    if(obj8 != null){
                        h.setCompanyWebsite(obj8.toString());
                    }

                    Object obj9 = getValue(row.getCell(9));
                    if(obj9 != null){
                        h.setCompanyEmail(obj9.toString());
                    }

                    Object obj10 = getValue(row.getCell(10));
                    if(obj10 != null){
                        h.setCompanyScope(obj10.toString());
                    }
                    list.add(h);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static Object getValue(Cell cell) {
        if(cell == null){
            return null;
        }
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

}
