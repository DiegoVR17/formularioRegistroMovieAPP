package com.example.app1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.example.app1.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()

    private lateinit var registerBinding: ActivityRegisterBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = registerBinding.root
        setContentView(view)

        registerBinding.editTextDate.setOnClickListener {
            showDatePickerDialog()
        }

        val cities = resources.getStringArray(R.array.colombian_cities)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        registerBinding.spinnerCities.adapter = adapter

        registerBinding.buttonRegister.setOnClickListener {
            val nombre = registerBinding.nameTextInputEditText.text.toString()
            val email = registerBinding.emailTextInputEditText.text.toString()
            val password = registerBinding.passTextInputEditText.text.toString()
            val repPassword = registerBinding.repPassTextInputEditText.text.toString()
            val date = registerBinding.editTextDate.text.toString()
            var genre = "Masculino"

            if(registerBinding.radioButtonWomen.isChecked)genre="Femenino"

            if ((nombre.isEmpty()) || (email.isEmpty()) || (password.isEmpty()) || (repPassword.isEmpty()) || (date.isEmpty())){
                Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show()
            }
            else if ((password.length < 6)){
                Toast.makeText(this,"La contraseña, debe tener minímo 6 digitos",Toast.LENGTH_SHORT).show()
            }
            else if((password != repPassword))Toast.makeText(this,"Las contraseñas no son iguales",Toast.LENGTH_SHORT).show()
            else {
                registerBinding.infoTextView.setText("Bienvenido:$nombre e-mail:$email fecha:$date sexo:$genre")
            }
        }
    }

    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Aquí puedes hacer algo con la fecha seleccionada, como mostrarla en el EditText.
                registerBinding.editTextDate.setText("$dayOfMonth/${monthOfYear + 1}/$year")
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.setOnCancelListener { dialogInterface ->
            // Se ejecuta si el usuario cierra el diálogo sin seleccionar una fecha.
        }

        datePickerDialog.show()
    }
}

