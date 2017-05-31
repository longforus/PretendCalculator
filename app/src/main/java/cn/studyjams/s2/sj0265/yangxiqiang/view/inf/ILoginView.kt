package cn.studyjams.s2.sj0265.yangxiqiang.view.inf

import cn.studyjams.s2.sj0265.yangxiqiang.presenter.inf.ILoginPresenter
import com.google.android.gms.common.api.GoogleApiClient

/**
 * Created by XQ Yang on 2017/5/26  20:20.
 * Description :
 */
interface ILoginView:IVIew<ILoginPresenter>, GoogleApiClient.OnConnectionFailedListener  {
	  fun  showProgress(show : Boolean)
}