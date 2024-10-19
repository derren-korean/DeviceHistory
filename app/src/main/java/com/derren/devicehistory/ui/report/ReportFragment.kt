package com.derren.deviceHistory.ui.report

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.derren.devicehistory.R
import com.derren.devicehistory.databinding.FragmentReportBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.log

class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val reportViewModel =
//            ViewModelProvider(this).get(ReportViewModel::class.java)

        _binding = FragmentReportBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonInitial.setOnClickListener {
            // 초동 템플릿 로드
            loadTemplate(R.layout.template_initial)
        }

        binding.buttonMiddle.setOnClickListener {
            // 중간 템플릿 로드
            loadTemplate(R.layout.template_middle)
        }

        binding.buttonComplete.setOnClickListener {
            // 완료 템플릿 로드
            loadTemplate(R.layout.template_complete)
        }


        // 공유할 텍스트
        val shareText = "장비 수리 이력:\n" +
                "시리얼 넘버: XYZ12345\n" +
                "위치: 서울\n" +
                "마지막 수리 날짜: 2024-09-29\n" +
                "수리 내용: 모터 교체"

        // 화면의 빈 곳을 터치하면 키보드를 내리도록 설정
        root.setOnTouchListener { v, event ->
            hideKeyboard()
            false
        }

        // 버튼 클릭 시 텍스트 공유 기능 추가
        binding.floatingActionButton.setOnClickListener {
            shareText(shareText)
        }
        return root
    }

    // 텍스트 공유 메서드
    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        val chooser = Intent.createChooser(intent, "Share via")
        startActivity(chooser)
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = requireActivity().currentFocus
        currentFocusView?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun loadTemplate(templateResId: Int) {
        Log.d("ReportFragment", "templateResId: " + templateResId +"눌림")
        // 템플릿 전환 로직 (FragmentTransaction 사용)
        val fragment = TemplateFragment.newInstance(templateResId)
        childFragmentManager.beginTransaction()
            .replace(R.id.template_container, fragment)
            .addToBackStack(null)  // 뒤로가기 버튼 추가
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}