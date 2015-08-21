/**
 * Created by zzwwws on 2015/8/10.
 */
import scalaj.http.Http
object StockReminder {

  def main(args: Array[String]): Unit = {
    val codelist = List("600029.SS","600115.SS","600820.SS","600897.SS","601111.SS","601328.SS","601600.SS","002170.SZ","002570.SZ")

    println("date \t\t"+ "code \t\t"+"yest \t"+"average \t\t"+"highprice \t" + "lowprice \t"+"buy price \t"+"sell price \t" + "sd")
    codelist.foreach(predict)
  }

  def predict(code:String)={

    val ROOT_URL = "http://ichart.yahoo.com/table.csv"

    val timelength = 300

    val N = 21

    val data:Array[String] = Http(ROOT_URL).param("s",code).asString.body.toString.split("\n")

    val validdata = data.drop(1).filter(_.split(",")(5).toDouble > 0)

    val prices:Array[Double] = validdata.take(timelength).map(_.split(",")(4)).map(_.toDouble)

    val highPrices:Array[Double] = validdata.take(timelength).map(_.split(",")(2)).map(_.toDouble)

    val lowPrices:Array[Double] = validdata.take(timelength).map(_.split(",")(3)).map(_.toDouble)

    val date = data.drop(1).take(timelength).map(_.split(",")(0))

    val average:Seq[Double]= for(i<-0 to 2*N+1) yield(prices.slice(i+1, i+1+N).sum/N)

    val currentAverage = prices.slice(0,N).sum/N

    val deviation:Seq[Double]= for(i <- 0 to 2*N ) yield (Math.abs(prices(i)-average(i)))

    val averageDeviation:Seq[Double]=for(i<- 0 to N)yield (deviation.slice(i+1,i+1+N).sum/N)

    //    val standardDeviation:Seq[Double]=(0 to N) map(i=>Math.sqrt((i+1 to i+1+N).map(j=>Math.pow(averageDeviation(j)-deviation(j),2)).sum/N))

    val currentStandardDeviation = Math.sqrt(((0 to N) map(i=>Math.pow(averageDeviation(i)-deviation(i),2))).sum/N)

    val nextHighPrice = prices(0) * 1.1

    val nextLowPrice = prices(0) * 0.9

    val probablePrices = (currentAverage - 2 * currentStandardDeviation, currentAverage + 2*currentAverage)

    var buyprices:String = probablePrices._1 +""
    var sellprices:String = probablePrices._2 +""

    if(probablePrices._1 > nextHighPrice || probablePrices._1 < nextLowPrice){
      buyprices = "--"
    }
    if(probablePrices._2 > nextHighPrice || probablePrices._2 < nextLowPrice){
      sellprices = "--"
    }
    println(date(0) + "\t"+code + "\t" + prices(0) + "\t" + currentAverage.toString.substring(0,4)+"\t\t\t"+ nextHighPrice.toString.substring(0,4) + "\t\t"
      + nextLowPrice.toString.substring(0,4) + "\t\t" + buyprices + "\t\t\t" + sellprices +"\t\t\t"+ currentStandardDeviation)
  }

}
