package com.tfb.project.task;

import com.tfb.project.service.ProfitDayNewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author leish
 * @create 2018-09-19
 **/
@EnableScheduling
@Component
@Slf4j
public class ProfitTask {

    @Autowired
    ProfitDayNewService task;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void doValidTask(){
        String sDate = "2017-12-01";
        String eDate = "2018-09-01";
        try {
            doValid(sDate,eDate);
            task.updateSpName();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("doValidTask err:"+e);
        }
    }


    /**
     * 重跑汇总
     */
    public void doInsertOneDay(String sDate, String eDate) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        // 2017年12月1日-2018年3月21日的数据、  2018-03-21 到 2018-09-01
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

        List<CompletableFuture<String>> list = new ArrayList();
        while (true){
            log.info("当前执行时间:"+sDate);
            String begin = sDate.concat(" 00:00:00");
            String end = sDate.concat(" 23:59:59");
            CompletableFuture<String> task1 = task.doInsert(begin,end); //执行一天的汇总
            list.add(task1);

            if(Objects.equals(sDate,eDate)){
                break;
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateformat.parse(sDate));
            cal.add(Calendar.DAY_OF_MONTH, 1);
            sDate = dateformat.format(cal.getTime());
        }



        //判断执行完毕
        allDone(list);
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
    }

    /**
     * 重跑分润
     */
    public void doTaskOneDayProfit(String sDate, String eDate) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        // 2017年12月1日-2018年3月21日的数据
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

        List list = new ArrayList();
        while (true){
            log.info("当前执行时间:"+sDate);
            String begin = sDate.concat(" 00:00:00");
            String end = sDate.concat(" 23:59:59");
            CompletableFuture<String> task1 = task.doProfit(begin,end); //执行一天的分润
            list.add(task1);

            if(Objects.equals(sDate,eDate)){
                break;
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateformat.parse(sDate));
            cal.add(Calendar.DAY_OF_MONTH, 1);
            sDate = dateformat.format(cal.getTime());
        }


        allDone(list);
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
    }

    /**
     * 校验一天的数据
     */
    public void doValid(String sDate, String eDate) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        // 2017年12月1日-2018年3月21日的数据
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

        List list = new ArrayList();
        while (true){
            log.info("当前执行时间:"+sDate);
            String begin = sDate.concat(" 00:00:00");
            String end = sDate.concat(" 23:59:59");
            CompletableFuture<String> task1 = task.doValid(begin,end); //执行一天的分润
            list.add(task1);

            if(Objects.equals(sDate,eDate)){
                break;
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateformat.parse(sDate));
            cal.add(Calendar.DAY_OF_MONTH, 1);
            sDate = dateformat.format(cal.getTime());
        }


        allDone(list);
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
    }


    private void allDone(List list) throws ExecutionException, InterruptedException {
        //判断执行完毕
        while (list.size()>0){
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                CompletableFuture<String> next = (CompletableFuture<String>) iterator.next();
                if(next.isDone()){
                    iterator.remove();
                }
            }
            try {
                Thread.sleep(1000*list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
