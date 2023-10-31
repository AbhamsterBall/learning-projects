package com.hyh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyh.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpBaseMapper extends BaseMapper<Emp> {
}
