package com.zgj.reggie.mapper;

import com.zgj.reggie.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author zgj
 * @since 2022-07-25
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
