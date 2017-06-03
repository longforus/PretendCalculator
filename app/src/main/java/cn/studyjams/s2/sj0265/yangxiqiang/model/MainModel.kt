package cn.studyjams.s2.sj0265.yangxiqiang.model

import android.graphics.Color
import cn.studyjams.s2.sj0265.yangxiqiang.DEFAULT_ENTER_SP_KEY
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.method.getString
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.MainBean
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IMainModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IMainPresenter

/**
 * Created by XQ Yang on 2017/5/18  20:10.
 * Description :
 */
class MainModel(override var presenter : IMainPresenter) : IMainModel {
	override fun getEnterKey() : String? {
		return getString(presenter.view.context, DEFAULT_ENTER_SP_KEY)
	}
	
	override fun getMainList() : List<MainBean> {
		var list : List<MainBean> = listOf()
		list += MainBean(R.mipmap.btn_pad_c_n, -1)
		list += MainBean(R.mipmap.btn_pad_del_n, -1)
		list += MainBean(R.mipmap.btn_pad_div_n, -1)
		list += MainBean(R.mipmap.btn_pad_mul_n, -1)
		list += MainBean(-1, 7)
		list += MainBean(-1, 8)
		list += MainBean(-1, 9)
		list += MainBean(R.mipmap.btn_pad_minus_n, -1)
		list += MainBean(-1, 4)
		list += MainBean(-1, 5)
		list += MainBean(-1, 6)
		list += MainBean(R.mipmap.btn_pad_plus_n, -1)
		list += MainBean(-1, 1)
		list += MainBean(-1, 2)
		list += MainBean(-1, 3)
		val bean = MainBean(R.mipmap.btn_pad_equal_n, -1)
		bean.backColor = Color.RED
		list += bean
		list += MainBean(R.mipmap.btn_pad_percent_n, -1)
		list += MainBean(-1, 0)
		list += MainBean(-1, -130)
		return list
	}
	
	
}