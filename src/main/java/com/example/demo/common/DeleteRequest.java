package com.example.demo.common;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author ziying
 */


public class DeleteRequest implements Serializable{

  private Long id;

  private static final long serialVersionUID = 1L;

  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  @Override
  public String toString() {
      return "DeleteRequest{" +
              "id=" + id +
              '}';
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      DeleteRequest that = (DeleteRequest) o;

      return id != null ? id.equals(that.id) : that.id == null;
  }

  @Override
  public int hashCode() {
      return id != null ? id.hashCode() : 0;
  }
  
}
