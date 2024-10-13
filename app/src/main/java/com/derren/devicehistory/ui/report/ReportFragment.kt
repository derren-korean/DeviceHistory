package com.derren.deviceHistory.ui.report

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.derren.deviceHistory.MainActivity
import com.derren.devicehistory.R
import com.derren.devicehistory.databinding.FragmentReportBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        val reportViewModel =
            ViewModelProvider(this).get(ReportViewModel::class.java)

        _binding = FragmentReportBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textReport
        reportViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // 공유할 텍스트
        val shareText = "장비 수리 이력:\n" +
                "시리얼 넘버: XYZ12345\n" +
                "위치: 서울\n" +
                "마지막 수리 날짜: 2024-09-29\n" +
                "수리 내용: 모터 교체"

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}