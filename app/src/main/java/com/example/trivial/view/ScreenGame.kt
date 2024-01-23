import android.os.CountDownTimer
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
import androidx.compose.runtime.remember
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

    val tiempoInicialMilisegundos = ( myViewModel.ajustes.segundos*1000 ).toLong() // 5 segundos
    val intervaloMilisegundos = 1000L // 1 segundo
    var tiempo:String by rememberSaveable { mutableStateOf("") }

    

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        var questionIndice by rememberSaveable { mutableStateOf( 0 ) }
        var question = myViewModel.preguntas[questionIndice]

        val cuentaAtras = object : CountDownTimer(tiempoInicialMilisegundos, intervaloMilisegundos) {
            override fun onTick(millisUntilFinished: Long) {
                // Se llama cada vez que el contador cuenta un intervalo (cada segundo en este caso)
                val segundosRestantes = millisUntilFinished / 1000
                tiempo = "Segundos restantes: $segundosRestantes"
            }

            override fun onFinish() {
                // Se llama cuando la cuenta atrás termina
                tiempo = "¡Cuenta atrás terminada!"
                myViewModel.quitarCount()
            }
        }

        if (myViewModel.contador % 2 == 0){
            questionIndice = myViewModel.randowPregunta()
            myViewModel.quitarCount()
            cuentaAtras.start()
        } else {
            Text(text = tiempo)
            playQuestions( question, myViewModel )
        }

    }
}

@Composable
fun playQuestions(question: Preguntas, myViewModel: MyViewModel){

    Text(text = question.enunciado , fontSize = 20.sp , fontWeight = FontWeight.Bold )
    botones(question, myViewModel )

}

@Composable
fun botones(question: Preguntas, myViewModel: MyViewModel){

    var color1:Color by remember { mutableStateOf( Color.Gray ) }
    var color2:Color by remember { mutableStateOf( Color.Gray ) }
    var color3:Color by remember { mutableStateOf( Color.Gray ) }
    var color4:Color by remember { mutableStateOf( Color.Gray ) }

    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta1){
                color1 = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                color1 = Color.Red
            }
            myViewModel.quitarCount()
        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(color1)
    ){
        Text(text = question.respuesta1 , fontSize = 25.sp , fontWeight = FontWeight.Bold )
    }

    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta2){
                color2 = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                color2 = Color.Red
            }
            myViewModel.quitarCount()
        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
    colors = ButtonDefaults.buttonColors(color2)
    ){
        Text(text = question.respuesta2 , fontSize = 25.sp , fontWeight = FontWeight.Bold )
    }

    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta3){
                color3 = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                color3 = Color.Red
            }
            myViewModel.quitarCount()
        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(color3)
    ){
        Text(text = question.respuesta3 , fontSize = 25.sp , fontWeight = FontWeight.Bold )
    }

    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta4){
                color4 = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                color4 = Color.Red
            }
            myViewModel.quitarCount()
        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(color4)
    ){
        Text(text = question.respuesta4 , fontSize = 25.sp , fontWeight = FontWeight.Bold )
    }
}








