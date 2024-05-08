interface AEDSet<E>:Iterable<E> {
    val size:Int
    fun add(element: E): Boolean
    fun remove(element: E): Boolean
    fun contains(element:E): Boolean
}