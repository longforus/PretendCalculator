package cn.studyjams.s2.sj0265.yangxiqiang.presenter.impl

import android.content.Context
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.BtnAdapter
import cn.studyjams.s2.sj0265.yangxiqiang.model.IMainModel
import cn.studyjams.s2.sj0265.yangxiqiang.model.impl.MainModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.IMainPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.IMainView

/**
 * Created by XQ Yang on 2017/5/18  20:09.
 * Description :
 */
class MainPresenter(override var view : IMainView) :IMainPresenter{
	override var model : IMainModel
		get() =   MainModel()
		set(value) {}
	
	var adapter:BtnAdapter? = null
	init {
//		model = MainModel()
		view.getRv().post({
			val itemHeight = view.getRv().height / 5
			adapter = BtnAdapter(view as Context,model.getMainList(),itemHeight)
			view.setAdapter(adapter as BtnAdapter)
		})
		
	}
}