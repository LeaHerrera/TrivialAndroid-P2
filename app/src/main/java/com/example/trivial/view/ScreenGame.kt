import android.os.CountDownTimer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.example.trivial.model.Preguntas
import com.example.trivial.navigation.Routes
import kotlinx.coroutines.delay


@Composable
fun ScreenGame(navController: NavController, myViewModel: MyViewModel) {



    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        var question = myViewModel.preguntas[myViewModel.indicePregunta]

        if (myViewModel.contador == 0){
            navController.navigate(Routes.PantallaFinal.route)
        }
        playQuestions( question, myViewModel )
        barraDeTiempo(myViewModel = myViewModel)
    }
}


@Composable
fun playQuestions(question: Preguntas, myViewModel: MyViewModel) {

    Text(text = question.enunciado , fontSize = 20.sp , fontWeight = FontWeight.Bold )
    botones(question, myViewModel )

}

@Composable
fun botones(question: Preguntas, myViewModel: MyViewModel) {

    Column {
        pregunta(respuesta = question.resultCorrect, enunciado = question.respuesta1, myViewModel = myViewModel)
        pregunta(respuesta = question.resultCorrect, enunciado = question.respuesta2, myViewModel = myViewModel)
        pregunta(respuesta = question.resultCorrect, enunciado = question.respuesta3, myViewModel = myViewModel)
        pregunta(respuesta = question.resultCorrect, enunciado = question.respuesta4, myViewModel = myViewModel)
    }

}
@Composable
fun pregunta (respuesta:String , enunciado:String, myViewModel: MyViewModel){

    var color:Color by remember {  mutableStateOf( Color.Gray ) }
    var boton by remember {  mutableStateOf( false ) }

    if( myViewModel.tiempo == 2 && boton){
        color = Color.Gray
        myViewModel.recetReloj()
        boton = false
    }

    Button(
        onClick = {
            if (respuesta == enunciado) {
                color = Color.Green
                myViewModel.añadirResCorrecta()
            } else {
                color = Color.Red
            }
            myViewModel.recetReloj()
            boton = true
            myViewModel.quitarCount()
            myViewModel.randowPregunta()
        },
        Modifier
            .width(300.dp)
            .padding(10.dp), //margen
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(text = enunciado, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }
}

fun espera(seg :Int){
    Thread.sleep((seg*100).toLong())
}
@Composable
fun barraDeTiempo(myViewModel: MyViewModel) {

    val segTotal = myViewModel.ajustes.segundos

    val animatedProgress by animateFloatAsState(
        targetValue = myViewModel.barraTiempo ,
        label = ""
    )
    LaunchedEffect(myViewModel.barraTiempo ) {
        while (myViewModel.barraTiempo < 1f) {
            delay(1000)
            myViewModel.addReloj()
        }
    }
    Column() {
        Text(text = "  ${myViewModel.tiempo}s / ${segTotal}s" , modifier = Modifier.padding(top = 25.dp))
        LinearProgressIndicator(progress = animatedProgress , modifier = Modifier
            .height(10.dp)
            .fillMaxWidth(0.8f))
    }
    if (myViewModel.tiempo == segTotal) {
        myViewModel.randowPregunta()
        myViewModel.quitarCount()
        myViewModel.recetReloj()
    }
}








