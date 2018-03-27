package edu.neu.coe.csye7200.prodrec.dataclean.main

case class Config(input: String = null, output:String = null)

object AppRunner extends Serializable {


  def main(args: Array[String]) {

    val parser = new scopt.OptionParser[Config]("DataCleaningApp")
    {
      head("Data Cleaning App", "1.0")
      opt[String]('f', "input") required() action
        { (x, c) => c.copy(input = x) } text("input is the input path")
      opt[String]('o', "output") required() action
        { (x, c) => c.copy(output = x) } text("output is the output path")
    }

    parser.parse(args, Config()) match {
      case Some(config) =>
        val input = config.input
        val output = config.output
      case None =>
    }

//    val filePath = "data-cleaning-app/src/main/resources/train_ver2.csv"
//
//    val spark = SparkSession
//      .builder
//      .master("local")
//      .appName("DataCleaning")
//      .getOrCreate()
//
//    var df = spark.read
//      .option("header", "true")
//      //    .schema(customSchema)
//      .csv(filePath)
//
//    //  df.printSchema()
//
//    df = DFUtils.removeWhitespaceAllColumns(df)
//
//    df = df.filter(not(col("age") === ""))
//
//    df = DFUtils.castColumnTo(df, "age", IntegerType)
//
//    df = df.filter(col("age") > 27)
//
//    df.write.format("csv").save("destination")
//
//    df.show()
//
//    spark.close()

  }
}