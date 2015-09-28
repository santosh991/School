/**
 * 
 */
package com.yahoo.petermwenda83.server.cache;
/**
 * 
 * @author peter
 *
 */
public class CacheVariables {

    // Names of caches
    // These are caches of Accounts in the system
    // The following caches have an email as the key for the object
    public static String CACHE_STATISTICS_BY_ACCOUNT = "StatisticsEmail";
    public final static String CACHE_ACCOUNTS_BY_USERNAME = "AccountsUsername";

    // The following caches have an UUID as the key for the object
    public final static String CACHE_ACCOUNTS_BY_UUID = "AccountsUuid";

    //Cache variables to be used in the admin section
    public static String CACHE_ALL_ACCOUNTS_STATISTICS_KEY = "CacheAllAccountsStatisticsKey";
    public static String CACHE_ALL_ACCOUNTS_STATISTICS = "CacheAllAccountsStatisticsKey";
}

