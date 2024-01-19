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

    fun añadirResCorrecta(){
        respuestasCorrectas++
    }
    fun arrayPreguntas():Array<Preguntas> {

        val cadena:Array<Preguntas> = preguntas
        return cadena

    }
}