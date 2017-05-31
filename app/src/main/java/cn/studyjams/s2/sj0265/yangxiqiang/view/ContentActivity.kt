package cn.studyjams.s2.sj0265.yangxiqiang.view

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.widget.Toast
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.ContentPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IContentPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IContentView
import kotlinx.android.synthetic.main.activity_content.*



class ContentActivity : AppCompatActivity(),IContentView{
	override val context : Activity
		get() = this
	override var presenter : IContentPresenter?
		get() = ContentPresenter(this)
		set(value) {}
	private var exitTime : Long = 0
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_content)
		tb.setTitle("Secret")
		setSupportActionBar(tb)
		tb.setNavigationIcon(R.mipmap.ic_chevron_left)
		
	}
	
	override fun onCreateOptionsMenu(menu : Menu?) : Boolean {
		menuInflater.inflate(R.menu.menu_content,menu)
		return true
	}
	
	override fun onKeyDown(keyCode : Int, event : KeyEvent) : Boolean {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			if (System.currentTimeMillis() - exitTime > 2000) {
				Toast.makeText(context, "Again according to exit the program", Toast.LENGTH_SHORT).show()
				exitTime = System.currentTimeMillis()
			} else {
				finish()
			}
			return true
		}
		return super.onKeyDown(keyCode, event)
	}
}
