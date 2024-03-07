/*
 * @Author: zhuziying 1249353194@qq.com
 * @Date: 2024-01-23 19:12:19
 * @LastEditors: zhuziying 1249353194@qq.com
 * @LastEditTime: 2024-02-03 18:16:04
 * @FilePath: /demo/src/main/java/com/example/demo/common/ResultUtils.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.example.demo.common;

/**
 * 返回工具类
 *
 * @author ziying
 */

public class ResultUtils {
  /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @return
     */
    public static BaseResponse<Void> error(int code, String message) {
      return new BaseResponse<>(code, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse<Void> error(ErrorCode errorCode, String message) {
        // return new BaseResponse(errorCode.getCode(), null, message);
        return new BaseResponse<>(errorCode, message);
    }

}
