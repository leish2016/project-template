package com.tfb.project.task;

import com.tfb.project.StartAppTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * @author leish
 * @create 2018-09-19
 **/
public class ProfitTaskTest extends StartAppTests {

    @Autowired
    ProfitTask profitTask;


    @Test
    public void doInsertOneDayTest()throws Exception {
        String sDate = "2017-12-01";
        String eDate = "2018-09-01";
        profitTask.doInsertOneDay(sDate,eDate);
    }

    @Test
    public void doTaskOneDayProfitTest()throws Exception {
        String sDate = "2017-12-01";
        String eDate = "2017-12-01";
        profitTask.doTaskOneDayProfit(sDate,eDate);
    }

    @Test
    public void doValidTest()throws Exception {
        String sDate = "2017-12-01";
        String eDate = "2018-09-01";
        profitTask.doValid(sDate,eDate);
    }
}