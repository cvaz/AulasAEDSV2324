class StackArray<E>: Stack<E> {

    private var elements:Array<E?>
    private var size:Int=0

    constructor(){
        elements= arrayOfNulls<Any?>(10) as Array<E?>
    }
    override fun push(value: E) {
        if(size==elements.size){
            increaseCapacity()
        }
        elements[size++]=value
    }

    override fun pop(): E? {
        if(isEmpty()) return null

        val toReturn=elements[--size]
        elements[size]=null
        return toReturn

    }

    override fun peek(): E? {
        return if(size==0) null else elements[size]
    }

    override fun isEmpty()=size==0

    private fun increaseCapacity(){
        val new= arrayOfNulls<Any?>(2*elements.size) as Array<E?>
        System.arraycopy(elements,0,new,0,elements.size)
        elements = new

    }
}