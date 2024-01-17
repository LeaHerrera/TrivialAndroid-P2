
data class Preguntas(
    val dificultad: dificultad ,
    val enunciado: String,
    val respuesta1:String,
    val respuesta2: String,
    val respuesta3: String,
    val resultCorrect:Int
)

enum class dificultad {
    facil , normal , dificil , experto
}
enum class categoria {
    geografia , Historia , entretenimiento , ciencias , deportes
}