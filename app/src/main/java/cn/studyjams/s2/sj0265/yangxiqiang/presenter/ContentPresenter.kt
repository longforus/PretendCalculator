package cn.studyjams.s2.sj0265.yangxiqiang.presenter

import android.content.Intent
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.BaseAdapter
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.ContentAdapter
import cn.studyjams.s2.sj0265.yangxiqiang.method.empty
import cn.studyjams.s2.sj0265.yangxiqiang.model.ContentModel
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.DataBean
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IContentModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IContentPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.EditActivity
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IContentView
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by XQ Yang on 5/30/2017  11:41 AM.
 * Description :
 */
class ContentPresenter(override var view : IContentView) : IContentPresenter {
	override fun remove(bean : DataBean?) {
		if (bean != null) {
			adapter.remove(bean)
		}
	}
	
	override fun upDatePassword(trim : String, trim1 : String) : Boolean {
		if (!trim.empty() && trim == trim1) {
			if (trim.matches(Regex("^[a-zA-Z]\\w{5,17}$"))) {
				FirebaseAuth.getInstance().currentUser?.updatePassword(trim)?.addOnCompleteListener { task ->
					if (task.isSuccessful) {
						view.showSnackBar("Modify Success")
					} else {
						view.showSnackBar("Modify Failure")
					} }
			} else {
				view.showSnackBar("Begin with a letter, the length between 6 ~ 18, can only contain a-z, 0-9, and _")
				return false
			}
		} else {
			view.showSnackBar("Two different input")
			return false
		}
		return true
	}
	
	override fun setData(bean : DataBean?) {
		if (bean != null) {
			adapter.addData(bean)
		}
	}
	
	override fun addData(value : DataBean?) {
		if (value != null) {
			view.showRv()
			adapter.addData(value)
		}
	}
	
	val adapter = ContentAdapter(view.context)
	
	init {
		model.setListener()
		adapter.onClickListener = object : BaseAdapter.OnItemClickListener<DataBean, ContentAdapter.ViewHolder> {
			override fun onItemClick(holder : ContentAdapter.ViewHolder?, bean : DataBean, position : Int) {
				view.context.startActivity(Intent(view.context, EditActivity::class.java).putExtra("bean", bean))
			}
		}
	}
	
	override fun getAdapter() : BaseAdapter<DataBean, ContentAdapter.ViewHolder> {
		return adapter
	}
	
	
	override fun saveEnterKey(key : String) {
		if (!key.empty()) {
			model.saveEnterKey(if (key.length > 16) key.substring(0, 16) else key)
			view.showSnackBar("Enter key save success,Remember it")
		}
	}
	
	override var model : IContentModel
		get() = ContentModel(this)
		set(value) {}
}