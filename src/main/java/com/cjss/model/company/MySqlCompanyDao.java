package com.cjss.model.company;

public class MySqlCompanyDao {

    private static final String TABLE = "companies";


    private MySqlCompanyDao(){};

    private static class SingletonHandler{
        public static MySqlCompanyDao instance = new MySqlCompanyDao();
    }

    public static MySqlCompanyDao newInstance(){
        return SingletonHandler.instance;
    }
}
