package cn.studyjams.s2.sj0265.yangxiqiang.view.impl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.IMainPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.impl.MainPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.IMainView

class MainActivity : AppCompatActivity(), IMainView {
	 var presenter: IMainPresenter? = null
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		presenter   = 	MainPresenter(this@MainActivity)
	}
	
}
