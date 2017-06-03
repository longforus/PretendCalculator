package cn.studyjams.s2.sj0265.yangxiqiang.model

import cn.studyjams.s2.sj0265.yangxiqiang.DEFAULT_ENTER_SP_KEY
import cn.studyjams.s2.sj0265.yangxiqiang.method.setString
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IContentModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IContentPresenter

/**
 * Created by XQ Yang on 5/30/2017  11:42 AM.
 * Description :
 */
class ContentModel(override var presenter : IContentPresenter) :IContentModel {
	override fun saveEnterKey(key : String) {
		setString(presenter.view.context, DEFAULT_ENTER_SP_KEY,key)
	}
}