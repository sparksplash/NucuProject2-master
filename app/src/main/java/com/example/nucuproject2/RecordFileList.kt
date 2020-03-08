package com.example.nucuproject2

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class RecordFileList : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_record_file_list, container, false)

        val listview = v.findViewById<View>(R.id.list) as ListView

        val fileList = ArrayList<String>()
        val file : File = File(Environment.getExternalStorageDirectory().toString() + "/NucuStorage")
        val list : Array<File> = file.listFiles()

        for (i in list.indices) {
            fileList.add(list[i].name)
        }

        val adapter = ListViewAdapter(v.context, fileList)
        listview.adapter = adapter

        listview.setOnItemClickListener { parent, view, position, id ->
            RecordedFileDetail.newInstance(adapter.getItem(position).toString())
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment, RecordedFileDetail())
                ?.commit()
        }
        return v
    }
}
