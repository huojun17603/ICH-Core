package com.ich.core.base;

public class SQLFilter {
    /**
     * 防止sql注入
     * @param sql
     * @return
     */
    public static String TransactSQLInjection(String sql) {
        return sql.replaceAll(".*(['\";]+|(--)+).*", "");
    }

}
