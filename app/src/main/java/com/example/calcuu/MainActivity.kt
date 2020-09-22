package com.example.calc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import com.example.calcuu.R
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt


internal enum class State {
    START, PLUS, MINUS, DIVIDE, MULTIPLY, EQUAL, POW, PERCENT, SQRT
}

internal enum class Btn {
    PLUS, MINUS, DIVIDE, MULTIPLY, UNDEFINED, POW, PERCENT, SQRT
}

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var textInput: TextView? = null
    private var buttonClear: Button? = null
    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null
    private var button6: Button? = null
    private var button7: Button? = null
    private var button8: Button? = null
    private var button9: Button? = null
    private var button0: Button? = null
    private var buttonMul: Button? = null
    private var buttonDiv: Button? = null
    private var buttonPlus: Button? = null
    private var buttonMinus: Button? = null
    private var buttonDot: Button? = null
    private var buttonEq: Button? = null
    private var buttonSqrt: Button? = null
    private var buttonPow: Button? = null
    private var buttonPercent: Button? = null

    private var dot = 0
    private var num1 = 0.0
    private var num2 = 0.0
    private var sTate: State = State.START
    private var btn: Btn = Btn.UNDEFINED
    var second = 0
    var negative = 0

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textInput?.text = savedInstanceState.getString("TextInput")
        second = savedInstanceState.getInt("Second")
        negative = savedInstanceState.getInt("Negative")
        btn = Btn.valueOf(savedInstanceState.getString("Btn").toString())
        sTate = State.valueOf(savedInstanceState.getString("State").toString())
        dot = savedInstanceState.getInt("dot")
        num1 = savedInstanceState.getDouble("num1")
        num2 = savedInstanceState.getDouble("num2")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("TextInput", textInput!!.text.toString())
        outState.putString("State", sTate.toString())
        outState.putInt("Second", second)
        outState.putInt("Negative", negative)
        outState.putString("Btn", btn.toString())
        outState.putInt("dot", dot)
        outState.putDouble("num1", num1)
        outState.putDouble("num2", num2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textInput = findViewById(R.id.numberInput)
        buttonEq = findViewById(R.id.buttonResult)
        buttonClear = findViewById(R.id.buttonClear)
        button1 = findViewById(R.id.buttonNumber1)
        button2 = findViewById(R.id.buttonNumber2)
        button3 = findViewById(R.id.buttonNumber3)
        button4 = findViewById(R.id.buttonNumber4)
        button5 = findViewById(R.id.buttonNumber5)
        button6 = findViewById(R.id.buttonNumber6)
        button7 = findViewById(R.id.buttonNumber7)
        button8 = findViewById(R.id.buttonNumber8)
        button9 = findViewById(R.id.buttonNumber9)
        button0 = findViewById(R.id.buttonNumber0)
        buttonMul = findViewById(R.id.buttonMultiply)
        buttonDiv = findViewById(R.id.buttonDivide)
        buttonPlus = findViewById(R.id.buttonPlus)
        buttonMinus = findViewById(R.id.buttonMinus)
        buttonDot = findViewById(R.id.buttonDot)
        buttonSqrt = findViewById(R.id.buttonSqrt)
        buttonPow = findViewById(R.id.buttonPower)
        buttonPercent = findViewById(R.id.buttonPercent)
        buttonClear?.setOnClickListener(this)
        button1?.setOnClickListener(this)
        button2?.setOnClickListener(this)
        button3?.setOnClickListener(this)
        button4?.setOnClickListener(this)
        button5?.setOnClickListener(this)
        button6?.setOnClickListener(this)
        button7?.setOnClickListener(this)
        button8?.setOnClickListener(this)
        button9?.setOnClickListener(this)
        button0?.setOnClickListener(this)
        buttonMul?.setOnClickListener(this)
        buttonDiv?.setOnClickListener(this)
        buttonPlus?.setOnClickListener(this)
        buttonMinus?.setOnClickListener(this)
        buttonDot?.setOnClickListener(this)
        buttonEq?.setOnClickListener(this)

        buttonSqrt?.setOnClickListener(this)
        buttonPow?.setOnClickListener(this)
        buttonPercent?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonClear -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (textInput!!.text.toString().isEmpty()) {
                } else if (textInput!!.text.toString().length != 1) {
                    try {
                        textInput!!.text = textInput!!.text.toString()
                                .substring(0, textInput!!.text.toString().length - 1)
                        num1 = textInput!!.text.toString().toDouble()
                    } catch (e: NumberFormatException) {
                        textInput!!.text = ""
                    }
                } else textInput!!.text = ""
                dot = if (textInput!!.text.toString().contains(".")) {
                    1
                } else 0
                negative = 0
            }
            R.id.buttonDot -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (textInput!!.text.toString().contains(".")) dot = 1
                if (dot != 1) textInput!!.append(".")
                dot = 1
            }
            R.id.buttonNumber1 -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (sTate !== State.START && sTate !== State.EQUAL && negative == 0) {
                    sTate = State.START
                    textInput!!.text = ""
                }
                textInput!!.append("1")
            }
            R.id.buttonNumber2 -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (sTate !== State.START && sTate !== State.EQUAL && negative == 0) {
                    sTate = State.START
                    textInput!!.text = ""
                }
                textInput!!.append("2")
            }
            R.id.buttonNumber3 -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (sTate !== State.START && sTate !== State.EQUAL && negative == 0) {
                    sTate = State.START
                    textInput!!.text = ""
                }
                textInput!!.append("3")
            }
            R.id.buttonNumber4 -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (sTate !== State.START && sTate !== State.EQUAL && negative == 0) {
                    sTate = State.START
                    textInput!!.text = ""
                }
                textInput!!.append("4")
            }
            R.id.buttonNumber5 -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (sTate !== State.START && sTate !== State.EQUAL && negative == 0) {
                    sTate = State.START
                    textInput!!.text = ""
                }
                textInput!!.append("5")
            }
            R.id.buttonNumber6 -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (sTate !== State.START && sTate !== State.EQUAL && negative == 0) {
                    sTate = State.START
                    textInput!!.text = ""
                }
                textInput!!.append("6")
            }
            R.id.buttonNumber7 -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (sTate !== State.START && sTate !== State.EQUAL && negative == 0) {
                    sTate = State.START
                    textInput!!.text = ""
                }
                textInput!!.append("7")
            }
            R.id.buttonNumber8 -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (sTate !== State.START && sTate !== State.EQUAL && negative == 0) {
                    textInput!!.text = ""
                    sTate = State.START
                }
                textInput!!.append("8")
            }
            R.id.buttonNumber9 -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (sTate !== State.START && sTate !== State.EQUAL && negative == 0) {
                    sTate = State.START
                    textInput!!.text = ""
                }
                if (sTate !== State.START && negative == 0) {
                    sTate = State.START
                    textInput!!.text = ""
                }
                textInput!!.append("9")
            }
            R.id.buttonNumber0 -> {
                if (textInput?.text.toString() == "Infinity") {
                    textInput?.text = ""
                }
                if (textInput?.text.toString() == "Error") {
                    textInput?.text = ""
                }
                if (sTate != State.START && sTate != State.EQUAL || second == 1) {
                    sTate = State.START
                    textInput?.text = ""
                    second = 0
                }
                if (textInput?.text.toString()
                                .isEmpty() || textInput?.text.toString()[0] == '0' && dot == 0
                ) {
                    textInput?.text = "0"
                } else if (textInput?.text.toString()
                                .contains(".")
                ) textInput?.append("0") else textInput?.append("0")
            }
            R.id.buttonPlus -> {
                sTate = State.PLUS
                btn = Btn.PLUS
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                try {
                    num1 = textInput!!.text.toString().toDouble()
                    if (negative == 1) {
                        negative = 0
                    }
                } catch (e: NumberFormatException) {
                    textInput!!.text = "Infinity"
                }
                dot = 0
            }
            R.id.buttonMinus -> {
                sTate = State.MINUS
                btn = Btn.MINUS
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                if (textInput!!.text.toString().isEmpty()) {
                    textInput!!.text = "-"
                    negative = 1
                } else {
                    try {
                        num1 = textInput!!.text.toString().toDouble()
                        if (negative == 1) {
                            negative = 0
                        }
                    } catch (e: NumberFormatException) {
                        textInput!!.text = "Infinity"
                    }
                }
                dot = 0
            }
            R.id.buttonMultiply -> {
                sTate = State.MULTIPLY
                btn = Btn.MULTIPLY
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                try {
                    num1 = textInput!!.text.toString().toDouble()
                    if (negative == 1) {
                        negative = 0
                    }
                } catch (e: NumberFormatException) {
                    textInput!!.text = "Infinity"
                }
                dot = 0
            }
            R.id.buttonDivide -> {
                sTate = State.DIVIDE
                btn = Btn.DIVIDE
                if (textInput!!.text.toString() == "Error") {
                    textInput!!.text = ""
                }
                try {
                    num1 = textInput!!.text.toString().toDouble()
                    if (negative == 1) {
                        negative = 0
                    }
                } catch (e: NumberFormatException) {
                    textInput!!.text = "Infinity"
                }
                dot = 0
            }
            R.id.buttonSqrt -> {
                sTate = State.SQRT
                btn = Btn.SQRT
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                try {
                    num1 = textInput!!.text.toString().toDouble()
                    if (negative == 1) {
                        negative = 0
                    }
                } catch (e: NumberFormatException) {
                    textInput!!.text = "Infinity"
                }
                dot = 0
            }
            R.id.buttonPercent -> {
                sTate = State.PERCENT
                btn = Btn.PERCENT
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                try {
                    num1 = textInput!!.text.toString().toDouble()
                    if (negative == 1) {
                        negative = 0
                    }
                } catch (e: NumberFormatException) {
                    textInput!!.text = "Infinity"
                }
                dot = 0
            }
            R.id.buttonPower -> {
                sTate = State.POW
                btn = Btn.POW
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                try {
                    num1 = textInput!!.text.toString().toDouble()
                    if (negative == 1) {
                        negative = 0
                    }
                } catch (e: NumberFormatException) {
                    textInput!!.text = "Infinity"
                }
                dot = 0
            }
            R.id.buttonResult -> {
                if (textInput!!.text.toString() == "Infinity") {
                    textInput!!.text = ""
                }
                try {
                    num2 = textInput!!.text.toString().toDouble()
                } catch (e: NumberFormatException) {
                    textInput!!.text = "Infinity"
                }
                when {
                    btn === Btn.PLUS -> textInput!!.text =
                            String.format(
                                    Locale.US,  "%.2f",
                                    num1 + num2
                            )
                    btn === Btn.MINUS -> textInput!!.text =
                            String.format(
                                    Locale.US, "%.2f",
                                    num1 - num2
                            )
                    btn === Btn.MULTIPLY -> textInput!!.text =
                            String.format(
                                    Locale.US, "%.2f",
                                    num1 * num2
                            )
                    btn === Btn.DIVIDE -> textInput!!.text =
                            String.format(
                                    Locale.US, "%.2f",
                                    num1 / num2
                            )
                    btn === Btn.POW -> textInput!!.text =
                            String.format(
                                    Locale.US, "%.2f",
                                    num1.pow(num2)
                            )
                    btn === Btn.PERCENT -> textInput!!.text =
                            String.format(
                                    Locale.US, "%.2f",
                                    num1 * 0.01
                            )
                    btn === Btn.SQRT -> textInput!!.text =
                            String.format(Locale.US, "%.2f", sqrt(num1))
                }
                sTate = State.EQUAL
            }
        }
    }
}