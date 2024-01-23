import androidx.lifecycle.ViewModel
import com.example.trivial.model.Dificultad
import com.example.trivial.model.Preguntas
import com.example.trivial.model.Settings

class  MyViewModel:ViewModel () {

    var preguntas:Array<Preguntas> = arrayOf(
        Preguntas(Dificultad.facil,"FACIL","A","B","C" ,"D","C"),
        Preguntas(Dificultad.normal,"normal","A","B","C" ,"D","B"),
        Preguntas(Dificultad.dificil,"dificil","A","B","C" ,"D","A"),
        Preguntas(Dificultad.experto,"experto","A","B","C" ,"D","D")

    )
        private set

    var ajustes:Settings = Settings(Dificultad.facil , 5 , 10)
        private set
    var respuestasCorrectas = 0
        private set

     var contador = ajustes.rondas * 2
        private set

    fun añadirResCorrecta(){
        respuestasCorrectas++
    }

    // OBTENER PREGUNTA ALEATORIA
    fun randowPregunta():Int{

        val pregunta:Int

        var cadena:Array<Preguntas> = preguntaNivel( ajustes.dificultad , preguntas )
        pregunta = preguntaAleatoria(cadena)

        return pregunta

    }

    fun añadirCount(){
        contador++
    }
    fun quitarCount(){
        contador++
    }
    private fun preguntaNivel(difficulty:Dificultad, cadena:Array<Preguntas>):Array<Preguntas>{

        var cadenaDificultad:Array<Preguntas> // cadena con solo las preguntas de esa dificultad que se retornara
        var listaCadena:MutableList<Preguntas> = mutableListOf() //Lista de almacenaje para contar preguntas de la dificultad buscada
        var preguntaVacia:Preguntas = Preguntas(Dificultad.dificil,"","","","","", "")

        //localizamos las preguntas
        for ( pregunta in cadena ){
            if (pregunta.dificultad == difficulty){
                listaCadena.add(pregunta)
            }
        }

        //almacenamos las preguntas
        cadenaDificultad = Array(listaCadena.size){preguntaVacia}
        repeat(listaCadena.size){
            cadenaDificultad[it] = listaCadena[it]
        }

        return cadenaDificultad
    }

    private fun preguntaAleatoria( cadena: Array<Preguntas>):Int { //saca un dato tio pregunta de una lista de manera aleatoria

        val indiceAleatorio = (cadena.indices).random() // Obtiene un índice aleatorio del array

        return indiceAleatorio
    }
}