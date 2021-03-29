package io.pomplan.common


interface Serializer<T, S> {
    fun serialize(value: T): S
    fun deserialize(serialized: S): T
}
