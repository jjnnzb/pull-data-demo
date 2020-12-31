package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ResultBean;
import com.example.demo.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Jining
 * @since 2020/12/31 19:47
 */
@Service
@Slf4j
public class DataServiceImpl implements DataService {
    
    /**
     * Mock third-party api path.
     */
    private static final String THIRD_PARTY_API_PATH = "http://***.com/api/v1/data";
    
    @Override
    public JSONObject analyseData(String keyword) {
        JSONObject result = new JSONObject();
        // TODO pull data and save to database for at most 5 seconds.
        pullData(keyword);
        // analyse data
        List<ResultBean> resultBeanList = fetchDataAndAnalyse(keyword);
        result.put("data", resultBeanList);
        return result;
    }
    
    private void pullData(String keyword) {
        List<ResultBean> resultBeanList = new ArrayList<>();
        String response;
        try {
            response = IOUtils.toString(new URL(THIRD_PARTY_API_PATH + keyword), StandardCharsets.UTF_8);
            JSONObject json = JSON.parseObject(response);
            if (json != null) {
                Integer num = json.getInteger("childrenNum");
                String subKeyword = json.getString("subKeyword");
                // raw data, can be converted to class and save to database.
                JSONArray data = json.getJSONArray("data");
                saveRawDataToDatabase(data);
                if (num != null && num > 0) {
                    // Iterate for more data
                    pullData(subKeyword);
                }
            }
        } catch (IOException e) {
            log.error("error message:{}", e.getMessage());
            log.error("error detail:{}", e.toString());
        }
    }
    
    private void saveRawDataToDatabase(JSONArray data) {
        if(data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                // ResultBean resultBean = jsonObject.toJavaObject(ResultBean.class);
                // dataDao.insert(xxx.class);
            }
        }
    }
    
    private List<ResultBean> fetchDataAndAnalyse(String keyword) {
        List<ResultBean> list = new ArrayList<>(100);
        // Read data from database
        // List<ResultBean> list = dataDao.query(keyword);
        // process list, handle parent-children relation and return result.
        return list;
    }
}
