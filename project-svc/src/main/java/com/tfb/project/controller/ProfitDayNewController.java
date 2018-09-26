package com.tfb.project.controller;

import com.tfb.project.domain.dto.RandomValue;
import com.tfb.project.service.ProfitDayNewService;
import com.tfb.project.task.ProfitTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leish
 * @create 2018-09-19
 **/
@Api(description="分润汇总服务")
@RestController
@RequestMapping("api/")
public class ProfitDayNewController {

    @Autowired
    ProfitDayNewService profitDayNewService;

    @Autowired
    ProfitTask profitTask;

    @GetMapping("/count")
    @ApiOperation("获取当前分润汇总记录数")
    public int getCount() {
        return profitDayNewService.getCount();
    }

    @GetMapping("/do")
    @ApiOperation("执行分润汇总")
    public void doValidTask() {
        profitTask.doValidTask();
    }
}
