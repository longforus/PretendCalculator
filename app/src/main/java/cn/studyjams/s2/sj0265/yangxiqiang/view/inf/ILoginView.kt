package cn.studyjams.s2.sj0265.yangxiqiang.view.inf

import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.ILoginPresenter

/**
 * Created by XQ Yang on 2017/5/26  20:20.
 * Description :
 */
interface ILoginView:IVIew<ILoginPresenter> {
	  fun  showProgress(show : Boolean)
}