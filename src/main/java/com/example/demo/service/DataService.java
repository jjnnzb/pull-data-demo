package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Jiang Jining
 * @since 2020/12/31 19:45
 */
public interface DataService {
    /**
     * Analyse data with keyword submitted from client.
     *
     * @param keyword keyword
     * @return the analysed result as json
     */
    JSONObject analyseData(String keyword);
}
