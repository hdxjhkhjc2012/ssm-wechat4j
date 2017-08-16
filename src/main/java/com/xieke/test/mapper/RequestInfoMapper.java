package com.xieke.test.mapper;

import com.xieke.test.model.RequestInfo;

import java.util.List;

public interface RequestInfoMapper {
    int deleteByPrimaryKey(String msgid);

    int insert(RequestInfo record);

    int insertSelective(RequestInfo record);

    RequestInfo selectByPrimaryKey(String msgid);

    int updateByPrimaryKeySelective(RequestInfo record);

    int updateByPrimaryKey(RequestInfo record);

    List<RequestInfo>  findAll();
}