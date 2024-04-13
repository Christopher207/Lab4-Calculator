package com.example.lab4_calculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnCero: Button
    private lateinit var btnUno: Button
    private lateinit var btnDos: Button
    private lateinit var btnTres: Button
    private lateinit var btnCuatro: Button
    private lateinit var btnCinco: Button
    private lateinit var btnSeis: Button
    private lateinit var btnSiete: Button
    private lateinit var btnOcho: Button
    private lateinit var btnNueve: Button
    private lateinit var btnPunto: Button
    private lateinit var btnEntre: Button
    private lateinit var btnPor: Button
    private lateinit var btnMas: Button
    private lateinit var btnMenos: Button
    private lateinit var btnIgual: Button
    private lateinit var btnClear: Button
    private lateinit var btnBackspace: Button
    private lateinit var tvPantalla: TextView
    private var historia = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnCero = findViewById(R.id.btnCero)
        btnUno = findViewById(R.id.btnUno)
        btnDos = findViewById(R.id.btnDos)
        btnTres = findViewById(R.id.btnTres)
        btnCuatro = findViewById(R.id.btnCuatro)
        btnCinco = findViewById(R.id.btnCinco)
        btnSeis = findViewById(R.id.btnSeis)
        btnSiete = findViewById(R.id.btnSiete)
        btnOcho = findViewById(R.id.btnOcho)
        btnNueve = findViewById(R.id.btnNueve)
        btnPor = findViewById(R.id.btnPor)
        btnMas = findViewById(R.id.btnMas)
        btnMenos = findViewById(R.id.btnMenos)
        btnEntre = findViewById(R.id.btnEntre)
        btnPunto = findViewById(R.id.btnPunto)
        btnClear = findViewById(R.id.btnClear)
        btnBackspace = findViewById(R.id.btnBackspace)
        btnIgual = findViewById(R.id.btnIgual)
        tvPantalla = findViewById(R.id.tvPantalla)

        btnCero.setOnClickListener{
            historia+='0'
            tvPantalla.text = historia
        }
        btnUno.setOnClickListener{
            historia+='1'
            tvPantalla.text = historia
        }
        btnDos.setOnClickListener{
            historia+='2'
            tvPantalla.text = historia
        }
        btnTres.setOnClickListener{
            historia+='3'
            tvPantalla.text = historia
        }
        btnCuatro.setOnClickListener{
            historia+='4'
            tvPantalla.text = historia
        }
        btnCinco.setOnClickListener{
            historia+='5'
            tvPantalla.text = historia
        }
        btnSeis.setOnClickListener{
            historia+='6'
            tvPantalla.text = historia
        }
        btnSiete.setOnClickListener{
            historia+='7'
            tvPantalla.text = historia
        }
        btnOcho.setOnClickListener{
            historia+='8'
            tvPantalla.text = historia
        }
        btnNueve.setOnClickListener{
            historia+='9'
            tvPantalla.text = historia
        }
        btnPunto.setOnClickListener{
            historia+='.'
            tvPantalla.text = historia
        }
        btnEntre.setOnClickListener{
            historia+='/'
            tvPantalla.text = historia
        }
        btnPor.setOnClickListener{
            historia+='x'
            tvPantalla.text = historia
        }
        btnMas.setOnClickListener{
            historia+='+'
            tvPantalla.text = historia
        }
        btnMenos.setOnClickListener{
            historia+='-'
            tvPantalla.text = historia
        }
        btnBackspace.setOnClickListener{
            historia = historia.substring(0,historia.length - 1)
            tvPantalla.text = historia
        }
        btnClear.setOnClickListener{
            historia = ""
            tvPantalla.text = historia
        }
        var terminos = emptyArray<String>()
        var armandoTermino = ""
        fun calcularResultado(operaciones: Array<String>): Double {
            // Lista para almacenar los números y operadores después de resolver las operaciones de multiplicación y división
            val numerosYOperadores = mutableListOf<Any>()

            // Resuelve las operaciones de multiplicación y división y guarda el resultado en una nueva lista
            var i = 0
            while (i < operaciones.size) {
                val elemento = operaciones[i]
                if (elemento == "*" || elemento == "/") {
                    numerosYOperadores.add(elemento)
                } else {
                    try {
                        val numero = elemento.toDouble()
                        if (numerosYOperadores.isNotEmpty() && numerosYOperadores.last() is Double) {
                            val operador = numerosYOperadores.removeAt(numerosYOperadores.size - 1) as String
                            val numeroAnterior = numerosYOperadores.removeAt(numerosYOperadores.size - 1) as Double
                            val resultado = if (operador == "*") numeroAnterior * numero else numeroAnterior / numero
                            numerosYOperadores.add(resultado)
                        } else {
                            numerosYOperadores.add(numero)
                        }
                    } catch (e: NumberFormatException) {
                        // Si el elemento no es un número, se asume que es un operador y se ignora
                    }
                }
                i++
            }

            // Calcula el resultado final teniendo en cuenta las operaciones de suma y resta
            var resultadoFinal = numerosYOperadores[0] as Double
            i = 1
            while (i < numerosYOperadores.size) {
                val operador = numerosYOperadores[i] as String
                val numeroSiguiente = numerosYOperadores[i + 1] as Double
                if (operador == "+") {
                    resultadoFinal += numeroSiguiente
                } else if (operador == "-") {
                    resultadoFinal -= numeroSiguiente
                }
                i += 2
            }

            return resultadoFinal
        }



        btnIgual.setOnClickListener{
            for(n in historia){
                if(n=='/' || n=='x' || n=='+' || n=='-'){
                    terminos+=armandoTermino
                    terminos+=n.toString()
                }else{
                    armandoTermino+=n
                }
            }
            tvPantalla.text = calcularResultado(terminos).toString()


        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}