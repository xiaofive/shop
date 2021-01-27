package com.jyzt.es.service;

import java.util.Map;

public interface EsService {

    String getByCode(String ncTableName, String code, String yNeed);

    String getByPk(String ncTableName, String pk, String yNeed);

    Map<String, Object> getByCode(String ncTableName, String code);

    Map<String, Object> getByPk(String ncTableName, String pk);


}
