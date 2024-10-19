package com.derren.deviceHistory.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TemplateFragment : Fragment() {

    private var templateResId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        templateResId = arguments?.getInt("template_res_id")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return templateResId?.let { inflater.inflate(it, container, false) }
    }

    companion object {
        fun newInstance(templateResId: Int): TemplateFragment {
            val fragment = TemplateFragment()
            val args = Bundle()
            args.putInt("template_res_id", templateResId)
            fragment.arguments = args
            return fragment
        }
    }
}
