package cn.studyjams.s2.sj0265.yangxiqiang.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView

/**
 * Created by XQ Yang on 2017/5/18  20:02.
 * Description :
 */
abstract class BaseAdapter<D,H:BaseHolder<D>>(val context : Context, var dataList : List<D>) : RecyclerView.Adapter<H>() {
	var onClickListener: OnItemClickListener<D,H>? = null
	
	override fun onBindViewHolder(holder : H?, position : Int) {
		holder?.itemView?.setOnClickListener({onClickListener?.onItemClick(holder,dataList[position],position)})
		holder?.bind(dataList[position], position)
	}
	
	override fun getItemCount() : Int {
		return dataList.size
	}
	
	fun addData(data : D?) {
		if (data!=null) {
			val indexOf = dataList.indexOf(data)
			if (indexOf>=0) {
				val mutableList = dataList as MutableList
				mutableList.set(indexOf,data)
				notifyItemChanged(indexOf)
			} else {
				dataList+=data
				notifyItemInserted(dataList.size-1)
			}
		}
	}
	
	
	interface OnItemClickListener<D,H:BaseHolder<D>>{
		fun onItemClick(holder : H?, bean : D, position : Int)
	}
}



