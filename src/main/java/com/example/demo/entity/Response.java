package com.example.demo.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiang Jining
 * @since 2020/11/1 11:08
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Response {
    private Integer code;
    private String message;
    private JSONObject info;
    
    public static Response success(JSONObject data) {
        return Response.builder().code(200).message("success").info(data).build();
    }
    
    public static Response error(JSONObject data) {
        return Response.builder().code(500).message("fail").info(data).build();
    }
}
