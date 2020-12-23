package com.http;

import oracle.jdbc.driver.OracleDriver;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * Created by 10412 on 2016/12/27.
 * JDBC的六大步骤
 * JAVA连接Oracle的三种方式
 */
public class JdbcTest
{
    public static ArrayList execute(String sql) {
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList re = new ArrayList();
        try {
            //第一步：注册驱动
            //第一种方式：类加载(常用)
            //Class.forName("oracle.jdbc.OracleDriver");

            //第二种方式：利用Driver对象
            Driver driver = new OracleDriver();
            DriverManager.deregisterDriver(driver);


            //第二步：获取连接
            //第一种方式：利用DriverManager（常用）
            //connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "你的oracle数据库用户名", "用户名密码");

            //第二种方式：直接使用Driver
            Properties pro = new Properties();
            pro.put("user", "usr_move");
            pro.put("password", "mscs2019");
            connect = driver.connect("jdbc:oracle:thin:@39.105.55.139:1521:zyyxg", pro);

            //测试connect正确与否
            System.out.println(connect);  


            //第三步：获取执行sql语句对象
            //第一种方式:statement
            //statement = connect.createStatement();

            //第二种方式：PreStatement
            PreparedStatement preState = connect.prepareStatement(sql );


            //第四步：执行sql语句
            //第一种方式：
            //resultSet = statement.executeQuery("select  * from tb1_dept");

            //第二种方式：
            //preState.setInt(1, 2);//1是指sql语句中第一个？,  2是指第一个？的values值
            boolean select = false;
            if(sql.indexOf("select") == 0) {
            	resultSet = preState.executeQuery();        //执行查询语句
            	select =true;
            }else {
            	boolean execute = preState.execute();
            }
            
            //查询任何语句，如果有结果集，返回true，没有的话返回false,注意如果是插入一条数据的话，虽然是没有结果集，返回false，但是却能成功的插入一条数据
            //
           

            //第五步：处理结果集
            if(select) {
            	while (resultSet.next())
                {
                    //int id = resultSet.getInt("id");
                	Map m = new HashMap();
                    String code = resultSet.getString("CODE");
                    String rowid = resultSet.getString("ROWID");
                    m.put("code", code);
                    m.put("rowid", rowid);
                    re.add(m);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //第六步：关闭资源
                try {
                    if (resultSet!=null) resultSet.close();
                    if (statement!=null) statement.close();
                    if (connect!=null) connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
		return re;
    }
}