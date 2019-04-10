package com.cjss.model.vacancy;

public class MySqlVacancyDao {

    private static final String TABLE = "vacancies";

    private MySqlVacancyDao() {
    }

    private static class SingletonHandler {
        public static MySqlVacancyDao instance = new MySqlVacancyDao();
    }

    public static MySqlVacancyDao newInstance() {
        return SingletonHandler.instance;
    }
}
