import scala.util.Random

/**
 * Created by zzwwws on 2015/7/14.
 */
object HelloScala {

  def main (args: Array[String]) {
    print("Hello, World!")
  }

  def max(x:Int, y:Int):Int = {
    if(x > y)
      x
    else
      y
  }
  def greet = println("Hello")

  class ScalaClass{
    val a = 1
    var b = 0

    def add (x: Int, y:Int) : Int = {
      x + y
    }

    def count():Int = {
      b += 1
      b
    }
  }


  object ScalaClass{
    private val aa = 1
    val bb = 2
    def add: Int = aa + bb
  }
  val res = ScalaClass.add

  val s = new ScalaClass
  s.add(2,3)
  s.count

  (x:Int)=>x+1

  val a = 1
  val b = if(a == 1)2 else 3

  def func():String = {
    val str = "Hello, Scala!"
    for(s <- str if s > 'A';if s <= 'h')yield s

  }

  var t = 10
  while(t > 0 ){
    println(t)
    t -= 1
  }

  val date = "Monday"

  date match{
    case "Monday" => println(1)
    case "Tuesday" => println(2)
    case "Wednesday" => println(3)
    case "Thursday" => println(4)
    case "Friday" => println(5)
    case "Saturday" => println(6)
    case "Sunday" => println(7)
  }

  val array = Array[String]("hello","world","!\n")
  for(str <- array)print(str)
  for(i <- 0 to array.length - 1) print(array(i))
  val arrays = new Array[String](3)
  arrays(0) = "ar"

  val lis = List(1,2,3)


}
