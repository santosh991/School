/**
 * 
 */
package com.yahoo.petermwenda83.cache;

import java.io.File;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.SizeOfPolicyConfiguration;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.AllBean;
import com.yahoo.petermwenda83.contoller.users.User;
import com.yahoo.petermwenda83.model.curriculum.SubjectDAO;
import com.yahoo.petermwenda83.model.exam.ExamDAO;
import com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO;
import com.yahoo.petermwenda83.model.registration.StudentDAO;
import com.yahoo.petermwenda83.model.regparents.ParentsDAO;
import com.yahoo.petermwenda83.model.user.UsresDAO;

/**
 * @author peter
 *
 */
public class Init {
	    protected SubjectDAO subjectDAO;
	    protected ParentsDAO parentsDAO;
	    protected UsresDAO usersDAO;
	    protected StudentDAO studentDAO;
	    protected StaffRegistrationDAO staffRegistrationDAO;
	    protected ExamDAO examDAO;
	    
	    
	    private CacheManager cacheManager;
	    
	    private SizeOfPolicyConfiguration sizeOfPolicyConfiguration;

	    private Logger logger = Logger.getLogger(this.getClass());
	    
	   
	    public void Init(){
	       
	    	subjectDAO = SubjectDAO.getInstance();
	    	parentsDAO = ParentsDAO.getInstance();
	    	usersDAO = UsresDAO.getInstance();
	    	studentDAO = StudentDAO.getInstance();
	    	staffRegistrationDAO = StaffRegistrationDAO.getInstance();
	    	examDAO = ExamDAO.getInstance();
	    	
	        sizeOfPolicyConfiguration = new SizeOfPolicyConfiguration();
	        sizeOfPolicyConfiguration.setMaxDepthExceededBehavior("abort");

	        logger.info("Starting to initialize cache");
	        initCache();
	        logger.info("Have finished initializing cache");
	  }
	    

	    /**
	     *
	     */
	    protected void initCache() {
	    	DiskStoreConfiguration diskConfig = new DiskStoreConfiguration();
	        diskConfig.setPath(System.getProperty("java.io.tmpdir") + File.separator +
	        		"ehcache" + File.separator + PropertiesConfig.getConfigValue("CACHE_FILE"));        
	        
	        Configuration config = (new Configuration()).diskStore(diskConfig);
	        config.setMaxBytesLocalHeap(Long.parseLong(PropertiesConfig.getConfigValue("MAX_BYTES_LOCAL_HEAP")));
	        config.setMaxBytesLocalDisk(Long.parseLong(PropertiesConfig.getConfigValue("MAX_BYTES_LOCAL_DISK")));
	        config.setUpdateCheck(false);

	        cacheManager = CacheManager.create(config);      

	        List<? extends AllBean> objList;

	        objList = subjectDAO.getAllSubjects(); 
	        initCacheByUuid(CacheVariables.CACHE_SUBJECT_BY_UUID, objList);
	        
	        objList = usersDAO.getAllUsers();
	        initCacheByUuid(CacheVariables.CACHE_USER_BY_UUID, objList);
	       
	        objList = parentsDAO.getAllStudentParent();
	        initCacheByUuid(CacheVariables.CACHE_PARENTS_BY_UUID, objList);

	        objList = studentDAO.getAllStudents();
	        initCacheByUuid(CacheVariables.CACHE_REG_BY_UUID, objList);

	        objList = staffRegistrationDAO.getAllTeacher();
	        initCacheByUuid(CacheVariables.CACHE_SREG_STATUS_BY_UUID, objList);
	                
	      //  objList = examDAO.getAllExamtype(); 
	        initCacheByUuid(CacheVariables.CACHE_EXAM_BY_UUID, objList);
	        
	        
	        initUsersCache(CacheVariables.CACHE_USER_BY_UUID);
	                
	        initParentsCache(CacheVariables.CACHE_PARENTS_BY_UUID);
	                        
	        initRegCache(CacheVariables.CACHE_REG_BY_UUID);
	        
	        initSRegCache(CacheVariables.CACHE_SREG_STATUS_BY_UUID);
	        
	        initExamCache(CacheVariables.CACHE_EXAM_BY_UUID);
	        
	        initSubjectCache(CacheVariables.CACHE_SUBJECT_BY_UUID);

	          
	    }
	    

	    /**
	     *
	     * @param cacheName
	     * @param objList
	     */
	    private void initCacheByUuid(String cacheName, List<? extends AllBean> objList) {
	    	Cache cache = null;
	        if (!cacheManager.cacheExists(cacheName)) {
	            CacheConfiguration cacheConfig = new CacheConfiguration().sizeOfPolicy(sizeOfPolicyConfiguration);
	            cacheConfig.setCopyOnRead(false); // Whether the Cache should copy elements it returns
	            cacheConfig.setCopyOnWrite(false); // Whether the Cache should copy elements it gets
	            cacheConfig.setEternal(true); // Sets whether elements are eternal.        
	            cacheConfig.setName(cacheName); // Sets the name of the cache.

	            cache = new Cache(cacheConfig);
	            cacheManager.addCacheIfAbsent(cache);
	            if (cache.getStatus() == Status.STATUS_UNINITIALISED) {
	                cache.initialise();
	            }
	            
	        } else {
	        	CacheManager mgr = CacheManager.getInstance();
	        	cache = mgr.getCache(cacheName);
	        }
	     
	        for (AllBean b : objList) {
	            cache.put(new Element(b.getUuid(), b)); // UUID as the key            
	        }
	    }    
	    

