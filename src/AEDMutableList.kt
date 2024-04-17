interface AEDMutableList<E>:AEDList<E>{
    fun add(element: E): Boolean
    fun add(pos:Int, element:E): Boolean
    fun remove(pos:Int):Boolean
    fun remove(element:E):Boolean
}