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

        fun divYmult(terminos: Array<String>): Array<String> {
            fun evaluarOperacion(index: Int, operador: String, array: Array<String>): Array<String> {
                if (index < 1 || index + 1 >= array.size) {
                    throw IllegalArgumentException("Índice fuera de rango")
                }

                val num1 = array[index - 1].toFloat()
                val num2 = array[index + 1].toFloat()
                val resultado = when (operador) {
                    "x" -> num1 * num2
                    "/" -> num1 / num2
                    else -> throw IllegalArgumentException("Operador no válido: $operador")
                }
                array[index - 1] = resultado.toString()
                return array.take(index).plus(array.drop(index + 2)).toTypedArray()
            }
            val index = terminos.indexOfFirst { it == "x" || it == "/" }
            return if (index != -1) {
                divYmult(evaluarOperacion(index, terminos[index], terminos))
            } else {
                terminos
            }
        }
        fun adYsus(terminos: Array<String>): Array<String> {
            fun evaluarOperacion(index: Int, operador: String, array: Array<String>): Array<String> {
                if (index < 1 || index + 1 >= array.size) {
                    throw IllegalArgumentException("Índice fuera de rango")
                }

                val num1 = array[index - 1].toFloat()
                val num2 = array[index + 1].toFloat()
                val resultado = when (operador) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    else -> throw IllegalArgumentException("Operador no válido: $operador")
                }
                array[index - 1] = resultado.toString()
                return array.take(index).plus(array.drop(index + 2)).toTypedArray()
            }

            val index = terminos.indexOfFirst { it == "+" || it == "-" }
            return if (index != -1) {
                adYsus(evaluarOperacion(index, terminos[index], terminos))
            } else {
                terminos
            }
        }

        btnIgual.setOnClickListener{
            var terminos = emptyArray<String>()
            var armandoTermino = ""
            for(n in historia){
                if(n=='/' || n=='x' || n=='+' || n=='-'){
                    terminos+=armandoTermino
                    terminos+=n.toString()
                    armandoTermino=""
                }else{
                    armandoTermino+=n
                }
            }
            terminos+=armandoTermino
            terminos = divYmult(terminos)
            terminos = adYsus(terminos)
            tvPantalla.text = terminos[0]
            historia = ""
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}