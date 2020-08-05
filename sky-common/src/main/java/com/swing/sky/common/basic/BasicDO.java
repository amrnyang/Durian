package com.swing.sky.common.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swing.sky.common.annotation.Excel;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * xxxDO 数据对象的共有字段
 *
 * @author swing
 */
public class BasicDO {
    protected static final long serialVersionUID = 1L;
    /**
     * 主键id,自增字段
     */
    @Excel(name = "ID", cellType = Excel.ColumnType.NUMERIC)
    protected Long id;
    /**
     * 是否使用（1 使用，0 停用）
     */
    @Excel(name = "使用状态", readConverterExp = "true=使用,false=停用")
    protected Boolean use;

    /**
     * 显示顺序
     */
    @Excel(name = "显示顺序")
    protected Integer orderNum;

    /**
     * 创建者
     */
    @Excel(name = "创建者")
    protected String createBy;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date createTime;

    /**
     * 更新者
     */
    @Excel(name = "更新者")
    protected String updateBy;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间", dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date updateTime;

    /**
     * 备注
     */
    protected String remark;

    public BasicDO(@NotNull Boolean use, Integer orderNum, String createBy, Date createTime, String updateBy, Date updateTime, String remark) {
        this.use = use;
        this.orderNum = orderNum;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.remark = remark;
    }

    public BasicDO() {
    }

    public Boolean getUse() {
        return use;
    }

    public void setUse(Boolean use) {
        this.use = use;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BasicDO basicDO = (BasicDO) o;
        return Objects.equals(id, basicDO.id) &&
                Objects.equals(use, basicDO.use) &&
                Objects.equals(orderNum, basicDO.orderNum) &&
                Objects.equals(createBy, basicDO.createBy) &&
                Objects.equals(createTime, basicDO.createTime) &&
                Objects.equals(updateBy, basicDO.updateBy) &&
                Objects.equals(updateTime, basicDO.updateTime) &&
                Objects.equals(remark, basicDO.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, use, orderNum, createBy, createTime, updateBy, updateTime, remark);
    }

    @Override
    public String toString() {
        return "BasicDO{" +
                "id=" + id +
                ", use=" + use +
                ", orderNum=" + orderNum +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
