import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            ScreenResultHorizontal(myViewModel = myViewModel, context = context, navController = navController)
        }
        else -> {
            ScreenResultVertical(myViewModel = myViewModel, context = context, navController = navController)
        }
    }



}
@Composable
fun ScreenResultVertical(myViewModel: MyViewModel , context: Context , navController: NavController){

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()

    ){

        CuadroResultado(myViewModel = myViewModel)

        RespuestasCorrectas(myViewModel = myViewModel)

        Spacer(modifier = Modifier.fillMaxWidth(0.2f))

        VoverButton(navController = navController , myViewModel = myViewModel)
        CompartirButton(text = "¡He optenido ${myViewModel.respuestasCorrectas} / ${myViewModel.ajustes.rondas} en el TRIVIAL!", context = context , myViewModel = myViewModel)
    }
}

@Composable
fun ScreenResultHorizontal(myViewModel: MyViewModel , context: Context , navController: NavController){

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()

    ){

        val texto by rememberSaveable { mutableStateOf("") }
        val imagen = if (myViewModel.respuestasCorrectas == myViewModel.ajustes.rondas) R.drawable.bien else R.drawable.mal

        CuadroResultado(myViewModel = myViewModel)

        Column {

            RespuestasCorrectas(myViewModel = myViewModel)

            Spacer(modifier = Modifier.fillMaxWidth(0.2f))

            VoverButton(navController = navController , myViewModel = myViewModel)
            CompartirButton(text = "¡He optenido ${myViewModel.respuestasCorrectas} / ${myViewModel.ajustes.rondas} en el TRIVIAL!", context = context , myViewModel = myViewModel)

        }
   }

}

@Composable
fun CuadroResultado(myViewModel: MyViewModel){

    val texto by rememberSaveable { mutableStateOf("") }
    val imagen = if (myViewModel.respuestasCorrectas == myViewModel.ajustes.rondas) R.drawable.bien else R.drawable.mal

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(0.9f)
            .border(3.dp, Color.Blue)
    ){

        Image(
            painter = painterResource(id = imagen ) ,
            contentDescription = ""
        )
        Text( text = "${myViewModel.respuestasCorrectas} / ${myViewModel.ajustes.rondas}" , fontSize = 30.sp , fontWeight = FontWeight.Bold )
        Text( text = texto , fontSize = 30.sp , fontWeight = FontWeight.Bold )
    }

}


@Composable
fun RespuestasCorrectas(myViewModel: MyViewModel){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .alpha(0.5f)
            .padding(10.dp)
            .fillMaxWidth(0.9f)
            .border(3.dp, Color.Blue)
    ){

        val mitad = myViewModel.preguntasResult.size / 2
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ){

            Resultado1(myViewModel = myViewModel, mitad = mitad )
            Resultado2(myViewModel = myViewModel, mitad = mitad )

        }
    }
}

@Composable
fun Resultado1 (myViewModel: MyViewModel , mitad :Int){

    var color:Color by remember { mutableStateOf( Color.Gray ) }

    Column (
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(horizontal = 10.dp)
    ){
        for (pregunta in 0 until mitad){

            if (myViewModel.preguntasResult[pregunta].resultado ==  1){
                color = Color.Green
            } else if ( myViewModel.preguntasResult[pregunta].resultado ==  -1 ){
                color = Color.Red
            } else {
                color = Color.DarkGray
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color)
                    .fillMaxWidth(0.9f)
            ){
                Text(text = myViewModel.preguntasResult[pregunta].question.enunciado , fontSize = 20.sp )
            }
        }
    }
}

@Composable
fun Resultado2(myViewModel: MyViewModel, mitad: Int){

    var color:Color by remember { mutableStateOf( Color.Gray ) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
    ){
        for (pregunta in mitad..myViewModel.preguntasResult.lastIndex){

            if (myViewModel.preguntasResult[pregunta].resultado ==  1){
                color = Color.Green
            } else if ( myViewModel.preguntasResult[pregunta].resultado ==  -1 ){
                color = Color.Red
            } else {
                color = Color.DarkGray
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color)
                    .fillMaxWidth(0.9f)
            ){
                Text(text = myViewModel.preguntasResult[pregunta].question.enunciado , fontSize = 20.sp, fontWeight = FontWeight(500) )
            }
        }
    }
}

@Composable
fun CompartirButton(text: String, context: Context , myViewModel: MyViewModel) {

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)

    val colorFondo:Color = if (myViewModel.DarkMode) Color.Gray else Color.Black
    val colorLetra:Color = if (myViewModel.DarkMode) Color.Black else Color.White


    Button(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(5.dp)
            .border(4.dp, Color.Blue, RoundedCornerShape(100.dp))
        ,
        onClick = {
            ContextCompat.startActivity(context, shareIntent, null)
        },
        colors = buttonColors(colorFondo)
    ) {
        Icon(imageVector = Icons.Default.Share, contentDescription = null)
        Text(" Compartir ", fontWeight = FontWeight.Black, color = colorLetra)
    }
}

@Composable
fun VoverButton(navController: NavController , myViewModel: MyViewModel) {

    val colorFondo:Color = if (myViewModel.DarkMode) Color.Gray else Color.Black
    val colorLetra:Color = if (myViewModel.DarkMode) Color.Black else Color.White

    Button(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(5.dp)
            .border(4.dp, Color.Blue, RoundedCornerShape(100.dp))
        ,
        onClick = {
            navController.navigate(Routes.PantallaMenu.route)
        },
        colors = buttonColors(colorFondo)
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu),
            contentDescription = "menu" ,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(0.3f)
        )
        Text(" MENU ", fontWeight = FontWeight.Black , color = colorLetra)
    }
}

