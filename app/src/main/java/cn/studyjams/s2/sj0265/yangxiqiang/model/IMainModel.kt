package cn.studyjams.s2.sj0265.yangxiqiang.model

import cn.studyjams.s2.sj0265.yangxiqiang.model.bean.MainBean

/**
 * Created by XQ Yang on 2017/5/18  20:07.
 * Description :
 */
interface IMainModel :ITopModel {
	fun getMainList() : List<MainBean>
}