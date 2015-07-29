/**
 * Created by zzwwws on 2015/7/29.
 */
object Chapter12 {
  def main(args: Array[String]) {
    values(x=>x*x, -5,5).foreach(println)
    val array = Array(2,1,3,5,4)
    println(array.reduce(big))
    println(muly(5))
    println(multi(-1))
    println(largest(x=>10*x-x*x,1 to 100))
    println(adjustToPair(_*_)(6,7))
    val pair = (1 to 10) zip (11 to 20)
    pair.map(adjustToPair(_+_)(_)).foreach(println)
    unless(1 > 2){println("hi unless test success")}
  }
  def values(fun:(Int)=>Int, low:Int, high:Int)={
    for(i <- low to high)yield (i, fun(i))
  }
  def big(x:Int, y:Int)={
    if(x > y)x
    else y
  }

  def muly(n:Int)={
    1 to n reduceLeft(_ * _)
  }

  def multi(n:Int)={
    Range(1,n+1).foldLeft(1)((a,b)=>a*b)
  }

  def largest(f:(Int)=>Int, inputs:Seq[Int])={
    val index = inputs.reduceLeft((A,B)=>if(f(A) > f(B))A else B)
    f(index)
  }

  def adjustToPair(f:(Int,Int)=>Int)= (A:Tuple2[Int,Int])=>f(A._1,A._2)

  def unless(condition:Boolean)(block:Unit)={
    if(!condition)block
  }

}
