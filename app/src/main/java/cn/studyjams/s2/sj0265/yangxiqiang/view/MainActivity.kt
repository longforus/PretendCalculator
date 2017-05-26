package cn.studyjams.s2.sj0265.yangxiqiang.view

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.TextView
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.BtnAdapter
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IMainPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.MainPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IMainView

class MainActivity : AppCompatActivity(), IMainView {
	override val context : Activity
		get() = this
	
	var tvArr : Array<TextView>? = null
	
	override fun showText(pos : Int, toString : String) {
		tvArr?.get(pos)?.text = toString
	}
	
	override fun getRv() : RecyclerView {
		return findViewById(R.id.rv_btns) as RecyclerView
	}
	
	override fun setAdapter(adapter : BtnAdapter) {
		kotlinx.android.synthetic.main.activity_main.rv_btns.adapter = adapter
		kotlinx.android.synthetic.main.activity_main.rv_btns.layoutManager = StaggeredGridLayoutManager(4, GridLayoutManager.VERTICAL)
	}
	
	override var presenter : IMainPresenter
		get() = presenter
		set(value) {}
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		presenter = MainPresenter(this@MainActivity)
		tvArr = arrayOf(kotlinx.android.synthetic.main.activity_main.tv1, kotlinx.android.synthetic.main.activity_main.tv2, kotlinx.android.synthetic.main.activity_main.tv3)
	
	}
	
}
