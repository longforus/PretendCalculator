package cn.studyjams.s2.sj0265.yangxiqiang.adapter

import android.content.Context
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
class BtnAdapter(context : Context, dataList : List<MainBean>, val itemHeight : Int) : BaseAdapter<MainBean, BtnAdapter.ViewHolder>(context, dataList) {
	
	override fun onCreateViewHolder(parent : ViewGroup?, viewType : Int) : ViewHolder {
		val view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)
		if (itemHeight > 0) {
			val params = view.layoutParams
			params.height = if (viewType == 1) itemHeight * 2 else itemHeight
			view.layoutParams = params
		}
		return ViewHolder(view)
	}
	
	
	override fun getItemViewType(position : Int) : Int {
		return if (dataList[position].imgId == R.mipmap.btn_pad_equal_n) 1 else super.getItemViewType(position)
	}
	inner class ViewHolder(view : View) : BaseHolder<MainBean>(view) {
		override fun bind(item : MainBean, pos : Int) {
			if (item.imgId > 0) {
				itemView.iv.visibility = View.VISIBLE
				itemView.tv.visibility = View.GONE
				itemView.iv.setImageResource(item.imgId)
			} else {
				itemView.iv.visibility = View.GONE
				itemView.tv.visibility = View.VISIBLE
				itemView.tv.text = if (item.num == -130) "." else item.num.toString()
			}
			if (item.backColor != 0) {
				itemView.setBackgroundColor(item.backColor)
			}
		}
		
	}
}


