package com.rustfisher.tutorial2020.kotlinguide

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rustfisher.tutorial2020.R

class KotlinGuideFragment : Fragment() {


    companion object {
        fun newInstance() = KotlinGuideFragment()
        private const val TAG = "rustApp"
    }

    private lateinit var viewModel: KotlinGuideViewModel
    private lateinit var tv1: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.kotlin_guide_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv1 = view.findViewById(R.id.tv1)
        tv1.setOnClickListener {
            Log.d(TAG, "click " + it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(KotlinGuideViewModel::class.java)

        val inputStr = "Rust Fisher"
        var res1: Int = viewModel.stringMapper(inputStr, viewModel.stringLengthFunc)
        var res2: Int = viewModel.stringMapper(inputStr, viewModel.stringLengthDouble)
        Log.d(TAG, "res1: " + res1 + ", res2: " + res2)
    }

}
