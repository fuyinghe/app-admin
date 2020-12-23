package com.webmos.framework.util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.webmos.framework.model.ImpModel;
import com.hrbwmxx.framework.fj.model.ExcelModel;
import com.hrbwmxx.framework.fj.model.ExcelMsg;
import com.hrbwmxx.framework.util.Constant;
/**
 * 
* @title: ExcelUtil 
* @description：excel通用导出模板
* @author： lijingyu
* @date： 2018年1月24日 下午2:21:20
* @param <T>
 */
public class ExcelUtil<T> {
	private static String isRequired="0";//是否必填。0否1是。
	public static Workbook  modelExport(String title,List<ImpModel> list) throws Exception {
		Map<String, String> columnMap=null;
		String[] rowName=null;
		String[] rowFName=null;
		List<Map<String, String>> dataList=null;
		//将数据转化为数组数据
		if ( list==null  ) {
			throw new Exception("数据未找到");
		}
		
		rowName =new String[list.size()];
		dataList=new ArrayList<Map<String,String>>();
		columnMap=new HashMap<String, String>();
		//装载列数据
		for (int i = 0; i < list.size(); i++) {
			ImpModel model=list.get(i);
			rowName[i]=model.getTitle();
			columnMap.put(model.getTitle(), model.getIsRequired());
			/*//封装map
			Map<String, String> map=new HashMap<String, String>();
			map.put(model.getTitle(), model.getIsRequired());
			dataList.add(map);*/
		} 
		//装载列属性
		return baseExport(title, rowName, null,columnMap);
		
		
	}
 
	
    public static  Workbook   baseExport(String title,String[] rowName,List<Map<String, String>> dataList,Map<String, String> columnMap){ 
    	  try{
    		  // 创建工作簿对象
              Workbook workbook = new XSSFWorkbook();  
              // 创建工作表
              Sheet sheet = workbook.createSheet(title);                     
              CellStyle style = getStyle(workbook);//单元格样式对象
              
              // 定义所需列数
              int columnNum = rowName.length;
              Row rowRowName = sheet.createRow(0); // 在索引2的位置创建行(最顶端的行开始的第二行)
              
              // 将列头设置到sheet的单元格中
              for(int n=0;n<columnNum;n++){
            	    // 产生表格标题行
                  CellStyle columnTopStyle = ExcelUtil.getColumnTopStyle(workbook);//获取列头样式对象
                  Cell  cellRowName = rowRowName.createCell(n);//创建列头对应个数的单元格
                  cellRowName.setCellType(Cell.CELL_TYPE_STRING);//设置列头单元格的数据类型
                  RichTextString text = new XSSFRichTextString(rowName[n]);
                 
                  if(null!=columnMap&&!isRequired.equals(   columnMap.get(rowName[n])   )) {
                	Font font=workbook.createFont();
                	font.setColor(Font.COLOR_RED);
					columnTopStyle.setFont(font);
                  }else {
                	Font font=workbook.createFont();
                  	font.setColor(HSSFColor.BLACK.index);
  					columnTopStyle.setFont(font);
                  }
                  cellRowName.setCellValue(text);//设置列头单元格的值
                  cellRowName.setCellStyle(columnTopStyle);//设置列头单元格样式
              }
              
              //将查询出的数据设置到sheet对应的单元格中
              if(null!=dataList) {
            	  for(int i=0;i<dataList.size();i++){
                      
                	  Map<String, String> map = dataList.get(i);//遍历每个对象
                      Row row = sheet.createRow(i+3);//创建所需的行数
                      Iterator<String> iterator = map.keySet().iterator();  
                      int j=0;
                      while(iterator.hasNext()) {  
                    	     Cell  cell = null;   //设置单元格的数据类型
                             String key = iterator.next();  
                             String value=map.get(key);
                             cell = row.createCell(j,Cell.CELL_TYPE_STRING);
                             cell.setCellValue(value.toString());    
                             cell.setCellStyle(style);    
                             j++;
                   
                     } 
                     
                  }
              }
              //让列宽随着导出的列长自动适应
              for (int colNum = 0; colNum < columnNum; colNum++) {
                  int columnWidth = sheet.getColumnWidth(colNum) / 256;
                  for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                      Row currentRow;
                      //当前行未被使用过
                      if (sheet.getRow(rowNum) == null) {
                          currentRow = sheet.createRow(rowNum);
                      } else {
                          currentRow = sheet.getRow(rowNum);
                      }
                      if (currentRow.getCell(colNum) != null) {
                          Cell currentCell = currentRow.getCell(colNum);
                          if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
                              int length = currentCell.getStringCellValue().getBytes().length;
                              if (columnWidth < length) {
                                  columnWidth = length;
                              }
                          }
                      }
                  }
                  if(colNum == 0){
                      sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
                  }else{
                      sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
                  }
              }
              
