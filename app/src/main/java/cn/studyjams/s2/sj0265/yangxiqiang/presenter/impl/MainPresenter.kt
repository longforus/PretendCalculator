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
import java.math.BigDecimal

/**
 * Created by XQ Yang on 2017/5/18  20:09.
 * Description :
 */
class MainPresenter(override var view : IMainView) : IMainPresenter {
	override var model : IMainModel
		get() = MainModel()
		set(value) {}
	
	var adapter : BtnAdapter? = null
	var curPos = 0
	var varArr = arrayOf(BigDecimal(0), BigDecimal(0), BigDecimal(0))
	val buildArr = arrayOf(StringBuilder(16), StringBuilder(16), StringBuilder(16))
	var function = 0
	
	init {
//		model = MainModel()
		view.getRv().post({
			buildArr[0].append(0)
			val itemHeight = view.getRv().height / 5
			adapter = BtnAdapter(view as Context, model.getMainList(), itemHeight)
			view.setAdapter(adapter as BtnAdapter)
			initListener()
		})
	}
	
	private fun initListener() {
		adapter?.onClickListener = object : OnItemClickListener {
			override fun onItemClick(holder : ViewHolder?, bean : MainBean, position : Int) {
				if (bean.imgId != -1) onOperatorClick(bean.imgId) else onNumClick(bean.num)
			}
		}
	}
	
	private fun onNumClick(num : Int) {
		append(curPos, num)
	}
	
	private fun append(pos : Int, num : Number) {
		val build : StringBuilder? = buildArr[pos]
		
		if (build != null && build.length < 16) {
			if (build.startsWith("0")) {
				if (!build.contains(".")) {
					build.deleteCharAt(0)
				}
			}
			if (num == -130) {
				if (!build.contains(".")) {
					build.append(".")
				}
			} else {
				build.append(num)
			}
			
			if (pos == 1) {
				when (function) {
					0 -> {//+
					
					}
					1 -> {//-
					
					}
					2 -> {//*
					
					}
					3 -> {///
						var ca = kotlin.CharArray(16)
						build.getChars(1, build.length, ca, 0)
						varArr[2] = varArr[0].divide(BigDecimal(String(ca).trim()))
						buildArr[2] = StringBuilder("=").append(varArr[2])
						view.showText(0, buildArr[2].toString())
					}
				}
			}
			view.showText(pos, build.toString())
		}
	}
	
	private fun onOperatorClick(imgId : Int) {
		when (imgId) {
			R.mipmap.btn_pad_c_n -> clearAll()
			R.mipmap.btn_pad_del_n -> delete()
			R.mipmap.btn_pad_div_n -> div()
		}
	}
	
	private fun div() {
		varArr[0] = BigDecimal(buildArr[0].toString())
		view.showText(2, buildArr[0].toString())
		buildArr[1].append("÷")
		view.showText(1, buildArr[1].toString())
		curPos = 1
		function = 3
		buildArr[2].append("=")
		buildArr[2].append(buildArr[0])
		view.showText(0, buildArr[2].toString())
	}
	
	private fun delete() {
		if (curPos != 2) {
			val builder = buildArr[curPos]
			builder.deleteCharAt(builder.length - 1)
			if (builder.isEmpty()) {
				builder.append(0)
			}
			view.showText(curPos, builder.toString())
		}
	}
	
	private fun clearAll() {
		for (i in 0 .. 2) {
			buildArr[i] = StringBuilder(16)
			if (i == 0) {
				buildArr[i].append(0)
			}
			view.showText(i, buildArr[i].toString())
		}
		function = 0
		curPos = 0
	}
}


