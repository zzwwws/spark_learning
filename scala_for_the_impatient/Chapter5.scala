import scala.beans.BeanProperty

/**
 * Created by zzwwws on 2015/7/22.
 */
object Chapter5 {

  def main (args: Array[String]): Unit ={
    val counter = new Counter

    val time1 = new Timer(12,20)
    val time2 = new Timer(12,30)
    println(time1.before2(time2))
    val person = new Person(-1)
    println(person.getAge)
  }

  class Counter{
    private var value = 0

    def increment(){if(value < Int.MaxValue)value += 1}
    def current(){value}
  }

  class BankAccount{
    private var balance = 0

    def deposit(money:Int)={
      balance -= money
    }
    def withdraw(money:Int)={
      balance += money
    }
  }
  class Timer(private var hours:Int, private var minutes:Int ){

    def before(other:Timer):Boolean={
      if(other.hours > this.hours)true
      else if(other.hours < this.hours)false
      else if(other.minutes > this.minutes)true
      else false
    }

    def before2(other:Timer):Boolean={
      if(other.hours * 60 + other.minutes > this.hours * 60 + this.minutes)true
      else false
    }
  }

  class Person(private var age:Int){
    if(age < 0)age = 0
    def getAge()= age
  }
}

