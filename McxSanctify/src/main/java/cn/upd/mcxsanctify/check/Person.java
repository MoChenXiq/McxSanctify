package cn.upd.mcxsanctify.check;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public class Person implements ConfigurationSerializable {
    public String name;
    public String introduction;

    public Person(String name, String introduction) {
        this.name = name;
        this.introduction = introduction;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("introduction",introduction);
        return map;
    }

    public static Person deserialize(Map<String, Object> map) {
        return new Person(
                (map.get("name") != null ? (String) map.get("name") : ""),
                (map.get("introduction") != null ? (String) map.get("introduction") : "")
        );
    }
}