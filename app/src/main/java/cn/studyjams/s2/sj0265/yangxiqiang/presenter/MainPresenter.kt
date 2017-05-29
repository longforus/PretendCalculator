package cn.studyjams.s2.sj0265.yangxiqiang.presenter

import android.content.Context
import android.text.TextUtils
import cn.studyjams.s2.sj0265.yangxiqiang.DERAULT_ENTER_KEY
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.BtnAdapter
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.OnItemClickListener
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.ViewHolder
import cn.studyjams.s2.sj0265.yangxiqiang.model.MainModel
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.MainBean
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IMainModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IMainPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IMainView
import java.math.BigDecimal

/**
 * Created by XQ Yang on 2017/5/18  20:09.
 * Description :
 */
class MainPresenter(override var view : IMainView) : IMainPresenter {

	
	override var model : IMainModel
		get() = MainModel(this)
		set(value) {}
	
	var adapter : BtnAdapter? = null
	var curPos = 0
	var varArr = arrayOf(BigDecimal(0), BigDecimal(0), BigDecimal(0))
	val buildArr = arrayOf(StringBuilder(16), StringBuilder(16), StringBuilder(16))
	var function = 0
	override var enterKey :String? = null
	init {
//		model =
		view.getRv().post({
			buildArr[0].append(0)
			val itemHeight = view.getRv().height / 5
			adapter = BtnAdapter(view as Context, model.getMainList(), itemHeight)
			view.setAdapter(adapter as BtnAdapter)
			initListener()
		})
		enterKey = model.getEnterKey()
		if (TextUtils.isEmpty(enterKey)) {
			enterKey = DERAULT_ENTER_KEY
		}
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
					if (build.isEmpty()) {
						build.append(0)
					}
						build.append(".")
				}
			} else {
				build.append(num)
			}
			
			if (pos == 1) {
				calculate(build)
			}
			view.showText(pos, build.toString())
		}
	}
	
	private fun onOperatorClick(imgId : Int) {
		when (imgId) {
			R.mipmap.btn_pad_c_n -> clearAll()
			R.mipmap.btn_pad_del_n -> delete()
			R.mipmap.btn_pad_div_n -> dispose(3)
			R.mipmap.btn_pad_mul_n -> dispose(2)
			R.mipmap.btn_pad_minus_n -> dispose(1)
			R.mipmap.btn_pad_plus_n -> dispose(0)
			R.mipmap.btn_pad_percent_n -> disposePercent()
		}
	}
	
	private fun disposePercent() {
		if (curPos == 0) {
			val substring = buildArr[0].toString().trim()
			varArr[0] = BigDecimal(substring)
			varArr[0] = varArr[0].divide(BigDecimal(100),10, BigDecimal.ROUND_DOWN)
			buildArr[0] = StringBuilder(varArr[0].toString())
			while (buildArr[0].endsWith("0")||buildArr[0].endsWith(".")) {
				buildArr[0].deleteCharAt(buildArr[0].length-1)
			}
			view.showText(0, buildArr[0].toString())
		}
	}
	
	private var curOperator : String = ""
	
	private fun dispose(s : Int) {
		if (!curOperator.isEmpty()) {
			return
		}
		function = s
		varArr[0] = BigDecimal(buildArr[0].toString())
		view.showText(2, buildArr[0].toString())
		when (function) {
			0 -> {//+
				curOperator = "+"
			}
			1 -> {//-
				curOperator = "-"
			}
			2 -> {//*
				curOperator = "x"
			}
			3 -> {///
				curOperator = "÷"
			}
		}
		buildArr[1].append(curOperator)
		view.showText(1, buildArr[1].toString())
		curPos = 1
		buildArr[2].append("=")
		buildArr[2].append(buildArr[0])
		view.showText(0, buildArr[2].toString())
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
			if (curPos == 1) {
				if (builder.length > 1) {
					calculate(builder)
				} else {
					view.showText(0, "="+buildArr[0].toString().trim())
				}
			}
			if (builder.isEmpty()) {
				if (curPos==1) {
					builder.append(curOperator)
				} else {
					builder.append(0)
				}
			}
			view.showText(curPos, builder.toString())
		}
	}
	
	/**
	 * 真正的进行计算
	 */
	private fun calculate(builder : StringBuilder) {
		val substring = builder.toString().substring(1, builder.length).trim()
		when (function) {
			0 -> {//+
				varArr[2] = varArr[0].add(BigDecimal(substring))
			}
			1 -> {//-
				varArr[2] = varArr[0].subtract(BigDecimal(substring))
			}
			2 -> {//*
				varArr[2] = varArr[0].multiply(BigDecimal(substring))
			}
			3 -> {///
				varArr[2] = varArr[0].divide(BigDecimal(substring), 10, BigDecimal.ROUND_DOWN)
			}
		}
		buildArr[2] = StringBuilder("=").append(varArr[2])
		while (/*buildArr[2].endsWith("0") ||*/ buildArr[2].endsWith(".")) {
			buildArr[2].deleteCharAt(buildArr[2].length - 1)
		}
		view.showText(0, buildArr[2].toString())
	}
	
	private fun clearAll() {
		for (i in 0 .. 2) {
			buildArr[i] = StringBuilder(16)
			if (i == 0) {
				buildArr[i].append(0)
			}
			view.showText(i, buildArr[i].toString())
		}
		curOperator = ""
		function = 0
		curPos = 0
	}
}


