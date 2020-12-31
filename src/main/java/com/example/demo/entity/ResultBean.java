package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jiang Jining
 * @since 2020/12/31 20:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean {
    private Long id;
    private Long parentId;
    private String publishTime;
    private String title;
    private String content;
    private List<ResultBean> children;
}
