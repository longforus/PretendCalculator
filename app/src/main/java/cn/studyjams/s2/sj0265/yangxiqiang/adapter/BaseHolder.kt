package cn.studyjams.s2.sj0265.yangxiqiang.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by XQ Yang on 6/3/2017  12:14 PM.
 * Description :
 */
abstract class BaseHolder<D>(itemView : View?) : RecyclerView.ViewHolder(itemView) {
	abstract fun bind(item:D,pos:Int)
}