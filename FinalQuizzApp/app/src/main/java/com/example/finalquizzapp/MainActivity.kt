package com.example.finalquizzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.Parcelize

@Parcelize
class Transfer(var points: Int,var cheat_counter: Int,var correct_counter: Int,var question_number: Int, val correct_answ : Boolean) : Parcelable

class MainActivity : AppCompatActivity() {

    private val qcont:TextView by lazy { findViewById(R.id.qcontent) }
    private val qnumber:TextView by lazy { findViewById(R.id.qnumber)}
    private val Tbtn:Button by lazy { findViewById(R.id.TrueBtn) }
    private val Fbtn:Button by lazy { findViewById(R.id.FalseBtn) }
    private val Cbtn:Button by lazy { findViewById(R.id.CheatBtn) }
    private val pointsTxt : TextView by lazy { findViewById(R.id.pointsTextQ)}

    class Stats ( var points : Int, var cheat_counter : Int, var correct_counter : Int, var question_number : Int, var hidden_answer : Boolean)
    var player_stats = Stats(0,0,0,1,false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val backdata = intent.getParcelableExtra<Transfer>("gameState")
        if(backdata != null) //jeżeli wracam z drugiej aktywnosci - laduje statystyki
        {
            player_stats.points = backdata.points
            player_stats.question_number = backdata.question_number
            player_stats.cheat_counter = backdata.cheat_counter
            player_stats.correct_counter = backdata.correct_counter
        }
        //ustawianie pol textview
        qcont.text = "${Qlist[player_stats.question_number - 1].question}"
        qnumber.text = "#${player_stats.question_number}"
        pointsTxt.text = "Points: ${player_stats.points}"
        //Log.i("pomocy","${backdata!!.question_number}"
    }

    //private var player_stats = Stats(0,0,0,0)

    class Questions( val question : String, val answer : Boolean) //klasa stworzona do przechowywania pytan

//tworze zmienna klasy questions ktora przechowuje pytania i odpowiedzi do nich
    val Qlist = listOf<Questions>(
        Questions("Friction is a force that opposes motion", true),
        Questions("Mass and weight are the same thing", false),
        Questions("Force and pressure are the same thing", false),
        Questions("Pressure is directly proportional to force", true),
        Questions("Isaac Newton discovered Newton's laws", true),
        Questions("electricity is the flow of electrons", true),
        Questions("Everybody loves learning physics", false),
        Questions("WFiA rulez", true),
        Questions("This is second to last question", true),
        Questions("This was the best quiz ever", false),
    )


    private fun play()  //funkcja ktora "popycha" gre do przodu, aktualizuje punkty i wyswietlane pytanie + przechodzi do podsumowania na koniec
    {
        player_stats.question_number += 1
        pointsTxt.text = "Points: ${player_stats.points}"

        if(player_stats.question_number <= Qlist.size)
        {
            qnumber.text = "#${player_stats.question_number}"
            qcont.text = "${Qlist[player_stats.question_number - 1].question}"
        }
        else
        {
            //gdy skoncza sie pytania gra przechodzi do aktywnosci podsumowania
            val finalIntent = Intent(this,SummaryActivity::class.java).putExtra("sum",Transfer(player_stats.points,player_stats.cheat_counter,player_stats.correct_counter,player_stats.question_number,false))
            startActivity(finalIntent)
        }

    }

    fun answeredTrue(view: View) //funkcja wywolywana przy wcisnieciu przycisku true
    {

        Process_answer(true)
    }

    fun answeredFalse(view: View) //funkcja wywolywana przy wcisnieciu przycisku false
    {

        Process_answer(false)
    }

    private fun Process_answer(answer : Boolean) //funkcja ktora sprawdza czy wcisnieta odpowiedz jest poprawna i wywoluje funkcje play
    {

        var p_answer = Qlist[player_stats.question_number - 1].answer
        if(answer == p_answer) //porownuje odpowiedz gracza z kluczem
        {
            player_stats.points += 10
            player_stats.correct_counter += 1 //dodanie punktow do statystyk
            play()
        }
        else
        {
            play()
        }

        Log.i("PUNKTY", "${player_stats.points}")
    }

    fun answeredCheat(view: View) //funkcja wywolywana po wcisnieciu przycisku cheat, powoduje przejscie do drugiej aktywnosci
    {
        player_stats.cheat_counter += 1
        player_stats.points -= 15

        val chIntent = Intent(this,SecondActivity::class.java).putExtra("gameState",Transfer(player_stats.points,player_stats.cheat_counter,player_stats.correct_counter,player_stats.question_number,Qlist[player_stats.question_number - 1].answer))
        startActivity(chIntent)  //podaje statystyki do drugiej aktywnosci i do niej przechodze
    }


}

