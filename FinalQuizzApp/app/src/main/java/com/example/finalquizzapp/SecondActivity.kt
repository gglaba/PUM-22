package com.example.finalquizzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val transferedData = intent.getParcelableExtra<Transfer>("gameState")
        val answer_show: TextView by lazy { findViewById(R.id.answerText) }
        val points : TextView by lazy { findViewById(R.id.pointsText)}

        if (transferedData != null) { //wczytuje statystyki z pierwszej aktywnosci i wyswietlam poprawna odp
            answer_show.text = "${transferedData.correct_answ}"
            points.text = "Points: ${transferedData.points}"
        }
    }

        fun goBack(view: View) //funkcja wywolywana przez przycisk take me back, przesyla statystyki z powrotem
        {
            val transferedData= intent.getParcelableExtra<Transfer>("gameState")
            val mainIntent =Intent(this,MainActivity::class.java).putExtra("gameState",Transfer(transferedData!!.points,transferedData!!.cheat_counter,transferedData!!.correct_counter,transferedData!!.question_number,transferedData!!.correct_answ))
            startActivity(mainIntent)
        }



}