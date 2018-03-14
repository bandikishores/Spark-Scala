package com.bandi



import org.apache.spark.SparkConf
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.StreamingContext

/**
 * Created by bandi.kishore on 3/12/18.
 */
object SparkMainObject {
  
  var ssc: StreamingContext = null;

  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      println("Job name not provided!")
    } else {
      applyOnKafka(args(0))
    }
  }

  def applyOnKafka(jobName: String) = {
    /*
    new Thread(new Runnable {
      def run() {
        spawnThreadAndAwait()
      }
    }).start()*/
    
    startSSC(jobName)
  }
    def startSSC(jobName: String) = {
    val conf = new SparkConf()
      .setAppName("myJob")
    ssc = new StreamingContext(conf, Seconds(10))
      KafkaSparkDirectStreams.applyTransformations(ssc, Set(jobName))
      
      
        println("Kish - Starting");
      ssc.start()

      ssc.awaitTermination()
    }

  def spawnThreadAndAwait() = {

  /*  var sc: Scanner = new Scanner(System.in);
    var str: String = "";
    while (str.equalsIgnoreCase("exit")) {
      str = sc.next();
      if (str == null || str.length() == 0 || str.equalsIgnoreCase("exit")) {
        str = "exit";
      } else {*/
    val str = "Test_Add_Event"
       /* println(str + " Topic Entered");
        KafkaSparkDirectStreams.applyTransformations(ssc, Set(str));
        println("Exiting "+str);
        */
//    Thread.sleep(5000)
//    topic = topic++Set(str)
//        ssc.stop(true, true);
//        println("Kish - Restarted");
//        println("topics added : " + topic)
//        startSSC();
        println("Kish - Restart Completed");
     /* }
      println("Was this entered " + str)
    }*/
  }

}
