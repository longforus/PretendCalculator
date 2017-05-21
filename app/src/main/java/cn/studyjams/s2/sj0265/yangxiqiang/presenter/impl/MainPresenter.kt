package cn.studyjams.s2.sj0265.yangxiqiang.presenter.impl

import android.content.Context
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.BtnAdapter
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.OnItemClickListener
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.ViewHolder
import cn.studyjams.s2.sj0265.yangxiqiang.model.IMainModel
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.MainBean
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
	val buildArr = arrayOf(StringBuilder(16),StringBuilder(16),StringBuilder(16))
	init {
//		model = MainModel()
		view.getRv().post({
			val itemHeight = view.getRv().height / 5
			adapter = BtnAdapter(view as Context,model.getMainList(),itemHeight)
			view.setAdapter(adapter as BtnAdapter)
			initListener()
		})
	}
	
	private fun initListener() {
		adapter?.onClickListener = object :OnItemClickListener{
			override fun onItemClick(holder : ViewHolder?, bean : MainBean, position : Int) {
					if (bean.imgId!=-1) onOperatorClick(bean.imgId) else onNumClick(bean.num)
			}
		}
	}
	
	private fun onNumClick(num : Int) {
		append(0,num)
	}
	
	private fun append(pos: Int,num : Number) {
		val build:StringBuilder? = buildArr[pos]
		if (build!=null&&build.length <16) {
			if (build.startsWith("0")) {
				if (!build.contains(".")) {
					build.deleteCharAt(0)
				}
			}
			build.append(num)
			view.showText(pos,build.toString())
		}
	}
	
	private fun onOperatorClick(imgId : Int) {
		when (imgId) {
			R.mipmap.btn_pad_c_n->clearAll()
		}
	}
	
	private fun clearAll() {
		for (i in 0 .. 2) {
			buildArr[i] = StringBuilder(16)
			if (i == 0) {
				buildArr[i].append(0)
			}
			view.showText(i,buildArr[i].toString())
		}
	}
}


