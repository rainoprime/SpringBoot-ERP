package com.liujinshui.bus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujinshui.bus.dao.LeavebillMapper;
import com.liujinshui.bus.entity.Leavebill;
import com.liujinshui.bus.service.LeavebillService;
import com.liujinshui.bus.vo.LeavebillVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-08
 */
@Service
public class LeavebillServiceImpl extends ServiceImpl<LeavebillMapper, Leavebill> implements LeavebillService {
    @Resource
    private LeavebillMapper LeavebillMapper;

    @Override
    public IPage<Leavebill> findLeaveBillByPage(IPage<Leavebill> page, LeavebillVo leavebillVo) throws Exception {


        return LeavebillMapper.findLeaveBillByPage(page,leavebillVo);
    }
}
