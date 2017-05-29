package cn.studyjams.s2.sj0265.yangxiqiang.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.LoginPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.ILoginPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.ILoginView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*



/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(), ILoginView {
	override var presenter : ILoginPresenter? = null
	override val context : Activity
		get() = this
	
	// UI references.
	private var mEmailView : AutoCompleteTextView? = null
	private var mPasswordView : EditText? = null
	private var mProgressView : View? = null
	private var mLoginFormView : View? = null
	private var mAuth : FirebaseAuth? = null
	
	private val onEditorActionListener = TextView.OnEditorActionListener { textView, id, keyEvent ->
		if (id == EditorInfo.IME_NULL) {
			attemptLogin()
			return@OnEditorActionListener true
		}
		false
	}
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)
		// Set up the login form.
		presenter = LoginPresenter(this)
		mEmailView = findViewById(R.id.email) as AutoCompleteTextView
		mPasswordView = findViewById(R.id.password) as EditText
		mPasswordView!!.setOnEditorActionListener(onEditorActionListener)
		password1.setOnEditorActionListener(onEditorActionListener)
		val mEmailSignInButton = findViewById(R.id.email_sign_in_button) as Button
		mEmailSignInButton.setOnClickListener { attemptLogin() }
		email_register_button.setOnClickListener { attemptRegister() }
		mLoginFormView = findViewById(R.id.login_form)
		mProgressView = findViewById(R.id.login_progress)
		til.isEnabled = false
		mAuth = FirebaseAuth.getInstance()
	
	}
	
	private fun attemptRegister() {
		if (!til.isEnabled) {
			til.isEnabled = true
			til.visibility = View.VISIBLE
			mPasswordView?.imeOptions = EditorInfo.IME_ACTION_NEXT
			mPasswordView?.setOnEditorActionListener(null)
			email_sign_in_button.setText(getString(R.string.action_register))
			email_register_button.setText("Return")
		} else {
			til.isEnabled = false
			til.visibility = View.GONE
			mPasswordView?.imeOptions = EditorInfo.IME_ACTION_UNSPECIFIED
			mPasswordView?.setOnEditorActionListener(onEditorActionListener)
			email_sign_in_button.setText(getString(R.string.action_sign_in))
			email_register_button.setText(getString(R.string.action_register))
		}
	}
	
	private fun register(email : String, password : String) {
		mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(this, { task ->
			showProgress(false)
			if (task.isSuccessful) {
				AlertDialog.Builder(this).setTitle("Register success!").setMessage("Please remember your account and password").setPositiveButton("Ok", {
					dialog, which ->
					dialog.dismiss()
					gotoContent()
				}).create().show()
			} else {
				AlertDialog.Builder(this).setTitle("Register failure!").setMessage(task.exception?.message).setPositiveButton("Ok", {
					dialog, which ->
					dialog.dismiss()
				}).create().show()
			}
		})
	}
	
	
	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	private fun attemptLogin() {
		// Reset errors.
		mEmailView!!.error = null
		mPasswordView!!.error = null
		
		// Store values at the time of the login attempt.
		val email = mEmailView!!.text.toString()
		val password = mPasswordView!!.text.toString()
		
		var cancel = false
		var focusView : View? = null
		
		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
			mPasswordView!!.error = getString(R.string.error_invalid_password)
			focusView = mPasswordView
			cancel = true
		}
		
		// Check for a valid email address.
		if (TextUtils.isEmpty(email)) {
			mEmailView!!.error = getString(R.string.error_field_required)
			focusView = mEmailView
			cancel = true
		} else if (!isEmailValid(email)) {
			mEmailView!!.error = getString(R.string.error_invalid_email)
			focusView = mEmailView
			cancel = true
		}
		
		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView!!.requestFocus()
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			showProgress(true)
			if (til.isEnabled) {
				register(email,password)
			} else {
				loginWithEmail(email, password)
			}
		}
	}
	
	private fun loginWithEmail(email : String, password : String) {
		mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(this@LoginActivity, { task ->
			showProgress(false)
			if (task.isSuccessful) {
				gotoContent()
			}
		})?.addOnFailureListener(this, { exception ->
			AlertDialog.Builder(this).setTitle("Login failure!").setMessage(exception.message.toString()).setPositiveButton("Retry", {
				dialog, which ->
				dialog.dismiss()
			}).create().show()
		})
	}
	
	private fun gotoContent() {
		//去主界面
		
	}
	
	private fun isEmailValid(email : String) : Boolean {
		return email.contains("@")
	}
	
	private fun isPasswordValid(password : String) : Boolean {
		return password.length > 4
	}
	
	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private fun showProgress(show : Boolean) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
			
			mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
			mLoginFormView!!.animate().setDuration(shortAnimTime.toLong()).alpha((if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
				override fun onAnimationEnd(animation : Animator) {
					mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
				}
			})
			
			mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
			mProgressView!!.animate().setDuration(shortAnimTime.toLong()).alpha((if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
				override fun onAnimationEnd(animation : Animator) {
					mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
				}
			})
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
			mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
		}
	}
	
}

