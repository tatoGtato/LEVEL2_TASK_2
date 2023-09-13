package com.example.task_2

import android.annotation.SuppressLint
import android.media.audiofx.DynamicsProcessing.Eq
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task_2.ui.theme.TASK_2Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TASK_2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Struct()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Struct() {
    var equation by remember { mutableStateOf(Equation("-", "-", "?")) }
    val context = LocalContext.current
    val localDensity = LocalDensity.current
    var HeightDp by remember { mutableStateOf(0.dp) }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    HeightDp = with(localDensity) { coordinates.size.height.toDp() }},
                title = {
                    Text(
                        text = context.getString(R.string.app_name),
                        color = Color(140,176,78),
                        fontFamily = cartoon
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(92,69,45)
                )
            )
        },
        modifier = Modifier.background(Color(225,223,182)),
        content = {
                padding -> Logic(Modifier.padding(padding),
                                HeightDp,
                                equation,
                                rigthPromt = {
                                    equation = Equation("🧌","🧌","🧌")
                                },
                                checkPromt =  {
                                    checkPromt(equation)
                                }
                            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    equation =  RandomPromt()
                },
                containerColor = Color 	(197,238,125),
                icon = { Icon(Icons.Filled.ArrowForward,
                    "Extended floating action button.", tint = Color 	(92,69,45)) },
                text = { Text(text = context.getString(R.string.next),
                    color = Color 	(92,69,45)) },
            )

        }
    )
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Logic(modifier: Modifier,
          Topbar: Dp,
          equation: Equation,
          rigthPromt:()->Unit,
          checkPromt:(Equation) -> Boolean) {
    val context = LocalContext.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color(225,223,182))
            .padding(top = Topbar + 5.dp)
            .fillMaxSize(),
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = context.getString(R.string.title),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = cartoon,
                color = Color( 	122,146,68)
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = context.getString(R.string.title2),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = cartoon,
                color = Color( 	122,146,68))
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ){
            Text(text = context.getString(R.string.a),
                fontSize = 50.sp,
                fontFamily = shrek,
                color = Color(92,69,45))
            Text(text = context.getString(R.string.b),
                fontSize = 50.sp,
                fontFamily = shrek,
                color = Color 	(92,69,45))
            Text(text = context.getString(R.string.c),
                fontSize = 50.sp,
                fontFamily = shrek,
                color = Color 	(92,69,45))
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = equation.firstArg,
                fontSize = 40.sp,
                fontFamily = shrek,
                color = Color 	(92,69,45))
            Text(text = equation.secondArg,
                fontSize = 40.sp,
                fontFamily = shrek,
                color = Color(92,69,45))
            Text(text = equation.answer, fontSize = 40.sp,
                modifier = Modifier.padding(end = 30.dp),
                color = Color(92,69,45),
                fontFamily = shrek
            )
        }
        Row {
            if (equation.answer != "?"){
                Button(
                    modifier = Modifier.padding(10.dp).height(50.dp).width(110.dp),
                    colors = ButtonDefaults.buttonColors(Color(197,238,125)),
                    border = BorderStroke(width = 3.dp, color = Color(92,69,45)),
                    onClick = {
                        if(checkPromt(equation)){
                            Toast.makeText(context, context.getString(R.string.answer_is_true),
                                Toast.LENGTH_SHORT).show()
                            rigthPromt()
                        } else {
                            Toast.makeText(context, context.getString(R.string.wrong_answer),
                                Toast.LENGTH_SHORT).show()
                        }
                    }) {
                    Text(text = context.getString(R.string.button_true), color = Color(92,69,45))
                }
                Button(
                    modifier = Modifier.padding(10.dp).height(50.dp).width(110.dp),
                    colors = ButtonDefaults.buttonColors(Color(197,238,125)),
                    border = BorderStroke(width = 3.dp, color = Color(92,69,45)),
                    onClick = {
                        if(!checkPromt(equation)){
                            Toast.makeText(context, context.getString(R.string.answer_is_false),
                                Toast.LENGTH_SHORT).show()
                            rigthPromt()
                        } else {
                            Toast.makeText(context, context.getString(R.string.wrong_answer),
                                Toast.LENGTH_SHORT).show()
                        }
                    }) {
                    Text(text = context.getString(R.string.button_false), color = Color(92,69,45))
                }
            }
        }
    }

}

//Dta class que genera el tipo "equation"
data class Equation(
    val firstArg: String,
    val secondArg: String,
    var answer: String
)

fun RandomPromt(): Equation{
    var val1 = (0..1).random()
    var val2 = (0..1).random()
    var val3 = (0..1).random()
//    var val1 = 1
//    var val2 = 1
//    var val3 = 1
    var opts = listOf("T", "F")

    return (Equation(opts[val1], opts[val2], opts[val3]))
}

fun checkPromt(equation: Equation): Boolean{
    var lista = mutableListOf("", "", "")
    if (equation.firstArg == "T") { lista[0] = "true" } else lista[0] = "false"
    if (equation.secondArg == "T") { lista[1] = "true" } else lista[1] = "false"
    if (equation.answer == "T") { lista[2] = "true" } else lista[2] = "false"
    return (lista[0].toBoolean() && lista[1].toBoolean()).toString() == lista[2]

}