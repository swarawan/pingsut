package service

import model.Suit
import repository.SuitRepository

class SuitService(private val repository: SuitRepository) {

	private val winCondition = 3
	private var playerScore = 0
	private var compScore = 0

	fun start() {
		println("=== GAME SUIT ===")
		printCollection()

		print("Pilih : ")
		val playerChoose = readln().toInt()
		when (validateUserInput(playerChoose)) {
			false -> start()
			else -> runRound(playerChoose)
		}
	}

	private fun printCollection(index: Int = 0) {
		val selectedSuit = repository.findByIndex(index)
		println("${selectedSuit.id}. ${selectedSuit.name}")

		var newIndex = index
		val maxIndexCollection = repository.size()
		if (newIndex < maxIndexCollection - 1) {
			newIndex += 1
			printCollection(newIndex)
		}
	}

	private fun validateUserInput(userInput: Int) = when {
		userInput < 1 || userInput > 3 -> {
			println("xxx | Inputan tidak dapat di proses")
			println()
			false
		}

		else -> true
	}

	private fun runRound(playerChoose: Int) {
		val playerSuit = repository.findById(playerChoose)
		val computerSuit = repository.findById(3)

		when {
			playerSuit == null || computerSuit == null -> start()
			else -> round(playerSuit, computerSuit)
		}
		winnerCheck()
	}

	private fun round(playerSuit: Suit, compSuit: Suit) {
		println("Anda memilih: ${playerSuit.name}")
		println("Computer memilih: ${compSuit.name}")

		when {
			playerSuit.winWith == compSuit -> {
				playerScore++
				println("Anda menang")
			}

			playerSuit.loseWith == compSuit -> {
				compScore++
				println("Computer menang")
			}

			else -> return
		}
	}

	private fun winnerCheck() {
		println("Score anda : $playerScore")
		println("Score computer : $compScore")
		when {
			compScore == winCondition -> println("Computer Menang")
			playerScore == winCondition -> println("Anda menang")
			else -> start()
		}
	}
}