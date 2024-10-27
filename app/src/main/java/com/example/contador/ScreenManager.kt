package com.example.contador

import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contador.ui.theme.*


@Composable
fun HomeScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen"){
        composable("first_Screen") {
            Screen1(navController = navController)
        }
        composable(
            route ="second_Screen/{start}/{work}/{rest}",

            ) {
                backStackEntry ->
            val start= backStackEntry.arguments?.getString("start")
            val work= backStackEntry.arguments?.getString("work")
            val rest= backStackEntry.arguments?.getString("rest")
            if (start!= null && work!= null && rest != null) {
                Screen2(navController = navController, start,work,rest)
            }
        }
        composable(
            route = "third_Screen/{start}/{work}/{rest}",

            ) {
                backStackEntry ->
            val start= backStackEntry.arguments?.getString("start")
            val work= backStackEntry.arguments?.getString("work")
            val rest= backStackEntry.arguments?.getString("rest")
            if (start!= null && work!= null && rest != null) {
                Screen3(navController = navController, start, work, rest)
            }
        }
        composable(
            route = "fourth_Screen/{start}/{work}/{rest}",

            ) {
                backStackEntry ->
            val start= backStackEntry.arguments?.getString("start")
            val work= backStackEntry.arguments?.getString("work")
            val rest= backStackEntry.arguments?.getString("rest")
            if (start!= null && work!= null && rest != null) {
                Screen4(navController = navController, start, work, rest)
            }
        }
    }

}
fun checkSettings(set : Int, work : Int, rest : Int): Boolean {
    return set != 0 && work != 0 && rest != 0
}
// Screen Settings
@Composable
fun Screen1(navController: NavController) {
    // Defaults values
    val myContext = LocalContext.current
    var showDialog by remember {mutableStateOf(false)}
    var sets by rememberSaveable { mutableIntStateOf(4) }
    var work by rememberSaveable { mutableIntStateOf(5) }
    var rest by rememberSaveable { mutableIntStateOf(10) }
    var nplayer : MediaPlayer
    if(showDialog) {
        AlertDialogExample({showDialog = false},{showDialog = false},"Guardar preajuste")
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = BacgorundScreen1)
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TimeSection(
            label = "Intervalos",
            time = sets,
            onIncrease= {sets++},
            onDecrease = {if (sets>0)sets-- }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        TimeSection(
            label = "Trabajo",
            time = work,
            onIncrease= {work++},
            onDecrease = {if (work>0)work-- }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        TimeSection(
            label = "Descanso",
            time = rest,
            onIncrease= {rest++},
            onDecrease = {if (rest>0)rest-- }
        )
        Spacer(modifier = Modifier.padding(30.dp))
        Button(
            onClick = {
                if (checkSettings(sets,work,rest)) {
                    navController.navigate(
                        "second_Screen/{start}/{work}/{rest}".replace(
                            oldValue = "{start}",
                            newValue = sets.toString()
                        ).replace(
                            oldValue = "{work}",
                            newValue = work.toString()
                        ).replace(
                            oldValue = "{rest}",
                            newValue = rest.toString()
                        )
                    )
                }
//                nplayer = MediaPlayer.create(myContext, R.raw.noti);
//                nplayer.start()
            }, shape = RectangleShape
        ) {
            Text("Empezar actividad")
        }
        Button(
            onClick = {
                showDialog = true

            }, shape = RectangleShape
        ) {
            Text("Guardar preajustes")
        }


    }

}
//Screen 10 second
@Composable
fun Screen2(navController: NavController, start: String?, work: String?, rest: String?) {
    var prepareTime by remember { mutableLongStateOf(10L) }
    val sets by remember { mutableIntStateOf(start?.toInt() ?:0) }
    val miConterDown by remember{ mutableStateOf(CounterDown(10, {newvalue -> prepareTime = newvalue}))}

    LaunchedEffect(Unit) {
        miConterDown.start()
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundScreen2)
            .padding(10.dp)
    ){
        Text(text = "$sets Sets Restantes ", fontSize = 30.sp, fontStyle = FontStyle.Italic , fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("$prepareTime", fontSize = 30.sp, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "¡¡PREPÁRATE!!", color = TextScreen2, fontWeight = FontWeight.Bold, fontSize = 45.sp, textDecoration = TextDecoration.Underline, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Monospace)
        if (prepareTime<=0) {
            LaunchedEffect(Unit) {
                navController.navigate("third_Screen/{start}/{work}/{rest}".replace(
                    oldValue = "{start}",
                    newValue = sets.toString()
                ).replace(
                    oldValue = "{work}",
                    newValue = work.toString()
                ).replace(
                    oldValue = "{rest}",
                    newValue = rest.toString()
                ))
            }
            //miConterDown.cancel()
        }
    }

}


//Work's screen
@Composable
fun Screen3(navController: NavController, start: String?, work: String?, rest: String?) {
    val setsRemaining by remember { mutableIntStateOf(start?.toInt() ?:0) }
    var workTime by remember { mutableLongStateOf(work?.toLong() ?:0) }
    val restTime by remember { mutableLongStateOf(rest?.toLong() ?:0) }
    var counter by remember { mutableStateOf<CounterDown?>(null) }
    val currentScreen by remember { mutableStateOf("work") }
    var buttonInitOrPause by remember { mutableStateOf("Pausar") }
    var workTimeSaved by remember { mutableLongStateOf(workTime) }

    LaunchedEffect(Unit) {
        workTimeSaved = workTime
        counter = CounterDown(work?.toLong() ?: 10, { newvalue -> workTime = newvalue})
        counter?.start()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundScreen3)
    ) {
        val timeLeft = if (currentScreen == "work") workTime else restTime
        Text(text = "$setsRemaining Sets Restantes ", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "$timeLeft",  fontSize = 30.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Tiempo restante ", color = Color.Blue, fontSize = 20.sp)
        Text(text = "¡VAMOS TÚ PUEDES!", color = TestScreen3, fontSize = 40.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)

        if (timeLeft<=0){
            LaunchedEffect(Unit) {
                navController.navigate("fourth_Screen/{start}/{work}/{rest}".replace(
                    oldValue = "{start}",
                    newValue = start.toString()
                ).replace(
                    oldValue = "{work}",
                    newValue = work.toString()
                ).replace(
                    oldValue = "{rest}",
                    newValue = rest.toString()
                ))}
        }

        Button(onClick = {
            if (counter?.counterState == false){
                buttonInitOrPause = "Pausar"
                if (setsRemaining > 0) {
                    counter = CounterDown(timeLeft) { remaining ->
                            workTime = remaining
                    }
                    counter?.start()
                }
            }else{
                buttonInitOrPause = "Reanudar"
                counter?.cancel()
            }

        }) {
            Text(text = buttonInitOrPause)
        }

        Button(onClick = {
            counter?.cancel()
            counter?.counterState = false
            buttonInitOrPause = "Pausar"
            workTime = work?.toLong() ?: 60L
            counter?.start()

        }) {
            Text("Reiniciar")
        }
    }
}
// Rest's screen
@Composable
fun Screen4(navController: NavController, start: String?, work: String?, rest: String?) {
    var restTime by remember { mutableLongStateOf(rest?.toLong() ?: 0) }
    var setsRemaining by remember { mutableIntStateOf(start?.toInt() ?: 0) }
    val miConterDown by remember{ mutableStateOf(CounterDown(10, {newvalue -> restTime = newvalue}))}
    LaunchedEffect(Unit) {
        miConterDown.start()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundScreen4)
    ) {
        Text(text = "$setsRemaining Sets Restantes ", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "$restTime", fontSize = 20.sp )
        Text(text = "Descansa", fontSize = 45.sp, fontWeight = FontWeight.Bold, color = TextScreen4)




            if (setsRemaining<=0) {
                LaunchedEffect(Unit) {
                navController.navigate("first_Screen")
                    }
            }else{
                if (setsRemaining>0 && restTime<=0){
                LaunchedEffect(Unit) {
                    setsRemaining -= 1
                    navController.navigate("second_Screen/{start}/{work}/{rest}".replace(
                        oldValue = "{start}",
                        newValue = setsRemaining.toString()
                    ).replace(
                        oldValue = "{work}",
                        newValue = work.toString()
                    ).replace(
                        oldValue = "{rest}",
                        newValue = rest.toString()
                    ))
                              }}
            }
        }

    }

