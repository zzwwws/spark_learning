import java.io.File
import java.io.PrintWriter

import scala.io.Source

/**
 * Created by zzwwws on 2015/7/24.
 */
object Chapter9 {
  def main(args: Array[String]) {
    subdirs(new File("E:/spark_learning")).foreach(println)
//    reverselines("E:/spark_learning/scala_for_the_impatient/redme.md")
    replacetab("E:/spark_learning/scala_for_the_impatient/test2.txt")
    printdict("E:/spark_learning/scala_for_the_impatient/redme.md")
//    println(calcfloat("E:/spark_learning/scala_for_the_impatient/test3.txt"))
    write2n("E:/spark_learning/scala_for_the_impatient/test4.txt")
    printnonefloat("E:/spark_learning/scala_for_the_impatient/test3.txt")
    findimgsrc("E:/spark_learning/scala_for_the_impatient/test5.txt").foreach(println)
    listallfiles(new File("E:/spark_learning")).foreach(println)
  }
  def subdirs(dir:File):Iterator[File]={
    val children = dir.listFiles().filter(_.isDirectory)
    children.toIterator ++ children.toIterator.flatMap(subdirs(_))
  }

  def listallfiles(dir:File):Iterator[File]={
    val part = dir.listFiles().partition(_.isFile)
    part._1.filter(_.getName.endsWith(".scala")).toIterator ++ part._2.flatMap(listallfiles(_))
  }

  def writetofile(path:String)(op:PrintWriter=>Unit)={
    val out = new PrintWriter(path)
    try{
      op(out)
    }finally {
      out.close()
    }
  }
  def reverselines(path:String)={
    val source =Source.fromFile(path)
    if(source != null){
      val lines = source.getLines().toArray.reverse
      writetofile(path){
        p=>lines.foreach(p.println)
      }
    }

  }

  def replacetab(path:String)={
    val source = Source.fromFile(path)
    if(source != null){
      val pattern = "\t".r
      val rstring = pattern.replaceAllIn(source.mkString," ")
      writetofile(path){
        p=>p.println(rstring)
      }
    }
  }

  def printdict(path:String): Unit ={
    Source.fromFile(path).mkString.split("\\s+").filter(_.length > 12).foreach(println)
  }

  def calcfloat(path:String)={
    val regex = "[0-9]+[.]?[0-9]?".r
    val floatarray = regex.findAllIn(Source.fromFile(path).mkString).toArray.map(_.toFloat)
    val sum = floatarray.reduce(_+_)
    val aver = sum / (floatarray.size)
    val max = floatarray.sorted.head
    val min = floatarray.sorted.last

    (sum, aver, max, min)
  }

  def write2n(path:String): Unit ={
    writetofile(path){
      p=>0 to 20 map (n=>p.println(math.pow(2,n) + "\t"*(4- (math.log(math.pow(2,n).toString.length)/math.log(2)).toInt ) + math.pow(2,-n)))
    }
  }

  def printnonefloat(path:String)={
    val regex = "[0-9]+[.]?[0-9]?"
    Source.fromFile(path).mkString.split("\\s+").filter(!_.matches(regex)).foreach(println)
  }

  def findimgsrc(path:String)={
    val regex = "img src=\"[^=]*\"".r
    val imgsrc = regex.findAllIn(Source.fromFile(path).mkString).toArray
    for(src<-imgsrc)yield src.substring(src.indexOf("\"")+1, src.lastIndexOf("\""))
  }


}
