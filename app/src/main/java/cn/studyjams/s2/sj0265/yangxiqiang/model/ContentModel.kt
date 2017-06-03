package cn.studyjams.s2.sj0265.yangxiqiang.model

import cn.studyjams.s2.sj0265.yangxiqiang.DEFAULT_ENTER_SP_KEY
import cn.studyjams.s2.sj0265.yangxiqiang.method.setString
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.DataBean
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.IContentModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IContentPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase


/**
 * Created by XQ Yang on 5/30/2017  11:42 AM.
 * Description :
 */
class ContentModel(override var presenter : IContentPresenter) :IContentModel , ChildEventListener {
	
	override fun onChildMoved(p0 : DataSnapshot?, p1 : String?) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun onChildChanged(p0 : DataSnapshot?, p1 : String?) {
		val bean = p0?.getValue(DataBean())
		presenter.setData(bean)
	}
	
	override fun onChildAdded(p0 : DataSnapshot?, p1 : String?) {
//		presenter.addData(p0?.getValue(DataBean::class.java))
		val bean = p0?.getValue(DataBean())
		bean?.key = p0?.key
		presenter.addData(bean)
	}
	
	override fun onChildRemoved(p0 : DataSnapshot?) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	
	
	override fun onCancelled(p0 : DatabaseError?) {
	}
	
	override fun setListener() {
		val reference = database.getReference(FirebaseAuth.getInstance().currentUser?.uid)
		reference.addChildEventListener(this)
	}
	
	
	val database: FirebaseDatabase= FirebaseDatabase.getInstance()
	
	override fun saveEnterKey(key : String) {
		setString(presenter.view.context, DEFAULT_ENTER_SP_KEY,key)
	}
	
}


