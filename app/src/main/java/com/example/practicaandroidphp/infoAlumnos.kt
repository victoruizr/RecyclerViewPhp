package com.example.practicaandroidphp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class infoAlumnos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_alumnos)
        val bundle : Bundle?=intent.extras

        if(bundle!=null){
            val nombre = bundle.getString("nombre")
            val apellidos = bundle.getString("apellidos")
            val dni = bundle.getInt("dni")
            val imagen = bundle.getString("imagen")
            val tutor = bundle.getString("tutor")
            val imagenTutor = bundle.getString("imagenTutor")

            val textView4 = findViewById(R.id.textView4) as TextView
            val textView5 = findViewById(R.id.textView5) as TextView
            val imagen1 = findViewById(R.id.imageView2) as ImageView
            val imagen2 = findViewById(R.id.imageView3) as ImageView

            textView4.text="Alumnos\n"+dni.toString()+"\n"+nombre+"\n"+apellidos+"\n"
            Glide.with(this).load(imagen).into(imagen1)
            textView5.text="Tutor\n"+tutor+"\n"
            Glide.with(this).load(imagenTutor).into(imagen2)
        }
    }
}