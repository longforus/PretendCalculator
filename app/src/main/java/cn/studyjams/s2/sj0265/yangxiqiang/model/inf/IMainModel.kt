package cn.studyjams.s2.sj0265.yangxiqiang.model.inf

import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.MainBean
import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.IMainPresenter

/**
 * Created by XQ Yang on 2017/5/18  20:07.
 * Description :
 */
interface IMainModel : IModel<IMainPresenter> {
	fun getMainList() : List<MainBean>
	fun getEnterKey() : String?
}