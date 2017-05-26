package cn.studyjams.s2.sj0265.yangxiqiang.model.inf

/**
 * Created by XQ Yang on 2017/5/26  19:57.
 * Description :
 */
interface IModel<P: cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.ITopPresenter>: ITopModel {
	var presenter:P
}