package BigBrother
import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties

import scala.collection.JavaConverters._
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.PrintWriter;

object ConsumerBigBrother extends App {

  class Message(id: Int,  time: String, coords: (Int, Int))
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("auto.offset.reset", "earliest")
  props.put("group.id", "consumer-group")
  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
  consumer.subscribe(util.Arrays.asList("PERIODIC", "ALERT"))

  while (true) {
    val record = consumer.poll(1000).asScala
    for (data <- record.iterator) {
      println("-----------------------------------------------------")
      println(data.value())
      if (data.value().contains("Alert")){
        println(data.value().contains("Alert"))
      }
      }
  }

}

/*
import sys.process._

object ConsumerBigBrother extends App{

  "ls -al".!

}*/