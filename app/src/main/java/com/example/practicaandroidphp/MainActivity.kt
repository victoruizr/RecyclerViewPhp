package com.example.practicaandroidphp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private var listaAlumnos: MutableList<Alumnos>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listaAlumnos = mutableListOf<Alumnos>()
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        conectarJson()

        //Toast.makeText(this, listaAlumnos!!.size.toString(), Toast.LENGTH_SHORT).show()
        /*for (alumnos in listaAlumnos) {
            resultado.text = alumnos.dni + " " + alumnos.nombre + " " + alumnos.apellidos + "\n"
        }*/
    }

    fun conectarJson() {
        // conecta con una url y devuelve su contenido
        var listaAlumnos = ArrayList<Alumnos>()
        val url = "http://iesayala.ddns.net/victor/"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            //conectó correctamente
            val jsonArray = JSONArray(response)
            for (i in 0..jsonArray.length()-1) {
                val jsonObject = JSONObject(jsonArray.getString(i));
                val alu = Alumnos(jsonObject.getInt("DNI"),jsonObject.getString("nombre"),jsonObject.getString("apellidos"),jsonObject.getString("imagen"),jsonObject.getString("telefono"),jsonObject.getString("tutor"),jsonObject.getString("imagenTutor"))
                //Toast.makeText(this, alu.nombre, Toast.LENGTH_SHORT).show()
                listaAlumnos!!.add(alu)
            }
           // Toast.makeText(this, listaAlumnos.size.toString(), Toast.LENGTH_SHORT).show()
            val adapter = AdaptadorAlumnos(listaAlumnos)
            recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            recyclerView.adapter=adapter
        }, Response.ErrorListener {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        })

        queue.add(stringRequest)
        //return listaAlumnos
    }


}