//15:12:35	13970762417900218	[????????????]	1 2	news.21cn.com/social/daqian/2008/05/29/4777194_1.shtml
val data = sc.textFile("~/SogouQ.reduced")
//00:00:00到12:00:00 之间搜索总数
val totalcount = data.filter(_.split("\\s+")(0).split(':').mkString("").toInt <= 120000)
//搜索结果排第一，点击次序第二的总条数
val datacount = data.filter(_.split("\\s+").slice(3,5).mkString("") == "12")
