package com.swing.sky.es.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swing.sky.es.module.domain.TiQuestionSearchDO;

import java.util.Date;

/**
 * @author swing
 */
public class QuestionResponse extends TiQuestionSearchDO {
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Override
    public String toString() {
        return "QuestionResponse{" +
                "updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
