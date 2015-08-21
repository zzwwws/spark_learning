/**
 * Created by zzwwws on 2015/8/12.
 */


import java.io.File

import scalaj.http.Http
object StockPicker {

  def main(args: Array[String]): Unit = {

//    println(predict("600111.SS"))

    val result = listallfiles(new File("E:/table")).map(name=>predict(processName(name))).filter(_.size > 0).toArray

    println("date \t\t"+ "code \t\t"+"yest \t"+"average \t\t\t"+"probableprices \t\t\t" + "sd \t\t\t"+"sd times \t"+"yasdrate\t")
    result.sortBy(li=>Double.formatted(li(7).toString)).reverse.map(_.mkString("\t")).foreach(println)
  }

  def predict(code:String)={

    val ROOT_URL = "http://ichart.yahoo.com/table.csv"

    val timelength = 300

    val N = 21

    val goldrate = 2

    try{
      val data:Array[String] = Http(ROOT_URL).param("s",code).asString.body.toString.split("\n")

      if(data.size < N || data(1).split(",")(5).toDouble <= 0)Array()

      val validdata = data.drop(1).filter(_.split(",")(5).toDouble > 0)

      val prices:Array[Double] = validdata.take(timelength).map(_.split(",")(4)).map(_.toDouble)

      val highPrices:Array[Double] = validdata.take(timelength).map(_.split(",")(2)).map(_.toDouble)

      val lowPrices:Array[Double] = validdata.take(timelength).map(_.split(",")(3)).map(_.toDouble)

      val date = validdata.take(timelength).map(_.split(",")(0))

      val average:Seq[Double]= for(i<-0 to 4*N+1) yield(prices.slice(i+1, i+1+N).sum/N)

      val currentAverage = prices.slice(0,N).sum/N

      val deviation:Seq[Double]= for(i <- 0 to 4*N+1 ) yield (Math.abs(prices(i)-average(i)))

      val averageDeviation:Seq[Double]=for(i<- 0 to 2*N+1)yield (deviation.slice(i+1,i+1+N).sum/N)

      val standardDeviation:Seq[Double]=(0 to N) map(i=>Math.sqrt(((i+1 to i+1+N).map(j=>Math.pow(averageDeviation(j)-deviation(j),2))).sum/N))

      val currentStandardDeviation = Math.sqrt(((0 to N) map(i=>Math.pow(averageDeviation(i)-deviation(i),2))).sum/N)

      val nextHighPrice = prices(0) * 1.1

      val nextLowPrice = prices(0) * 0.9

      val probablePrices = (currentAverage - goldrate * currentStandardDeviation, currentAverage + goldrate*currentAverage)

      if((average(0)-prices(0)) > goldrate * standardDeviation(0)){
        val times = ((0 to N) filter (i=> prices(i) <= (average(i) - goldrate * standardDeviation(i)))).size
        val yasd = (average(0)-prices(0))/standardDeviation(0)
        println(code)
        Array(date(0),code,prices(0),currentAverage, probablePrices._1, currentStandardDeviation, times, yasd)
      }
      else Array()
    }catch{
      case e:Exception=>{
        println(e.toString)
        Array()
      }
    }

  }

  def listallfiles(dir:File):Iterator[String]={
    val part = dir.listFiles().partition(_.isFile)
    part._1.filter(_.getName.endsWith(".csv")).toIterator.map(_.getName) ++ part._2.flatMap(listallfiles(_))
  }

  def processName(fileName:String)={
    val name = fileName.slice(6,12)
    if(name.startsWith("60"))name +".SS"
    else name + ".SZ"
  }

}
