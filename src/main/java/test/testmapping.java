package test;
import org.apache.ibatis.session.SqlSession;

import entity.user;
import mapper.UserMapping;
import util.GetsqlSession;
public class testmapping {
   public static void main(String[] args) {
	   SqlSession session = GetsqlSession.createSqlSession();
	   UserMapping userMapping = session.getMapper(UserMapping.class);
	   user user = userMapping.findUserbyName("admin");
	   System.out.println(user.getUserID());
   }
}
