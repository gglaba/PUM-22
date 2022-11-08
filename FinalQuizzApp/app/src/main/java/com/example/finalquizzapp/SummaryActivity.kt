package com.example.finalquizzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class SummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val points : TextView = findViewById(R.id.pointsSum)
        val corrects : TextView = findViewById(R.id.correctSum)
        val cheats : TextView = findViewById(R.id.cheatsSum)
        val finalStats = intent.getParcelableExtra<Transfer>("sum")

        if(finalStats != null)
        {
            //ustawiam textview
            points.text = "Total points: ${finalStats.points}"
            corrects.text = "Correct answers: ${finalStats.correct_counter}"
            cheats.text = "Used cheats: ${finalStats.cheat_counter}"
        }
    }
}