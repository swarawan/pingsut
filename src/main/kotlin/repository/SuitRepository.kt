package repository

import model.Suit

class SuitRepository {

	private val suitCollection = mutableListOf<Suit>()

	init {
		generateSuitCharacter()
	}

	private fun generateSuitCharacter() {
		val paper = Suit(1, "Kertas")
		val rock = Suit(2, "Batu")
		val scissor = Suit(3, "Gunting")

		paper.winWith = rock
		paper.loseWith = scissor

		rock.winWith = scissor
		rock.loseWith = paper

		scissor.winWith = paper
		scissor.loseWith = rock

		suitCollection.add(paper)
		suitCollection.add(rock)
		suitCollection.add(scissor)
	}

	fun findById(id: Int): Suit? = suitCollection.find { it.id == id }

	fun findByIndex(index: Int): Suit = suitCollection[index]

	fun size() = suitCollection.size
}