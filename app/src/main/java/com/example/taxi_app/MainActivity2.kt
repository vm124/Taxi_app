package com.example.taxi_app

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taxi_app.ui.theme.Taxi_appTheme

class MainActivity2 : BaseActivity() {
    private lateinit var pathTextView: TextView // Объявление переменной pathTextView
    private lateinit var callTaxiButton: Button // Объявление переменной callTaxiButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val surnameTextView: TextView = findViewById(R.id.surnameTextView)
        val phoneTextView: TextView = findViewById(R.id.phoneTextView)
        pathTextView = findViewById(R.id.pathTextView)
        callTaxiButton = findViewById(R.id.callTaxiButton) // Инициализация переменной callTaxiButton
        val setPathButton: Button = findViewById(R.id.setPathButton)

        // Получение данных о пользователе из Intent
        val phone = intent.getStringExtra("phone")
        val name = intent.getStringExtra("name")
        val surname = intent.getStringExtra("surname")

        // Отображение данных о пользователе
        nameTextView.text = "Name: $name"
        surnameTextView.text = "Surname: $surname"
        phoneTextView.text = "Phone: $phone"

        // Обработка нажатия на кнопку "Set Path"
        setPathButton.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivityForResult(intent, REQUEST_CODE_SET_PATH)
        }

        // Обработка нажатия на кнопку "Call Taxi"
        callTaxiButton.setOnClickListener {
            // Показ всплывающего сообщения Toast об успешной отправке такси
            showToast("Taxi dispatched successfully!")
        }
    }

    companion object {
        private const val REQUEST_CODE_SET_PATH = 100
    }


    // Переопределяем метод onActivityResult для обработки результата от третьего активити
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SET_PATH && resultCode == Activity.RESULT_OK) {
            val startStreet = data?.getStringExtra("startStreet")
            val startHouse = data?.getStringExtra("startHouse")
            val startFlat = data?.getStringExtra("startFlat")
            val destinationStreet = data?.getStringExtra("destinationStreet")
            val destinationHouse = data?.getStringExtra("destinationHouse")
            val destinationFlat = data?.getStringExtra("destinationFlat")

            val path = "Start: $startStreet $startHouse $startFlat, Destination: $destinationStreet $destinationHouse $destinationFlat"
            pathTextView.text = path
            callTaxiButton.isEnabled = true // Активируем кнопку "Call Taxi"
        }
    }

    // Метод для отображения всплывающего сообщения
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}