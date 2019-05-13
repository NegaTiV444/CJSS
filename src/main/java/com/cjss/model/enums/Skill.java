package com.cjss.model.enums;

public enum Skill {

    JAVA("Java"),
    PYTHON("Python"),
    C("C"),
    CPP("C++"),
    DOTNET(".NET"),
    JS("JavaScript"),
    HTMLCSS("HTML+CSS"),
    DELPHY("Delphy"),
    PHP("PHP"),
    RUBY("Ruby"),
    SWIFT("Swift"),
    ANDROID("Android");

    private final String value;

    Skill(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Skill getSkill(String value) {
        switch (value.toUpperCase()) {
            case "JAVA":
                return Skill.JAVA;
            case "PYTHON":
                return Skill.PYTHON;
            case "C":
                return Skill.C;
            case "C++":
                return Skill.CPP;
            case ".NET":
                return Skill.DOTNET;
            case "JAVASCRIPT":
                return Skill.JS;
            case "HTML+CSS":
                return Skill.HTMLCSS;
            case "DELPHY":
                return Skill.DELPHY;
            case "PHP":
                return Skill.PHP;
            case "RUBY":
                return Skill.RUBY;
            case "SWIFT":
                return Skill.SWIFT;
            case "ANDROID":
                return Skill.ANDROID;
        }
        throw new IllegalArgumentException("No enum constant com.cjss.model.enums.Skill." + value);
    }

}
