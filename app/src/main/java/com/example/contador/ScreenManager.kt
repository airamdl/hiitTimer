package com.example.contador

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


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


@Composable
fun Screen1(navController: NavController) {
    var sets by rememberSaveable { mutableIntStateOf(4) }
    var work by rememberSaveable { mutableIntStateOf(20) }
    var rest by rememberSaveable { mutableIntStateOf(10) }

    Column(
        Modifier
            .fillMaxSize()
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
                navController.navigate("second_Screen/{start}/{work}/{rest}".replace(
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
        ) {
            Text("Start Activity")
        })


    }

}

@Composable
fun Screen2(navController: NavController, start: String?, work: String?, rest: String?) {
    var prepareTime by remember { mutableStateOf(10L) }
    var setsRemaining by remember { mutableStateOf(start?.toInt() ?:0) }
    var workRemaining by remember { mutableStateOf(work?.toInt() ?:0) }
    var restRemaining by remember { mutableStateOf(rest?.toInt() ?:0) }
    var miConterDown by remember{ mutableStateOf(CounterDown(10, {newvalue -> prepareTime = newvalue}))}

    LaunchedEffect(Unit) {
        miConterDown.start()
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Sets Restantes $setsRemaining", fontSize = 30.sp)
        Text(text = "Preparate para Trabajar en $prepareTime segundos", color = Color.Blue, fontSize = 25.sp)
        Button(onClick = {prepareTime=0}) { Text(text="prueba")}

        if (prepareTime<=0) {
            LaunchedEffect(Unit) {
                navController.navigate("third_Screen/{start}/{work}/{rest}".replace(
                    oldValue = "{start}",
                    newValue = setsRemaining.toString()
                ).replace(
                    oldValue = "{work}",
                    newValue = workRemaining.toString()
                ).replace(
                    oldValue = "{rest}",
                    newValue = restRemaining.toString()
                ))
            }
            //miConterDown.cancel()

        }
    }

}



@Composable
fun Screen3(navController: NavController, start: String?, work: String?, rest: String?) {
    var setsRemaining by remember { mutableStateOf(start?.toInt() ?:0) }
    var workTime by remember { mutableStateOf(work?.toLong() ?:0) }
    var restTime by remember { mutableStateOf(rest?.toLong() ?:0) }
    var counter by remember { mutableStateOf<CounterDown?>(null) }
    var currentScreen by remember { mutableStateOf("work") }
    var workTimeSaved by remember { mutableStateOf(workTime) }

    LaunchedEffect(Unit) {
        setsRemaining = work?.toInt() ?:0
        workTime = work?.toLong() ?: 0
        restTime = 10
        workTimeSaved = workTime
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val timeLeft = if (currentScreen == "work") workTime else restTime
        Text(text = "Sets Restantes $setsRemaining", fontSize = 30.sp)
        Text(text = "Tiempo restante: $timeLeft segundos", color = Color.Blue, fontSize = 20.sp)

        Button(onClick = {
            if (setsRemaining > 0) {
                counter = CounterDown(timeLeft) { remaining ->
                    if (currentScreen == "work") {
                        workTime = remaining
                    } else {
                        restTime = remaining
                    }
                }
                counter?.start()
                if (currentScreen == "work") {
                    currentScreen = "work"


                }

            }
        }
        ) {
            Text("Iniciar Temporizador")
        }
        if (timeLeft<=0){
            LaunchedEffect(Unit) {
                navController.navigate("fourth_Screen/{rest}".replace(
                    oldValue = "{rest}",
                    newValue = restTime.toString()
                ))}
        }

        Button(onClick = {
            counter?.cancel()
        }) {
            Text("Pausar")
        }

        Button(onClick = {
            counter?.cancel()
            workTime = workTimeSaved


        }) {
            Text("Reiniciar")
        }
    }
}
@Composable
fun Screen4(navController: NavController, start: String?, work: String?, rest: String?) {
    var restTime by remember { mutableStateOf(0L) }
    var setsRemaining by remember { mutableStateOf(0) }

LaunchedEffect(Unit) {
    setsRemaining = rest?.toInt() ?: 0
    restTime = restTime?.toLong() ?:0
}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Sets Restantes $setsRemaining")
        Text(text = "Tiempo de Descanso + $restTime", color = Color.Blue)
        Log.i("DAM2",restTime.toString())

            if (setsRemaining<=0) {
                LaunchedEffect(Unit) {
                navController.navigate("first_Screen")
                    }
            }else{
                if (restTime>10){

                LaunchedEffect(Unit) {
                setsRemaining -= 1
                navController.navigate("second_Screen/{start}".replace(
                    oldValue = "{start}",
                    newValue = setsRemaining.toString()
                ))}}
            }
        }

    }


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
        Text(text = label, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "$time", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(onClick = onDecrease) { Text(text = "-") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onIncrease) { Text(text = "+") }

        }

    }}

class CounterDown(var segundos: Long, var loquehacealhacertick: (Long) -> Unit) {
    private var counterState: Boolean = false

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
