package com.liujinshui.bus.service.impl;

import com.liujinshui.bus.entity.Customer;
import com.liujinshui.bus.dao.CustomerMapper;
import com.liujinshui.bus.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-08
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
