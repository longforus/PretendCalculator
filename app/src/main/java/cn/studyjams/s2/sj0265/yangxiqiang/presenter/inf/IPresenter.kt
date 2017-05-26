package cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf

import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.ITopModel

/**
 * Created by XQ Yang on 2017/5/18  20:19.
 * Description :
 */
interface IPresenter< VIEW: cn.studyjams.s2.sj0265.yangxiqiang.view.inf.ITopVIew,MODEL: ITopModel>: ITopPresenter {
	var view :VIEW
	var model:MODEL
}