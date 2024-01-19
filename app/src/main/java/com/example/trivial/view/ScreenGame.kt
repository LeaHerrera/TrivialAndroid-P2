import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial.model.Dificultad
import com.example.trivial.model.Preguntas


@Composable
fun ScreenGame(navController: NavController, myViewModel: MyViewModel) {


    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        var contadorPreguntas:Int by rememberSaveable { mutableIntStateOf( myViewModel.ajustes.rondas ) }
        var controlCount:Int by rememberSaveable { mutableIntStateOf( contadorPreguntas + 1 ) }
       // var preguntaVacia:Preguntas by rememberSaveable { mutableStateOf(Preguntas(Dificultad.dificil,"h","h","h","h","h", "h") ) }

        //do {
            /* var question:Preguntas by rememberSaveable { mutableStateOf(  Preguntas(Dificultad.dificil,"","","","","", "") ) }
            if (controlCount > contadorPreguntas){
                question = randowPregunta(myViewModel)
                controlCount--
            } */

          //  Text(text = preguntaVacia.enunciado )

           // botones(question = preguntaVacia, contador = {  }, myViewModel = myViewModel )
           // contadorPreguntas--

       // } while ( contadorPreguntas == 0  )



    }
}
@Composable
fun botones(question: Preguntas, contador: () -> Unit, myViewModel: MyViewModel){

    var colorBotones:Array<Color> = Array(4){ Color.Gray }
    var botonCount:Int = 0

    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta1){
                colorBotones[botonCount] = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                colorBotones[botonCount] = Color.Red
            }
            contador
        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(colorBotones[botonCount])
    ){
        Text(text = question.respuesta1 , fontSize = 20.sp , fontWeight = FontWeight.Bold )
    }

    botonCount++
    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta2){
                colorBotones[botonCount] = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                colorBotones[botonCount] = Color.Red
            }
            contador
        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
    colors = ButtonDefaults.buttonColors(colorBotones[botonCount])
    ){
        Text(text = question.respuesta2 , fontSize = 20.sp , fontWeight = FontWeight.Bold )
    }

    botonCount++
    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta3){
                colorBotones[botonCount] = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                colorBotones[botonCount] = Color.Red
            }
            contador
        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(colorBotones[botonCount])
    ){
        Text(text = question.respuesta3 , fontSize = 20.sp , fontWeight = FontWeight.Bold )
    }

    botonCount++
    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta4){
                colorBotones[botonCount] = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                colorBotones[botonCount] = Color.Red
            }
            contador
        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(colorBotones[botonCount])
    ){
        Text(text = question.respuesta4 , fontSize = 20.sp , fontWeight = FontWeight.Bold )
    }
}


// OBTENER PREGUNTA ALEATORIA
fun randowPregunta(myViewModel: MyViewModel):Preguntas{

    val pregunta:Preguntas

    var cadena:Array<Preguntas> = preguntaNivel( myViewModel.ajustes.dificultad , myViewModel.preguntas )
    pregunta = preguntaAleatoria(cadena)

    return pregunta

}

fun preguntaNivel(difficulty:Dificultad, cadena:Array<Preguntas>):Array<Preguntas>{

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

fun preguntaAleatoria( cadena: Array<Preguntas>):Preguntas { //saca un dato tio pregunta de una lista de manera aleatoria

    var palabraAleatoria:Preguntas

    val indiceAleatorio = (cadena.indices).random() // Obtiene un índice aleatorio del array
    palabraAleatoria = cadena[indiceAleatorio] // Devuelve el elemento del array en el índice aleatorio

    return palabraAleatoria
}
