package com.liujinshui.bus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liujinshui.bus.entity.Leavebill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liujinshui.bus.vo.LeavebillVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-08
 */
public interface LeavebillService extends IService<Leavebill> {

    IPage<Leavebill> findLeaveBillByPage(IPage<Leavebill> page,LeavebillVo leavebillVo) throws Exception;
}
