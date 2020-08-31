package br.xpi.modelServer.model

import br.xpi.model.winerecord.WineRecord


/**
 * Basic trait for a model. For simplicity, we assume the data to be scored are WineRecords.
 */
trait Model {
    def score(record: WineRecord): Any
    def cleanup(): Unit
    def toBytes: Array[Byte]
    def getType: Long
}
