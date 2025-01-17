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

    private fun checkForAWin(): Boolean {
        // Horizontal check
        for (row in buttons) {
            if (row[0].text == row[1].text && row[1].text == row[2].text && row[0].text.isNotEmpty()) {
                return true
            }
        }

        // Vertical check
        for (i in 0..2) {
            if (buttons[0][i].text == buttons[1][i].text && buttons[1][i].text == buttons[2][i].text && buttons[0][i].text.isNotEmpty()) {
                return true
            }
        }

        // Diagonal check
        if (buttons[0][0].text == buttons[1][1].text && buttons[1][1].text == buttons[2][2].text && buttons[0][0].text.isNotEmpty()) {
            return true
        }
        if (buttons[0][2].text == buttons[1][1].text && buttons[1][1].text == buttons[2][0].text && buttons[0][2].text.isNotEmpty()) {
            return true
        }

        return false
    }

    private fun checkTie(): Boolean {
        for (row in buttons) {
            for (button in row) {
                if (button.text.isEmpty()) {
                    return false
                }
            }
        }
        return true
    }

    private fun showWinnerDialog(message: String) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Game Over")
        builder.setMessage(message)

        builder.setPositiveButton("OK") { _, _ ->
            resetBoard()
        }

        val dialog = builder.create()
        dialog.show()
    }
}
