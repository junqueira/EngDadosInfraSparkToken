package br.xpi.kafka.client

import br.xpi.kafka.RecordProcessorTrait
import org.apache.kafka.clients.consumer.ConsumerRecord


/**
 * Used by {@link DataReader}. It's generally NOT recommended to use a global, static,
 * mutable variable, e.g., `RecordProcessor.count`, but for our simple purposes, it's okay.
 */
class RecordProcessor extends RecordProcessorTrait[Array[Byte], Array[Byte]] {
    override def processRecord(record: ConsumerRecord[Array[Byte], Array[Byte]]): Unit = {
        RecordProcessor.count += 1

        println(s" test processRecord")
        val key = record.key()
        val value = record.value()
        println(s"Retrieved message #${RecordProcessor.count}: " +
            mkString("key", key) + ", " + mkString("value", value))
    }

    private def mkString(label: String, array: Array[Byte]) = {
        println(s" test array byts 2")
        if (array == null)
            s"${label} = ${array}"
        else
            s"${label} = ${array}, size = ${array.size}, first 5 elements = ${array.take(5).mkString("[", ",", "]")}"
//            s"${label} = ${array}, test = ${array.size}"
    }
}

object RecordProcessor {
    var count = 0L
}
