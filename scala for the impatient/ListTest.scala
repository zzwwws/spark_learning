/**
 * Created by zzwwws on 2015/7/17.
 */
object ListTest {

  def main (args: Array[String]) {

    def signum(x:Int)={
      if(x > 0) 1
      else if(x < 0)-1
      else 0
    }

    countdown(10)

    println(xn(2,1024))

  }

  def countdown(n:Int)={
    for(i <- n to (0,-1))println(i)
  }

  def mul(input:String)={
    var re:Double = 1
    for(str <- input){
      re *= str
      println(re)
    }
    re
  }

  def product(s:String):BigInt={
    if(s.length == 1)BigInt(s.charAt(0))
    else BigInt(s.head) * product(s.tail)
  }

  def properties(n:Int)={
    if(n < 0)-1
    else if(n == 0)0
    else if(n % 2 == 1)1
    else 2
  }

  //calculate x^n recursive
  def xn(x:BigDecimal,n:Int):BigDecimal= properties(n) match{
    case -1=>1/xn(x,-n)
    case 0=>1
    case 1 =>x *xn(x,n-1)
    case 2 =>xn(x,n/2) * xn(x,n/2)
  }
}
