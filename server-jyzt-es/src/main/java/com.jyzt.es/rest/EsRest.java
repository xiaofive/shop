package com.jyzt.es.rest;

import com.jyzt.common.result.Result;
import com.jyzt.es.service.EsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/***
 *功能描述: 基于ES对外rest
 * @author wangyang
 */
@RestController
public class EsRest {

    @Resource
    private EsService esService;

    /**
     * @param ncTableName NC数据库表名
     * @param code        NC表code值
     * @param yNeed       需要查询的字段名称
     * @return
     */
    @GetMapping("/getYNeedByCode")
    public Result getYNeedByCode(@RequestParam("ncTableName") String ncTableName,
                                 @RequestParam("code") String code,
                                 @RequestParam("yNeed") String yNeed) {
        return Result.success(esService.getByCode(ncTableName, code, yNeed));
    }

    /**
     * @param ncTableName NC数据库表名
     * @param pk          NC表pk值
     * @param yNeed       需要查询的字段名称
     * @return
     */
    @GetMapping("/getYNeedByPk")
    public Result getYNeedByPk(@RequestParam("ncTableName") String ncTableName,
                               @RequestParam("pk") String pk,
                               @RequestParam("yNeed") String yNeed) {
        return Result.success(esService.getByPk(ncTableName, pk, yNeed));
    }

    /**
     * @param ncTableName NC数据库表名
     * @param code        NC表code值
     * @return
     */
    @GetMapping("/getByCode")
    public Result getByCode(@RequestParam("ncTableName") String ncTableName,
                            @RequestParam("code") String code) {
        return Result.success(esService.getByCode(ncTableName, code));
    }

    /**
     * @param ncTableName NC数据库表名
     * @param pk          NC表pk值
     * @return
     */
    @GetMapping("/getByPk")
    public Result getByPk(@RequestParam("ncTableName") String ncTableName,
                          @RequestParam("pk") String pk) {
        return Result.success(esService.getByPk(ncTableName, pk));
    }

}
