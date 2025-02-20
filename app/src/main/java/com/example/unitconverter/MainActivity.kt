package com.example.unitconverter

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConverter()
                }
            }
        }
    }
}

@Composable
fun unitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Rupees")}
    var outputUnit by remember { mutableStateOf("Dollar")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    var conversionFactor = remember { mutableStateOf(0.012)}
    val oconversionFactor = remember{ mutableStateOf(0.012)}

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value )
        outputValue = String.format("%.3f",result)
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Currency Converter",style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = inputValue, onValueChange = { inputValue = it;convertUnits()}, label = { Text(text = "Enter value")})
        Spacer(modifier = Modifier.height(14.dp))
        Row(){
            //Input Box
            Box{
                Button(onClick = { iExpanded = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    )){
                    Text(text = "$inputUnit")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false}) {
                        DropdownMenuItem(text = { Text(text = "Rupees")},
                            onClick = { iExpanded = false
                                        inputUnit = "Rupees"
                                        conversionFactor.value = 86.6
                                        convertUnits()})
                        DropdownMenuItem(text = { Text(text = "Dollars")},
                            onClick = { iExpanded = false
                                iExpanded = false
                                inputUnit = "Dollars"
                                conversionFactor.value = 0.012
                                convertUnits()})
                    }
                }
            }
            Spacer(modifier = Modifier.width(60.dp))
            //Output Box
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = "$outputUnit")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Rupees")},
                                     onClick = { oExpanded = false
                                     outputUnit = "Rupees"
                                     conversionFactor.value = 86.6
                                     convertUnits()})
                    DropdownMenuItem(text = { Text(text = "Dollars")},
                        onClick = { oExpanded = false
                            oExpanded = false
                            outputUnit = "Dollars"
                            conversionFactor.value = 0.012
                            convertUnits()})
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Result: $outputValue $outputUnit",
            fontSize = 23.sp,
            color = Color(0xFF03274D)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun unitConverterPreview(){
    unitConverter()
}