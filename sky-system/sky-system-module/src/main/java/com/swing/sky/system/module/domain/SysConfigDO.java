package com.swing.sky.system.module.domain;

import com.swing.sky.system.module.annotation.Excel;
import com.swing.sky.common.basic.BasicDO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 系统配置表
 * sys_config
 *
 * @author swing
 */
public class SysConfigDO extends BasicDO implements Serializable {

    /**
     * 参数名称
     */
    @Excel(name = "参数名称")
    @NotNull(message = "配置名不为null")
    @Size(min = 1, max = 100, message = "配置名长度应在1~100")
    private String configName;

    /**
     * 参数键名
     */
    @Excel(name = "参数键")
    @NotNull(message = "配置标识不为null")
    @Size(min = 1, max = 100, message = "配置标识长度应在1~100")
    private String configKey;

    /**
     * 参数键值
     */
    @Excel(name = "参数值")
    @NotNull(message = "配置值不为null")
    @Size(min = 1, max = 1000, message = "配置值长度应在1~100")
    private String configValue;

    /**
     * 系统内置（1是 0否）
     */
    @Excel(name = "是否内置", readConverterExp = "true=是,false=否")
    private Boolean inner;

    public SysConfigDO(@NotNull Boolean use, Integer orderNum, String createBy, Date createTime, String updateBy, Date updateTime, String remark, @NotNull(message = "配置名不为null") @Size(min = 1, max = 100, message = "配置名长度应在1~100") String configName, @NotNull(message = "配置标识不为null") @Size(min = 1, max = 100, message = "配置标识长度应在1~100") String configKey, @NotNull(message = "配置值不为null") @Size(min = 1, max = 1000, message = "配置值长度应在1~100") String configValue, Boolean inner) {
        super(use, orderNum, createBy, createTime, updateBy, updateTime, remark);
        this.configName = configName;
        this.configKey = configKey;
        this.configValue = configValue;
        this.inner = inner;
    }

    public SysConfigDO() {
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Boolean getInner() {
        return inner;
    }

    public void setInner(Boolean inner) {
        this.inner = inner;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysConfigDO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SysConfigDO that = (SysConfigDO) o;
        return Objects.equals(configName, that.configName) &&
                Objects.equals(configKey, that.configKey) &&
                Objects.equals(configValue, that.configValue) &&
                Objects.equals(inner, that.inner);
    }

    @Override
    public String toString() {
        return "SysConfigDO{" +
                "configName='" + configName + '\'' +
                ", configKey='" + configKey + '\'' +
                ", configValue='" + configValue + '\'' +
                ", inner=" + inner +
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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), configName, configKey, configValue, inner);
    }
}