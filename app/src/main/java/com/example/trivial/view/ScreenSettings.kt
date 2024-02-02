import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController


@Composable
fun ScreenSettings(navController: NavController, myViewModel: MyViewModel) {


    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        Text(text = "pantalla Ajustes")
        ModoOscuro(myViewModel = myViewModel)
        
        EscogerSeundos(myViewModel = myViewModel)
        
        VoverButton(navController = navController)
    }
}

@Composable
fun ModoOscuro(myViewModel: MyViewModel) {

    var state = myViewModel.DarkMode

    Switch(
        checked = state,
        onCheckedChange = { myViewModel.changeDarkMode(!state) },
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color(179, 0, 0, 255),
            checkedThumbColor = Color(41, 116, 0, 255),
            checkedTrackColor = Color.Gray,
            uncheckedTrackColor = Color.Black,
            checkedBorderColor = Color.Blue,
            uncheckedBorderColor = Color.Blue
        )
    )

}

@Composable
fun EscogerSeundos(myViewModel: MyViewModel){

    Text(text = "4")
    RadioButton(selected = (myViewModel.ajustes.rondas == 4), onClick = { myViewModel.changeRondas(4) })

    Text(text = "6")
    RadioButton(selected = (myViewModel.ajustes.rondas == 6), onClick = { myViewModel.changeRondas(6) })

    Text(text = "8")
    RadioButton(selected = (myViewModel.ajustes.rondas == 8), onClick = { myViewModel.changeRondas(8) })

    Text(text = "10")
    RadioButton(selected = (myViewModel.ajustes.rondas == 10), onClick = { myViewModel.changeRondas(10) })

    Text(text = "14")
    RadioButton(selected = (myViewModel.ajustes.rondas == 14), onClick = { myViewModel.changeRondas(14) })



}

