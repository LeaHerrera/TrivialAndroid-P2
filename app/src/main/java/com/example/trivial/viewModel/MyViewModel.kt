import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trivial.model.Dificultad
import com.example.trivial.model.Pregunta
import com.example.trivial.model.Result
import com.example.trivial.model.Settings

class  MyViewModel:ViewModel () {

    var preguntas:Array<Pregunta> = arrayOf(
        Pregunta(Dificultad.facil,"FACIL1","A","B","C" ,"D","C"),
        Pregunta(Dificultad.facil,"FACIL2","A","B","C" ,"D","D"),
        Pregunta(Dificultad.facil,"normal","A","B","C" ,"D","B"),
        Pregunta(Dificultad.facil,"dificil","A","B","C" ,"D","A"),
        Pregunta(Dificultad.facil,"experto","A","B","C" ,"D","D")
    )
        private set

    var preguntasResult:MutableList< Result > = mutableListOf()
        private set

    var ajustes:Settings = Settings(Dificultad.facil , 5 , 10)
        private set
    var DarkMode by mutableStateOf(false)
        private set

    var respuestasCorrectas by mutableStateOf(0)
        private set

    var contador by mutableStateOf(ajustes.rondas)
        private set

    var indicePregunta by mutableStateOf( 0)
        private set

    var tiempo by mutableStateOf(0)
        private set
    var barraTiempo by mutableStateOf( 0f )
        private set

    fun añadirResCorrecta(){
        respuestasCorrectas++
    }

    // OBTENER PREGUNTA ALEATORIA
    fun randowPregunta(){

        val pregunta:Int

        var cadena:Array<Pregunta> = preguntaNivel( ajustes.dificultad , preguntas )
        pregunta = preguntaAleatoria(cadena)

        indicePregunta = pregunta

    }
    fun changeDarkMode( estado:Boolean){
        DarkMode = estado
    }
    fun changeSeg( num:Int){
        ajustes.segundos = num
    }
    fun changeRondas( num:Int ){
        ajustes = Settings( ajustes.dificultad , num , ajustes.segundos)
    }
    fun changeDifficulty( difficulty:Dificultad){
        ajustes.dificultad = difficulty
    }

    fun añadirResult(question:Pregunta , ganar:Int){
        preguntasResult.add( Result( question , ganar ) )
    }

    fun addReloj(){
        tiempo++
        barraTiempo += 1f / ajustes.segundos
    }
    fun recetReloj(){
        tiempo = 0
        barraTiempo = 0f
    }

    fun quitarCount(){
        contador--
    }
    private fun preguntaNivel(difficulty:Dificultad, cadena:Array<Pregunta>):Array<Pregunta>{

        var cadenaDificultad:Array<Pregunta> // cadena con solo las preguntas de esa dificultad que se retornara
        var listaCadena:MutableList<Pregunta> = mutableListOf() //Lista de almacenaje para contar preguntas de la dificultad buscada
        var preguntaVacia:Pregunta = Pregunta(Dificultad.dificil,"","","","","", "")

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
    fun recet(){
        preguntasResult = mutableListOf()
        respuestasCorrectas = 0
        contador = ajustes.rondas
    }

    private fun preguntaAleatoria( cadena: Array<Pregunta>):Int { //saca un dato tio pregunta de una lista de manera aleatoria

        var indiceAleatorio = 0

        do{
            var seguir = true
             indiceAleatorio = (cadena.indices).random() // Obtiene un índice aleatorio del array

            repeat(preguntasResult.size){
               if ( cadena[indiceAleatorio].enunciado.equals( preguntasResult[it].question.enunciado) ){
                   seguir = false
               }
            }

        } while ( !seguir )

        return indiceAleatorio
    }
}