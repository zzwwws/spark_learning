/**
 * Created by zzwwws on 2015/8/3.
 */
object Chapter14 {
  def main(args: Array[String]) {
    println(question2((1,2)))
    question3(Array(1,2,3,4)).foreach(print)
    println(price(Multiple(10,Article("Blackwell Toster", 29.95))))
  }

  def question2(tuple:Tuple2[Int, Int])= tuple match{
    case (x,y)=>(y,x)
  }

  def question3(array:Array[Int])= {
    val Array(x,y,_*) = array
    y+:x+:array.drop(2)
  }

  abstract class Item
  case class Article(description:String, price:Double) extends Item
  case class Bundle(description:String, discount:Double, items:Item*) extends Item
  case class Multiple(count:Int, item:Item) extends Item

  def price(it:Item):Double= it match{
    case Article(_,p)=>p
    case Bundle(_,disc,its @ _*)=>its.map(price _).sum -disc
    case Multiple(count, item)=>price(item) * count
  }


}
