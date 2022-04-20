package util;

public class Stringnull {
    public static boolean isEmpty(String str) {
    	if(str == null || "".equals(str.trim())) {
    		return true;
    	}
    	return false;
    }
}
