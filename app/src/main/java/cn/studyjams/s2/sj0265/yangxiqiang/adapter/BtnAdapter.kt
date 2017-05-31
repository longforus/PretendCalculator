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
class BtnAdapter(val context : Context, var dataList : List<MainBean>, val itemHeight : Int) : RecyclerView.Adapter<ViewHolder>() {
	var onClickListener: OnItemClickListener? = null
	override fun onCreateViewHolder(parent : ViewGroup?, viewType : Int) : ViewHolder {
		val view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)
		if (itemHeight >0) {
		val params = view.layoutParams
			params.height =if (viewType==1)  itemHeight*2 else itemHeight
			view.layoutParams = params
		}
		return ViewHolder(view)
	}
	
	override fun onBindViewHolder(holder : ViewHolder?, position : Int) {
		holder?.itemView?.setOnClickListener({onClickListener?.onItemClick(holder,dataList[position],position)})
		holder?.bind(dataList[position], position)
	}
	
	override fun getItemCount() : Int {
		return dataList.size
	}
	
	override fun getItemViewType(position : Int) : Int {
		return	if (dataList[position].imgId==R.mipmap.btn_pad_equal_n) 1 else super.getItemViewType(position)
	}
	
	
}


interface OnItemClickListener{
	fun onItemClick(holder : ViewHolder?, bean : MainBean, position : Int)
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
			itemView.tv.text =if (bean.num==-130) "." else bean.num.toString()
		}
		if (bean.backColor != 0) {
			itemView.setBackgroundColor(bean.backColor)
		}
	}
	
}