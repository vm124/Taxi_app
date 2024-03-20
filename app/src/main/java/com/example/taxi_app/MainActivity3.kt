package com.example.taxi_app

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
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

class MainActivity3 : BaseActivity() {

    private lateinit var startStreetEditText: EditText
    private lateinit var startHouseEditText: EditText
    private lateinit var startFlatEditText: EditText
    private lateinit var destinationStreetEditText: EditText
    private lateinit var destinationHouseEditText: EditText
    private lateinit var destinationFlatEditText: EditText
    private lateinit var okButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        startStreetEditText = findViewById(R.id.startStreetEditText)
        startHouseEditText = findViewById(R.id.startHouseEditText)
        startFlatEditText = findViewById(R.id.startFlatEditText)
        destinationStreetEditText = findViewById(R.id.destinationStreetEditText)
        destinationHouseEditText = findViewById(R.id.destinationHouseEditText)
        destinationFlatEditText = findViewById(R.id.destinationFlatEditText)
        okButton = findViewById(R.id.okButton)

        // Добавляем слушатели изменений текста к каждому полю ввода
        startStreetEditText.addTextChangedListener(TextChangedListener())
        startHouseEditText.addTextChangedListener(TextChangedListener())
        startFlatEditText.addTextChangedListener(TextChangedListener())
        destinationStreetEditText.addTextChangedListener(TextChangedListener())
        destinationHouseEditText.addTextChangedListener(TextChangedListener())
        destinationFlatEditText.addTextChangedListener(TextChangedListener())

        // Проверяем, заполнены ли все поля
        val allFieldsFilled = startStreetEditText.text.isNotBlank() &&
                startHouseEditText.text.isNotBlank() &&
                startFlatEditText.text.isNotBlank() &&
                destinationStreetEditText.text.isNotBlank() &&
                destinationHouseEditText.text.isNotBlank() &&
                destinationFlatEditText.text.isNotBlank()

        // Активируем кнопку "OK" в зависимости от заполненности полей
        okButton.isEnabled = allFieldsFilled

        // Назначение обработчика нажатия на кнопку
        okButton.setOnClickListener {
            // Получение текста из полей для ввода
            val startStreet = startStreetEditText.text.toString()
            val startHouse = startHouseEditText.text.toString()
            val startFlatEditText = startFlatEditText.text.toString()

            val destinationStreet = destinationStreetEditText.text.toString()
            val destinationHouse = destinationHouseEditText.text.toString()
            val destinationFlatEditText = destinationFlatEditText.text.toString()

            // Создание Intent с результатом
            val resultIntent = Intent()
            resultIntent.putExtra("startStreet", startStreet)
            resultIntent.putExtra("startHouse", startHouse)
            resultIntent.putExtra("startFlat", startFlatEditText)

            resultIntent.putExtra("destinationStreet", destinationStreet)
            resultIntent.putExtra("destinationHouse", destinationHouse)
            resultIntent.putExtra("destinationFlat", destinationFlatEditText)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()

        }
    }
    inner class TextChangedListener : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            // Проверяем, заполнены ли все поля
            val allFieldsFilled = startStreetEditText.text.isNotBlank() &&
                    startHouseEditText.text.isNotBlank() &&
                    startFlatEditText.text.isNotBlank() &&
                    destinationStreetEditText.text.isNotBlank() &&
                    destinationHouseEditText.text.isNotBlank() &&
                    destinationFlatEditText.text.isNotBlank()

            okButton.isEnabled = allFieldsFilled
        }
    }
}