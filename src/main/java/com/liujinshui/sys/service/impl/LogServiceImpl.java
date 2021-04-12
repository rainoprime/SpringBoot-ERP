package com.liujinshui.sys.service.impl;

import com.liujinshui.sys.entity.Log;
import com.liujinshui.sys.dao.LogMapper;
import com.liujinshui.sys.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-06
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
