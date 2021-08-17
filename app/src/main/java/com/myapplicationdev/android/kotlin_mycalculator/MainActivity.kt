package com.myapplicationdev.android.testkotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var calcList = arrayListOf<Any>()
    var currNum = arrayListOf<Any>()
    var toDisplay = arrayListOf<Any>()

    fun btnOnClick(view: View) {
        var msg = ""
        var btnSelected = view as Button
        when (btnSelected.id) {
            btn1.id -> {
                // clear all inputs and values
                msg = "AC"
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
            btn16.id -> {
                msg = "."
                if (!currNum.get(currNum.size - 1).equals(".")) {
                    currNum.add(msg)
                }

            }
            btn17.id -> msg = "="
        }

        try {
            var num = msg.toInt()
            currNum.add(num)
        } catch (e: Exception) {
            // if msg is not number
            if (!msg.equals(".") && !msg.equals("=")) {
                calcList.add(currNum.joinToString(separator = ""))
                calcList.add(msg)
                currNum.clear()
            } else if (msg.equals("=")) {
                calcList.add(currNum.joinToString(separator = ""))
                currNum.clear()
                calculate()
            }
        }

        toDisplay.add(msg)
        tvResult.text = toDisplay.joinToString(separator = "")
        Log.i("currNum", currNum.toString())
        Log.i("calcList", calcList.toString())

    }

    fun calculate() {
        var times = calcList.contains("*")
        var div = calcList.contains("/")
        var minus = calcList.contains("-")
        var plus = calcList.contains("+")
        Log.i("before calcList", calcList.toString())
        while (times || div) {
            for (i in calcList.size - 1 downTo 0) {
                var curr = calcList.get(i).toString()
                if (curr.equals("/")) {
                    var next = calcList.get(i + 1).toString().toDouble()
                    var prev = calcList.get(i - 1).toString().toDouble()
                    var result = prev / next

                    calcList.removeAt(i + 1)
                    calcList.removeAt(i)
                    calcList.set(i - 1, result)
                } else if (curr.equals("*")) {
                    var next = calcList.get(i + 1).toString().toDouble()
                    var prev = calcList.get(i - 1).toString().toDouble()
                    var result = BigDecimal(prev.div(next))

                    calcList.remove(i + 1)
                    calcList.remove(i)
                    calcList.set(i - 1, result)
                }
            }
            Log.i("after calcList1", calcList.toString())
        }
        Log.i("after calcList2", calcList.toString())
    }
}