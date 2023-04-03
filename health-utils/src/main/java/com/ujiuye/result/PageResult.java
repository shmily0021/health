package com.ujiuye.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data  // 除了构造器都有
@AllArgsConstructor  // 有参
@NoArgsConstructor   // 无参
public class PageResult implements Serializable {
    private Long total;  // 总条数
    private List rows;   // 当前页结果
}
