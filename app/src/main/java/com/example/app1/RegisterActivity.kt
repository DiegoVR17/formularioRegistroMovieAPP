package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app1.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = registerBinding.root
        setContentView(view)

        registerBinding.buttonRegister.setOnClickListener {
            val email = registerBinding.emailTextInputEditText.text.toString()
            registerBinding.infoTextView.text = email
            val password = registerBinding.passTextInputEditText.text.toString()
            val repPassword = registerBinding.repPassTextInputEditText.text.toString()

            var genre = "Masculino"
            if(registerBinding.radioButtonWomen.isChecked)genre="Femenino"

            if(password == repPassword)Toast.makeText(this, "Bienvenido: $email" + "Genero: $genre",Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"Las contraseñas no son iguales",Toast.LENGTH_SHORT).show()
        }
    }
}