/**
 * 
 */
package com.yahoo.petermwenda83.cache;

import java.util.ArrayList;
import java.util.List;

import com.yahoo.petermwenda83.contoller.users.User;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author peter
 *
 */
public class TestCache {
	static CacheManager mgr = CacheManager.getInstance();
	static Cache usersCache = mgr.getCache(CacheVariables.CACHE_USER_BY_UUID);//
	static List<User> userList = new ArrayList<User>();
	
    public static void main(String [] a){ 
    	Element element;
    	User user;
    	List<?> keys; 

        keys = usersCache.getKeys();
        for (Object key : keys) {
            element = usersCache.get(key);
            user = (User) element.getObjectValue();
            userList.add(user);
        }
	for(User u : userList){
		System.out.println(u); 
	   }
    	
     }
}
