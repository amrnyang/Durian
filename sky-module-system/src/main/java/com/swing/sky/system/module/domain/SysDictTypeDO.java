package com.swing.sky.system.module.domain;

import com.swing.sky.common.annotation.Excel;
import com.swing.sky.common.basic.BasicDO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * sys_dict_type
 * 字典类型
 *
 * @author swing
 */
public class SysDictTypeDO extends BasicDO implements Serializable {

    /**
     * 类型名称
     */
    @Excel(name = "类型名称")
    @NotNull(message = "字典类型名称不为null")
    @Size(min = 1, max = 100, message = "字典类型名称的长度范围在1~100")
    private String typeName;

    /**
     * 类型标识
     */
    @Excel(name = "类型标识")
    @NotNull(message = "字典类型标识不为null")
    @Size(min = 1, max = 100, message = "字典类型标识的长度范围在1~100")
    private String typeSign;

    public SysDictTypeDO(@NotNull Boolean use, Integer orderNum, String createBy, Date createTime, String updateBy, Date updateTime, String remark, @NotNull(message = "字典类型名称不为null") @Size(min = 1, max = 100, message = "字典类型名称的长度范围在1~100") String typeName, @NotNull(message = "字典类型标识不为null") @Size(min = 1, max = 100, message = "字典类型标识的长度范围在1~100") String typeSign) {
        super(use, orderNum, createBy, createTime, updateBy, updateTime, remark);
        this.typeName = typeName;
        this.typeSign = typeSign;
    }

    public SysDictTypeDO() {
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeSign() {
        return typeSign;
    }

    public void setTypeSign(String typeSign) {
        this.typeSign = typeSign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysDictTypeDO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SysDictTypeDO that = (SysDictTypeDO) o;
        return Objects.equals(typeName, that.typeName) &&
                Objects.equals(typeSign, that.typeSign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), typeName, typeSign);
    }

    @Override
    public String toString() {
        return "SysDictTypeDO{" +
                "typeName='" + typeName + '\'' +
                ", typeSign='" + typeSign + '\'' +
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