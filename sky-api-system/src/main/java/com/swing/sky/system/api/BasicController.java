package com.swing.sky.system.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swing.sky.common.constant.PageConstants;
import com.swing.sky.common.utils.SkySqlUtil;
import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.web.utils.ServletUtils;

import java.util.List;

/**
 * 基本的Controller
 *
 * @author swing
 */
public class BasicController {

    /**
     * 开始分页请求数据
     */
    protected void startPage() {
        //从请求中获取分页信息
        Integer pageNum = ServletUtils.getParameterToInt(PageConstants.PAGE_NUM);
        Integer pageSize = ServletUtils.getParameterToInt(PageConstants.PAGE_SIZE);
        String orderByColumn = ServletUtils.getParameter(PageConstants.ORDER_BY_COLUMN);
        String isAsc = ServletUtils.getParameter(PageConstants.IS_ASC);
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            //防止sql注入
            String orderBy = SkySqlUtil.escapeOrderBySql(orderByColumn, isAsc);
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo buildDataTable(List<?> list) {
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setStatus(200);
        tableDataInfo.setRows(list);
        tableDataInfo.setTotal(new PageInfo(list).getTotal());
        return tableDataInfo;
    }
}
