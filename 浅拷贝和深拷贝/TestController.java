package com.tcwong.demo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/test1")
    public String testCopy() {
        List<Map<String, String>> list1 = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        map1.put("姓名", "张三");
        map2.put("年龄", "18");
        list1.add(map1);
        list1.add(map2);
//        List<Map<String, String>> list2 = new ArrayList<>();
//        list2.addAll(list1);
        List<Map<String, String>> list2 = deepCopy(list1);
        Map<String,List<Map<String, String>>> resultMap = new HashMap<>();
        resultMap.put("A班", list1);
        resultMap.put("B班", list2);
        return JSON.toJSONString(resultMap);
    }

    @GetMapping("/test2")
    public String testCopy1() {
        List<Map<String, String>> list1 = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        map1.put("A", "AAAA");
        map2.put("B", "BBBB");
        list1.add(map1);
        list1.add(map2);
        List<Map<String, String>> list2 = deepCopy(list1);
        Map<String,List<Map<String, String>>> resultMap = new HashMap<>();
        resultMap.put("list1", list1);
        resultMap.put("list2", list2);
        return JSON.toJSONString(resultMap);
    }

    private List<Map<String, String>> deepCopy(List<Map<String, String>> list) {
        List<Map<String, String>> copyList = new ArrayList<>();
        list.forEach(stringStringMap -> {
            Map<String, String> map = new HashMap<>();
            Set<String> keys = stringStringMap.keySet();
            String key = keys.iterator().next();
            Collection<String> values = stringStringMap.values();
            String value = values.iterator().next();
            map.put(key, value);
            copyList.add(map);
        });
        return copyList;
    }

    @GetMapping("/test3")
    public Object test3() {
        Map<String, List<Map<String,String>>> resultMap = new HashMap<>();
        List<Map<String,String>> list1 = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("姓名","张三");
        Map<String, String> map2 = new HashMap<>();
        map2.put("年龄", "18");
        list1.add(map1);
        list1.add(map2);
        List<Map<String,String>> list2 = new ArrayList<>();
        Map<String, String> map3 = new HashMap<>();
        map3.put("姓名","李四");
        Map<String, String> map4 = new HashMap<>();
        map4.put("年龄", "20");
        list2.add(map3);
        list2.add(map4);
        List<Map<String,String>> list3 = new ArrayList<>();
        Map<String, String> map5 = new HashMap<>();
        map5.put("姓名","王五");
        Map<String, String> map6 = new HashMap<>();
        map6.put("年龄", "22");
        list3.add(map5);
        list3.add(map6);
        List<Map<String,String>> list4 = new ArrayList<>();
        Map<String, String> map7 = new HashMap<>();
        map7.put("姓名","赵六");
        Map<String, String> map8 = new HashMap<>();
        map8.put("年龄", "30");
        list4.add(map7);
        list4.add(map8);
        resultMap.put("A班", list1);
        resultMap.put("B班", list2);
        resultMap.put("C班", list3);
        resultMap.put("D班", list4);
        return resultMap;
    }

}
