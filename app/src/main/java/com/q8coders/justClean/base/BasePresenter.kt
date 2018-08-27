package com.q8coders.justClean.base

import android.content.Context
import android.text.TextUtils
import com.q8coders.justClean.R
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.model.error.ErrorModel
import com.q8coders.justClean.screen.main.MainActivity
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException


/**
 * @Created by shahid on 8/26/2018.
*/
abstract class BasePresenter<V : BaseView> {

    @Inject lateinit var mView: V

    protected fun getAppContext(): Context = MyApplication.applicationComponent.getContext()


    protected fun handelApiError(throwable: Throwable?): String {
        return when (throwable) {

            is SSLPeerUnverifiedException ->{
                Timber.e("Certificate pinning failure!")
                MainActivity.INSTANCE!!.getLocaleString(R.string.security_patches_has_been_updated)
            }
            is SSLHandshakeException ->{
                Timber.e("ssl handshake exception!")
                MainActivity.INSTANCE!!.getLocaleString(R.string.ssl_handshake_exception)
            }
            is IOException -> {// network connection issue
                Timber.e("Network issue")
                if(throwable is SocketTimeoutException){
                    MainActivity.INSTANCE!!.getLocaleString(R.string.connection_time_out)
                }else{
                    MainActivity.INSTANCE!!.getLocaleString(R.string.please_make_sure_internet_is_working)
                }
            }
            is HttpException -> {
                // Api error
                var errorMessage: String? = throwable.response().errorBody()?.string()

                errorMessage = if (TextUtils.isEmpty(errorMessage)) {
                    MainActivity.INSTANCE!!.getLocaleString(R.string.something_went_wrong)
                }else{
                    val customErrorMessage = MyApplication.applicationComponent.getGsonObject().fromJson(errorMessage, ErrorModel::class.java)
                    customErrorMessage.statusMessage
                }
                Timber.e("API Error=$errorMessage")

                errorMessage!!
            }
            else -> { // something went wrong
                Timber.e( throwable?.message)
                MainActivity.INSTANCE!!.getLocaleString(R.string.something_went_wrong)
            }
        }

    }

}