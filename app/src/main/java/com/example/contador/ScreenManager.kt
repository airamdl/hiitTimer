package com.example.contador

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import android.media.MediaPlayer
import androidx.compose.ui.platform.LocalContext
import java.time.format.TextStyle


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
        composable(route ="third_Screen/{start}/{work}/{rest}",

            ) {
                backStackEntry ->
            val start= backStackEntry.arguments?.getString("start")
            val work= backStackEntry.arguments?.getString("work")
            val rest= backStackEntry.arguments?.getString("rest")
            if (start!= null && work!= null && rest != null) {
                Screen3(navController = navController, start, work, rest)
            }
        }
        composable(route ="fourth_Screen/{start}/{work}/{rest}",

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
    if (set != 0 && work != 0 && rest != 0){
        return true
    }
    return false
}
// Screen Settings
@Composable
fun Screen1(navController: NavController) {
    // Defaults values
    var sets by rememberSaveable { mutableIntStateOf(4) }
    var work by rememberSaveable { mutableIntStateOf(5) }
    var rest by rememberSaveable { mutableIntStateOf(10) }
    var nplayer : MediaPlayer
    val myContext = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .background(color = BacgorundScreen1)
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TimeSection(
            label = "Sets",
            time = sets,
            onIncrease= {sets++},
            onDecrease = {if (sets>0)sets-- }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        TimeSection(
            label = "Work Time",
            time = work,
            onIncrease= {work++},
            onDecrease = {if (work>0)work-- }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        TimeSection(
            label = "Rest Time",
            time = rest,
            onIncrease= {rest++},
            onDecrease = {if (rest>0)rest-- }
        )
        Spacer(modifier = Modifier.padding(30.dp))


        (Button(
            onClick = {
                if (checkSettings(sets,work,rest) == true) {
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
                nplayer = MediaPlayer.create(myContext, R.raw.noti);
//                nplayer.start()
            }
        ) {
            Text("Start Activity")
        })


    }

}
//Screen 10 second
@Composable
fun Screen2(navController: NavController, start: String?, work: String?, rest: String?) {
    var prepareTime by remember { mutableStateOf(10L) }
    var sets by remember { mutableStateOf(start?.toInt() ?:0) }
    var miConterDown by remember{ mutableStateOf(CounterDown(10, {newvalue -> prepareTime = newvalue}))}

    LaunchedEffect(Unit) {
        miConterDown.start()
    }




    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =Modifier
            .fillMaxSize()
            .background(color = BackgroundScreen2)
            .padding(10.dp)
    ){
        Text(text = "$sets Sets Restantes ", fontSize = 30.sp, fontStyle = FontStyle.Italic , fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("$prepareTime", fontSize = 30.sp, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "¡¡PREPÁRATE!!", color = TextScreen2, fontWeight = FontWeight.Bold, fontSize = 45.sp, textDecoration = TextDecoration.Underline, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Monospace)
//        Text(text = "hola", textStyle = MaterialTheme.typography.bodyMedium.copy(
//            fontSize = 16.sp,
//            lineHeight = 24.sp,
//            color =  Color.Black,
//            fontWeight = FontWeight.Bold
//        ))
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
    var setsRemaining by remember { mutableStateOf(start?.toInt() ?:0) }
    var workTime by remember { mutableStateOf(work?.toLong() ?:0) }
    var restTime by remember { mutableStateOf(rest?.toLong() ?:0) }
    var counter by remember { mutableStateOf<CounterDown?>(null) }
    var currentScreen by remember { mutableStateOf("work") }
    var buttonInitOrPause by remember { mutableStateOf("Pausar") }
    var workTimeSaved by remember { mutableStateOf(workTime) }

    LaunchedEffect(Unit) {
        setsRemaining = start?.toInt() ?:0
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
    var restTime by remember { mutableStateOf(rest?.toLong() ?: 0) }
    var setsRemaining by remember { mutableStateOf(start?.toInt() ?: 0) }
    var miConterDown by remember{ mutableStateOf(CounterDown(10, {newvalue -> restTime = newvalue}))}
    LaunchedEffect(Unit) {
        restTime = restTime?.toLong() ?:0
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
