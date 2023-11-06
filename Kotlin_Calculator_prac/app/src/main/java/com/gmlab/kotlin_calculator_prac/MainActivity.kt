package com.gmlab.kotlin_calculator_prac

import android.app.DatePickerDialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tv_SelectedDate : TextView? = null
    private var tv_AgeInMinutes : TextView? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tv_SelectedDate = findViewById(R.id.tv_SelectedDate)
        tv_AgeInMinutes = findViewById(R.id.tv_AgeInMinutes)

        btnDatePicker.setOnClickListener{
            clickDatePicker()

        }
    }

    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd =  DatePickerDialog(this,
            { _, selectedYear, selectedMonth,selectedDayOfMonth->
                Toast.makeText(this,"Year was $selectedYear , month was ${selectedMonth+1} , day of month was $selectedDayOfMonth",Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedYear/${selectedMonth+1}/$selectedDayOfMonth"

                tv_SelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time / 60000

                        val diffDateInMinutes = currentDateInMinutes - selectedDateInMinutes

                        tv_AgeInMinutes?.text = diffDateInMinutes.toString()
                    }

                }

            },

            year,
            month,
            day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}