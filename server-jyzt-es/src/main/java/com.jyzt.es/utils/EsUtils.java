package com.jyzt.es.utils;

import com.jyzt.common.exception.SysException;
import com.jyzt.es.bean.EsErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.cache.clear.ClearIndicesCacheRequest;
import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/***
 *功能描述: ES工具类
 * @author wangyang
 */
@Slf4j
public class EsUtils {

    public static String INDEX_NAME = "jy_bill_01"; //FIXME 测试用
    private static String INDEX_TYPE = "doc"; //FIXME 测试用

    /**
     * 创建索引
     *
     * @param indexName
     * @param alias
     * @param mapping
     */
    public static void createIndex(String indexName, String alias, Map<String, Object> mapping) {
        if (indexExists(indexName)) {
            throw new SysException(EsErrorEnum.INDEX_EXIST);
        }
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)   //分片数
                .put("index.number_of_replicas", 2) //备份数
        );
        request.mapping(INDEX_TYPE, mapping);
        request.alias(new Alias(alias));
        request.masterNodeTimeout(TimeValue.timeValueMinutes(2));
        request.waitForActiveShards(ActiveShardCount.DEFAULT);
        try {
            EsClientFactory.getEsClient().indices().create(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            log.error("", exception);
            throw new SysException(EsErrorEnum.INDEX_CREATE_ERROR);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
    }

    /**
     * 删除索引
     *
     * @param indexName
     */
    public static void deleteIndex(String indexName) {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        request.timeout(TimeValue.timeValueMinutes(2));
        request.timeout("2m");
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        request.masterNodeTimeout("1m");
        try {
            EsClientFactory.getEsClient().indices().delete(request, RequestOptions.DEFAULT).isAcknowledged();
        } catch (ElasticsearchException exception) {
            log.error("", exception);
            throw new SysException(EsErrorEnum.INDEX_DELETE_ERROR);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }

    }

    /**
     * 索引是否存在
     *
     * @param indexName
     * @return
     */
    public static boolean indexExists(String indexName) {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        boolean exist = false;
        try {
            exist = EsClientFactory.getEsClient().indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
        return exist;
    }

    /**
     * 别名查询
     *
     * @param aliasName
     * @return
     * @throws IOException
     */
    public static Map<String, Set<AliasMetaData>> getAlias(String aliasName) throws IOException {
        GetAliasesRequest requestWithAliases = new GetAliasesRequest(aliasName);
        requestWithAliases.local(true);
        GetAliasesResponse response = EsClientFactory.getEsClient().indices().getAlias(requestWithAliases, RequestOptions.DEFAULT);
        return response.getAliases();
    }

    /**
     * 创建文档
     *
     * @param indexName
     * @param documentId
     * @param source
     */
    public static void createDocument(String indexName, String documentId, Map<String, Object> source) {
        IndexRequest indexRequest = new IndexRequest(indexName)
                .id(documentId)
                .type(INDEX_TYPE)
                .source(source)
                .opType(DocWriteRequest.OpType.CREATE);
        try {
            EsClientFactory.getEsClient().index(indexRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            log.error("", e);
            throw new SysException(EsErrorEnum.DOCUMENT_CREATE_ERROR);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
    }

    /**
     * 创建文档
     *
     * @param indexName
     * @param documentId
     * @param jsonData
     */
    public static void createDocument(String indexName, String documentId, String jsonData) {
        IndexRequest indexRequest = new IndexRequest(indexName)
                .id(documentId)
                .type(INDEX_TYPE)
                .source(jsonData, XContentType.JSON)
                .opType(DocWriteRequest.OpType.CREATE);
        try {
            EsClientFactory.getEsClient().index(indexRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            log.error("", e);
            throw new SysException(EsErrorEnum.DOCUMENT_CREATE_ERROR);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
    }

    /**
     * 获取文档
     *
     * @param indexName
     * @param documentId
     * @return
     */
    public static String getDocumentById(String indexName, String documentId) {
        GetRequest getRequest = new GetRequest(indexName, INDEX_TYPE, documentId);
        try {
            GetResponse getResponse = EsClientFactory.getEsClient().get(getRequest, RequestOptions.DEFAULT);
            if (getResponse.isExists()) {
                return getResponse.getSourceAsString();
            }
            throw new SysException(EsErrorEnum.DOCUMENT_NOT_EXIST);
        } catch (ElasticsearchException e) {

            log.error("", e);
            if (e.status() == RestStatus.NOT_FOUND) {
                throw new SysException(EsErrorEnum.INDEX_NOT_EXIST);
            }
            if (e.status() == RestStatus.CONFLICT) {
                throw new SysException(EsErrorEnum.DOCUMENT_VERSION_CONFLICT);
            }
            throw new SysException(EsErrorEnum.DOCUMENT_GET_ERROR);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
    }

    /**
     * 文档是否存在
     *
     * @param indexName
     * @param documentId
     * @return
     */
    public static boolean documentExists(String indexName, String documentId) {
        GetRequest getRequest = new GetRequest(indexName, INDEX_TYPE, documentId);
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_ none_");
        boolean exist = false;
        try {
            exist = EsClientFactory.getEsClient().exists(getRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
        return exist;
    }

    /**
     * 删除文档
     *
     * @param indexName
     * @param documentId
     */
    public static void deleteDocumentById(String indexName, String documentId) {
        DeleteRequest deleteRequest = new DeleteRequest(indexName, "doc", documentId);
        deleteRequest.timeout(TimeValue.timeValueMinutes(2));
        deleteRequest.timeout("2m");
        try {
            DeleteResponse deleteResponse = EsClientFactory.getEsClient().delete(deleteRequest, RequestOptions.DEFAULT);
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                throw new SysException(EsErrorEnum.DOCUMENT_NOT_EXIST);
            }
        } catch (ElasticsearchException exception) {
            log.error("", exception);
            throw new SysException(EsErrorEnum.DOCUMENT_DELETE_ERROR);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
    }

    public static void updateDocument(String indexName, String documentId, Map<String, Object> map) {
        UpdateRequest request = new UpdateRequest(indexName, INDEX_TYPE, documentId);
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.retryOnConflict(3);
        request.doc(map);
        try {
            EsClientFactory.getEsClient().update(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                throw new SysException(EsErrorEnum.DOCUMENT_NOT_EXIST);
            }
            if (e.status() == RestStatus.CONFLICT) {
                throw new SysException(EsErrorEnum.DOCUMENT_VERSION_CONFLICT);
            }
            throw new SysException(EsErrorEnum.DOCUMENT_UPDATE_ERROR);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
    }

    public static void updateDocumentByScript(String indexName, String documentId, Script script) {
        UpdateRequest request = new UpdateRequest(indexName, INDEX_TYPE, documentId);
        request.retryOnConflict(3);
        request.script(script);
        try {
            EsClientFactory.getEsClient().update(request, RequestOptions.DEFAULT);
            log.info("call es update => {}", script.toString());
        } catch (ElasticsearchException e) {
            log.error("", e);
            if (e.status() == RestStatus.NOT_FOUND) {
                throw new SysException(EsErrorEnum.DOCUMENT_NOT_EXIST);
            }
            if (e.status() == RestStatus.CONFLICT) {
                throw new SysException(EsErrorEnum.DOCUMENT_VERSION_CONFLICT);
            }
            throw new SysException(EsErrorEnum.DOCUMENT_UPDATE_ERROR);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
    }

    public static void updateDocument(String indexName, String documentId, String field, String value) {
        UpdateRequest request = new UpdateRequest(indexName, INDEX_TYPE, documentId);
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.retryOnConflict(3);
        request.doc(field, value);
        try {
            EsClientFactory.getEsClient().update(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                throw new SysException(EsErrorEnum.DOCUMENT_NOT_EXIST);
            }
            if (e.status() == RestStatus.CONFLICT) {
                throw new SysException(EsErrorEnum.DOCUMENT_VERSION_CONFLICT);
            }
            throw new SysException(EsErrorEnum.DOCUMENT_UPDATE_ERROR);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
    }

    public static void updateDocument(String indexName, String documentId, XContentBuilder xContentBuilder) {
        UpdateRequest request = new UpdateRequest(indexName, INDEX_TYPE, documentId);
        request.timeout(TimeValue.timeValueSeconds(1));
        request.retryOnConflict(3);
        request.timeout("1s");
        request.doc(xContentBuilder);
        try {
            EsClientFactory.getEsClient().update(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                throw new SysException(EsErrorEnum.DOCUMENT_NOT_EXIST);
            }
            if (e.status() == RestStatus.CONFLICT) {
                throw new SysException(EsErrorEnum.DOCUMENT_VERSION_CONFLICT);
            }
            throw new SysException(EsErrorEnum.DOCUMENT_UPDATE_ERROR);
        } catch (IOException e) {
            throw new SysException(EsErrorEnum.ES_CONNECTION_ERROR);
        }
    }

    /**
     * open索引
     *
     * @param indexName
     * @throws IOException
     */
    public static void openIndex(String... indexName) throws IOException {
        OpenIndexRequest request = new OpenIndexRequest(indexName);
        request.timeout(TimeValue.timeValueMinutes(2));
        request.timeout("2m");
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        request.masterNodeTimeout("1m");
        request.waitForActiveShards(2);
        request.waitForActiveShards(ActiveShardCount.DEFAULT);
        request.indicesOptions(IndicesOptions.strictExpandOpen());
        EsClientFactory.getEsClient().indices().open(request, RequestOptions.DEFAULT);
    }

    /**
     * close索引
     *
     * @param indexName
     * @throws IOException
     */
    public static void closeIndex(String... indexName) throws IOException {
        CloseIndexRequest request = new CloseIndexRequest(indexName);
        request.timeout(TimeValue.timeValueMinutes(2));
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        EsClientFactory.getEsClient().indices().close(request, RequestOptions.DEFAULT);
    }

    /**
     * 清除指定索引缓存
     *
     * @param indexName
     * @throws IOException
     */
    public static void clearCache(String indexName) throws IOException {
        try {
            ClearIndicesCacheRequest requestMultiple = new ClearIndicesCacheRequest(indexName);
            requestMultiple.queryCache(true);
            requestMultiple.fieldDataCache(true);
            requestMultiple.requestCache(true);
            EsClientFactory.getEsClient().indices().clearCache(requestMultiple, RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            log.error("", exception);
            if (exception.status() == RestStatus.NOT_FOUND) {
                throw new SysException(EsErrorEnum.INDEX_NOT_EXIST);
            }
        }
    }

    /**
     * 清除索引缓存
     *
     * @throws IOException
     */
    public static void clearAllCache() throws IOException {
        try {
            ClearIndicesCacheRequest requestAll = new ClearIndicesCacheRequest();
            requestAll.queryCache(true);
            requestAll.fieldDataCache(true);
            requestAll.requestCache(true);
            EsClientFactory.getEsClient().indices().clearCache(requestAll, RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            log.error("", exception);
            if (exception.status() == RestStatus.NOT_FOUND) {
                throw new SysException(EsErrorEnum.CLEAR_CACHE_ERROR);
            }
        }
    }

    /**
     * 刷新指定索引
     *
     * @param indexName
     * @throws IOException
     */
    public static void refreIndex(String indexName) throws IOException {
        RefreshRequest request = new RefreshRequest(indexName);
        try {
            EsClientFactory.getEsClient().indices().refresh(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            log.error("", exception);
            if (exception.status() == RestStatus.NOT_FOUND) {
                throw new SysException(EsErrorEnum.INDEX_NOT_EXIST);
            }
        }
    }

    /**
     * 刷新所有索引
     *
     * @throws IOException
     */
    public static void refreAll() throws IOException {
        RefreshRequest requestAll = new RefreshRequest();
        EsClientFactory.getEsClient().indices().refresh(requestAll, RequestOptions.DEFAULT);
    }

    /**
     * 持久化指定索引内存到磁盘
     *
     * @param indexName
     * @throws IOException
     */
    public static void flush(String indexName) throws IOException {
        FlushRequest requestMultiple = new FlushRequest(indexName);
        requestMultiple.indicesOptions(IndicesOptions.lenientExpandOpen());
        requestMultiple.waitIfOngoing(true);
        requestMultiple.force(true);
        try {
            EsClientFactory.getEsClient().indices().flush(requestMultiple, RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            log.error("", exception);
            if (exception.status() == RestStatus.NOT_FOUND) {
                throw new SysException(EsErrorEnum.INDEX_NOT_EXIST);
            }
        }
    }

    /**
     * 持久化到磁盘
     *
     * @throws IOException
     */
    public void flushAll() throws IOException {
        try {
            FlushRequest requestAll = new FlushRequest();
            requestAll.indicesOptions(IndicesOptions.lenientExpandOpen());
            requestAll.waitIfOngoing(true);
            requestAll.force(true);
            EsClientFactory.getEsClient().indices().flush(requestAll, RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            log.error("", exception.getDetailedMessage());
            throw new SysException(EsErrorEnum.INDEX_FLUSH_ERROR);
        }
    }

    public static SearchHits search(String indexName, SearchSourceBuilder searchSourceBuilder) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName); //指定索引
        searchRequest.source(searchSourceBuilder);
//        searchRequest.routing("routing");
//        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
//        searchRequest.preference("_local");
        SearchResponse searchResponse = null;
        try {
            searchResponse = EsClientFactory.getEsClient().search(searchRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            //log.error("", exception);
            throw new SysException(EsErrorEnum.ES_SEARCH_ERROR);
        }
        return searchResponse.getHits();
    }

    public static boolean checkFieldMethod(String methodName, Class<?> cls) {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return true;
            }
        }
        return false;
    }

}