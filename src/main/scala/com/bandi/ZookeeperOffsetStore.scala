package com.bandi
import com.google.common.base.Stopwatch
import kafka.utils.{ZKStringSerializer, ZkUtils}
import org.I0Itec.zkclient.ZkClient
import org.apache.spark.rdd.RDD
import kafka.utils.ZKStringSerializer
import org.I0Itec.zkclient.ZkConnection
import kafka.utils.ZKStringSerializer
import org.apache.spark.streaming.kafka010.HasOffsetRanges
import org.apache.kafka.common.TopicPartition
import org.apache.spark.streaming.kafka010.CanCommitOffsets
import scalaz.std.stream
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.DStream

/**
  * Created by bandi.kishore on 3/13/18.
  */
class ZookeeperOffsetStore(zkHosts: String, zkPath: String) extends OffsetStore {
  private val zkClient = ZkUtils.createZkClient(zkHosts, 10000, 10000);
  private val zkConnection = new ZkConnection(zkHosts);
  private val zkUtils = new ZkUtils(zkClient, zkConnection, false);

  // Read the previously saved offsets from Zookeeper
  override def readOffsets(topic: String): Option[Map[TopicPartition, Long]] = {

    println("Reading offsets from ZooKeeper")
    val stopwatch = new Stopwatch()

    val (offsetsRangesStrOpt, _) = zkUtils.readDataMaybeNull(zkPath + "/" + topic)

    offsetsRangesStrOpt match {
      case Some(offsetsRangesStr) =>
        println(s"Read offset ranges: ${offsetsRangesStr}")

        val offsets = offsetsRangesStr.split(",")
          .map(s => s.split(":"))
          .map { case Array(partitionStr, offsetStr) => (new TopicPartition(topic, partitionStr.toInt) -> offsetStr.toLong) }
          .toMap

        println("Done reading offsets from ZooKeeper. Took " + stopwatch)

        Some(offsets)
      case None =>
        println("No offsets found in ZooKeeper. Took " + stopwatch)
        None
    }

  }

  // Save the offsets back to ZooKeeper
  //
  // IMPORTANT: We're not saving the offset immediately but instead save the offset from the previous batch. This is
  // because the extraction of the offsets has to be done at the beginning of the stream processing, before the real
  // logic is applied. Instead, we want to save the offsets once we have successfully processed a batch, hence the
  // workaround.
  override def saveOffsets(messages: DStream[ConsumerRecord[String, String]], topic: String, rdd: RDD[_]): Unit = {

    println("Saving offsets to ZooKeeper")
    val stopwatch = new Stopwatch()

    val offsetsRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
    offsetsRanges.foreach(offsetRange => println(s"Using ${offsetRange}"))

    val offsetsRangesStr = offsetsRanges.map(offsetRange => s"${offsetRange.partition}:${offsetRange.fromOffset}")
      .mkString(",")
    println(s"Writing offsets to ZooKeeper: ${offsetsRangesStr}")
    zkUtils.updatePersistentPath(zkPath + "/" + topic, offsetsRangesStr)
    
    println("Commiting to Kafka")
      messages.asInstanceOf[CanCommitOffsets].commitAsync(offsetsRanges)
    println("Done Commiting to Kafka")

    println("Sleep Zookeeper")
    println("Done updating offsets in ZooKeeper. Took " + stopwatch)
    Thread.sleep(3000)

  }

}