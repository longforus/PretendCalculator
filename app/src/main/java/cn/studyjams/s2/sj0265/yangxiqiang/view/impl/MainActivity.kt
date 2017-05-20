package cn.studyjams.s2.sj0265.yangxiqiang.view.impl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.BtnAdapter
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.IMainPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.impl.MainPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView {
	override fun setAdapter(adapter : BtnAdapter) {
		rv_btns.adapter = adapter
		rv_btns.layoutManager = GridLayoutManager(this,4,GridLayoutManager.VERTICAL,false)
	}
	
	override var presenter : IMainPresenter
		get() = presenter
		set(value) {}
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		presenter   = 	MainPresenter(this@MainActivity)
	}
	
}
