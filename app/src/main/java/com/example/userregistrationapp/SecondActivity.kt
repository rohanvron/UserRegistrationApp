package com.example.userregistrationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.userregistrationapp.ui.theme.UserRegistrationAppTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name") ?: ""
        val dob = intent.getStringExtra("dob") ?: ""
        val email = intent.getStringExtra("email") ?: ""

        setContent {
            UserRegistrationAppTheme {
                UserDetails(name, dob, email)
            }
        }
    }
}

@Composable
fun UserDetails(name: String, dob: String, email: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        Text(
            text = "Registration Successful",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 80.dp, bottom = 8.dp)
        )
        Text(
            text = "Your Details are Submitted successfully",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),

            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column {
                TableRow("Name", "Birthday", "Email Id", isHeader = true)
                HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                TableRow(name, dob, email)
                HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun TableRow(col1: String, col2: String, col3: String, isHeader: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
    ) {
        TableCell(col1, Modifier.weight(1f), isHeader)
        TableCell(col2, Modifier.weight(1f), isHeader)
        TableCell(col3, Modifier.weight(1f), isHeader)
    }
}

@Composable
fun TableCell(text: String, modifier: Modifier = Modifier, isHeader: Boolean) {
    Text(
        text = text,
        fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal,
        fontSize = 14.sp,
        modifier = modifier,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}