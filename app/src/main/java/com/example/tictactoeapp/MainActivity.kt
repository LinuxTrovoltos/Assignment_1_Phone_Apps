package com.example.tictactoeapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var currentPlayer = "X"
    private var isGameOver = false
    private lateinit var buttons: Array<Array<Button>>

    private fun changeTurns() {
        if (!isGameOver) {
            currentPlayer = if (currentPlayer == "X") "O" else "X"
            Toast.makeText(this, "$currentPlayer, it's your turn!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeButtons() {
        for (row in buttons) {
            for (button in row) {
                button.setOnClickListener {
                    if (button.text.isEmpty() && !isGameOver) {
                        button.text = currentPlayer
                        if (checkForAWin()) {
                            isGameOver = true
                            showWinnerDialog("$currentPlayer WINS!")
                        } else if (checkTie()) {
                            isGameOver = true
                            showWinnerDialog("IT'S A TIE!")
                        } else {
                            changeTurns()
                        }
                    }
                }
            }
        }
    }



    private fun resetBoard() {
        for (row in buttons) {
            for (button in row) {
                button.text = ""
            }
        }
        currentPlayer = "X"
        isGameOver = false
        Toast.makeText(this, "Game reset! X starts.", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons = arrayOf(
            arrayOf(findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3)),
            arrayOf(findViewById(R.id.button4), findViewById(R.id.button5), findViewById(R.id.button6)),
            arrayOf(findViewById(R.id.button7), findViewById(R.id.button8), findViewById(R.id.button9))
        )

        initializeButtons()
    }
}
