package Drone1

import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties

import scala.collection.JavaConverters._

object ConsumerDrone1 extends App {
  class Message(id: Int, time: String,  coords: (Int, Int))
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("auto.offset.reset", "earliest")
  props.put("group.id", "consumer-group")
  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
  consumer.subscribe(util.Arrays.asList("SOS-RESPONSE-1"))
  while (true) {
    val record = consumer.poll(1000).asScala
    for (data <- record.iterator) {
      val string = data.value()

      println("-----------------------------------------------------")
      println("Received ERROR CODE: " + string)
    }
    Thread.sleep(1000)
  }
}