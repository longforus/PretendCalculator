package cn.studyjams.s2.sj0265.yangxiqiang.model

import cn.studyjams.s2.sj0265.yangxiqiang.method.empty
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.DataBean
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IEditModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IEditPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

/**
 * Created by XQ Yang on 6/3/2017  11:27 AM.
 * Description :
 */
class EditModel(override var presenter : IEditPresenter) : IEditModel {
	
	override fun remove(bean : DataBean?) {
		if (!(bean?.key?.empty())!!) {
			try {
				database.getReference("/"+FirebaseAuth.getInstance().currentUser?.uid+"/"+bean.key).removeValue()
			} catch(e : Exception) {
				e.printStackTrace()
			}
		}
	}
	
	val database : FirebaseDatabase = FirebaseDatabase.getInstance()
	val reference = database.getReference(FirebaseAuth.getInstance().currentUser?.uid)
	
	override fun modify(bean : DataBean) {
		if (!(bean.key?.empty())!!) {
			bean.date = Date()
			val map = HashMap<String, DataBean>()
			map.put(bean.key as String, bean)
			reference.updateChildren(map as Map<String, Any>?,presenter as DatabaseReference.CompletionListener)
		}
	}
	
	override fun saveContent(trim : DataBean) {
		val push = reference.push()
		push.setValue(trim, presenter as DatabaseReference.CompletionListener)
	}
}