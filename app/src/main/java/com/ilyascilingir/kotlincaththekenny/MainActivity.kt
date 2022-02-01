package com.ilyascilingir.kotlincaththekenny

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.ilyascilingir.kotlincaththekenny.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ImageArray

        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        hideImages()



        //CountDown Time
        object : CountDownTimer(15500,1000){
            override fun onTick(p0: Long) {
                binding.timeText.text = "Time : ${p0/1000}"
            }

            override fun onFinish() {

                binding.timeText.text = " Süreniz bitti ! "

                handler.removeCallbacks(runnable)

                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)

                alert.setTitle(" Game Over !")
                alert.setMessage(" Restart the game ? ")
                alert.setPositiveButton(" Yes ") {dialog, which ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->

                    val alert1 = AlertDialog.Builder(this@MainActivity)

                    alert1.setTitle(" Congratulations :) ")
                    alert1.setMessage(" Your Score : ${score} ")
                    alert1.setNegativeButton("Kapat") {dialog, which ->
                        finish()
                    }
                    alert1.show()
                }
                alert.show()
            }
        }.start()
    }

    fun hideImages () {

        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){
                image.visibility = View.INVISIBLE
                }

                var random = Random()
                var randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }


    fun increaseScore (view : View) {

        score++
        binding.scoreText.text = "Score : ${score}"

    }
}