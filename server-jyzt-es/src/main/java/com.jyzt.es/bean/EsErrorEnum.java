package com.jyzt.es.bean;

import com.jyzt.common.exception.ExceptionEnumInterface;

/***
 *功能描述: es相关的错误代码枚举
 * @author wangyang
 */
public enum EsErrorEnum implements ExceptionEnumInterface {

    INDEX_CREATE_ERROR("es_0101", "index_create_error"),
    INDEX_DELETE_ERROR("es_0102", "index_delete_error"),
    INDEX_NOT_EXIST("es_0103", "index_not_exist"),
    INDEX_EXIST("es_0103", "index_exist"),

    DOCUMENT_CREATE_ERROR("es_0201", "document_create_error"),
    DOCUMENT_NOT_EXIST("es_0202", "document_not_exist"),
    DOCUMENT_VERSION_CONFLICT("es_0203", "document_version_conflict"),
    DOCUMENT_HAS_EXIST("es_0204", "document_has_exist"),
    DOCUMENT_DELETE_ERROR("es_0205", "document_delete_error"),
    DOCUMENT_UPDATE_ERROR("es_0206", "document_update_error"),
    DOCUMENT_GET_ERROR("es_0206", "document_get_error"),

    INDEX_FLUSH_ERROR("es_0301", "index_flush_error"),
    CLEAR_CACHE_ERROR("es_0302", "clear_cache_error"),
    ES_CONNECTION_ERROR("es_0303", "es_connection_error"),

    FIELD_TYPE_NOT_EXIST("es_0401", "field_type_not_exist"),
    CONVERT_ES_DATA_ERROR("es_0501", "convert_es_data_error"),
    ES_SEARCH_ERROR("es_0601", "es_search_error"),
    ES_HEADER_CLASS_NONE("ES_0602", "es_header_class_none");

    private String code;

    private String msg;

    EsErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
