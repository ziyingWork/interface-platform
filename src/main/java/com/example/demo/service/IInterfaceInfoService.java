package com.example.demo.service;

import com.example.demo.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 接口信息 服务类
 * </p>
 *
 * @author ziying
 * @since 2024-01-26
 */
public interface IInterfaceInfoService extends IService<InterfaceInfo> {

  long createInterfaceInfo(InterfaceInfo interfaceInfo);

}
