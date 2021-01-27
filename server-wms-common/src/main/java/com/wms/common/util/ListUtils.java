package com.wms.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    /**
     * 将size 过长的list 拆成多个具体size 的list
     *
     * @param target 需要拆分的list
     * @param size   需要拆成的list长度
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> createObjectList(List<T> target, int size) {
        List<List<T>> listArr = new ArrayList<List<T>>();
        //获取被拆分的数组个数
        int arrSize = target.size() % size == 0 ? target.size() / size : target.size() / size + 1;
        for (int i = 0; i < arrSize; i++) {
            List<T> sub = new ArrayList<T>();
            //把指定索引数据放入到list中
            for (int j = i * size; j <= size * (i + 1) - 1; j++) {
                if (j <= target.size() - 1) {
                    //得到拆分后的集合
                    sub.add(target.get(j));
                }
            }
            //将拆分后的集合综合为一个集合
            listArr.add(sub);
        }
        return listArr;
    }
}
