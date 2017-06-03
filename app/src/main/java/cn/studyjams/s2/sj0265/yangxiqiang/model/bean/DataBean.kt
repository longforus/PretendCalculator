package cn.studyjams.s2.sj0265.yangxiqiang.model.bean

import com.google.firebase.database.GenericTypeIndicator
import java.io.Serializable
import java.util.*

/**
 * Created by XQ Yang on 6/3/2017  12:03 PM.
 * Description :
 */
 class DataBean: GenericTypeIndicator<DataBean>,Serializable {
	var content:String? = null
	var key:String? = null
	var date : Date? = null
	constructor()
	constructor( content:String, date : Date){
		this.content = content
		this.date = date
	}
	
	override fun equals(other : Any?) : Boolean {
		if (this === other) return true
		if (other?.javaClass != javaClass) return false
		
		other as DataBean
		
		if (key != other.key) return false
		
		return true
	}
	
	override fun hashCode() : Int {
		return key?.hashCode() ?: 0
	}
	
	
}