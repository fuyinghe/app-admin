package com.hrbwmxx.framework.fj.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrbwmxx.framework.fj.dao.ExcelMapper;
import com.hrbwmxx.framework.fj.model.ExcelModel;
import com.hrbwmxx.framework.fj.model.ExcelMsg;
import com.hrbwmxx.framework.fj.vo.ExcelVo;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.Constant;
import com.hrbwmxx.framework.util.ExcelUtil;

import common.Logger;
/**
 * 
* @title: ExcelServiceImpl 
* @description：excel实现类
* @author： lijingyu
* @date： 2018年1月22日 下午4:22:18
 */
@Service
public class ExcelServiceImpl implements IExcelService{
	 	
	    private static Logger logger=Logger.getLogger( ExcelServiceImpl.class );
	   
	    @Autowired
	    private  ExcelMapper excelMapper;
	    /** 
	     * 描述：读取数据将excel数据持久化到数据库中
	     * @param in,fileName 
	     * @return List<List<String[]>>
	     * @throws IOException  
	     */  
	    public Result importExcel( MultipartFile file, ExcelModel excelParam)throws Exception {
	    	
	    	//定义返回参数
	    	ExcelVo result=new ExcelVo();
	    	 
	    	//读取数据将excel数据持久化到数据库中
	    	InputStream  in=file.getInputStream();
	    	List<List<String[]>>  list =readFromExcel(in, file.getOriginalFilename(), excelParam);
	    	//持久化到数据库中,将收集的错误数据返回到前台处理
	    	if(null!=list&&list.size()>1) {
	    		saveList2Db(list.get(0));
	    		list.remove( list.get(0) );
	    		result.setList( list.get(0)  );
	    	}
	    	//如果返回list没有数据将错误提示返给前台
	    	if(list==null||list.size()<1) {
	    		result.setErrcode(Constant.ERRCODE_310);
	    		result.setErrmsg(Constant.EXCEL_ERROR_TEMPLATE_MSG);

	    	}
	    	return	result;
		}  
	    /**
	     * 
	    * @MethodName: saveList2Db 
	    * @description : 持久化到数据库中
	    * @author：lijingyu 
	    * @date： 2018年1月23日 下午2:30:43
	    * @param list void
	     */
	    private void saveList2Db(List<String[]> list) {
			if(null!=list&&list.size()>0) {
				//TODO excelMapper.saveBatchList(list);
			}
		}

		/** 
	     * 描述：
	     * 1、获取IO流中的数据，组装成List<List<Object>>对象 
	     * 2、当错误数据达到一定的条数时候只返回预先设置条错误信息
	     * 3、只有数据没有错误数据才返回相应的list集合
	     * @param in,fileName 
	     * @return 
	     * @throws IOException  
	     */  
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public  List<List<String[]>> readFromExcel(InputStream in,String fileName,ExcelModel excelParam) throws Exception{ 
	    	//定义返回参数（返回集合包含错误集合）
	    	List<List<String[]>>  list = null;  
	        List<String[]> errorList=null;
	        List<String[]> rightList=null;
	        //创建Excel工作薄  
	        Workbook work = this.getWorkbook(in,fileName);  
	        if(null == work){  
	            throw new Exception("创建Excel工作薄为空！");  
	        }  
	        Sheet sheet = null;  
	        Row row = null;  
	        Cell cell = null;  
	          
	        list = new ArrayList< List< String[]> >();  
	        errorList=new ArrayList<String[]>();   
	        rightList=new ArrayList<String[]>();   
	        //遍历Excel中所有的sheet  
	        for (int i = 0; i < work.getNumberOfSheets(); i++) {  
	            sheet = work.getSheetAt(i);  
	            if(sheet==null){continue;}  
	           //当前错误数据达到一定数量不予收集数据了，同时跳出当前循环
                if(null!=errorList&&(errorList.size()>Constant.EXCEL_ERROR_SIZE)) {
                	break;
                } 
	            //遍历当前sheet中的所有行  
	            for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum(); j++) {  
	                row = sheet.getRow(j);  
	                if(row==null||row.getFirstCellNum()==j){continue;}  

	                String[] objs=new  String[row.getPhysicalNumberOfCells()];
	                String[] errors=new  String[row.getPhysicalNumberOfCells()];//记录失败坐标，具体数据，原因
	                boolean  isAdd=true;//标记当前行数据是否添加到入库正确数据项中
	                //校验列长度是否正确
	                ExcelMsg  msgRow=checkRow(row);
	                if(!msgRow.isSuccess()){
	                	logger.error(Constant.EXCEL_ERROR_TEMPLATE_MSG);
	                	return null;  
	                }
	                //当前错误数据达到一定数量不予收集数据了，同时跳出当前循环 
                    if(null!=errorList&&(errorList.size()>Constant.EXCEL_ERROR_SIZE)) {
                    	break;
                    }
	                //校验
	                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {  
	                	//此处校验如果成功添加到objs数组
	                    cell = row.getCell(y);  
	                    Object value = getCellValue(  cell,  excelParam);
	                    //数据校验，如果失败，跳出当前循环，记录错误原因
	                    ExcelMsg  msgObj=checkCell(cell);
	                    if(!msgObj.isSuccess()||null==value){
	                    	int celNum=y+1;
	                    	String str=  "错误坐标：sheet["+sheet.getSheetName()+"]-(第"+j+"行，第"+celNum+"列)," 
	                    				+"错误数据：["+value+"]," 
	                    				+"错误原因：["+msgObj.getMsg()+"]";
	                    	errors[y]=str;
	                    	errorList.add(errors);
	                    	isAdd=false;
	                    	continue;
	                    	//break;
	                    }
	                    //该数据允许导入并且该数据每个数据项均正确
	                    if(msgObj.isSuccess()&&isAdd) {
	                    	objs[y]=value+"";
	                    	rightList.add(objs);
	                    }
	                    //重置下一行导入标识为正确(目标是不影响下一行数据的导入)
	                    if(y ==(row.getLastCellNum()-1)){
	                    	isAdd=true;
	                    }
	                    
	                }  
	               
	            }  
	        }
	       //当有错误数据时不予入库，直接返回空rightList集合
	        if(null!=errorList&&errorList.size()>0) {
	        	rightList=new ArrayList();
	        }
	        //处理excel数据不为空时候返回数据给调用方法，要先添加正确数据，然后添加错误数据
	        if(null!=list) {
	        	list.add(rightList);  
	 	        list.add(errorList);
	        }
	       
