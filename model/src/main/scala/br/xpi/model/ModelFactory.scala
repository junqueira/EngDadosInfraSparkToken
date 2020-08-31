package br.xpi.modelServer.model


/**
 * Basic trait for a model factory.
 */
trait ModelFactory {
    def create(input: ModelToServe): Model
    def restore(bytes: Array[Byte]): Model
}
