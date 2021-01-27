package com.jyzt.common.util;


import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public class PageUtil {

    /**
     * 分批处理
     * @param collection
     * @param pointsDataLimit  每次分批数量
     * @param <T>
     * @return
     */
    public  static <T> List<List<T>> splitList(Collection<T> collection, int pointsDataLimit)
    {
        if(0==pointsDataLimit){
            pointsDataLimit=1000;
        }
        List<List<T>> result= Lists.newArrayList();
        List<T> list= Lists.newArrayList();
        list.addAll(collection);
        //分批处理
          if(null!=list&&list.size()>0){

            Integer size = list.size();
            //判断是否有必要分批
            if(pointsDataLimit<size){
              int part = size/pointsDataLimit;//分批数
              //
                int subSize=0;
              for (int i = 0; i < part; i++) {
                //1000条
                List<T> listPage = list.subList(subSize, subSize+pointsDataLimit);
                  result.add(listPage);
                  subSize=subSize+pointsDataLimit;
                 }

              if(!list.isEmpty()){
                  result.add(list.subList(subSize,size));//表示最后剩下的数据
              }

            }else{
                 result.add(list) ;

            }
              return  result  ;
          }else{
              return Lists.newArrayList();
          }
    }


}
