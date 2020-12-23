package com.http;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//FileUploadMain.send();
		ArrayList para = new ArrayList();
		para.add("code");
		int from = 0;
		for(int j=0; j<=123375; j+=500) {
			String sql ="select rowid,rownum||'|@|'||1||'|@|'||2||'|@|'||t.sf||'|@|'||t.xm||'|@|'||1010||'|@|'||t.sf||'|@|'||''||'|@|' as code from T_JZG_bk t where up='0' and rownum < " +j+ " minus select rowid,rownum||'|@|'||1||'|@|'||2||'|@|'||t.sf||'|@|'||t.xm||'|@|'||1010||'|@|'||t.sf||'|@|'||''||'|@|' as code from T_JZG_bk t where up='0' and rownum < "+ from;
			System.out.println(sql);
			ArrayList re = JdbcTest.execute(sql);
			ZIPUtil.write("D:\\work/data.txt", re, para);
			ZIPUtil.compress("D:\\work/data.txt", "D:\\work/data.zip");
			
			String message = FileUploadMain.send();
			if(message.indexOf("成功") >= 0) {
				String s = "update T_JZG_bk t set t.up2 = 0 where t.id in (select id from T_JZG_bk where up='0' and rownum < " +j+ " minus select id from T_JZG_bk where up='0'and rownum < "+ from+")";
				JdbcTest.execute(s);
				System.out.println(s);
			}else {
				String s = ("update T_JZG_bk t set t.up2 = 1 where t.id in (select id from T_JZG_bk where up='0' and rownum < " +j+ " minus select id from T_JZG_bk where up='0'and rownum <"+ from+")");
				JdbcTest.execute(s);
				System.out.println(s);
			}
			from = j;
		}
//		ArrayList re = JdbcTest.execute("select rowid,rownum||'|@|'||1||'|@|'||2||'|@|'||t.sf||'|@|'||t.xm||'|@|'||1010||'|@|'||t.sf||'|@|'||''||'|@|' as code from T_JZG_bk t where t.flag is null and rownum < 100");

//		for(int i=0; i<re.size(); i++) {
//			ArrayList r = new ArrayList();
//			r.add(re.get(i));
//			ZIPUtil.write("D:\\work/data.txt", r, para);
//			ZIPUtil.compress("D:\\work/data.txt", "D:\\work/data.zip");
//			FileUploadMain.send();
//		}
		
		
	}

}
