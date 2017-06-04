package cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf

import cn.studyjams.s2.sj0265.yangxiqiang.adapter.BaseAdapter
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.ContentAdapter
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.DataBean
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IContentModel
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IContentView

/**
 * Created by XQ Yang on 5/30/2017  11:05 AM.
 * Description :
 */
interface IContentPresenter:IPresenter<IContentView,IContentModel> {
	fun saveEnterKey(key : String)
	fun getAdapter() : BaseAdapter<DataBean,ContentAdapter.ViewHolder>
	fun addData(value : DataBean?)
	fun setData(bean : DataBean?)
	fun  upDatePassword(trim : String, trim1 : String):Boolean
	fun remove(bean : DataBean?)
}