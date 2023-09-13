package com.example.task_2.ui.theme

import android.os.Build
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task_2.Equation
import com.example.task_2.R
import com.example.task_2.cartoon
import com.example.task_2.checkPromt
import com.example.task_2.shrek


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Logic(modifier: Modifier, Topbar: Dp, equation: Equation) {
    val context = LocalContext.current
    var equation2 = equation
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
                color = Color( 	122,146,68)
            )
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
                color = Color(92,69,45)
            )
            Text(text = context.getString(R.string.b),
                fontSize = 50.sp,
                fontFamily = shrek,
                color = Color 	(92,69,45)
            )
            Text(text = context.getString(R.string.c),
                fontSize = 50.sp,
                fontFamily = shrek,
                color = Color 	(92,69,45)
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = equation2.firstArg,
                fontSize = 40.sp,
                fontFamily = shrek,
                color = Color 	(92,69,45)
            )
            Text(text = equation2.secondArg,
                fontSize = 40.sp,
                fontFamily = shrek,
                color = Color(92,69,45)
            )
            Text(text = equation2.answer, fontSize = 40.sp,
                modifier = Modifier.padding(end = 30.dp),
                color = Color(92,69,45),
                fontFamily = shrek
            )
        }
        Row {
            if (equation2.answer != "?"){
                Button(
                    modifier = Modifier.padding(10.dp).height(50.dp).width(110.dp),
                    colors = ButtonDefaults.buttonColors(Color(197,238,125)),
                    border = BorderStroke(width = 3.dp, color = Color(92,69,45)),
                    onClick = {
                        if(checkPromt(equation2)){
                            Toast.makeText(context, context.getString(R.string.answer_is_true),
                                Toast.LENGTH_SHORT).show()
                            equation2 = Equation("🧌","🧌","🧌")
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
                        if(!checkPromt(equation2)){
                            Toast.makeText(context, context.getString(R.string.answer_is_false),
                                Toast.LENGTH_SHORT).show()
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