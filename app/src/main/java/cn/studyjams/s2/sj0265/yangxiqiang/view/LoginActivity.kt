package cn.studyjams.s2.sj0265.yangxiqiang.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.LoginPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.ILoginPresenter
import cn.studyjams.s2.sj0265.yangxiqiang.view.inf.ILoginView
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
	private var  isRegister: Boolean = false
	
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
		mEmailView = email as AutoCompleteTextView
		mPasswordView = password as EditText
		mPasswordView!!.setOnEditorActionListener(onEditorActionListener)
		password1.setOnEditorActionListener(onEditorActionListener)
		val mEmailSignInButton =email_sign_in_button
		mEmailSignInButton.setOnClickListener { attemptLogin() }
		email_register_button.setOnClickListener { attemptRegister() }
		mLoginFormView = login_form
		mProgressView = login_progress
	}
	
	private fun attemptRegister() {
		if (!isRegister) {
			isRegister = true
			til.visibility = View.VISIBLE
			mPasswordView?.imeOptions = EditorInfo.IME_ACTION_NEXT
			mPasswordView?.setOnEditorActionListener(null)
			email_sign_in_button.setText(getString(R.string.action_register))
			email_register_button.setText("Return")
		} else {
			isRegister = false
			til.visibility = View.GONE
			mPasswordView?.imeOptions = EditorInfo.IME_ACTION_UNSPECIFIED
			mPasswordView?.setOnEditorActionListener(onEditorActionListener)
			email_sign_in_button.setText(getString(R.string.action_sign_in))
			email_register_button.setText(getString(R.string.action_register))
		}
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
		val password1text = password1!!.text.toString()
		
		var cancel = false
		var focusView : View? = null
		
		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
			mPasswordView!!.error = getString(R.string.error_invalid_password)
			focusView = mPasswordView
			cancel = true
		}
		// Check for a valid password, if the user entered one.
		if (isRegister&&(TextUtils.isEmpty(password1text) || !isPasswordValid(password1text))) {
			password1!!.error = getString(R.string.error_invalid_password)
			focusView = password1
			cancel = true
		}
		
		// Check for a valid password, if the user entered one.
		if (isRegister&&( password != password1text)) {
			password1!!.error = getString(R.string.error_invalid_password1)
			focusView = password1
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
			if (isRegister) {
				presenter?.register(email,password)
			} else {
				presenter?.loginWithEmail(email, password)
			}
		}
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
	override fun showProgress(show : Boolean) {
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

