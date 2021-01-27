package com.wms.es.service.impl;

import com.google.common.collect.Maps;
import com.wms.es.bean.NcCacheConstant;
import com.wms.es.service.EsService;
import com.wms.es.utils.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EsServiceImpl implements EsService {

    private static List<String> ncDict = new LinkedList<>();

    @PostConstruct
    private void init() {
        Field[] fields = NcCacheConstant.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.getType().toString().endsWith("java.lang.String") && Modifier.isStatic(field.getModifiers()))
                    ncDict.add(field.get(NcCacheConstant.class).toString());
            }
        } catch (Exception e) {
            log.error("nc dict init error");
        }

    }

    @Override
    public String getByCode(String ncTableName, String code, String yNeed) {
        if (!ncDict.contains(ncTableName)) {
            return null;
        }
        String data = null;
        try {
            data = _getByCode(ncTableName, code).get(yNeed).toString();
        } catch (Exception e) {
            //log.warn(ncTableName + "_" + code + "_none:" + yNeed); TODO 有数据后放出
        }
        return data;
    }

    @Override
    public String getByPk(String ncTableName, String pk, String yNeed) {
        if (!ncDict.contains(ncTableName)) {
            return null;
        }
        String data = null;
        try {
            data = _getByPk(ncTableName, pk).get(yNeed).toString();
        } catch (Exception e) {
            //log.warn(ncTableName + "_" + pk + "_none:" + yNeed); TODO 有数据后放出
        }
        return data;
    }

    @Override
    public Map<String, Object> getByCode(String ncTableName, String code) {
        if (!ncDict.contains(ncTableName)) {
            return null;
        }
        Map<String, Object> data = null;
        try {
            data = _getByCode(ncTableName, code);
        } catch (Exception e) {
            //log.warn(ncTableName + "_" + code + "_none:" + yNeed); TODO 有数据后放出
        }
        return data;
    }

    @Override
    public Map<String, Object> getByPk(String ncTableName, String pk) {
        if (!ncDict.contains(ncTableName)) {
            return null;
        }
        Map<String, Object> data = null;
        try {
            data = _getByPk(ncTableName, pk);
        } catch (Exception e) {
            //log.warn(ncTableName + "_" + pk + "_none:" + yNeed); TODO 有数据后放出
        }
        return data;
    }

    private static SearchSourceBuilder buildSearchParams(Map<String, Object> map, int size) {

        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if (map.size() != 0) {
            for (Map.Entry<String, Object> mp : map.entrySet()) {
                query.must(QueryBuilders.termQuery(mp.getKey(), mp.getValue()));
            }
        }

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(size);

        return searchSourceBuilder;
    }

    private Map<String, Object> _getByCode(String ncTableName, String code) throws Exception {
        Map<String, Object> pm = Maps.newHashMap();
        pm.put("code", code);
        SearchSourceBuilder qb = buildSearchParams(pm, 1);
        SearchHits hits = EsUtils.search(ncTableName, qb);
        Map<String, Object> data = hits.getAt(0).getSourceAsMap();
        return data;
    }

    private Map<String, Object> _getByPk(String ncTableName, String pk) throws Exception {
        Map<String, Object> pm = Maps.newHashMap();
        pm.put("pk", pk);
        SearchSourceBuilder qb = buildSearchParams(pm, 1);
        SearchHits hits = EsUtils.search(ncTableName, qb);
        return hits.getAt(0).getSourceAsMap();
    }

}
