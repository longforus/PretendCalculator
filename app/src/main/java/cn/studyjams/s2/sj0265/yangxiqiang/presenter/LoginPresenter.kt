package cn.studyjams.s2.sj0265.yangxiqiang.presenter

import cn.studyjams.s2.sj0265.yangxiqiang.model.LoginModel
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.ILoginModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.ILoginPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.ILoginView

/**
 * Created by XQ Yang on 2017/5/26  20:29.
 * Description :
 */
class LoginPresenter(override var view : ILoginView) :ILoginPresenter {
	override var model : ILoginModel
		get() = LoginModel(this)
		set(value) {}
	
	
}