import java.util

/**
 * Created by zzwwws on 2015/7/22.
 */
object Chapter4 {

  def main (args: Array[String]) {

    val names = List("computer","book","bag")
    val prices:List[Double] = List(1000,10,100)

    val pair:Map[String,Double] = names.zip(prices).toMap
    println(pair)

    println(question1(pair))
    println(question2)
    println(question3.toMap)

    println(question4)
    println(question5)
    println(question8(Array(1,3,2,5,6)))
    println(question9(Array(1,2,3,2,2,5,6,-1,4),2))
  }

  def question1(map:Map[String,Double])= for((k,v)<-map)yield (k,v*0.9)

  def question2()={
    import scala.io.Source
    import scala.collection.mutable.HashMap
    val source = Source.fromFile("E:/spark/README.md")
    val tokens = source.mkString.split("\\s+")
    val map = new HashMap[String, Int]
    for(token<-tokens) {
      if(map.getOrElse(token,0) == 0)map(token) = 1
      else map(token) += 1
    }
    map
  }

  def question3()={
    import scala.io.Source
    val source =Source.fromFile("E:/spark/README.md")
    val tokens = source.mkString.split("\\s+")
    for(token<-tokens)yield (token, tokens.count(_ == token))
  }

  def question4()={
    import scala.io.Source
    val source =Source.fromFile("E:/spark/README.md")
    val tokens = source.mkString.split("\\s+")
    tokens.toList.map(A=>(A,tokens.count(_ == A))).sortBy(-_._2).distinct
  }

  def question5()={
    import scala.io.Source
    import scala.collection.mutable.Map
    import scala.collection.JavaConversions.mapAsScalaMap
    val source = Source.fromFile("E:/spark/README.md")
    val tokens = source.mkString.split("\\s+")
    val treemap:util.TreeMap[String, Int] = new util.TreeMap[String, Int]()
    val list = for(token <- tokens){
      if(!treemap.containsKey(token))
      treemap.put(token, tokens.count(_ == token))
    }

    val map : Map[String,Int] = treemap.descendingMap()
    map
  }

  def question8(values:Array[Int])={
    (values.sorted.head, values.sorted.last)
  }

  def question9(values:Array[Int],v:Int)={
    val sorted = values.sorted
    (sorted.count(_ < v),sorted.count(_ == v),sorted.count(_ > v))
  }

}
