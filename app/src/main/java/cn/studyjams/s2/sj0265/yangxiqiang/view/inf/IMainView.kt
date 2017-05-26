package cn.studyjams.s2.sj0265.yangxiqiang.view.inf

import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IMainPresenter

/**
 * Created by XQ Yang on 2017/5/18  20:05.
 * Description :
 */
interface IMainView: IVIew<IMainPresenter> {
	fun setAdapter(adapter : cn.studyjams.s2.sj0265.yangxiqiang.adapter.BtnAdapter)
	fun getRv(): android.support.v7.widget.RecyclerView
	fun showText(pos:Int,toString : String)
}