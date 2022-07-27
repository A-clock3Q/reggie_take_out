package com.zgj.reggie.service.impl;

import com.zgj.reggie.entity.Employee;
import com.zgj.reggie.mapper.EmployeeMapper;
import com.zgj.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author zgj
 * @since 2022-07-25
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
