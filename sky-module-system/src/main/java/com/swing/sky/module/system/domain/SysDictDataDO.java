package com.swing.sky.module.system.domain;

import com.swing.sky.common.annotation.Excel;
import com.swing.sky.common.basic.BasicDO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * sys_dict_data
 * 字典数据
 *
 * @author swing
 */
public class SysDictDataDO extends BasicDO implements Serializable {

    /**
     * 字典类型
     */
    @Excel(name = "类型ID")
    private Long typeId;

    /**
     * 字典键
     */
    @Excel(name = "字典键")
    @NotNull(message = "字典标签不为null")
    @Size(min = 1, max = 100, message = "字典数据标签的长度范围在1~100")
    private String dataKey;


    /**
     * 字典值
     */
    @Excel(name = "字典值")
    @NotNull(message = "字典值不为null")
    @Size(min = 1, max = 1000, message = "字典值的长度范围在1~1000")
    private String dataValue;

    /**
     * 样式属性（其他样式扩展）
     */
    @Excel(name = "其他样式扩展")
    private String cssClass;

    /**
     * 表格回显样式
     */
    @Excel(name = "表格回显样式")
    private String listClass;

    /**
     * 是否默认（1是 0否）
     */
    @Excel(name = "类型ID", readConverterExp = "true=是,false=否")
    private Boolean isDefault;

    public SysDictDataDO(@NotNull Boolean use, Integer orderNum, String createBy, Date createTime, String updateBy, Date updateTime, String remark, @NotNull(message = "字典类型不为null") Long typeId, @NotNull(message = "字典标签不为null") @Size(min = 1, max = 100, message = "字典数据标签的长度范围在1~100") String dataKey, @NotNull(message = "字典值不为null") @Size(min = 1, max = 1000, message = "字典值的长度范围在1~1000") String dataValue, String cssClass, String listClass, Boolean isDefault) {
        super(use, orderNum, createBy, createTime, updateBy, updateTime, remark);
        this.typeId = typeId;
        this.dataKey = dataKey;
        this.dataValue = dataValue;
        this.cssClass = cssClass;
        this.listClass = listClass;
        this.isDefault = isDefault;
    }

    public SysDictDataDO() {
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getListClass() {
        return listClass;
    }

    public void setListClass(String listClass) {
        this.listClass = listClass;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysDictDataDO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SysDictDataDO that = (SysDictDataDO) o;
        return Objects.equals(typeId, that.typeId) &&
                Objects.equals(dataKey, that.dataKey) &&
                Objects.equals(dataValue, that.dataValue) &&
                Objects.equals(cssClass, that.cssClass) &&
                Objects.equals(listClass, that.listClass) &&
                Objects.equals(isDefault, that.isDefault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), typeId, dataKey, dataValue, cssClass, listClass, isDefault);
    }

    @Override
    public String toString() {
        return "SysDictDataDO{" +
                "typeId=" + typeId +
                ", dataKey='" + dataKey + '\'' +
                ", dataValue='" + dataValue + '\'' +
                ", cssClass='" + cssClass + '\'' +
                ", listClass='" + listClass + '\'' +
                ", isDefault=" + isDefault +
                ", id=" + id +
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