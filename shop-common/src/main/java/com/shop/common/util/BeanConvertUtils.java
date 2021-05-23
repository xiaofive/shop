package com.shop.common.util;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * 基于 Orika 的 bean 转换工具，动态添加类映射定义
 *
 * Author: wang Y
 * Date: 2021-05-23
 */
public class BeanConvertUtils {
    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    private static <S, D> BoundMapperFacade<S, D> getBoundMapperFacade(Class<S> srcClazz, Class<D> dstClazz) {
        return mapperFactory.getMapperFacade(srcClazz, dstClazz);
    }

    /**
     * 将类型为 S 的 src 对象转换成类型为 D 的对象
     *
     * @param src
     * @param dstClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> D map(S src, Class<D> dstClazz) {
        if (src == null) {
            return null;
        }
        return getBoundMapperFacade((Class<S>) src.getClass(), dstClazz).map(src);
    }

    /**
     * 将类型为 S 的 src 对象集合，转换成类型为 D 的对象列表
     *
     * @param srcCollection
     * @param srcClazz
     * @param dstClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> List<D> listMap(Collection<S> srcCollection, Class<S> srcClazz, Class<D> dstClazz) {
        if (srcCollection == null) {
            return null;
        }

        BoundMapperFacade<S, D> boundMapperFacade = getBoundMapperFacade(srcClazz, dstClazz);

        List<D> dstList = new ArrayList<>(srcCollection.size());
        for (S s : srcCollection) {
            D d = boundMapperFacade.map(s);
            dstList.add(d);
        }
        return dstList;
    }

    /**
     * 将类型为 S 的 src 对象列表，转换成类型为 D 的对象列表
     *
     * @param srcCollection
     * @param dstClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> List<D> listMap(List<S> srcCollection, Class<D> dstClazz) {
        if (srcCollection == null) {
            return null;
        }

        if (srcCollection.size() == 0) {
            return Collections.emptyList();
        }

        BoundMapperFacade<S, D> boundMapperFacade = getBoundMapperFacade((Class<S>) srcCollection.get(0).getClass(), dstClazz);

        List<D> dstList = new ArrayList<>(srcCollection.size());
        for (S s : srcCollection) {
            D d = boundMapperFacade.map(s);
            dstList.add(d);
        }
        return dstList;
    }

    /**
     * 将类型为 S 的 src 对象集合，转换成类型为 <K, D> 的Map, 主键的值使用每个对象的 keyFieldName 域的值，例如 refId
     *
     * @param srcCollection
     * @param srcClazz
     * @param dstClazz
     * @param keyFieldName
     * @param <S>
     * @param <D>
     * @param <K>
     * @return
     */
    public static <S, D, K> Map<K, D> mapByFieldMap(Collection<S> srcCollection, Class<S> srcClazz, Class<D> dstClazz, String keyFieldName) throws NoSuchFieldException, IllegalAccessException {
        BoundMapperFacade<S, D> boundMapperFacade = getBoundMapperFacade(srcClazz, dstClazz);
        Field keyField = dstClazz.getDeclaredField(keyFieldName);
        keyField.setAccessible(true);

        Map<K, D> dstMap = new HashMap(srcCollection.size());
        for (S s : srcCollection) {
            D d = boundMapperFacade.map(s);
            K keyValue = (K) keyField.get(d);
            dstMap.put(keyValue, d);
        }
        return dstMap;
    }

    /**
     * 忽略 id created updated deleted version
     *
     * @param src
     * @param dstClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> D mapIgnoreGenericKey(S src, Class<D> dstClazz) {
        if (src == null) {
            return null;
        }
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(src.getClass(), dstClazz)
                .exclude("id")
                .exclude("created")
                .exclude("updated")
                .exclude("deleted")
                .exclude("version")
                .byDefault()
                .register();
        return mapperFactory.getMapperFacade().map(src, dstClazz);
    }

    /**
     * 判断某个类里是否有某个方法
     *
     * @param clazz
     * @param methodName
     * @return
     */
    public static boolean isHaveSuchMethod(Class<?> clazz, String methodName) {
        Method[] methodArray = clazz.getMethods();
        boolean result = false;
        for (int i = 0; i < methodArray.length; i++) {
            if (methodArray[i].getName().equals(methodName)) {
                result = true;
                break;
            }
        }
        return result;
    }


}
