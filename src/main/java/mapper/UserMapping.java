package mapper;
import org.apache.ibatis.annotations.Param;

import entity.user;
public interface UserMapping {
     public user findUserbyName(String username);
     public void createUser(@Param("uname") String username,@Param("upwd") String userpwd);
     
}

