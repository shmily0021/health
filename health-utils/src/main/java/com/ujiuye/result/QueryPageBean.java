package com.ujiuye.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class QueryPageBean implements Serializable {
    private Integer currentPage;  // 页码
    private Integer pageSize;     // 每页显示条数
    private String queryString;   // 查询条件
}
