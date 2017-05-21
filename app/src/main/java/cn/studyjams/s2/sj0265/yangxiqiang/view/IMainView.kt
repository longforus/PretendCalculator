package cn.studyjams.s2.sj0265.yangxiqiang.view

import android.support.v7.widget.RecyclerView
import cn.studyjams.s2.sj0265.yangxiqiang.adapter.BtnAdapter
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.IMainPresenter

/**
 * Created by XQ Yang on 2017/5/18  20:05.
 * Description :
 */
interface IMainView:IVIew<IMainPresenter> {
	fun setAdapter(adapter : BtnAdapter)
	fun getRv():RecyclerView
}