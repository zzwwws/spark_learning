/**
 * Created by zzwwws on 2015/7/28.
 */
object Chapter10 {

  def main(args: Array[String]) {
    val sa = new SavingsAccount
    sa.withdraw(1.0)

    val aa = new Account
    aa.withdraw("Insufficent funds")

    val bb = new FileAccount with FileLogged{
      val filename = "app.log"
    }
    bb.out
  }

  class SavingsAccount extends ConsoleLogged with TimestampLogged with ShorterLogged{
    def withdraw(amount:Double)={
      super.log("Insufficient funds")
    }
  }

  class Account extends Logged{
    def withdraw(msg:String): Unit ={
      server(msg)
    }
    override def log(msg:String){println(msg)}
  }

  class FileAccount {
    def withdraw(amount:Double)={
    }
  }

  trait Logged{
    def log(msg:String){}
    def server(msg:String){log("SERVER:" + msg)}
  }

  trait ConsoleLogged extends Logged{
    override def log(msg:String){println(msg)}
  }

  trait TimestampLogged extends Logged{
    override abstract def log(msg:String): Unit ={
      super.log("2015-07-28 " + msg)
    }
  }

  trait ShorterLogged extends Logged{
    val length = 15
    override abstract def log(msg:String):Unit={
     super.log{ if(msg.length > length)(msg.substring(0,length - 3) + "..." )else msg }
    }
  }

  trait FileLogged extends Logged{
    val filename:String
    lazy val out = println(filename)
    override def log(msg:String):Unit={
      println(msg)
    }
  }
}
