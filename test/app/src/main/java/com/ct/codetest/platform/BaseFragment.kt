package com.ct.codetest.platform

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ct.codetest.contracts.AppLogger
import com.ct.codetest.usecases.AllSparksUseCase
import com.ct.codetest.usecases.TransformersUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject

abstract class BaseFragment: Fragment(), AppLogger {

    private val mTransformersUseCase: TransformersUseCase by inject()
    private val mAllSparksUseCase: AllSparksUseCase by inject()
    val sharedPreferences: SharedPreferences by inject()
    val mBaseViewModelFactory: BaseViewModelFactory =
        BaseViewModelFactory(Dispatchers.Main, Dispatchers.IO, mTransformersUseCase, mAllSparksUseCase, sharedPreferences)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId(): Int

    fun showToast(msg: String) {
        requireActivity().runOnUiThread {
            val toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    override fun logD(tag: String, message: String) {
        Log.d(tag,message)
    }

    override fun logE(tag: String, message: String) {
        Log.e(tag,message)
    }

    override fun logV(tag: String, message: String) {
        Log.v(tag,message)
    }

    override fun logI(tag: String, message: String) {
        Log.i(tag,message)
    }
}