package com.example.nucuproject2

import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_record.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private var isRecording = false
val mediaRecorder = MediaRecorder()
var num = 0
var stopTime = 0

class Record : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_record, container, false)

        val startBtn = v.findViewById<View>(R.id.StartBtn) as ImageButton

        startBtn.setOnClickListener {
            if(isRecording == false) {
                try{
                    initAudioRecorder()
                    mediaRecorder.start();
                    chronometer.base = (SystemClock.elapsedRealtime() + stopTime)
                    chronometer.start()
                    isRecording = true
                } catch (e : IllegalStateException) {
                    System.out.println("error");
                    e.printStackTrace();

                }
            } else {
                mediaRecorder.stop()
                //mediaRecorder.reset()
                //mediaRecorder.release()
                //stopTime = chronometer.base.toInt()- SystemClock.elapsedRealtime().toInt()    //if you want just to stop chronometer
                chronometer.setBase(SystemClock.elapsedRealtime())
                stopTime = 0
                chronometer.stop()
                chronometer.base = SystemClock.elapsedRealtime()
                isRecording = false
            }
        }

        return v
    }

    fun initAudioRecorder() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH:mm:ss")
        val formatted = current.format(formatter)

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        mediaRecorder?.setOutputFile(Environment.getExternalStorageDirectory().toString() + "/NucuStorage/" + formatted + ".mp3")
        mediaRecorder?.prepare()
//        try {
//            mediaRecorder.prepare()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }
}
