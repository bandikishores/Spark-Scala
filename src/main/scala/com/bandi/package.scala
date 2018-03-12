package com

import java.util

import scala.concurrent.Future

/**
  * Created by bandi.kishore on 3/12/18.
  */
/*package object spark_work {

  val bootstrapServers = "172.31.17.142:9092,172.31.21.255:9092,172.31.17.140:9092,172.31.17.226:9092,172.31.30.121:9092"

  val kafkaParams = Map[String, String](
    "auto.offset.reset" -> "smallest",
    "bootstrap.servers" -> bootstrapServers,
    "enable.auto.commit" -> "true"
  )

  val monitoringUrl = "http://dp-event-registry.swiggyint.in/event-checkpoint"

  val FETCH_URL: String = "http://dp-event-registry.swiggyint.in/schema-store/all"

  val zkUrl = "172.31.17.142:2181,172.31.21.255:2181,172.31.17.140:2181"

  val zkKafkaOffsetPath = "/spark_kafka_offset"

  val esNode = "172.31.14.98"

  val esPort = "9200"

  val pushRegistryNode = "172.31.11.189"

  val pushRegistryPort = 9091

  val pushRegistryJob = "spark"

  def getParams : util.Map[String, Object] = {
    val props = new util.HashMap[String, Object]()
    props.put("bootstrap.servers", bootstrapServers)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props
  }


  object MysqlConfig{
    val url = "jdbc:mysql://data-platform-configs.ckvce9fjaook.ap-southeast-1.rds.amazonaws.com:3306/dp_management?zeroDateTimeBehavior=convertToNull"
    val username = "root"
    val password = "Y6AhkfpYedbcf8Us"
    val driver = "com.mysql.jdbc.Driver"
  }

 val topic = Set("ListingV3Event", "GetMenuV2Event", "GetMenuV3Event", "GetDominosV2Event")++
   Set("CancelOrder", "ABExperiment", "TrackOrderETA", "OrderExecutiveAttendanceEvent",
      "ListingV4Request", "ListingV4Response",
      "ReadSettingsV2", "UpdateCartV1", "ApplyCartCouponV1", "RemoveCartCouponV1",
      "FlushCart", "UpdateCartV2", "ApplyCartCouponV2", "RemoveCartCouponV2",
      "UpdateCartMinimal", "PlaceOrder", "EditOrder","GetAllAddresses",
      "GTMEvent", "SearchV2Event", "AddCrossSelling", "FailedCrossSelling",
      "CheckTotalsCartV1", "CheckTotalsCartV2", "MapCardOrder", "AddAddress",
      "UpdateAddress", "RequestHelp", "SubmitRatings", "ConfirmOrder", "TryCrossSelling",
      "CreateCartToken", "CreateReservationTokenEvent", "FlushReservationTokenEvent", "CalculateBannerEvent")++
   Set("UpdateDeOrderStatusEvent", "DEAttendanceDataEvent", "CreateOrderBatchEvent", "FilterOrdersForBatchingEvent", "CompileFinalBatchListEvent",
      "CompileBatchesForAssignmentEvent", "CompileDEsForAssignmentEvent", "ComputeJITDelayEvent", "FilterEdgesByFirstMileEvent",
      "ComputeEdgeScoreEvent", "AssignDeEvent", "DeTagEvent", "DominosResponseDataEvent", "CreateCartEvent",
      "FlushTokenEvent", "CalculateBannerEvent", "DELocationDataEvent") ++
   Set("GetSimilarRestaurantsEvent", "GetMealCompletionEvent", "ListingModelEvent", "ListingDataEvent") ++
   Set("MenuFetchEvent", "MenuUpdateEvent")++
   Set("DeviceInfoEvent", "LandingSourceEvent", "AppsFlyerEvent") ++
   Set("DETaggedEvent", "PopBannerEvent", "PopOrderAssignedEvent", "PopTaggingZoneEvent") ++
   Set("InventoryCMSEvent", "InventoryExpire", "InventoryLock")++
   Set("BatchEvent", "PaymentApi", "AssignmentEvent")

}
*/



package object bandi {

  val bootstrapServers = "localhost:9092"

  val kafkaParams = Map[String, String](
    "auto.offset.reset" -> "smallest",
    "bootstrap.servers" -> bootstrapServers,
    "enable.auto.commit" -> "true"
  )

  val monitoringUrl = "http://dp-event-registry.swiggyint.in/event-checkpoint"

  val FETCH_URL: String = "http://dp-event-registry.swiggyint.in/schema-store/all"

  val zkUrl = "localhost:2181"

  val zkKafkaOffsetPath = "/spark_kafka_offset"

  val esNode = "172.31.14.98"

  val esPort = "9200"

  val pushRegistryNode = "172.31.11.189"

  val pushRegistryPort = 9091

  val pushRegistryJob = "spark"

  def getParams : util.Map[String, Object] = {
    val props = new util.HashMap[String, Object]()
    props.put("bootstrap.servers", bootstrapServers)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props
  }


  object MysqlConfig{
    val url = "jdbc:mysql://localhost:3306/event?zeroDateTimeBehavior=convertToNull"
    val username = "root"
    val password = "root"
    val driver = "com.mysql.jdbc.Driver"
  }

 val topic = Set("TestEvent")

}
