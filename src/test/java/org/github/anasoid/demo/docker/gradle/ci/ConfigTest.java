package org.github.anasoid.demo.docker.gradle.ci;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * configurtion test
 * all acces to configuration should use this class
 */
public class ConfigTest {

    public static final String EXECUTION_ID = String.valueOf(Math.abs( UUID.randomUUID().getLeastSignificantBits()));



    public static final String ENV_REST_URL_KEY = "rest.url";
    public static final String ENV_REST_JDBC_URL_KEY = "rest.db.url";




    /**
     * execution if of test
     * @return
     */
    public static String getExecutionId() {
        return EXECUTION_ID;
    }


    public static String getMandatoryProperty(String property) {
        String value = System.getProperty(property);
        if (StringUtils.isEmpty(value) ) {
            throw new RuntimeException("invalid <"+property+"> : (" + StringUtils.trimToEmpty(value) + ")");
        }
        return value;
    }
    public static String getMandatoryProperty(String property,String regex) {
        String value = getMandatoryProperty(property);
        if (!value.matches(regex)){
            throw new RuntimeException("invalid <"+property+"> : (" + StringUtils.trimToEmpty(value) + ") for regex (" + regex+")");
        }
        return value;
    }
    /**
     * Return Rest Url fro rest test
     *
     * @return
     */
    public static String getRestUrl() {

        return getMandatoryProperty(ENV_REST_URL_KEY,"http.*");
    }

    /**
     * Return Rest jdbc  Url fro rest test
     *
     * @return
     */
    public static String getRestDbUrl() {

        return getMandatoryProperty(ENV_REST_JDBC_URL_KEY,"jdbc.*");
    }




}
