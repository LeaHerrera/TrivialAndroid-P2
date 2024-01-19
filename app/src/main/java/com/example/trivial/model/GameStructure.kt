package com.example.trivial.model
data class Preguntas(
    val dificultad: Dificultad ,
    val enunciado: String,
    val respuesta1:String,
    val respuesta2: String,
    val respuesta3: String,
    val respuesta4: String,
    val resultCorrect:String
)

enum class Dificultad {
    facil , normal , dificil , experto
}
enum class categoria {
    geografia , Historia , entretenimiento , ciencias , deportes
}
data class Settings (
    var dificultad: Dificultad,
    var rondas:Int,
    var segundos:Int
)