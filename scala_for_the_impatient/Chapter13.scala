import sun.swing.MenuItemLayoutHelper.ColumnAlignment

/**
 * Created by zzwwws on 2015/7/29.
 */
object Chapter13 {
  def main(args: Array[String]): Unit = {
    println(question1("Mississippi"))
    println(question2("Mississippi"))
    println(question4(Array("a","b","c"), scala.collection.immutable.Map("a"->1, "d"->3,  "b"->2)))
    println(question5("a","+","",Array(1,2,3,4,5)))
    println(question6(List(1,2,3,4,5)))
    val price = List(5.0,20.0,9.95)
    val quantities = List(10,2,1)
    println(price.zip(quantities).map(Function.tupled(_ * _)))

    question8(Array(1,2,3,4,5,6),3).map(_.toList).foreach(println)
  }

  def question1(str:String)={
    import scala.collection.mutable.Map
    import scala.collection.mutable.SortedSet
    val map: Map[Char,SortedSet[Int]]= Map()
    for(i<-0 to str.length-1){
      map(str(i)) = SortedSet(i) | map.getOrElse(str(i),SortedSet())
    }
    map
  }

  def question2(str:String)={
    import scala.collection.mutable.SortedSet
    import scala.collection.immutable.Map
    val vector = for(i<-0 to str.length-1) yield (str(i),SortedSet(i))
    (Map[Char, SortedSet[Int]]() /:vector) {
      (map, tuple) => if (map.get(tuple._1) != None) map + (tuple._1 -> (map.getOrElse(tuple._1,SortedSet()) | tuple._2))
        else map + tuple
    }
  }

  def question3(li:Seq[Int])={
    li.filter(_ != 0)
  }

  def question4(seq:Seq[String], map:Map[String, Int])={
    seq.flatMap(map.get(_))
  }

  def question5(start:String, sep:String, end:String, seq:Seq[Any])={
    val result = seq.reduceLeft((a,b)=>a.toString + sep + b.toString)
    start + result.toString + end
  }

  def question6(lst:List[Int])={
//    (List[Int]() /: lst)((a,b)=>b +:a)
    (lst :\List[Int]())((a,b)=>b :+ a)
  }


  def question7(c1:Double, c2:Int)={
    c1 * c2
  }

  def question8(array:Array[Int], column:Int)={
    array.grouped(column)
  }

}
