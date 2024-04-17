interface AEDList<E> {

    var size: Int
    operator fun get(i:Int):E
    fun isEmpty():Boolean
    fun contains(element:E):Boolean

    //definir as operações
}