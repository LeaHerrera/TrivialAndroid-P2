import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial.model.Dificultad
import com.example.trivial.model.Pregunta
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
        BarraDeTiempo(myViewModel = myViewModel, question)
    }
}


@Composable
fun playQuestions(question: Pregunta, myViewModel: MyViewModel) {

    Text(text = question.enunciado , fontSize = 20.sp , fontWeight = FontWeight.Bold )
    botones(question, myViewModel )

}

@Composable
fun botones(question: Pregunta, myViewModel: MyViewModel) {


    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row {
                Column (
                    Modifier.fillMaxWidth(0.5f)
                ){
                    pregunta(question = question, enunciado = question.respuesta1, myViewModel = myViewModel)
                    pregunta(question = question, enunciado = question.respuesta3, myViewModel = myViewModel)
                }
                Column {
                    pregunta(question = question, enunciado = question.respuesta2, myViewModel = myViewModel)
                    pregunta(question = question, enunciado = question.respuesta4, myViewModel = myViewModel)
                }
            }
        }
        else -> {
            Column {
                pregunta(question = question, enunciado = question.respuesta1, myViewModel = myViewModel)
                pregunta(question = question, enunciado = question.respuesta2, myViewModel = myViewModel)
                pregunta(question = question, enunciado = question.respuesta3, myViewModel = myViewModel)
                pregunta(question = question, enunciado = question.respuesta4, myViewModel = myViewModel)
            }
        }
    }
}
@Composable
fun pregunta (question: Pregunta , enunciado:String, myViewModel: MyViewModel){

    var color:Color = Color.Gray

    Button(
        onClick = {
            if (question.resultCorrect == enunciado) {
                myViewModel.añadirResCorrecta()
                myViewModel.añadirResult(question , 1)
            } else {
                myViewModel.añadirResult(question , -1)
            }
            myViewModel.quitarCount()
            if (myViewModel.contador != 0){
                myViewModel.randowPregunta()
            }
            myViewModel.recetReloj()
        },
        Modifier
            .fillMaxWidth(0.7f)
            .padding(10.dp)
            .border(3.dp, Color.Blue, RoundedCornerShape(100.dp))
        , //margen
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(text = enunciado, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BarraDeTiempo(myViewModel: MyViewModel, question: Pregunta) {

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
            .fillMaxWidth(0.8f)
        )
    }
    if (myViewModel.tiempo == segTotal) {
        myViewModel.quitarCount()
        if (myViewModel.contador == 0){
            myViewModel.randowPregunta()
        }
        myViewModel.recetReloj()
        myViewModel.añadirResult(question , 0)
    }
}









