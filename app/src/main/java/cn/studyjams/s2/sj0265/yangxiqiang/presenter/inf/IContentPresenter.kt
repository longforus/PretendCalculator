package cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf

import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IContentModel
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IContentView

/**
 * Created by XQ Yang on 5/30/2017  11:05 AM.
 * Description :
 */
interface IContentPresenter:IPresenter<IContentView,IContentModel> {
	fun saveEnterKey(key : String)
}