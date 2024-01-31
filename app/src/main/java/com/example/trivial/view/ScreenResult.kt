import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.trivial.R
import com.example.trivial.navigation.Routes


@Composable
fun EndScreen(navController: NavController, myViewModel: MyViewModel) {

    val context = LocalContext.current

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()

    ){

        val texto by rememberSaveable { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.Gray)
                .alpha(0.5f)
                .padding(10.dp)
                .fillMaxWidth(0.9f)
                .border(3.dp, Color.Blue)
        ){

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground ) ,
                contentDescription = ""
            )
            Text( text = "${myViewModel.respuestasCorrectas} / ${myViewModel.ajustes.rondas}" , fontSize = 30.sp , fontWeight = FontWeight.Bold )
            Text( text = texto , fontSize = 30.sp , fontWeight = FontWeight.Bold )
        }

        RespuestasCorrectas(myViewModel = myViewModel)

        VoverButton(navController = navController)
        CompartirButton(text = "", context = context )
    }
}
@Composable
fun RespuestasCorrectas(myViewModel: MyViewModel){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Gray)
            .alpha(0.5f)
            .padding(10.dp)
            .fillMaxWidth(0.9f)
            .border(3.dp, Color.Blue)
    ){

        val mitad = myViewModel.preguntasResult.size / 2
        Row (
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ){
            for (pregunta in 0..mitad){
                Column (
                    modifier = Modifier
                        .background(Color.Gray)
                        .alpha(0.6f)
                        .fillMaxWidth()
                ){
                    Text(text = myViewModel.preguntasResult[pregunta].question.enunciado , fontSize = 5.sp)
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ){
            for (pregunta in mitad+1..myViewModel.preguntasResult.lastIndex){
                Column (
                    modifier = Modifier
                        .background(Color.Gray)
                        .alpha(0.6f)
                        .fillMaxWidth()
                ){
                    Text(text = myViewModel.preguntasResult[pregunta].question.enunciado , fontSize = 5.sp)
                }
            }
        }

    }

}


@Composable
fun CompartirButton(text: String, context: Context) {

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)

    Button(
        modifier = Modifier
            .fillMaxWidth(0.5f),
        onClick = {
            ContextCompat.startActivity(context, shareIntent, null)
        },
        colors = buttonColors(Color.Blue)
    ) {
        Icon(imageVector = Icons.Default.Share, contentDescription = null)
        Text(" Compartir ")
    }
}

@Composable
fun VoverButton(navController: NavController) {

    Button(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(15.dp),
        onClick = {
            navController.navigate(Routes.PantallaMenu.route)
        },
        colors = buttonColors(Color.Blue)
    ) {
        Text(" MENU ")
    }
}

