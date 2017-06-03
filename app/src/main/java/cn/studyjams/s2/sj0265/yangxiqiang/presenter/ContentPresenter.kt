package cn.studyjams.s2.sj0265.yangxiqiang.presenter

import cn.studyjams.s2.sj0265.yangxiqiang.method.empty
import cn.studyjams.s2.sj0265.yangxiqiang.model.ContentModel
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IContentModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IContentPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IContentView

/**
 * Created by XQ Yang on 5/30/2017  11:41 AM.
 * Description :
 */
class ContentPresenter(override var view : IContentView) :IContentPresenter{
	override fun saveEnterKey(key : String) {
		if (!key.empty()) {
			model.saveEnterKey(key)
			view.showSaveSuccess()
		}
	}
	
	override var model : IContentModel
		get() = ContentModel(this)
		set(value) {}
}