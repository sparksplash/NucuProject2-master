package com.example.nucuproject2

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_record_file_list.*
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(applicationContext, StartActivity::class.java) // add 2020.0308
        startActivity(intent)// add 2020.0308

        val dirPath : String = Environment.getExternalStorageDirectory().toString() + "/NucuStorage"
        val file = File(dirPath)
        if( !file.exists() )  // 원하는 경로에 폴더가 있는지 확인
            file.mkdirs()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, Record())
            .commit()

        recordBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, Record())
                .commit()
            recordBtn.setBackgroundColor(this.getResources().getColor(R.color.nucuBlue))
            recordBtn.setTextColor(this.getResources().getColor(R.color.white))
            listBtn.setBackgroundColor(this.getResources().getColor(R.color.white))
            listBtn.setTextColor(this.getResources().getColor(R.color.black))
        }

        listBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, RecordFileList())
                .commit()

            recordBtn.setBackgroundColor(this.getResources().getColor(R.color.white))
            recordBtn.setTextColor(this.getResources().getColor(R.color.black))
            listBtn.setBackgroundColor(this.getResources().getColor(R.color.nucuBlue))
            listBtn.setTextColor(this.getResources().getColor(R.color.white))
        }
    }
}
