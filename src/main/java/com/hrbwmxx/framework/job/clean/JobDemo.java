package com.hrbwmxx.framework.job.clean;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component("jobDemo")
public class JobDemo {

	/**
	 * 清除脏数据附件临时文件job
	 * @throws IOException
	 */
//	@Scheduled(cron = "0 0/5 14 * * ?")
	public void cleanAttrAndData() throws IOException{
		 //TODO 
	}
	
	
	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
	 * @throws IOException 
     */
    private static boolean deleteDir(File dir) throws IOException {
        if (dir.isDirectory()) {
            String[] children = dir.list();
          //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
      return dir.delete();
    }
    /**
     * 
     * @param path要删除的文件路径
     * @param key过滤不删除的路径
     * @return
     * @throws IOException 
     */
    private static  boolean  dealDir(String path,String key,String separator) throws IOException{
    	//获得根目录路径
    	File rootDir=new File(path);
    	//获得儿子名字集合
		String[] suns= rootDir.list();
		//删除成功
		boolean success =true;
		if(suns!=null){
      	    for(String sun:suns){
      	  	   //扣除关键字，删除其他数据
      	    	File sunDir=new File( rootDir +separator+sun );
      	    	if(!key.equals(  sun)  ){success=deleteDir(sunDir);}
      	    }
		}
    	return success  ;
    }

}
