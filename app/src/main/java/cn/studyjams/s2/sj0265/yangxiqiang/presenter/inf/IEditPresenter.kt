package cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf

import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.DataBean
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IEditModel
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IEditView

/**
 * Created by XQ Yang on 6/3/2017  11:23 AM.
 * Description :
 */
interface IEditPresenter :IPresenter<IEditView, IEditModel>{
	fun saveContent(bean : DataBean?, trim1 : String)
}