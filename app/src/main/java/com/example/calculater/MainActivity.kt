package com.example.calculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
   private var input :TextView?=null
    var lastnum:Boolean=false
    var lastdot:Boolean=false
    var c:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     input=findViewById(R.id.input)
    }
    fun onDigit(view: View){
         input?.append((view as Button).text)
        lastnum=true
        lastdot=false
    }
    fun Onclear(view: View){
         input?.text=""
        c=0
    }
    fun onDot(view: View){
         if(lastnum && !lastdot && c==0){
              input?.append(".")
             c++
             lastnum=false
             lastdot=true
         }
    }
    fun onOperator(view: View){
         input?.text?.let {
             if(lastnum && !isoperator(it.toString())){
                  input?.append((view as Button).text)
                 lastnum=false
                 lastdot=false
                 c=0
             }
         }
    }
    private fun isoperator(value:String):Boolean{
         if(value.startsWith(prefix = "-")){
              return false
         }
        else{
         return   value.contains(other = "/") ||value.contains(other="*")||value.contains(other = "+")||value.contains(other="-")
         }
    }
    fun Equal(view:View){
        if(lastnum){
             var tvVal=input?.text.toString()
            var prefix=""
             try{
                 if(tvVal.startsWith("-")){
                      tvVal=tvVal.substring(1)
                     prefix="-"
                 }
                 if(tvVal.contains("-")) {
                     var splitval = tvVal.split("-")
                     var one = splitval[0];
                     var two = splitval[1]
                     if(prefix.isNotEmpty()) {
                         one=prefix+one
                     }
                     val result = one.toDouble() - two.toDouble()
                     input?.text = deleteop(result.toString())
                 }
                 else if(tvVal.contains("+")){
                     var splitval=tvVal.split("+")
                     var one=splitval[0]
                     var two=splitval[1]
                     if(prefix.isNotEmpty()){
                         one=prefix+one
                     }
                     var result=two.toDouble()+one.toDouble()
                     input?.text=deleteop(result.toString())
                 }
                 else if(tvVal.contains("*")){
                     var splitval=tvVal.split("*")
                     var one=splitval[0]
                     var two=splitval[1]
                     if(prefix.isNotEmpty()){
                         one=prefix+one
                     }
                     var result=two.toDouble()*one.toDouble()
                     input?.text=deleteop(result.toString())
                 }
                 else if(tvVal.contains("/")){
                     var splitval=tvVal.split("/")
                     var one=splitval[0]
                     var two=splitval[1]
                     if(prefix.isNotEmpty()){
                         one=prefix+one
                     }
                     var result=one.toDouble()/two.toDouble()
                     input?.text=deleteop(result.toString())
                 }
             }
             catch (e:ArithmeticException){
                 e.printStackTrace()
             }
        }
    }
private fun deleteop(result:String):String{
         var re=result
    if(result.contains(".0")){
         re=result.substring(0,result.length-2)
    }
       return re
}


}