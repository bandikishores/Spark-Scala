package com.bandi


import com.github.fge.jsonschema.core.exceptions.ProcessingException
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration
import com.github.fge.jsonschema.main.{JsonSchema, JsonSchemaFactory}
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.spark.{SparkContext, rdd}
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.StreamingContext
import org.json4s.DefaultFormats
import org.json4s.jackson.Json4sScalaModule
import org.lyranthe.prometheus.client.PushRegistry
import org.json4s.jackson.Serialization.write
import org.json4s.jackson.Serialization.read

import scalaj.http.{Http, HttpOptions}
import scala.collection.JavaConverters._



/**
  * Created by bandi.kishore on 3/12/18.
  */
object KafkaSparkDirectStreams {


  /**
   * Read data from given kafka topics and store offsets
   *
   * @param ssc StreamingContext of spark
   * @param topic topic to read from kafka
   * @return Stream of (key,message) from kafka
   */
  def readFromKafka(ssc: StreamingContext)(topic: String): DStream[(String, String)] = {

    KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, Set(topic)).persist()

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
      applyTransformationOnMessagesForATopic(kafkaReader(topic).map(_._2), topic, ssc)
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
    println("Count " + 1 + " topics(s) totally")
    rdd.foreach(println)
  }
}
