import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial.R
import com.example.trivial.navigation.Routes


@Composable
fun ScreenMenu(navController: NavController, myViewModel: MyViewModel) {

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            ScreenMenuHorizontal(myViewModel = myViewModel, navController = navController)
        }
        else -> {
            ScreenMenuVertical(myViewModel = myViewModel, navController = navController)
        }
    }
}

@Composable
fun ScreenMenuHorizontal(myViewModel: MyViewModel, navController: NavController) {

    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight(0.8f).fillMaxWidth(0.4f)
        ){
            Image(
                painter = painterResource(id = R.drawable.logo ) ,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(0.8f)
            )
            Text(text = "Trivial Game" , fontWeight = FontWeight.Black , fontSize = 30.sp)
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight(0.5f)
        ){
            Box(modifier = Modifier.fillMaxHeight(0.5f)){
                PlayButton(navController = navController, myViewModel = myViewModel)
            }
            Box(modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.85f)
            ){
                SettingButton(navController = navController, myViewModel = myViewModel)
            }
        }
    }
}

@Composable
fun ScreenMenuVertical(myViewModel: MyViewModel, navController: NavController) {

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight(0.5f)
        ){
            Image(
                painter = painterResource(id = R.drawable.logo ) ,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(0.8f)
            )
            Text(text = "Trivial Game" , fontWeight = FontWeight.Black , fontSize = 30.sp)
        }

        Row (
            modifier = Modifier.fillMaxHeight(0.4f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(modifier = Modifier.fillMaxWidth(0.5f)){
                PlayButton(navController = navController, myViewModel = myViewModel)
            }
            Box(modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.85f)){
                SettingButton(navController = navController, myViewModel = myViewModel)
            }
        }
    }

}

@Composable
fun SettingButton(navController: NavController , myViewModel: MyViewModel) {

    val color:Color = if (myViewModel.DarkMode) Color.White else Color.Black

    Button(
        onClick = {
            navController.navigate(Routes.PantallaConfiguraciones.route)
        },
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(10.dp)
            .border(3.dp, Color.Blue, RoundedCornerShape(100.dp) )
        , //margen
        colors = ButtonDefaults.buttonColors(color)
    ){
        Image(
            painter = painterResource(id = R.drawable.settings3),
            contentDescription = "Settings" ,
            modifier = Modifier.fillMaxSize()
        )
     }
}

@Composable
fun PlayButton(navController: NavController , myViewModel: MyViewModel) {

    val color:Color = if (myViewModel.DarkMode) Color.White else Color.Black

    Button(
        onClick = {
            myViewModel.recet()
            navController.navigate(Routes.PantallaJuego.route)
        },
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(10.dp)
            .border(3.dp, Color.Blue, RoundedCornerShape(100.dp)), //margen
        colors = ButtonDefaults.buttonColors(color)
    ){
        Image(
            painter = painterResource(id = R.drawable.play),
            contentDescription = "Play",
            modifier = Modifier.padding(10.dp)
        )
    }
}