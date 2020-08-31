package br.xpi.modelServer.model

import br.xpi.model.tokenrecord.TokenRecord


/**
 * Basic trait for a model. For simplicity, we assume the data to be scored are WineRecords.
 */
trait ModelToken {
    def score(record: TokenRecord): Any
    def cleanup(): Unit
    def toBytes: Array[Byte]
    def getType: Long
}
