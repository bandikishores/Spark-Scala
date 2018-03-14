package com.bandi


import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies




/**
  * Created by bandi.kishore on 3/12/18.
  */
object KafkaSparkDirectStreams {
  
  
  val offsetStore = new ZookeeperOffsetStore(zkUrl, zkKafkaOffsetPath)


  /**
   * Read data from given kafka topics and store offsets
   *
   * @param ssc StreamingContext of spark
   * @param topic topic to read from kafka
   * @return Stream of (key,message) from kafka
   */
  def readFromKafka(ssc: StreamingContext)(topic: String): DStream[ConsumerRecord[String, String]] = {
 val offset = offsetStore.readOffsets(topic)
    val messages = offset match {
      case None => KafkaUtils.createDirectStream[String, String](ssc, LocationStrategies.PreferConsistent, Subscribe[String, String](Set(topic), kafkaParams)).persist()
      case Some(offsetValues) => 
        KafkaUtils.createDirectStream[String, String](ssc,  LocationStrategies.PreferConsistent, Subscribe[String, String](Set(topic), kafkaParams, offsetValues)).persist()
    } 
 println("Completed create Steam")
    messages.foreachRDD(rdd => {  // Side effect to store offsets
      offsetStore.saveOffsets(messages, topic, rdd)
    })
    messages
  }


  def validateEventSchema(events: Iterator[String], broadcast: Broadcast[Map[String, String]]) = {
    // val factory = JsonSchemaFactory.newBuilder().setLoadingConfiguration(LoadingConfiguration.newBuilder().setEnableCache(false).freeze()).freeze()
    events.map(event => println(event))
  }

  /**
   * Counts unique messages in tapplyTransformationshe topic and prints top 10
   *
   * @param ssc spark streaming context
   * @param topicMap topics to read from
   */
  def applyTransformations(ssc: StreamingContext, topicMap: Set[String]) = {
    val kafkaReader = readFromKafka(ssc) _
    topicMap.foreach(topic => {
      println("Submitted topic: " + topic + "from thread: " + Thread.currentThread().getId)
      applyTransformationOnMessagesForATopic(kafkaReader(topic).map(r => (r.key(),r.value())).map(_._2), topic, ssc)
    })
  }

  def applyTransformationOnMessagesForATopic(messages: DStream[String], topic: String, ssc: StreamingContext) = {
    val cachedMessages = messages.persist()
//    cachedMessages.foreachRDD(rdd => acc.add(topic, rdd.count()))
  //  TopicRouterMapping.executeConsumption(topic)(cachedMessages)
    cachedMessages.foreachRDD(rdd => updateAccumulators(ssc.sparkContext, rdd))
    //PrometheusWriter.pushCountMetricToPrometheus(acc)
  }
 

  /**
   *
   * @param sc Spark Context
   * @param rdd rdd of events
   */
  def updateAccumulators(sc: SparkContext, rdd: RDD[String]) = {
    //PrometheusWriter.pushCountMetricToPrometheus(acc)
    var count = 0;
    println("Count " + 1 + " topics(s) totally")
    rdd.foreach(t => count+1)
  }
}
