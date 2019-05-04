package com.cjss.model.enums;

public enum Skill {

    JAVA("Java"),
    PYTHON("Python"),
    C("C"),
    CPP("C++"),
    DOTNET(".NET"),
    JS("JavaScript"),
    HTMLCSS("HTML + CSS"),
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

}
