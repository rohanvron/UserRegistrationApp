package com.example.userregistrationapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.text.style.TextOverflow
import com.example.userregistrationapp.ui.theme.UserRegistrationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserRegistrationAppTheme {
                RegistrationForm()
            }
        }
    }
}

@Composable
fun RegistrationForm() {
    var name by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = "Welcome User",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 80.dp, bottom = 8.dp, start = 10.dp, end = 10.dp)
        )
        Text(
            text = "Please enter your details.",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )

        Text("Name",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 10.dp, end = 10.dp),
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            placeholder = { Text("Enter Your Name") },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color.Gray
            )
        )

        Text("Birth Date",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 10.dp, end = 10.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Dropdown for Month
            val months = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
            var expanded by remember { mutableStateOf(false) }
            Box(modifier = Modifier.weight(0.35f)) {
                OutlinedTextField(
                    value = month,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = { Text("Month") },
                    shape = RoundedCornerShape(8.dp),
                    readOnly = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Color.Gray
                    ),
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    months.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = {
                                Text(text = selectionOption, overflow = TextOverflow.Ellipsis)
                            },
                            onClick = {
                                month = selectionOption.take(3)
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = day,
                onValueChange = { day = it },
                placeholder = { Text("Day") },
                modifier = Modifier.weight(0.30f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = year,
                onValueChange = { year = it },
                placeholder = { Text("Year") },
                modifier = Modifier.weight(0.35f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.Gray
                )
            )
        }

        Text("Email Id",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 10.dp, end = 10.dp),

            )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Enter Your Email Id") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp, start = 10.dp, end = 10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color.Gray
            )
        )

        Button(
            onClick = {
                val dayWithSuffix = when {
                    day.endsWith("1") && !day.endsWith("11") -> "${day.toInt()}st"
                    day.endsWith("2") && !day.endsWith("12") -> "${day.toInt()}nd"
                    day.endsWith("3") && !day.endsWith("13") -> "${day.toInt()}rd"
                    else -> "${day.toInt()}th"
                }

                val intent = Intent(context, SecondActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("dob", "$month $dayWithSuffix, $year")
                    putExtra("email", email)
                }
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF88A2A)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Submit", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}