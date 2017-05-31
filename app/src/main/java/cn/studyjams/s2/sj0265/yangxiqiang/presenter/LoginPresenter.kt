package cn.studyjams.s2.sj0265.yangxiqiang.presenter

import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import cn.studyjams.s2.sj0265.yangxiqiang.model.LoginModel
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.ILoginModel
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.ILoginPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.ContentActivity
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.ILoginView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by XQ Yang on 2017/5/26  20:29.
 * Description :
 */
class LoginPresenter(override var view : ILoginView) :ILoginPresenter {
	override val REQUEST_LOGIN : Int
		get() = 1315
	
	override fun onGoogleLogin() {
		val intent = Auth.GoogleSignInApi.getSignInIntent(gac)
		view.context.startActivityForResult(intent,REQUEST_LOGIN)
	}
	
	var gso : GoogleSignInOptions? =null
	var gac: GoogleApiClient? = null

	private var mAuth : FirebaseAuth? = null
	override var model : ILoginModel
		get() = LoginModel(this)
		set(value) {}
	
	init {
		mAuth = FirebaseAuth.getInstance()
		gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
		gac = GoogleApiClient.Builder(view.context).enableAutoManage(view  as FragmentActivity, view).addApi(Auth.GOOGLE_SIGN_IN_API, gso as GoogleSignInOptions).build()
	}
	
	override fun register(email : String, password : String) {
		mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(view.context, { task ->
			view.showProgress(false)
			if (task.isSuccessful) {
				AlertDialog.Builder(view.context).setTitle("Register success!").setMessage("Please remember your account and password").setPositiveButton("Ok", {
					dialog, which ->
					dialog.dismiss()
					gotoContent()
				}).create().show()
			} else {
				AlertDialog.Builder(view.context).setTitle("Register failure!").setMessage(task.exception?.message).setPositiveButton("Ok", {
					dialog, which ->
					dialog.dismiss()
				}).create().show()
			}
		})
	}
	override fun loginWithEmail(email : String, password : String) {
		mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(view.context, { task ->
			view.showProgress(false)
			if (task.isSuccessful) {
				gotoContent()
			}
		})?.addOnFailureListener(view.context, { exception ->
			AlertDialog.Builder(view.context).setTitle("Login failure!").setMessage(exception.message.toString()).setPositiveButton("Retry", {
				dialog, which ->
				dialog.dismiss()
			}).create().show()
		})
	}
	
	 override fun gotoContent() {
		view.context.startActivity(Intent(view.context,ContentActivity::class.java))
		 view.context.finish()
	}
}