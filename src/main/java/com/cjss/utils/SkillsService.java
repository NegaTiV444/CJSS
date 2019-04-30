package com.cjss.utils;

import com.cjss.model.enums.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkillsService {

    private SkillsService() {
    }

    public static SkillsService getInstance() {
        return SingletonHandler.INSTANCE;
    }

    public List<Skill> parseSkills(Map<String, String[]> paramMap) {
        List<Skill> result = new ArrayList<>();
        for (Skill skill : Skill.values()) {
            if (paramMap.containsKey(skill.toString())) {
                result.add(skill);
            }
        }
        return result;
    }

    public String getSkillsString(List<Skill> skills) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < skills.size(); i++) {
            result.append(skills.get(i).toString() + " ");
        }
        return result.toString();
    }

    private static class SingletonHandler {
        static final SkillsService INSTANCE = new SkillsService();
    }
}
