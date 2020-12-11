package com.example.practicaandroidphp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//import com.bumptech.glide.Glide
//import kotlinx.android.synthetic.main.rowq.view.*

class AdaptadorAlumnos(val listaAlumnos: ArrayList<Alumnos>) :
    RecyclerView.Adapter<AdaptadorAlumnos.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorAlumnos.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lista_alumnos, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AdaptadorAlumnos.ViewHolder, position: Int) {
        holder.bindItems(listaAlumnos[position])
    }

    override fun getItemCount(): Int {
        return listaAlumnos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(alumnos: Alumnos) {
            val textView = itemView.findViewById(R.id.textView3) as TextView

            val imagen = itemView.findViewById(R.id.imageView) as ImageView
            textView.text = alumnos.dni.toString()+"\n"+alumnos.nombre+"\n"+alumnos.apellidos+"\n"
            Glide.with(itemView).load(alumnos.imagen).into(imagen)

            textView.setOnClickListener {
                Toast.makeText(itemView.context,"El telefono es "+alumnos.telefono, Toast.LENGTH_SHORT).show();
            }

            imagen.setOnClickListener {
                val intent = Intent(itemView.context,infoAlumnos::class.java)
                intent.putExtra("nombre",alumnos.nombre)
                intent.putExtra("apellidos",alumnos.apellidos)
                intent.putExtra("dni",alumnos.dni)
                intent.putExtra("imagen",alumnos.imagen)
                intent.putExtra("tutor",alumnos.tutor)
                intent.putExtra("imagenTutor",alumnos.imagenTutor)

                itemView.context.startActivity(intent)
            }

        }
    }

}

