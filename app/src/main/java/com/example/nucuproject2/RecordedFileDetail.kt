package com.example.nucuproject2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private var fileName: String? = null

class RecordedFileDetail : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_recorded_file_detail, container, false)
        val fileNameView = v.findViewById<TextView>(R.id.fileName)

        fileNameView.setText(fileName)

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) {
            fileName = param1
        }
    }

}
