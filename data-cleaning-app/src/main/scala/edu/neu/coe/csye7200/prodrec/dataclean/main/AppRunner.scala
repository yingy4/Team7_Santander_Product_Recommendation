package edu.neu.coe.csye7200.prodrec.dataclean.main

import edu.neu.coe.csye7200.prodrec.dataclean.io.DataParser
import edu.neu.coe.csye7200.prodrec.dataclean.pipeline.Pipeline
import org.apache.spark.sql.SparkSession
//import edu.neu.coe.csye7200.prodrec.dataclean.io

case class Config(input: String = null, output:String = null)

object AppRunner extends Serializable {


  def main(args: Array[String]) {

    val parser = new scopt.OptionParser[Config]("DataCleaningApp")
    {
      head("Data Cleaning App", "1.0")
      opt[String]('i', "input") required() action
        { (x, c) => c.copy(input = x) } text("input is the input path")
      opt[String]('o', "output") required() action
        { (x, c) => c.copy(output = x) } text("output is the output path")
    }

    parser.parse(args, Config()) match {
      case Some(config) =>
        val input = config.input
        val output = config.output

        val ss = SparkSession
          .builder()
          .appName("Data Cleaning App")
          .master("local")
          .config("spark.debug.maxToStringFields",100)
          .getOrCreate()

        ss.conf.getAll.foreach(println)

        val resultdf = Pipeline.run(input, ss)
        resultdf.show()
//        resultdf.coalesce(1).write.option("header","true").csv(output)

      case None =>
    }

  }
}
