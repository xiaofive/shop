package com.shop.common.config.mybatis;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

/**
 * 定义全局通用 selectByRefId() 方法
 * <p>
 * Author: wang Y
 * Date: 2021-05-22
 */
@Slf4j
public class SelectByRefId extends AbstractMethod {

    /**
     *自定义selectByRefId()
     *
     * Author: wang Y
     * Date: 2021-05-22
     */
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_BY_ID;
        SqlSource sqlSource = new RawSqlSource(
                configuration,
                String.format(sqlMethod.getSql(),
                sqlSelectColumns(tableInfo, false),
                tableInfo.getTableName(),
                        "ref_id",
                        "refId",
                        tableInfo.getLogicDeleteSql(true, false)
                ),
                Object.class);
        return this.addSelectMappedStatementForTable(mapperClass, "selectByRefId", sqlSource, tableInfo);
    }

}
