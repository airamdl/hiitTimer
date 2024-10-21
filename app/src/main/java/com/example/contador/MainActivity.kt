package com.example.contador

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.black)
            HomeScreen()
//            ContadorTheme {
//
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column(modifier = Modifier.padding(innerPadding)) {
//                        //MenuIntervalos()
//                        // var screenSelector by remember { mutableStateOf("screen1") }
////                        if (screenSelector == "screen1") {
////                            Screen1() { newScreen ->
////                                screenSelector = newScreen
////                            }
////                        } else if(screenSelector =="screen2"){
////                            Screen2() { newScreen ->
////                                screenSelector = newScreen
////                            }
////                        }else if (screenSelector == "screen3"){
////                            Screen3() { newScreen ->
////                                screenSelector = newScreen
////                        }
////                    }
//                    }
//                }
            }
        }
    }

//    @Preview(showBackground = true)
//    @Composable
//    fun GreetingPreview() {
//        ContadorTheme {
//            MainActivity()
//        }
//    }


//    @Composable
//    fun Counter(modifier: Modifier = Modifier) {
//        var theCounter by remember { mutableStateOf(0L) }
//        var miConterDown by remember {
//            mutableStateOf(
//                CounterDown(
//                    99,
//                    { newvalue -> theCounter = newvalue })
//            )
//        }
//
//        Column {
//            Text(
//                text = theCounter.toString(),
//                modifier = modifier
//            )
//            Button(onClick = {
//                miConterDown.toggle()
//            }) {
//                Text(
//                    text = "Pulsar"
//                )
//            }
//        }
//    }







//@Composable
//fun MenuIntervalos() {
//    var screenSelector by remember { mutableStateOf("screen1") }
//    var sets by rememberSaveable { mutableIntStateOf(4) }
//    var work by rememberSaveable { mutableIntStateOf(60) }
//    var rest by rememberSaveable { mutableIntStateOf(10) }
//    var test by rememberSaveable { mutableStateOf(false) }
//
//    Column(
//        Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        TimeSection(
//            label = "Sets",
//            time = sets,
//            onIncrease= {sets++},
//            onDecrease = {if (sets>0)sets-- }
//        )
//        Spacer(modifier = Modifier.padding(10.dp))
//        TimeSection(
//            label = "Work Time",
//            time = work,
//            onIncrease= {work++},
//            onDecrease = {if (work>0)work-- }
//        )
//        Spacer(modifier = Modifier.padding(10.dp))
//        TimeSection(
//            label = "Rest Time",
//            time = rest,
//            onIncrease= {rest++},
//            onDecrease = {if (rest>0)rest-- }
//        )
//        Spacer(modifier = Modifier.padding(30.dp))
//
//
//        (Button(
//            onClick = {
//                test = true;
//            }
//        ) {
//            Text("Start Activity")
//        })
//        if (test) {
//            Screen1() { newScreen ->
//                screenSelector = newScreen
//            }
//        }
//    }
//}






//@Composable
//fun TimeSection(
//    label : String,
//    time : Int,
//    onIncrease:()-> Unit,
//    onDecrease:()-> Unit
//) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = label, style = MaterialTheme.typography.titleLarge)
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Text(text = "$time", style = MaterialTheme.typography.bodyMedium)
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Row {
//            Button(onClick = onDecrease) { Text(text = "-") }
//            Spacer(modifier = Modifier.width(16.dp))
//            Button(onClick = onIncrease) { Text(text = "+") }
//
//        }
//
//    }
//
//
//}}
