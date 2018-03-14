package com.bandi

import kafka.common.TopicAndPartition
import org.apache.spark.rdd.RDD
import org.apache.kafka.common.TopicPartition
import org.apache.spark.streaming.dstream.DStream
import org.apache.kafka.clients.consumer.ConsumerRecord

/**
  * Created by bandi.kishore on 3/13/18.
  */
trait OffsetStore {

  def readOffsets(topic: String): Option[Map[TopicPartition, Long]]

  def saveOffsets(messages: DStream[ConsumerRecord[String, String]], topic: String, rdd: RDD[_]): Unit

}
