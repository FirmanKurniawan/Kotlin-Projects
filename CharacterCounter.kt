package com.andyra.andyramadhan
import java.util.Scanner

private val scn = Scanner(System.`in`)
private var iText = ""

private fun main() {
    inputText()
}

private fun inputText(){
    println("Character Counter Application")
    println("Please Input your text : ")
    
    iText = scn.next()
    if (iText.length < 1){
        awal(true)
    }
    else {
        hitung()
    }
}

fun hitung() {
    println("Characters : ${iText.length}")
    print("ulang? Y/N : ")
    val ulang = scn.next()
    if (ulang.equals("Y") || ulang.equals("y")){
        awal(true)
    }
}

fun awal(ulangi :Boolean){
    if(ulangi){
        inputText()
    }
}
