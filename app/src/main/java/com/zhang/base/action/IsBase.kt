package com.zhang.base.action

/**
 * @Author : zhang
 * @Create Time : 2021/11/24
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
interface IsBase {
    /**
     * 是否需要使用带有TitleBar的父容器
     */
    fun isLayoutToolbar(): Boolean = true

}