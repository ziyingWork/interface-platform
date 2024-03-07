/*
 * @Author: zhuziying 1249353194@qq.com
 * @Date: 2024-02-01 19:14:15
 * @LastEditors: zhuziying 1249353194@qq.com
 * @LastEditTime: 2024-02-01 19:14:40
 * @FilePath: /demo/src/main/java/com/example/demo/common/ResponseResult.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 统一响应类
 * @author ziying
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;
 
    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
 
    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
 
    public Integer getCode() {
        return code;
    }
 
    public void setCode(Integer code) {
        this.code = code;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public T getData() {
        return data;
    }
 
    public void setData(T data) {
        this.data = data;
    }
 
    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}