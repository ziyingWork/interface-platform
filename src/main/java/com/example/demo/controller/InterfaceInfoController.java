package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.common.BaseResponse;
import com.example.demo.common.DeleteRequest;
import com.example.demo.common.ErrorCode;
import com.example.demo.common.ResultUtils;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.dto.InterfaceInfo.InterfaceInfoAddRequest;
import com.example.demo.model.entity.InterfaceInfo;
import com.example.demo.service.IInterfaceInfoService;

import jakarta.annotation.Resource;
// import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




/**
 * <p>
 * 接口信息 前端控制器
 * </p>
 *
 * @author ziying
 * @since 2024-01-26
 */
@RestController
@RequestMapping("/interfaceInfo")
public class InterfaceInfoController {

  @Resource
  private IInterfaceInfoService interfaceInfoService;

  /**
   *  创建接口，成功返回接口id
   *  
   *  @param InterfaceInfoAddRequest
   *  @param httpServletRequest
   *  @return 
   */
  @PostMapping("/add")
  public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest interfaceInfoAddRequest) throws IllegalAccessException, InvocationTargetException {
    if(interfaceInfoAddRequest == null){
      throw new BusinessException(ErrorCode.PARAMS_ERROR);
    }
    InterfaceInfo interfaceInfo = new InterfaceInfo();
    try {
        BeanUtils.copyProperties(interfaceInfo, interfaceInfoAddRequest);
        interfaceInfo.setUserId(888L);
    } catch (Exception e) {
        e.printStackTrace();
    }
    interfaceInfoService.createInterfaceInfo(interfaceInfo);
    return ResultUtils.success(interfaceInfo.getId());
  }

  @GetMapping("/getlinshi")
  public int getMethodName(@RequestParam("num") int num) {
    return num;
  }

  /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody DeleteRequest deleteRequest) {
        if(deleteRequest == null || deleteRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

      return null;
    }





  
}