	    /**
	     *
	     * @param cacheName
	     */
	    private void initUsersCache(String cacheName) {

	        if (!cacheManager.cacheExists(cacheName)) {
	            CacheConfiguration cacheConfig = new CacheConfiguration().sizeOfPolicy(sizeOfPolicyConfiguration);
	            cacheConfig.setCopyOnRead(false); // Whether the Cache should copy elements it returns
	            cacheConfig.setCopyOnWrite(false); // Whether the Cache should copy elements it gets
	            cacheConfig.setEternal(true); // Sets whether elements are eternal.    	
	            cacheConfig.setName(cacheName); // Sets the name of the cache.

	            Cache userCache = new Cache(cacheConfig);
	            cacheManager.addCacheIfAbsent(userCache);
	            if (userCache.getStatus() == Status.STATUS_UNINITIALISED) {
	            	userCache.initialise();
	            }

	            List<User> allusers = usersDAO.getAllUsers();

	            if (StringUtils.equals(cacheName, CacheVariables.CACHE_USER_BY_UUID)) {
	                for (User a : allusers) {
	                	userCache.put(new Element(a.getUsername(), a));		// Username as the key
	                }
	            }
	        }
	    }

	    
	    /**
	     *
	     * @param cacheName
	     */
	    private void initParentsCache(String cacheName) {

	        if (!cacheManager.cacheExists(cacheName)) {
	            CacheConfiguration cacheConfig = new CacheConfiguration().sizeOfPolicy(sizeOfPolicyConfiguration);
	            cacheConfig.setCopyOnRead(false); // Whether the Cache should copy elements it returns
	            cacheConfig.setCopyOnWrite(false); // Whether the Cache should copy elements it gets
	            cacheConfig.setEternal(true); // Sets whether elements are eternal.    	
	            cacheConfig.setName(cacheName); // Sets the name of the cache.

	            Cache parentsCache = new Cache(cacheConfig);
	            cacheManager.addCacheIfAbsent(parentsCache);
	            if (parentsCache.getStatus() == Status.STATUS_UNINITIALISED) {
	            	parentsCache.initialise();
	            }

	        }
	    }


	    /**
	     *
	     * @param cacheName
	     */
	    private void initRegCache(String cacheName) {

	        if (!cacheManager.cacheExists(cacheName)) {
	            CacheConfiguration cacheConfig = new CacheConfiguration().sizeOfPolicy(sizeOfPolicyConfiguration);
	            cacheConfig.setCopyOnRead(false); // Whether the Cache should copy elements it returns
	            cacheConfig.setCopyOnWrite(false); // Whether the Cache should copy elements it gets
	            cacheConfig.setEternal(true); // Sets whether elements are eternal.    	
	            cacheConfig.setName(cacheName); // Sets the name of the cache.

	            Cache regCache = new Cache(cacheConfig);
	            cacheManager.addCacheIfAbsent(regCache);
	            if (regCache.getStatus() == Status.STATUS_UNINITIALISED) {
	            	regCache.initialise();
	            }

	        }
	    }

	    
	    /**
	     *
	     * @param cacheName
	     */
	    private void initSRegCache(String cacheName) {

	        if (!cacheManager.cacheExists(cacheName)) {
	            CacheConfiguration cacheConfig = new CacheConfiguration().sizeOfPolicy(sizeOfPolicyConfiguration);
	            cacheConfig.setCopyOnRead(false); // Whether the Cache should copy elements it returns
	            cacheConfig.setCopyOnWrite(false); // Whether the Cache should copy elements it gets
	            cacheConfig.setEternal(true); // Sets whether elements are eternal.    	
	            cacheConfig.setName(cacheName); // Sets the name of the cache.

	            Cache sregCache = new Cache(cacheConfig);
	            cacheManager.addCacheIfAbsent(sregCache);
	            if (sregCache.getStatus() == Status.STATUS_UNINITIALISED) {
	            	sregCache.initialise();
	            }
	        }
	    }
	    

	    /**
	     *
	     * @param cacheName
	     */
	    private void initExamCache(String cacheName) {

	        if (!cacheManager.cacheExists(cacheName)) {
	            CacheConfiguration cacheConfig = new CacheConfiguration().sizeOfPolicy(sizeOfPolicyConfiguration);
	            cacheConfig.setCopyOnRead(false); // Whether the Cache should copy elements it returns
	            cacheConfig.setCopyOnWrite(false); // Whether the Cache should copy elements it gets
	            cacheConfig.setEternal(true); // Sets whether elements are eternal.    	
	            cacheConfig.setName(cacheName); // Sets the name of the cache.
	            
	            Cache msgTemplateCache = new Cache(cacheConfig);
	            cacheManager.addCacheIfAbsent(msgTemplateCache);
	            if (msgTemplateCache.getStatus() == Status.STATUS_UNINITIALISED) {
	                msgTemplateCache.initialise();
	            }
	            
	        }
	    }
	    
	    
	    /**
	     *
	     * @param cacheName
	     */
	    private void initSubjectCache(String cacheName) {

	        if (!cacheManager.cacheExists(cacheName)) {
	            CacheConfiguration cacheConfig = new CacheConfiguration().sizeOfPolicy(sizeOfPolicyConfiguration);
	            cacheConfig.setCopyOnRead(false); // Whether the Cache should copy elements it returns
	            cacheConfig.setCopyOnWrite(false); // Whether the Cache should copy elements it gets
	            cacheConfig.setEternal(true); // Sets whether elements are eternal.    	
	            cacheConfig.setName(cacheName); // Sets the name of the cache.

	            Cache subjectCache = new Cache(cacheConfig);
	            cacheManager.addCacheIfAbsent(subjectCache);
	            if (subjectCache.getStatus() == Status.STATUS_UNINITIALISED) {
	            	subjectCache.initialise();
	            }

	        }
	    }
	  
	    
	    
	    public void destroy() {
	      //  super.destroy();
	        CacheManager.getInstance().shutdown();
	    }

}
