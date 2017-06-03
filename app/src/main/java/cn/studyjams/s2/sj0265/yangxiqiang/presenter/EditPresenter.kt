package cn.studyjams.s2.sj0265.yangxiqiang.presenter

import cn.studyjams.s2.sj0265.yangxiqiang.model.EditModel
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IEditModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IEditPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IEditView

/**
 * Created by XQ Yang on 6/3/2017  11:26 AM.
 * Description :
 */
class EditPresenter(override var view : IEditView) :IEditPresenter {
	override var model : IEditModel
		get() = EditModel(this)
		set(value) {}
}