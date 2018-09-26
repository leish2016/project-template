package com.tfb.project.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * Created by leish on 2017/8/18.
 */
@Data
public class RandomValue implements Serializable {

    /**
     * type : success
     * value : {"id":1,"quote":"Working with Spring Boot is like pair-programming with the Spring developers."}
     */

    private String type;
    private Value value;


    @Data
    public static class Value implements Serializable{
        /**
         * id : 1
         * quote : Working with Spring Boot is like pair-programming with the Spring developers.
         */

        private int id;
        private String quote;


    }
}
