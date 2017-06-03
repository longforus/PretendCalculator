package cn.studyjams.s2.sj0265.yangxiqiang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.method.format
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.DataBean
import kotlinx.android.synthetic.main.item_content.view.*

/**
 * Created by XQ Yang on 6/3/2017  12:29 PM.
 * Description :
 */
class ContentAdapter(context : Context) : BaseAdapter<DataBean, ContentAdapter.ViewHolder>(context, mutableListOf<DataBean>()) {
	
	override fun onCreateViewHolder(parent : ViewGroup?, viewType : Int) : ViewHolder {
		val view = LayoutInflater.from(context).inflate(R.layout.item_content, parent, false)
		return ViewHolder(view)
	}
	
	
	inner class ViewHolder(view : View) : BaseHolder<DataBean>(view) {
		override fun bind(item : DataBean, pos : Int) {
			itemView.tv.text = item.content
			itemView.tv_date.text =item.date?.format()
		}
	}
	
	
}


