package cn.studyjams.s2.sj0265.yangxiqiang.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.studyjams.s2.sj0265.yangxiqiang.R
import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.MainBean
import kotlinx.android.synthetic.main.item_main.view.*

/**
 * Created by XQ Yang on 2017/5/18  20:02.
 * Description :
 */
class BtnAdapter(val context : Context, var dataList : List<MainBean>) : RecyclerView.Adapter<ViewHolder>() {
	
	override fun onCreateViewHolder(parent : ViewGroup?, viewType : Int) : ViewHolder {
		return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main, parent, false))
	}
	
	override fun onBindViewHolder(holder : ViewHolder?, position : Int) {
		holder?.bind(dataList[position], position)
	}
	
	override fun getItemCount() : Int {
		return dataList.size
	}
}

class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
	fun bind(bean : MainBean, position : Int) {
		if (bean.imgId > 0) {
			itemView.iv.visibility = View.VISIBLE
			itemView.tv.visibility = View.GONE
			itemView.iv.setImageResource(bean.imgId)
		} else {
			itemView.iv.visibility = View.GONE
			itemView.tv.visibility = View.VISIBLE
			itemView.tv.text = bean.num.toString()
		}
		if (bean.backColor != 0) {
			itemView.setBackgroundColor(bean.backColor)
		}
	}
	
}