package com.tfb.project.service;

import com.tfb.project.mapper.TProfitDayNewMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

/**
 * @author leish
 * @create 2018-09-19
 **/
@Slf4j
@Service
public class ProfitDayNewService {

    @Autowired
    TProfitDayNewMapper tProfitDayNewMapper;

    public int getCount(){
        return tProfitDayNewMapper.selectCount(null);
    }


    public void updateSpName(){
        tProfitDayNewMapper.updateSpName();
    }

    @Async
    public CompletableFuture<String> doInsert(String sDate, String eDate){
        long start = System.currentTimeMillis();
        log.info(String.format("开始执行入库:[%s] - [%s]",sDate,eDate));
        //1. 将汇总数据插入到t_profit_day_new 表
        tProfitDayNewMapper.profitInsertDF(sDate,eDate);
        tProfitDayNewMapper.profitInsertDK(sDate,eDate);
        tProfitDayNewMapper.profitInsertWG(sDate,eDate);
        tProfitDayNewMapper.profitInsertGH(sDate,eDate);

        long end = System.currentTimeMillis();
        log.info(String.format("完成执行入库:[%s] - [%s]",sDate,eDate)+"，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("完成入库");
    }

    @Async
    public CompletableFuture<String> doProfit(String sDate, String eDate){
        long start = System.currentTimeMillis();
        log.info(String.format("开始执行分润:[%s] - [%s]",sDate,eDate));
        ///2. 更新商户代理商关联关系
        tProfitDayNewMapper.updateRelation(sDate,eDate);

        //一级,二级, 商户


        //3. 计算分润 商户收入,二级代理商成本、收入, 一级代理收入
        tProfitDayNewMapper.updateMerchantIncome(sDate,eDate);
        tProfitDayNewMapper.updateSubProfit(sDate,eDate);
        tProfitDayNewMapper.updateProfit(sDate,eDate);

        //4. 代付分润计算规则不同重算: 笔数* FfixedFee(固定每笔手续费)
        tProfitDayNewMapper.updateMerchantIncome_pay(sDate,eDate);
        tProfitDayNewMapper.updateSubProfit_pay(sDate,eDate);
        tProfitDayNewMapper.updateProfit_pay(sDate,eDate);

        //5. 更新1级业务类型
        tProfitDayNewMapper.updateBusType(sDate,eDate);
        long end = System.currentTimeMillis();
        log.info(String.format("完成执行分润耗时：" + (end - start) + "毫秒"));
        return CompletableFuture.completedFuture("完成分润");
    }

    @Async
    public CompletableFuture<String> doValid(String sDate, String eDate) {
        long start = System.currentTimeMillis();
        log.info(String.format("开始执行数据校验:[%s] - [%s]", sDate, eDate));
        //1. 验证一天的汇总数据是否入库成功
        int df = tProfitDayNewMapper.validDF(sDate, eDate);
        int dk = tProfitDayNewMapper.validDK(sDate, eDate);
        int wg = tProfitDayNewMapper.validWG(sDate, eDate);
        int gh = tProfitDayNewMapper.validGH(sDate, eDate);

        //一天的记录数
        int dayNum = df + dk + wg + gh;
        int insertCount = tProfitDayNewMapper.insertCount(sDate, eDate);
        if (dayNum != insertCount) {
            //删除当天记录
            int delNum = tProfitDayNewMapper.deleteData(sDate, eDate);
            // 重跑入库、分润
            doInsert(sDate, eDate);
            doProfit(sDate, eDate);

        }
        long end = System.currentTimeMillis();
        log.info(String.format("完成执行数据校验:[%s] - [%s]", sDate, eDate) + "，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("完成校验");
    }
}
