package model

data class Suit(
	val id: Int,
	val name: String,
	var winWith: Suit? = null,
	var loseWith: Suit? = null
)