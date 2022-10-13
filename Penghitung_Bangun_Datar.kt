package com.andyra.submission1ivanandyramadhan.Data.Local
import java.util.Scanner

private val bgnDatar = arrayOf("Persegi", "Persegi Panjang", "Lingkaran")
private val jenis = arrayOf("Luas", "Keliling")

private val sBgnDtr = bgnDatar.size
private val sJenis = jenis.size
private val scn = Scanner(System.`in`)
private var iBgn: Int = 0
private var iJns: Int = 0

private fun main() {
    pilihBangun()
}

private fun pilihBangun(){
    println("Aplikasi penghitung Keliling dan Luas bangun datar : ")
    println("Mohon pilih bangun datar : ")

    for(i in 0..sBgnDtr-1)
    {
        println("${i+1}. ${bgnDatar[i]}")
    }
    print("Masukkan Angka (1 - ${sBgnDtr}) : ")
    iBgn = scn.nextInt()-1

    sBgnDtr
    if (iBgn < 0  || iBgn >= sBgnDtr){
        awal(true)
    }
    else {
        hitung()
    }
}

fun hitung() {
    when(bgnDatar[iBgn]){
        "Persegi" -> persegi()
        "Persegi Panjang" -> pPanjang()
        "Lingkaran" -> lingkaran()
    }

    println("")
    print("ulang? Y/N : ")
    val ulang = scn.next()
    if (ulang.equals("Y") || ulang.equals("y")){
        awal(true)
    }
}


fun persegi() {
    println("")
    print("Masukkan sisi persegi : ")
    var sisi = scn.nextFloat()

    println("")
    println("sisi = ${sisi}")
    println("luas persegi = ${sisi*sisi}")
    println("keliling persegi = ${4*sisi}")

}
fun pPanjang() {
    println("")
    print("Masukkan panjang persegi : ")
    var pjg = scn.nextFloat()
    print("Masukkan lebar persegi : ")
    var lbr = scn.nextFloat()

    println("")
    println("panjang = ${pjg} , lebar = ${lbr}")
    println("luas persegi panjang = ${pjg*lbr}")
    println("keliling persegi panjang  = ${2*(pjg+lbr)}")

}

fun lingkaran() {
    println("")
    print("Masukkan jari - jari lingkaran : ")
    var jari = scn.nextFloat()

    println("")
    println("jari jari = ${jari}")
    println("luas lingkaran = ${3.14*jari*jari}")
    println("keliling lingkaran = ${2*3.14*jari}")
}

fun awal(ulangi :Boolean){
    if(ulangi){
        pilihBangun()
    }
}
