package cn.studyjams.s2.sj0265.yangxiqiang.view

import cn.studyjams.s2.sj0265.yangxiqiang.presenter.ITopPresenter

/**
 * Created by XQ Yang on 2017/5/18  20:18.
 * Description :
 */
interface IVIew<P:ITopPresenter>:ITopVIew {
	var presenter:P
}