// Timer settings's layout
@Composable
fun TimeSection(
    label : String,
    time : Int,
    onIncrease:()-> Unit,
    onDecrease:()-> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "$time", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(
                onClick = onDecrease,
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                shape = RoundedCornerShape(topEnd = 0.dp, topStart = 10.dp, bottomEnd = 20.dp)

            ) {
                Text(text = "-", fontSize = 20.sp, fontWeight = FontWeight.Bold )

            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = onIncrease,
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                shape = RoundedCornerShape(topEnd = 0.dp, topStart = 10.dp, bottomEnd = 20.dp)
            ) { Text(text = "+", fontWeight = FontWeight.Bold ) }

        }

    }}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
) {
    var text by remember { mutableStateOf("") }
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Nombre del preajuste") }
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancelar")
            }
        }

    )
}

class CounterDown(var segundos: Long, var loquehacealhacertick: (Long) -> Unit) {
    var counterState: Boolean = false

    private val myCounter = object : CountDownTimer((segundos * 1000L), 1000) {
        override fun onTick(millisUntilFinished: Long) {
            if (counterState) loquehacealhacertick(millisUntilFinished / 1000)
        }

        override fun onFinish() {
            counterState = false
            Log.i("CounterDown", "Tiempo finalizado")
        }
    }

    fun toggle() {
        if (this.counterState) {
            this.cancel()
        } else {
            this.start()
        }
    }

    fun start() {
        counterState = true
        this.myCounter.start()
    }

    fun cancel() {
        counterState = false
        this.myCounter.cancel()
    }
}
