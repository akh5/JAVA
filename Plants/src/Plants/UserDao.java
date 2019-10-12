package Plants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    //1定义数据库驱动
    public static String driver="com.mysql.jdbc.Driver" ;

    //定义url
    public static String url="jdbc:mysql://localhost:3306/shoot";

    //定义mysql用户名，每个人的用户在安装mysql的时候是自己定义的
    public static String user="root";

    //定义mysql设置是的密码
    static String password="";

    //定义连接对象Connection
    public static Connection con;

    //定义传输器对象
    public static Statement statement;

    public static void DBConnection() throws Exception {
        //注册数据库
        Class.forName(driver);
        //获取数据库连接
        con=DriverManager.getConnection(url, user, password);
        //获取传输器
        statement=con.createStatement();
        //
    }
    //将分数存入数据库
    public static void save(String name,int score) throws Exception {
        try {
            DBConnection();
            String sql="insert into score(username,score) values('"+name+"',"+score+")";
            //利用传输器将sql传入数据库
            statement.executeUpdate(sql);
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally {
            con.close();
        }

    }
    public static String[] getScoreAndUsername() throws Exception {
        ResultSet rs = null;
        String result[] = new String[100];
        try {
            DBConnection();
            String sql="select username,score from score order by score desc";
            rs = statement.executeQuery(sql);
            for (int i = 0 ;i<result.length;i++) {
                if(rs.next()) {
                    result[i] = "第"+(i+1)+"名"+rs.getString("username")+":"+rs.getInt("score");

                }
            }
        }  catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally {
            con.close();
            rs.close();
        }
        return result;

    }

    public static void main(String[] args) throws Exception {
        String result[] = getScoreAndUsername();
        for(int i = 0;i<result.length;i++) {
            if(result[i]!=null) {
                System.out.println(result[i]);
            }
        }

    }
}