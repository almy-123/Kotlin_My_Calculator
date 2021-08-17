package com.myapplicationdev.android.kotlin_mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var test = arrayListOf<Any>()
//        test.add(5)
//        test.add("*")
//        test.add(26)
//
//        for (i in 0..test.size - 1) {
//            if (i!=0){
//                Log.i("prev$i", test.get(i-1).toString())
//            }
//
//            Log.i("curr$i", test.get(i).toString())
//
//            if (i != test.size-1){
//                Log.i("next$i", test.get(i+1).toString())
//            }
//        }
    }

    var calcList = arrayListOf<Any>()

    fun btnOnClick(view: View) {
        var msg = ""
        var btnSelected = view as Button
        when (btnSelected.id) {
            btn1.id -> {
                // clear all inputs and values
                msg = "AC"
                toDisplay = ""
                tvResult.text = "0"
                calcList.clear()
                clearCurrNumber()
            }
            btn2.id -> msg = "/"
            btn3.id -> msg = "7"
            btn4.id -> msg = "8"
            btn5.id -> msg = "9"
            btn6.id -> msg = "*"
            btn7.id -> msg = "4"
            btn8.id -> msg = "5"
            btn9.id -> msg = "6"
            btn10.id -> msg = "-"
            btn11.id -> msg = "1"
            btn12.id -> msg = "2"
            btn13.id -> msg = "3"
            btn14.id -> msg = "+"
            btn15.id -> msg = "0"
            btn16.id -> msg = "."
            btn17.id -> msg = "="
        }

        try {
            // if user entered number
            var num = msg.toInt()
            appendNumber(num.toString())
        } catch (e: Exception) {
            var clear = msg.equals("AC")
            var equal = msg.equals("=")
            if (!clear && !equal) {
                appendExpression(msg)
            } else if (equal) {
                // add last number
                currNum = stringCurr.toInt()
                calcList.add(currNum)

                doCalculation()
            }

        }

        Log.d("test", calcList.toString())
    }

    fun clearCurrNumber() {
        currNum = 0
        stringCurr = ""
    }

    var toDisplay = ""
    var currNum = 0
    var stringCurr = ""

    fun appendNumber(entered: String) {
        toDisplay += entered
        stringCurr += entered
        tvResult.text = toDisplay
    }

    fun appendExpression(exp: String) {
        currNum = stringCurr.toInt()
        calcList.add(currNum)
        calcList.add(exp)
        clearCurrNumber()

        toDisplay += exp
        tvResult.text = toDisplay
    }

    var sum = 0
    fun doCalculation() {
        for (i in 0..calcList.size - 1) {
            try {
                if (calcList.get(i) is String) {
                    if (i != 0) {
                        Log.i("prev$i", calcList.get(i - 1).toString())
                    }

                    Log.i("string val", calcList.get(i).toString())

                    if (i != calcList.size - 1) {
                        Log.i("next$i", calcList.get(i + 1).toString())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}