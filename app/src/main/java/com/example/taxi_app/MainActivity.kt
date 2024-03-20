package com.example.taxi_app

import android.content.Intent
import android.content.SharedPreferences
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

class MainActivity : BaseActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var phoneEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var registrationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)

        phoneEditText = findViewById(R.id.phoneEditText)
        nameEditText = findViewById(R.id.nameEditText)
        surnameEditText = findViewById(R.id.surnameEditText)
        registrationButton = findViewById(R.id.registrationButton)

        // Проверяем, сохранены ли уже данные пользователя в SharedPreferences
        val isRegistered = sharedPreferences.contains("phone")
        if (isRegistered) {
            // Если данные пользователя уже сохранены, меняем текст кнопки на "Log in"
            registrationButton.text = "Log in"
        }

        // Добавляем слушатель изменений текста ко всем EditText полям
        phoneEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val isPhoneChanged = sharedPreferences.getString("phone", "") != s.toString()
                registrationButton.text = if (isPhoneChanged) "Registration" else "Log in"
            }
        })
        nameEditText.addTextChangedListener(TextChangedListener())
        surnameEditText.addTextChangedListener(TextChangedListener())

        registrationButton.isEnabled = false

        registrationButton.setOnClickListener {
            val phone = phoneEditText.text.toString()
            val name = nameEditText.text.toString()
            val surname = surnameEditText.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("phone", phone)
            editor.putString("name", name)
            editor.putString("surname", surname)
            editor.apply()

            // Запуск SecondActivity с передачей данных о пользователе
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("phone", phone)
            intent.putExtra("name", name)
            intent.putExtra("surname", surname)
            startActivity(intent)
        }

        // Восстановление данных пользователя из SharedPreferences при повторном запуске приложения
        if (isRegistered) {
            val phone = sharedPreferences.getString("phone", "")
            val name = sharedPreferences.getString("name", "")
            val surname = sharedPreferences.getString("surname", "")

            phoneEditText.setText(phone)
            nameEditText.setText(name)
            surnameEditText.setText(surname)
        }
    }

    inner class TextChangedListener : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            // Проверяем, заполнены ли все поля
            val allFieldsFilled = phoneEditText.text.isNotBlank() &&
                    nameEditText.text.isNotBlank() &&
                    surnameEditText.text.isNotBlank()

            registrationButton.isEnabled = allFieldsFilled
        }
    }
}



