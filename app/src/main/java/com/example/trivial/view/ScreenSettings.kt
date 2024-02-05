import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trivial.R
import com.example.trivial.model.Dificultad


@Composable
fun ScreenSettings(navController: NavController, myViewModel: MyViewModel) {


    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(0.7f)
    ){

        SettingImagen(myViewModel = myViewModel)
        Row {
            ModoOscuro(myViewModel = myViewModel)
            MenuDificultad(myViewModel = myViewModel)
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))

        EscogerRondas(myViewModel = myViewModel)

        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        
        BarraTime(myViewModel = myViewModel)

        Spacer(modifier = Modifier.fillMaxHeight(0.15f))

        VoverButton(navController = navController, myViewModel = myViewModel)
    }
}

@Composable
fun SettingImagen(myViewModel: MyViewModel){

    val img:Int = if (myViewModel.DarkMode) R.drawable.settings2 else R.drawable.settings1
    Image(
        painterResource(id = img),
        contentDescription = "settings" ,
        modifier = Modifier
            .fillMaxHeight(0.3f)
            .padding(20.dp) 
    )
    
}

@Composable
fun ModoOscuro(myViewModel: MyViewModel) {

    var state = myViewModel.DarkMode

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(text = "MODO OSCURO", fontWeight = FontWeight.Black)
        Switch(
            checked = state,
            onCheckedChange = { myViewModel.changeDarkMode(!state) },
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color(179, 0, 0, 255),
                checkedThumbColor = Color(41, 116, 0, 255),
                checkedTrackColor = Color.Transparent,
                uncheckedTrackColor = Color.Transparent,
                checkedBorderColor = Color.Blue,
                uncheckedBorderColor = Color.Blue
            )
        )
    }


}

@Composable
fun EscogerRondas(myViewModel: MyViewModel){

    var text by rememberSaveable { mutableStateOf(myViewModel.ajustes.rondas) }
    val opciones = arrayListOf<Int>( 4 , 6 , 8 , 10 , 14 )

    Text(text = "RONDAS ACTUALES: $text", fontWeight = FontWeight.Black)

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(0.9f)
            .border(3.dp, Color.Blue)
    ){
        repeat(opciones.size){ronda ->

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
            ){
                Text(text = opciones[ronda].toString() )
                RadioButton(
                    selected = (myViewModel.ajustes.rondas == opciones[ronda]),
                    onClick = { myViewModel.changeRondas(opciones[ronda]) ; text = opciones[ronda] }
                )
            }
        }
    }

}
@Composable
fun BarraTime(myViewModel: MyViewModel) {
    var sliderValue by remember { mutableStateOf(10f) }
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(text = "TIME: ${sliderValue.toInt()}s" , fontWeight = FontWeight.Black)
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            onValueChangeFinished = {
                myViewModel.changeSeg( sliderValue.toInt()  )
            },
            valueRange = 5f..30f,
            steps = 24,
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxHeight(0.1f)
        )
    }
   
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDificultad(myViewModel: MyViewModel){

    //DROPMENU
    val dificultades = arrayOf( "Nivel Facil" , "Nivel Normal" , "Nivel Dificil" , "Nivel Experto" )

    //variables para el DROPMENU (opcion multiple)
    var valorDificultad by rememberSaveable { mutableStateOf("Selecciona Dificultad") }
    var mostrar by rememberSaveable { mutableStateOf(false) }

    Box(){
        //TextField para el DROPMENU
        OutlinedTextField(
            //donde guardaremos los datos
            value = valorDificultad,
            onValueChange = { valorDificultad = it },
            //no se puede introducir texto
            enabled = false,
            readOnly = true,
            //para que aparescan las opciones
            modifier = Modifier
                .clickable { mostrar = true }
                .fillMaxWidth(0.8f)
                .padding(top = 30.dp)
                .border(2.dp, Color.Blue)
        )

        //opciones (DROPMENU)
        DropdownMenu(
            //variable booleano, cuando se mostraran las opciones
            expanded = mostrar,
            onDismissRequest = { mostrar = false },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .border(2.dp, Color.Blue),
        ) {
            //recorremos los valores del array
            dificultades.forEach { dificultad -> //cada valor
                //cada opcion se crea
                DropdownMenuItem(
                    text = { Text(text = dificultad , modifier = Modifier.padding(horizontal = 10.dp))  },
                    onClick = {
                        //deja de mostrar el menu
                        mostrar = false
                        //guardamos el valor
                        valorDificultad = dificultad

                        if ( dificultad == "Nivel Facil" ) {
                            myViewModel.changeDifficulty(Dificultad.facil)
                        } else if ( dificultad == "Nivel Normal") {
                            myViewModel.changeDifficulty(Dificultad.normal)
                        } else if ( dificultad == "Nivel Dificil") {
                            myViewModel.changeDifficulty(Dificultad.dificil)
                        } else{
                            myViewModel.changeDifficulty(Dificultad.experto)
                        }

                    }
                )
            }
        }
    }
}



