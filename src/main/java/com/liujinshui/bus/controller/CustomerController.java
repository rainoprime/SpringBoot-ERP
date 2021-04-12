package com.liujinshui.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liujinshui.bus.entity.Customer;
import com.liujinshui.bus.service.CustomerService;
import com.liujinshui.bus.vo.CustomerVo;
import com.liujinshui.common.utils.DataGridViewResult;
import com.liujinshui.common.utils.JSONResult;
import com.liujinshui.common.utils.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/bus/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("/customerlist")
    public DataGridViewResult customerlist(CustomerVo customerVo){
        //调用分页查询
        IPage<Customer> page = new Page<>(customerVo.getPage(),customerVo.getLimit());
        //条件构造器
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        //客户姓名、电话、联系人
        queryWrapper.like(StringUtils.isNotBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        queryWrapper.like(StringUtils.isNotBlank(customerVo.getTelephone()),"telephone",customerVo.getTelephone());
        queryWrapper.like(StringUtils.isNotBlank(customerVo.getLinkman()),"linkman",customerVo.getLinkman());


        customerService.page(page,queryWrapper);

        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }


    @RequestMapping("/addCustomer")
    public JSONResult addCustomer(Customer customer){
        if(customerService.save(customer)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }

    @RequestMapping("/updateCustomer")
    public JSONResult updateCustomer(Customer customer){
        if(customerService.updateById(customer)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }



    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        if(customerService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }

    @RequestMapping("/batchDelete")
    public JSONResult batchDelete(String ids){
        String idsArr[] = ids.split(",");
        if(customerService.removeByIds(Arrays.asList(idsArr))){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }
}

