package com.example.demo.service.impl;

import com.example.demo.model.entity.InterfaceInfo;
import com.example.demo.common.ErrorCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.InterfaceInfoMapper;
import com.example.demo.service.IInterfaceInfoService;

import jakarta.annotation.Resource;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口信息 服务实现类
 * </p>
 *
 * @author ziying
 * @since 2024-01-26
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo> implements IInterfaceInfoService {

  @Resource
  private InterfaceInfoMapper interfaceInfoMapper;
  
  @Override
  public long createInterfaceInfo(InterfaceInfo interfaceInfo) {
    if(interfaceInfo == null) {
      throw new BusinessException(ErrorCode.PARAMS_ERROR);
    } 
    Boolean allParamsNotEmpty = interfaceInfo.areSelectedFieldsNonNull(interfaceInfo);
    if(allParamsNotEmpty) {
      int result = interfaceInfoMapper.insert(interfaceInfo);
      if(result > 0) {
        return interfaceInfo.getId();
      }else {
        throw new BusinessException(ErrorCode.DATA_INSERT_ERROR);
      }
    }else {
      throw new BusinessException(ErrorCode.PARAMS_ERROR);
    }
  }
}
