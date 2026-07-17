package com.sharedPref

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.edit

import com.example.sharedprefference.R

@Composable
fun SharedPrefences(context: Context) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var saveData by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.size(300.dp)

        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") })
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") })
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            val sharedPref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            sharedPref.edit{
                putString("username" ,username)
                putString("password" , password)
            }
            password = ""
            username = ""
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Save Data")
        }

        Button(onClick = {
            val sharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            sharedPreferences.edit{
                clear()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Clear Data")
        }



        Button(onClick = {
            val sharedPref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val savedUsername = sharedPref.getString("username","No username!")
            val savedPassword = sharedPref.getString("password","No Password!")
            saveData = "Username : $savedUsername and Password : $savedPassword"



        }, modifier = Modifier.fillMaxWidth()) {
            Text("Show Save Data")
        }


        Button(onClick = {
            saveData = ""
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Remove data")
        }


        if(saveData.isNotEmpty()){
            Text(saveData)
        }


    }
}