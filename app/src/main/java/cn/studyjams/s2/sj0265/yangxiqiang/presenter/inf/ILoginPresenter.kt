package cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf

import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.ILoginModel
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.ILoginView

/**
 * Created by XQ Yang on 2017/5/26  20:20.
 * Description :
 */
interface ILoginPresenter:IPresenter<ILoginView,ILoginModel> {
	fun register(email : String, password : String)
	fun loginWithEmail(email : String, password : String)
}