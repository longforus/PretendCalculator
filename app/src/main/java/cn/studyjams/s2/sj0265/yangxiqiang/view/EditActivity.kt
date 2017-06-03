package cn.studyjams.s2.sj0265.yangxiqiang.view

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.DataBean
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.EditPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IEditPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IEditView
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity(),IEditView {
	override val context : Activity
		get() = this
	override var presenter : IEditPresenter?
		get() = EditPresenter(this)
		set(value) {}
	var bean:DataBean? = null
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_edit)
		fab_edit.setOnClickListener {
				presenter?.saveContent(bean,et_edit.text.toString().trim())
		}
		bean =  intent.getSerializableExtra("bean") as DataBean
		if (bean != null) {
			et_edit.setText(bean?.content)
			et_edit.setSelection(bean?.content?.length as Int)
		}
	}
}
