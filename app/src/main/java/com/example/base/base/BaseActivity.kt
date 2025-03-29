package com.example.base.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegate
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegateImpl
import java.util.Locale

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
    private val localeDelegate: LocaleHelperActivityDelegate = LocaleHelperActivityDelegateImpl()

    private var _binding: VB? = null
    open val binding get() = _binding!!
    lateinit var navController: LiveData<NavController>

    override
    fun createConfigurationContext(overrideConfiguration: Configuration): Context {
        val context = super.createConfigurationContext(overrideConfiguration)
        return LocaleHelper.onAttach(context)
    }

    override
    fun getApplicationContext(): Context =
        localeDelegate.getApplicationContext(super.getApplicationContext())

    override
    fun onResume() {
        super.onResume()
        localeDelegate.onResumed(this)
    }

    override
    fun onPause() {
        super.onPause()
        localeDelegate.onPaused()
    }

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setUpBottomNavigation()
        }

        setupData()
        setupUI()
        setupListener()
    }

    override
    fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        setUpBottomNavigation()
    }

    private fun initViewBinding() {
        _binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun setupUI()
    abstract fun setupData()
    abstract fun setupListener()

    open fun setUpBottomNavigation() {}


    open fun updateLocale(language: String) {
        localeDelegate.setLocale(this, Locale(language))
    }

    override
    fun onSupportNavigateUp(): Boolean {
        return navController.value?.navigateUp()!! || super.onSupportNavigateUp()
    }

    override
    fun getDelegate() = localeDelegate.getAppCompatDelegate(super.getDelegate())
}