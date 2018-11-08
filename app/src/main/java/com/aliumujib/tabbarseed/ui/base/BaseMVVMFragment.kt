package com.aliumujib.tabbarseed.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aliumujib.tabbarseed.BR
import com.aliumujib.tabbarseed.data.model.Status
import com.aliumujib.tabbarseed.ui.main.MainActivity
import com.aliumujib.tabbarseed.utils.common.NotNullObserver
import com.aliumujib.tabbarseed.utils.extensions.hideLoading
import com.aliumujib.tabbarseed.utils.extensions.hideViewLoading
import com.aliumujib.tabbarseed.utils.extensions.showLoading
import com.aliumujib.tabbarseed.utils.extensions.showViewLoading


/**
 * Created by f22labs on 07/03/17.
 */

abstract class BaseMVVMFragment<V : BaseViewModel> : Fragment() {

    private lateinit var viewModel: V


    open fun getViewModel(): V {
        return viewModel
    }

    open fun injectViewModel(viewModel: V) {
        this.viewModel = viewModel
    }

    open fun updateTitle(title: String) {
        (activity as MainActivity).updateToolbarTitle(title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()

    }


//    fun setUpToolbar(@DrawableRes toolbarRes:Int = R.drawable.left_long_arrow){
//        appCompatActivity().setSupportActionBar(toolbar)
//        appCompatActivity().supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        appCompatActivity().supportActionBar?.setHomeAsUpIndicator(toolbarRes)
//        toolbar.setNavigationOnClickListener {
//            appCompatActivity().finish()
//        }
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.setUp()

        getViewModel().isLoading.observe(this, NotNullObserver { data ->
            if (data!!.status == Status.RUNNING) {
                activity?.showLoading()
            } else {
                activity?.hideLoading()
            }
        })

        viewModel.snackbarMessage.observe(this, Observer {
            it.let {
                Toast.makeText(context, it!!, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.snackbarStringMessage.observe(this, Observer {
            it.let {
                Toast.makeText(context, it!!.replace("java.lang.Throwable:", ""), Toast.LENGTH_LONG).show()
            }
        })

        viewModel.dialogMessage.observe(this, Observer {
            showDialog(it!!)
        })

    }

    private fun showDialog(it: String) {
        val builder = AlertDialog.Builder(context!!)
        builder.setMessage(it)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        builder.create().show()
    }


    open fun showLoading() {
        showViewLoading()
    }

    open fun hideLoading() {
        hideViewLoading()
    }

    fun showDialog(title: String, action: () -> Unit) {
        val builder1 = android.app.AlertDialog.Builder(context)
        builder1.setMessage(title)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
                "Yes"
        ) { dialog, id ->
            action.invoke()
            dialog.cancel()
        }

        builder1.setNegativeButton(
                "No"
        ) { dialog, id -> dialog.cancel() }

        val alert11 = builder1.create()
        alert11.show()
    }

    abstract val layoutResID: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                inflater, layoutResID, container, false)
        val view = binding.root
        binding.setLifecycleOwner(this)
        binding.setVariable(BR.viewmodel, getViewModel())
        return view
    }


    open fun injectDependencies() {

    }

}
