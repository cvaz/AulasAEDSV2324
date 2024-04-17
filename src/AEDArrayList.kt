class AEDArrayList<E>:AEDMutableList<E>{

    private var elements:Array<E>

    override var size: Int=0
    /*   get() = 0
       set(value) {}*/

    constructor() {
        elements = arrayOfNulls<Any?>(10) as Array<E>
    }
    override fun add(element: E): Boolean {
        if(size == elements.size){
            increaseCapacity()
        }
        elements[size++]=element
        return true //porque tenho um array dinâmico
    }

    private fun increaseCapacity(){
        val new= arrayOfNulls<Any?>(2*elements.size) as Array<E>
        System.arraycopy(elements,0,new,0,elements.size)
        elements = new

    }

    override fun add(pos: Int, element: E): Boolean {
        //TPC
        TODO("Not yet implemented")
    }

    override fun remove(pos: Int): Boolean {
        //TPC
        TODO("Not yet implemented")
    }

    private fun search(element: E):Int{
        for(i in 0..< size){
            if(elements[i]==element) return i
        }
        return -1
    }


    override fun remove(element: E): Boolean {
        val pos=search(element)
        if(pos==-1) return false
        System.arraycopy(elements,pos+1,elements,pos,size-1-pos)
        size--
        return true
    }

    override fun contains(element: E): Boolean {
        return search(element)!=-1
    }


    override fun get(i: Int): E {
        if(i<0 || i>=size) throw ArrayIndexOutOfBoundsException()
        return elements[i]
    }

    override fun isEmpty()=size==0




    /* constructor( ){
         elements=
     }*/

}
