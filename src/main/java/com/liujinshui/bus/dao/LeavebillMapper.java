package com.liujinshui.bus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liujinshui.bus.entity.Leavebill;
import com.liujinshui.bus.vo.LeavebillVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-08
 */
public interface LeavebillMapper extends BaseMapper<Leavebill> {

    IPage<Leavebill> findLeaveBillByPage(@Param("page") IPage<Leavebill> page, @Param("leavebill") LeavebillVo leavebillVo) throws Exception;
}
