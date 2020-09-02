# Durian
<p align="center">
<img align="center" src="doc/imgs/logo.png">
<p>

--------------------------------------------------------------------------------

[English](./README.en.md) | 简体中文

[![Documentation Status](https://img.shields.io/badge/中文文档-最新-brightgreen.svg)](https://gitee.com/qiu-qian/Durian/wikis/pages)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](LICENSE)

## 介绍 <img img align="center" src="doc/imgs/introduction.png"/>

 榴莲校园，一个专注于Java技术的实践集合，励志为校园提供一套便捷的技术服务。
 
 涉及后台管理，多级鉴权，单点登录，solr引擎全文检索，校园题库，校园云盘，校园二手等多种技术服务。搭建框架，化繁为简，提高你的开发效率，使您专注于业务逻辑的开发。
 
 本项目的后台管理模块是基于我的另一个开源项目<img img align="center" src="doc/imgs/sky-logo.png"/>[Sky](https://gitee.com/qiu-qian/sky.git)，除后台管理模块使用Thymeleaf渲染以外，其他模块皆为restful风格的api，使用微信小程序做的前后端分离。为了专注与业务逻辑，对于持久层的代码，我专为项目设计了与之适应的代码生成器<img img align="center" src="doc/imgs/orange-logo.png"/>[Orange](https://gitee.com/qiu-qian/Orange.git)，支持关联结构(非外键)的代码生成，使您的二次开发更加快速。

 详细信息，请参考项目开发文档：[Durian开发文档](https://gitee.com/qiu-qian/Durian/wikis/pages)


<b>特别声明：本项目可用于学习或毕设，（如需商用，请联系我） ! ! !</b>

后台演示地址：[https://qiu-qian.top](https://qiu-qian.top:8085/)

题库小程序演示地址：

文档地址： [Durian开发文档](https://gitee.com/qiu-qian/Durian/wikis/pages?sort_id=2720449&doc_id=892234)

后台仓库地址：[https://gitee.com/qiu-qian/Durian](https://gitee.com/qiu-qian/Durian)

小程序仓库地址：[https://gitee.com/qiu-qian/durian-mini](https://gitee.com/qiu-qian/durian-mini)

## 预览 <img img align="center" src="doc/imgs/preview.png" width=25/>

### 后台预览
<table>
<tr> 
<td><img src="doc/imgs/show1.jpg"></td>
<td><img src="doc/imgs/show2.jpg"></td>
</tr>
<tr> 
<td><img src="doc/imgs/show3.jpg"></td>
<td><img src="doc/imgs/show6.jpg"></td>
</tr>
<tr> 
<td><img src="doc/imgs/show4.jpg"></td>
<td><img src="doc/imgs/show5.jpg"></td>
</tr>
</table>

### 小程序预览

<table>
<tr> 
<td><img src="doc/imgs/mini1.jpg"></td>
<td><img src="doc/imgs/mini2.jpg"></td>
<td><img src="doc/imgs/mini3.jpg"></td>
</tr>
</table>


## 项目结构 <img img align="center" src="doc/imgs/structure.png"/>

项目采用模块化的设计，遵循低耦合高内聚的设计思想，各个模块可单独部署，一个独立的项目模块（例如榴莲题库模块）是由如下三个子模块组成：

<table align="center">
<tr>
<td>sky-module-tiku</td>
<td>持久层和Service接口，此处代码可完全由生成器生成</td>
</tr>
<tr>
<td>sky-framework-tiku</td>
<td>相关框架，例如spring，mybatis等配置</td>
</tr
><tr>
<td>sky-api-tiku</td>
<td>对外提供api服务</td>
</tr>
</table>

<p align="center">
<img align="center" src="doc/imgs/durian-module.jpg" width=900>
<p>

### Solr搜索模型

<p align="center">
<img align="center" src="doc/imgs/solr.png" width=900>
<p>

## 功能介绍

<p align="center">
<img align="center" src="doc/imgs/function.png" width=900>
<p>


## 捐赠 <img img align="center" src="doc/imgs/reward.png" width=25/>
多多支持多多star<img img align="center" src="doc/imgs/star.png" width=20/>!!!


开源不易，演示服务器每个月还要缴费，可以请作者喝一瓶哇哈哈：

<div align="center">
<img src="doc/imgs/pay.png"  width="200px" height="200px" />
</div>