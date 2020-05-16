package com.example.marvelcharacterapp.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VM: ViewModel> : Fragment() {

    private val TAG = this::class.java.simpleName

    abstract val layout: Int

    private val viewModelClass: Class<VM> by lazy {
        Log.i(TAG, "actualTypeArgument[0] is " + "${(javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]}")
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    }

    private fun <T : ViewModel> ViewModelStoreOwner.getViewModelViaKoin(
        clazz: Class<T>,
        qualifier: Qualifier? = null,
        parameters: ParametersDefinition? = null
    ): T {
        return ViewModelCompat.getViewModel(
            clazz = clazz,
            qualifier = qualifier,
            parameters = parameters,
            owner = this
        )
    }

    protected val viewModel: VM by lazy { getViewModelViaKoin(viewModelClass) }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}