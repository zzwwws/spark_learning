import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * Created by zzwwws on 2015/7/21.
 */
object Chapter3 {

  def main (args: Array[String]) {

    val array = genArray(11)
    array.foreach(print)
    println()
    transfer(array).foreach(print)
    println()
    transferm(array).foreach(print)
    println()
    val sa:Array[Int] = Array(-1,-2,1,1,2,3)
    splitarray(sa).foreach(print)

    println()
    println(midvalue(sa))

    println(reverse(sa))

//    filterRepeat(sa).foreach(println)

    removeNeg(sa).foreach(println)
  }

  def genArray(n:Int)={
    val array = new Array[Int](n)
    for(i<- 0 to n-1)array(i) = Random.nextInt(n)
    array
  }

  def transfer(array:Array[Int])={

    val res = new Array[Int](array.length)
    for(i<-0 to array.length-1){
      if(i % 2 == 0 && i < array.length - 1)res(i) = array(i+1)
      else if(i % 2 == 1)res(i) = array(i-1)
      else res(i) = array(i)
    }
    res
  }
  def transferm(array:Array[Int])={
    for(i<-0 to array.length-1)
    yield {if (i % 2 == 0 && i < array.length - 1)array(i+1) else if(i % 2 == 1)array(i-1) else array(i)}
  }

  def splitarray(array:Array[Int])={
    val parray = array.partition(_>0)
    parray._1 ++ parray._2
  }

  def midvalue(array:Array[Int])={
    array.reduce(_+_)/array.length
  }

  def reverse(array:Array[Int])={
    val len = array.length
    for(i<- 0 to array.length-1 )yield array(len - 1 - i)
  }

  def filterRepeat(array:Array[Int])={
    array.distinct
  }

  def removeNeg(array:Array[Int])={

    val buffer = array.toBuffer
    val indexNeg = {for(i <- 0 to array.length -1 if array(i) < 0)yield i}
    val nindexNeg = indexNeg.drop(1)
    for(i <- nindexNeg)buffer.remove(i)
    buffer.toArray

  }

}
