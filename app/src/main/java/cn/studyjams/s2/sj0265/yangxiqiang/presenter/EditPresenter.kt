package cn.studyjams.s2.sj0265.yangxiqiang.presenter

import cn.studyjams.s2.sj0265.yangxiqiang.method.empty
import cn.studyjams.s2.sj0265.yangxiqiang.model.EditModel
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.DataBean
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IEditModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IEditPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.IEditView
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import java.util.*

/**
 * Created by XQ Yang on 6/3/2017  11:26 AM.
 * Description :
 */
class EditPresenter(override var view : IEditView) :IEditPresenter , DatabaseReference.CompletionListener{
	
	override fun deleteContent(bean : DataBean?) {
		 model.remove(bean)
	}
	
	override fun saveContent(bean : DataBean?, trim : String) {
		if (!trim.empty()) {
			if (bean==null) {
				model.saveContent(DataBean(trim,Date()))
			} else {
				bean.content = trim
				model.modify(bean)
			}
		}
	}
	
	override fun onComplete(p0 : DatabaseError?, p1 : DatabaseReference?) {
		view.finish()
	}
	
	
	override var model : IEditModel
		get() = EditModel(this)
		set(value) {}
}