	        work.close();  
	        return list;  
	    }  
	   /**
	    * 
	   * @MethodName: checkRow 
	   * @description : 校验列长度是否正确
	   * @author：lijingyu 
	   * @date： 2018年1月24日 上午9:49:39
	   * @param row
	   * @return ExcelMsg
	    */
	    private ExcelMsg checkRow(Row row) {
	    	//定义参数
	    	ExcelMsg msgObj= new ExcelMsg();
	    	String msg="未知异常";
	    	msgObj.setSuccess(true);
			msgObj.setMsg(msg);
			// TODO 校验列长度是否正确
			return msgObj;
		}
		/**
	     * 
	    * @MethodName: checkCell 
	    * @description : 校验是否成功，成功返回“ok”，失败返回失败原因
	    * @author：lijingyu 
	    * @date： 2018年1月23日 下午1:57:39
	    * @param cell
	    * @return String
	     */
	    private ExcelMsg checkCell(Cell cell) {
	    	//定义参数
	    	ExcelMsg msgObj= new ExcelMsg();
	    	String msg="未知异常";
	    	msgObj.setSuccess(false);
			msgObj.setMsg(msg);
			// TODO 从数据库中取数据模板校验
			return msgObj;
		}

		/** 
	     * 描述：根据文件后缀，自适应上传文件的版本  
	     * @param inStr,fileName 
	     * @return 
	     * @throws Exception 
	     */  
	    public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
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
	  
	    /** 
	     * 描述：对表格中数值进行格式化 
	     * @param cell 
	     * @return 
	     */  
	    public  Object getCellValue(Cell cell,ExcelModel excelParam){  
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
	            break;  
	        }  
	        return value;  
	    }
		/**
		 * 描述：对指定数据进行导出
		 * @param  
	     * @return  Workbook
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Workbook exportExcel(String key,String title) {
			//获取数据列    -例子String  rowName="序号,被考核部门,考核方案,发送时间,总分";
			String rowName=trans2RowName(key);
			//获取数据来源
			List<Object[]> dataList=new ArrayList<Object[]>();
			dataList=trans2ObjectList(key,rowName);
			//创建表数据并返回对象
			return	new ExcelUtil (title, rowName.split(","), dataList).baseExport();
		}
		/**
		 * 
		* @MethodName: trans2RowName 
		* @description : 生成列表头
		* @author：lijingyu 
		* @date： 2018年1月23日 下午4:44:07
		* @param key
		* @return String
		 */
		private String trans2RowName(String key) {
			// TODO Auto-generated method stub
			return "序号,被考核部门,考核方案,发送时间,总分";
		}
		/**
		 * 
		* @MethodName: trans2ObjectList 
		* @description : 将数据转换为list数组数据
		* @author：lijingyu 
		* @date： 2018年1月23日 下午4:44:58
		* @param key
		* @param rowName
		* @return List<Object[]>
		 */
		private List<Object[]> trans2ObjectList(String key,String rowName) {
			 
			//校验列长度为空则返回
			if("".equals(rowName)) { return null ;  }
			List<Object[]> objList=new ArrayList<Object[]>();
			int len=rowName.split(",").length;
			for (int i = 0; i < 100000; i++) {
				Object[] objs=new Object[len];
				objs[0]=i+"，我是第一列";
				objs[1]=i+"，我是第二列";
				objs[2]=i+"，我是第三列";
				objs[3]=i+"，我是第四列";
				objs[4]=i+"，我是第五列";
				objList.add(objs);
			} 
			return objList;
		}
		/**
		 * 查询数据库表明
		 */
		public String queryExcelTitle(String key ) {
			// TODO 查询excel模板数据title
			return "我是测试样例";
		}
		 
		
}
