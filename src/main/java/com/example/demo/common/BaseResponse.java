package com.example.demo.common;
import java.io.Serializable;

public class BaseResponse<T> implements Serializable{

  private int code;

  private T data;

  private String message;

  public BaseResponse(int code, T data, String message) {
      this.code = code;
      this.data = data;
      this.message = message;
  }


  public BaseResponse(int code, String message) {
      this(code, null, message);
  } 

  public BaseResponse(int code, T data) {
      this(code, data, "");
  }


  public BaseResponse(ErrorCode errorCode) {
      this(errorCode.getCode(), null, errorCode.getMessage());
  }

  public BaseResponse(ErrorCode errorCode, String message) {
    this(errorCode.getCode(), null, message);
  } 

  
  // Getter and setter methods
  public int getCode() {
      return code;
  }

  public void setCode(int code) {
      this.code = code;
  }

  public T getData() {
      return data;
  }

  public void setData(T data) {
      this.data = data;
  }

  public String getMessage() {
      return message;
  }

  public void setMessage(String message) {
      this.message = message;
  }

  // equals, hashCode and toString methods
  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      BaseResponse<?> that = (BaseResponse<?>) o;
      return code == that.code && 
            (data != null ? data.equals(that.data) : that.data == null) && 
            (message != null ? message.equals(that.message) : that.message == null);
  }

  @Override
  public int hashCode() {
      int result = code;
      result = 31 * result + (data != null ? data.hashCode() : 0);
      result = 31 * result + (message != null ? message.hashCode() : 0);
      return result;
  }

  @Override
  public String toString() {
      return "BaseResponse{" +
              "code=" + code +
              ", data=" + data +
              ", message='" + message + '\'' +
              '}';
  }



}
