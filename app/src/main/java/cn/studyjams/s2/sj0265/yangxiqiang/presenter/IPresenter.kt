package cn.studyjams.s2.sj0265.yangxiqiang.presenter

import cn.studyjams.s2.sj0265.yangxiqiang.model.ITopModel
import cn.studyjams.s2.sj0265.yangxiqiang.view.ITopVIew

/**
 * Created by XQ Yang on 2017/5/18  20:19.
 * Description :
 */
interface IPresenter< VIEW:ITopVIew,MODEL:ITopModel>:ITopPresenter {
	var view :VIEW
	var model:MODEL
}