           return workbook;

          }catch(Exception e){
              e.printStackTrace();
              return null;
          }
		
    	
    }
     
	/* 
     * 列头单元格样式
     */    
      public static CellStyle getColumnTopStyle(Workbook workbook) {
          
            // 设置字体
          Font font = workbook.createFont();
          //设置字体大小
          font.setFontHeightInPoints((short)11);
          //字体加粗
          font.setBoldweight(Font.BOLDWEIGHT_BOLD);
          //设置字体名字 
          font.setFontName("Courier New");
          //设置样式; 
          CellStyle style = workbook.createCellStyle();
          //设置底边框; 
          style.setBorderBottom(CellStyle.BORDER_THIN);
          //设置左边框;   
          style.setBorderLeft(CellStyle.BORDER_THIN);
          //设置右边框; 
          style.setBorderRight(CellStyle.BORDER_THIN);
          //设置顶边框; 
          style.setBorderTop(CellStyle.BORDER_THIN);
          //在样式用应用设置的字体;  
          style.setFont(font);
          //设置自动换行; 
          style.setWrapText(false);
          //设置水平对齐的样式为居中对齐;  
          style.setAlignment(CellStyle.ALIGN_CENTER);
          //设置垂直对齐的样式为居中对齐; 
          style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
          
          return style;
          
      }
      
      /*  
     * 列数据信息单元格样式
     */  
      public static CellStyle getStyle(Workbook workbook) {
            // 设置字体
            Font font = workbook.createFont();
            //设置字体大小
            font.setFontHeightInPoints((short)10);
            //字体加粗
            //font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            //设置字体名字 
            font.setFontName("Courier New");
            //设置样式; 
            CellStyle style = workbook.createCellStyle();
            //设置底边框; 
            style.setBorderBottom(CellStyle.BORDER_THIN);
            //设置左边框;   
            style.setBorderLeft(CellStyle.BORDER_THIN);
            //设置右边框; 
            style.setBorderRight(CellStyle.BORDER_THIN);
            //设置顶边框; 
            style.setBorderTop(CellStyle.BORDER_THIN);
            //在样式用应用设置的字体;  
            style.setFont(font);
            //设置自动换行; 
            style.setWrapText(false);
            //设置水平对齐的样式为居中对齐;  
            style.setAlignment(CellStyle.ALIGN_CENTER);
            //设置垂直对齐的样式为居中对齐; 
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
           
            return style;
      
      }
     /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public  static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
        Workbook wb = null;  
        String fileType = fileName.substring(fileName.lastIndexOf("."));  
        if(Constant.EXCEL2003L.equals(fileType)){  
            wb = new HSSFWorkbook(inStr);  //2003-  
        }else if(Constant.EXCEL2007U.equals(fileType)){  
            wb = new XSSFWorkbook(inStr);  //2007+  
        }else{  
            throw new Exception(Constant.EXCEL_ERROR_EXCEPTION_MSG);  
        }  
        return wb;  
    }  
    public static List<Map<String, String>> getDataList(InputStream inStr,String originalFilename) throws Exception{
       //定义空list
    	List<Map<String, String>>   list =null;
       //获得work对象
       Workbook work = getWorkbook(inStr,originalFilename);  
       if(null == work){  
           throw new Exception("创建Excel工作薄为空！");  
       }  
       Sheet sheet = null;  
       Row row = null;  
       Row rowHead = null;  
       Cell cell = null; 
       String key="";
       list=new ArrayList<Map<String,String>>();
	  //遍历Excel中所有的sheet  
       for (int i = 0; i < work.getNumberOfSheets(); i++) {  
           sheet = work.getSheetAt(i);  
           rowHead= sheet.getRow(0);  
           if(sheet==null){continue;}  
           //遍历当前sheet中的所有行  
           for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {  
        	   
               row = sheet.getRow(j);  
               if(row==null||row.getFirstCellNum()==j){continue;}  
               //遍历当前sheet行中的所有列
               HashMap<String, String> map=new HashMap<String,String>();
               for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {  
                   cell = row.getCell(y);  
                   Object value = getCellValue(  cell);
                   //遍历当前sheet0行中的列属性值作为key，当前值为map中value属性
                   map.put((rowHead.getCell(y).getStringCellValue()==null?"":rowHead.getCell(y).getStringCellValue().trim()), value+"");
                   map.put("columnCn", rowHead.getCell(y).getStringCellValue());
               }  
               list.add(map);
              
           }  
       } 
		return list;
    	
    }
    
    /** 
     * 描述：对表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    public static Object getCellValue(Cell cell){  
        Object value = null;  
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符  
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化  
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字  
        //判断数据是否为空，为空直接返回
        if(null==cell) {return value;}
        //校验通过，赋值返回
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_STRING:  
            value = cell.getRichStringCellValue().getString();  
            break;  
        case Cell.CELL_TYPE_NUMERIC:  
            if("General".equals(cell.getCellStyle().getDataFormatString())){  
                value = df.format(cell.getNumericCellValue());  
            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
                value = sdf.format(cell.getDateCellValue());  
            }else{  
                value = df2.format(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = cell.getBooleanCellValue();  
            break;  
        case Cell.CELL_TYPE_BLANK:  
            value = "";  
            break;  
        default:  
        	value = "";  
            break;  
        }  
        return value;  
    }

}
