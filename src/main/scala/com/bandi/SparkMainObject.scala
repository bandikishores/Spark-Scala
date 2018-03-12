package com.bandi

import kafka.serializer.StringDecoder
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{ Seconds, StreamingContext }
import org.apache.spark.{ SparkConf, SparkContext }
import java.util.Scanner

/**
 * Created by bandi.kishore on 3/12/18.
 */
object SparkMainObject {

  var topic = Set("Test_Event")
  
  var ssc: StreamingContext = null;

  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      println("Job name not provided!")
    } else {
      applyOnKafka(args(0))
    }
  }

  def applyOnKafka(jobName: String) = {

    /* conf.set("es.index.auto.create", "true")
    conf.set("es.nodes", esNode)
    conf.set("es.port", esPort)*/
    
    new Thread(new Runnable {
      def run() {
        spawnThreadAndAwait()
      }
    }).start()
    
    startSSC()
  }
    def startSSC() = {
    val conf = new SparkConf()
      .setAppName("myJob")
    ssc = new StreamingContext(conf, Seconds(5))
      KafkaSparkDirectStreams.applyTransformations(ssc, topic)
      
      
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
    Thread.sleep(5000)
    topic = topic++Set(str)
        ssc.stop(true, true);
        println("Kish - Restarted");
        println("topics added : " + topic)
        startSSC();
        println("Kish - Restart Completed");
     /* }
      println("Was this entered " + str)
    }*/
  }

}
