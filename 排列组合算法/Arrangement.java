package com.tcwong;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 集合排列问题算法
 */
public class Arrangement {

    public static void main(String[] args)
    {
        List<String> baseList = new ArrayList<>();
        baseList.add("1");
        baseList.add("2");
        baseList.add("3");
        baseList.add("4");
        baseList.add("5");
        baseList.add("6");
        baseList.add("7");
        baseList.add("8");

        int depath = 3;

        List<List<String>> resultList = listAll(baseList, depath);
        System.out.println(resultList.size());

        for(List<String> list : resultList)
        {
            System.out.println(list.toString());
        }
    }

    public static List<List<String>> listAll(List<String> baseList, int depth)
    {
        List<List<String>> resultList = new ArrayList<>();
        if(depth <= 0)
        {
            return resultList;
        }
        int size = baseList.size();
        depth = size > depth ? depth : size;
        List<String> transferList = new LinkedList<>();
        listAll(baseList, depth, transferList, resultList);
        return resultList;
    }

    private static void listAll(List<String> baseList, int depth, List<String> transferList, List<List<String>> resultList)
    {
        if(depth == 0)
        {
            List<String> list = new ArrayList<>(transferList);
            resultList.add(list);
            if(transferList.size() > 0)
            {
                transferList.remove(transferList.size() - 1);
            }
            return;
        }
        depth --;
        for(int i = 0; i < baseList.size(); i ++)
        {
            List<String> tempList = new LinkedList<>(baseList);
            transferList.add(tempList.remove(i));
            listAll(tempList, depth, transferList, resultList);
        }
        if(transferList.size() > 0)
        {
            transferList.remove(transferList.size() - 1);
        }
    }
}
