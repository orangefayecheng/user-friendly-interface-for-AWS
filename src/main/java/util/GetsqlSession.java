package util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.*;

public class GetsqlSession {
    public static SqlSession createSqlSession() {
    	SqlSessionFactory sqlfactory = null;
    	InputStream input = null;
    	SqlSession session = null;
    	try {
    		String resource = "mybatis.xml";
    		input = Resources.getResourceAsStream(resource);
    		sqlfactory = new SqlSessionFactoryBuilder().build(input);
    		session = sqlfactory.openSession();
    		return session;		
    	}catch(IOException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static void main(String[] args) {
    	System.out.println(createSqlSession());
    }
}

