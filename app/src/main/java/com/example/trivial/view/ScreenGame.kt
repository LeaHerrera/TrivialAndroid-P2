import android.os.CountDownTimer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.trivial.model.Preguntas
import com.example.trivial.navigation.Routes


@Composable
fun ScreenGame(navController: NavController, myViewModel: MyViewModel) {



    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        val tiempoInicialMilisegundos = ( myViewModel.ajustes.segundos*1000 ).toLong() // 5 segundos
        val intervaloMilisegundos = 1000L // 1 segundo
        var tiempo:String by rememberSaveable { mutableStateOf("") }


        var question = myViewModel.preguntas[myViewModel.indicePregunta]

        val cuentaAtras = object : CountDownTimer(tiempoInicialMilisegundos, intervaloMilisegundos) {
            override fun onTick(millisUntilFinished: Long) {
                // Se llama cada vez que el contador cuenta un intervalo (cada segundo en este caso)
                val segundosRestantes = millisUntilFinished / 1000
                tiempo = "Segundos restantes: $segundosRestantes"
                myViewModel.MantenerReloj()
            }

            override fun onFinish() {
                // Se llama cuando la cuenta atrás termina
                tiempo = "¡Se acabo el tiempo!"
                myViewModel.quitarCount()

                if (myViewModel.contador != 0){
                    myViewModel.añadirReloj()
                    myViewModel.randowPregunta()
                }
            }
        }

        if (myViewModel.contador == 0){
            navController.navigate(Routes.PantallaFinal.route)
        }

        if (myViewModel.reloj){
            cuentaAtras.cancel()
            cuentaAtras.start()
        }

        Text(text = tiempo)
        Text(text = " ${myViewModel.reloj}")
        playQuestions( question, myViewModel )

    }
}


@Composable
fun playQuestions(question: Preguntas, myViewModel: MyViewModel) {

    Text(text = question.enunciado , fontSize = 20.sp , fontWeight = FontWeight.Bold )
    botones(question, myViewModel )

}

@Composable
fun botones(question: Preguntas, myViewModel: MyViewModel) {

    var color1: Color = (Color.Gray)
    var color2: Color = (Color.Gray)
    var color3: Color = (Color.Gray)
    var color4: Color = (Color.Gray)

    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta1) {
                color1 = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                color1 = Color.Red
            }
            myViewModel.quitarCount()
            myViewModel.randowPregunta()
            myViewModel.añadirReloj()
        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(color1)
    ) {
        Text(text = question.respuesta1, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }

    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta2) {
                color2 = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                color2 = Color.Red
            }
            myViewModel.quitarCount()
            myViewModel.randowPregunta()
            myViewModel.añadirReloj()

        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(color2)
    ) {
        Text(text = question.respuesta2, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }

    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta3) {
                color3 = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                color3 = Color.Red
            }
            myViewModel.quitarCount()
            myViewModel.randowPregunta()
            myViewModel.añadirReloj()

        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(color3)
    ) {
        Text(text = question.respuesta3, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }

    Button(
        onClick = {
            if (question.resultCorrect == question.respuesta4) {
                color4 = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                color4 = Color.Red
            }
            myViewModel.quitarCount()
            myViewModel.randowPregunta()
            myViewModel.añadirReloj()


        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(color4)
    ) {
        Text(text = question.respuesta4, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }

}








