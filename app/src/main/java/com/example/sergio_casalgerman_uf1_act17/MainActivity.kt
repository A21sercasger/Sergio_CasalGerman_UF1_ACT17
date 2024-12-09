package com.example.sergio_casalgerman_uf1_act17

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var currentPlayer = "X"
    private val board = Array(3) { arrayOfNulls<String>(3) }
    private lateinit var buttons: Array<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons = arrayOf(
            findViewById(R.id.button0), findViewById(R.id.button1), findViewById(R.id.button2),
            findViewById(R.id.button3), findViewById(R.id.button4), findViewById(R.id.button5),
            findViewById(R.id.button6), findViewById(R.id.button7), findViewById(R.id.button8)
        )

        for (i in buttons.indices) {
            val row = i / 3
            val col = i % 3
            buttons[i].setOnClickListener {
                if (board[row][col] == null) {
                    board[row][col] = currentPlayer
                    buttons[i].text = currentPlayer
                    if (checkWinner()) {
                        Toast.makeText(this, "Player $currentPlayer wins!", Toast.LENGTH_LONG).show()
                        resetBoard()
                    } else {
                        currentPlayer = if (currentPlayer == "X") "O" else "X"
                    }
                }
            }
        }
    }

    private fun checkWinner(): Boolean {
        // Comprova files, columnes i diagonals
        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true
        return false
    }

    private fun resetBoard() {
        for (i in buttons.indices) {
            buttons[i].text = ""
        }
        for (row in board.indices) {
            for (col in board[row].indices) {
                board[row][col] = null
            }
        }
        currentPlayer = "X"
    }
}