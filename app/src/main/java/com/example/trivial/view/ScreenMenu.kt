import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial.R
import com.example.trivial.navigation.Routes


@Composable
fun ScreenMenu(navController: NavController, myViewModel: MyViewModel) {


    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){


        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground ) ,
            contentDescription = ""
        )
        Text(text = "Trivial Game")

        val context = LocalContext.current
        Button(
            onClick = {
                navController.navigate(Routes.PantallaJuego.route)
            },
            Modifier
                .width(300.dp)
                .padding(10.dp), //margen
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ){
            Text(text = "New Game" , fontSize = 20.sp , fontWeight = FontWeight.Bold )
        }

        Button(
            onClick = {
                navController.navigate(Routes.PantallaConfiguraciones.route)
            },
            Modifier
                .width(300.dp)
                .padding(10.dp)
            , //margen
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ){
            Text(text = "Ajustes" , fontSize = 20.sp , fontWeight = FontWeight.Bold )
        }
    }
}

