import repository.SuitRepository
import service.SuitService

fun main() {
	val repository = SuitRepository()
	val suit = SuitService(repository)
	suit.start()
}