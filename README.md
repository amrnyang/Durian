# Durian
<p align="center">
<img align="center" src="doc/imgs/logo.png">
<p>

--------------------------------------------------------------------------------
[English](./README.en.md) | 简体中文

[![Documentation Status](https://img.shields.io/badge/中文文档-最新-brightgreen.svg)](http://www.paddlepaddle.org.cn/documentation/docs/zh/1.8/beginners_guide/index_cn.html)
[![Release](https://img.shields.io/github/release/PaddlePaddle/Paddle.svg)](https://github.com/PaddlePaddle/Paddle/releases)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](LICENSE)

## 介绍
榴莲校园,一个Java技术的实践集合，涉及后台管理，权限集合，solr搜索引擎，校园云盘，校园题库，校园二手等多种技术服务。搭建框架，化繁为简，提高你的开发效率，使您专注于业务逻辑的开发。本项目的后台管理模块是基于我的另一个开源项目[Sky](https://gitee.com/qiu-qian/sky.git),除后台管理模块使用Thymeleaf渲染以外，其他模块皆为restful风格的api，使用微信小程序做的前后端分离。对于持久层的代码，为了专注与业务逻辑，我专为项目设计了与之适应的代码生成器[Orange](https://gitee.com/qiu-qian/Orange.git)，支持关联结构(非外键)的代码生成，是你的开发扩展更加快速。


特别声明：本项目可用于学习或毕设，禁止商用！

后台演示地址：

题库小程序演示地址：

文档地址：

## 项目结构
本项目采用模块化的设计，遵循低耦合高内聚的设计思想，可单独部署，一个独立的项目模块（例如榴莲题库模块）是由如下三个模块组成：
* sky-module-tiku (module:持久层和Service接口，此处代码可完全由生成器生成)
* sky-framework-tiku(framewrod:相关框架，例如spring，mybatis等配置)
* sky-api-tiku (api：对外提供api服务)

<p align="center">
<img align="center" src="doc/imgs/durian-module.png" width=900>
<p>
