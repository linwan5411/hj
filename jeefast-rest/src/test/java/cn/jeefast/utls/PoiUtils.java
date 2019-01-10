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
import java.math.BigDecimal;
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
            File excelFile = new File("d:/h/l.xlsx"); // 创建文件对象
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
                    if(count < 2 ) {
                        count++;
                        continue;
                    }

                    //如果当前行没有数据，跳出循环
                    if(row.getCell(0) == null){
                        continue;
                    }
                    HjHaciendaInfo h = new HjHaciendaInfo();

                    //最小区域
                    Object obj0 = getValue(row.getCell(2));
                    if(obj0 != null){
                        h.setAreaCode(obj0.toString());
                    }
                    //详细地址
                    Object obj1 = getValue(row.getCell(3));
                    if(obj1 != null){
                        h.setDetailAddr(obj1.toString());
                    }

                    // 名臣
                    Object obj2 = getValue(row.getCell(4));
                    if(obj2 != null){
                        h.setHaciendaName(obj2.toString());
                    }

                    //联系人
                    Object obj3 = getValue(row.getCell(5));
                    if(obj3 != null){
                        h.setLinkName(obj3.toString());
                    }

                    //电话
                    Object obj5 = getValue(row.getCell(6));
                    if(obj5 != null){
                      h.setLinkPhone(obj5.toString());
                    }

                    //土地面积
                    Object obj6 = getValue(row.getCell(7));
                    if(obj6 != null){
                        h.setServerMax(Double.valueOf(obj6.toString()));
                    }

                    //农场类型
                    Object obj7 = getValue(row.getCell(8));
                    if(obj7 != null){
                        h.setHaciendaGener(obj7.toString());
                    }

                    //土地性质
                    h.setHaciendaLand("耕地");
                    h.setServerCategory("21");

                    //附着物
                    Object obj8 = getValue(row.getCell(10));
                    if(obj8 != null){
                        h.setHaciendaScope(obj8.toString());
                    }

                    StringBuilder codes = new StringBuilder();
                    StringBuilder names = new StringBuilder();


                    Object obj11 = getValue(row.getCell(11));
                    if(obj11 != null){
                        names.append(obj11.toString()).append(",");
                        codes.append(getServerType(obj11.toString())).append(",");
                    }
                    Object obj12 = getValue(row.getCell(12));
                    if(obj12 != null){
                        names.append(obj12.toString()).append(",");
                        codes.append(getServerType(obj12.toString())).append(",");
                    }
                    Object obj13 = getValue(row.getCell(13));
                    if(obj13 != null){
                        names.append(obj13.toString()).append(",");
                        codes.append(getServerType(obj13.toString())).append(",");
                    }
                    Object obj14 = getValue(row.getCell(14));
                    if(obj14 != null){
                        names.append(obj14.toString()).append(",");
                        codes.append(getServerType(obj14.toString())).append(",");
                    }
                    Object obj15 = getValue(row.getCell(15));
                    if(obj15 != null){
                        names.append(obj15.toString()).append(",");
                        codes.append(getServerType(obj15.toString())).append(",");
                    }
                    Object obj16 = getValue(row.getCell(16));
                    if(obj16 != null){
                        names.append(obj16.toString()).append(",");
                        codes.append(getServerType(obj16.toString())).append(",");
                    }
                    Object obj17 = getValue(row.getCell(17));
                    if(obj17 != null){
                        names.append(obj17.toString()).append(",");
                        codes.append(getServerType(obj17.toString())).append(",");
                    }
                    Object obj18 = getValue(row.getCell(18));
                    if(obj18 != null){
                        names.append(obj18.toString()).append(",");
                        codes.append(getServerType(obj18.toString())).append(",");
                    }
                    if(codes.length() > 0){
                        h.setNeedServerName(codes.toString().substring(0,codes.length() -1));
                    }
                    if(names.length() > 0){
                        h.setNeedServer(names.toString().substring(0,names.length() -1));
                    }
                    //简介
                    Object obj19 = getValue(row.getCell(20));
                    if(obj19 != null) {
                        h.setHaciendaRemark(obj19.toString());
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


    public static void main(String[] args) {

        List<HjHaciendaInfo> l = landList();


        l.forEach(e ->{
            System.out.println(JsonUtils.Bean2Json(e));
        });
    }


    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010
     */
    public static List<HjServerInfo> serverList(){
        List<HjServerInfo> list = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File("d:/h/s.xlsx"); // 创建文件对象
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
                    if(count < 2 ) {
                        count++;
                        continue;
                    }

                    //如果当前行没有数据，跳出循环
                    if(row.getCell(0) == null){
                        continue;
                    }

                    //获取总列数(空格的不计算)
                    int columnTotalNum = row.getPhysicalNumberOfCells();

                    HjServerInfo h = new HjServerInfo();

                    int end = row.getLastCellNum();

                    //区
                    Object obj0 = getValue(row.getCell(2));
                    if(obj0 != null){
                        h.setAreaCode(obj0.toString());
                    }
                    //详细地址
                    Object obj1 = getValue(row.getCell(3));
                    if(obj1 != null){
                        h.setDetailAddr(obj1.toString());
                    }

                    //名称
                    Object obj2 = getValue(row.getCell(4));
                    if(obj2 != null){
                        h.setCompanyName(obj2.toString());
                    }

                    //联系人
                    Object obj3 = getValue(row.getCell(5));
                    if(obj3 != null){
                        h.setLinkName(obj3.toString());
                    }

                    //电话
                    Object obj5 = getValue(row.getCell(6));
                    if(obj5 != null){
                        h.setLinkPhone(obj5.toString());
                    }

                    //等级
                    Object obj7 = getValue(row.getCell(7));
                    if(obj7 != null){
                        h.setServerGrade(obj7.toString());
                    }

                    //服务类型
                    Object obj8 = getValue(row.getCell(8));
                    if(obj8 != null){
                        h.setServerGenre(obj8.toString());
                    }

                    //已服务农场（家）
                    Object obj9 = getValue(row.getCell(9));
                    if(obj9 != null){
                        h.setClientNum(new BigDecimal(obj9.toString()).intValue());
                    }

                    //已服务面积（亩
                    Object obj10 = getValue(row.getCell(10));
                    if(obj10 != null){
                        h.setServerMax(Double.valueOf(obj10.toString()));
                    }

                    StringBuilder codes = new StringBuilder();
                    StringBuilder names = new StringBuilder();


                    Object obj11 = getValue(row.getCell(11));
                    if(obj11 != null){
                        names.append(obj11.toString()).append(",");
                        codes.append(getServerType(obj11.toString())).append(",");
                    }
                    Object obj12 = getValue(row.getCell(12));
                    if(obj12 != null){
                        names.append(obj12.toString()).append(",");
                        codes.append(getServerType(obj12.toString())).append(",");
                    }
                    Object obj13 = getValue(row.getCell(13));
                    if(obj13 != null){
                        names.append(obj13.toString()).append(",");
                        codes.append(getServerType(obj13.toString())).append(",");
                    }
                    Object obj14 = getValue(row.getCell(14));
                    if(obj14 != null){
                        names.append(obj14.toString()).append(",");
                        codes.append(getServerType(obj14.toString())).append(",");
                    }
                    Object obj15 = getValue(row.getCell(15));
                    if(obj15 != null){
                        names.append(obj15.toString()).append(",");
                        codes.append(getServerType(obj15.toString())).append(",");
                    }
                    Object obj16 = getValue(row.getCell(16));
                    if(obj16 != null){
                        names.append(obj16.toString()).append(",");
                        codes.append(getServerType(obj16.toString())).append(",");
                    }
                    Object obj17 = getValue(row.getCell(17));
                    if(obj17 != null){
                        names.append(obj17.toString()).append(",");
                        codes.append(getServerType(obj17.toString())).append(",");
                    }
                    Object obj18 = getValue(row.getCell(18));
                    if(obj18 != null){
                        names.append(obj18.toString()).append(",");
                        codes.append(getServerType(obj18.toString())).append(",");
                    }
                    if(codes.length() > 0){
                        h.setServerCodes(codes.toString().substring(0,codes.length() -1));
                    }
                    if(names.length() > 0){
                        h.setServerCategory(names.toString().substring(0,names.length() -1));
                    }

                    Object obj19 = getValue(row.getCell(19));
                    if(obj19 != null){
                        h.setTrusteeshipList(obj19.toString());
                    }

                    Object obj20 = getValue(row.getCell(20));
                    if(obj20 != null){
                        h.setCompanyScope(obj20.toString());
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


    public static String getServerType(String name){
        if(name.equals("土地平整")){
            return "12";
        }else if(name.equals("测土施肥")){
            return "13";
        }else if(name.equals("育苗播种")){
            return "14";
        }else if(name.equals("无人机植保")){
            return "19";
        }else if(name.equals("农机收割")){
            return "16";
        }else if(name.contains("云烘干")){
            return "126";
        }else if(name.contains("农资供给")){
            return "128";
        }else {
            return "129";
        }
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
