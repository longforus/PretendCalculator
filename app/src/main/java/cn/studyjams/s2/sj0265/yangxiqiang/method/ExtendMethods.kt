package cn.studyjams.s2.sj0265.yangxiqiang.method

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import cn.studyjams.s2.sj0265.yangxiqiang.model.inf.ITopModel

/**
 * Created by XQ Yang on 2017/5/26  19:38.
 * Description :
 */
val DEFAULT_SP_NAME = "longforus"
fun ITopModel.getString(context : Context,key:String):String?{
     return 	getDefaultSP(context).getString(key,null)
}

fun ITopModel.getDefaultSP(context : Context): SharedPreferences {
	return  context.getSharedPreferences(DEFAULT_SP_NAME,Context.MODE_PRIVATE)
}

fun Activity.toastShort(msg : String) {
	Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

