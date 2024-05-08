

class AEDHashSet<E>:AEDSet<E> {

    class Node<E>{
        //sem sentinela, não circular
        var item: E
        var next: Node<E>?=null
        var previous: Node<E>?=null

        constructor(element:E){
            item=element
        }
    }
    private var elements:Array<Node<E>?>

    override var size: Int=0

    override fun iterator(): Iterator<E> {
        return MyIterator()
    }

    inner class MyIterator:Iterator<E>{
        var currentPos: Int = -1;
        var nodeIt: Node<E>? = null;
        var currentValue: Node<E>? = null;

        override fun hasNext(): Boolean {
            if (currentValue != null) return true;
            while (currentPos < elements.size) {
                if (nodeIt == null) {
                    currentPos++;
                    if (currentPos < elements.size) nodeIt = elements[currentPos]
                } else {

                    currentValue = nodeIt;
                    nodeIt = nodeIt?.next;
                    return true;
                }

            }
            return false;
        }
        override fun next(): E {
            if(!hasNext()) throw NoSuchElementException()
            val aux = currentValue?.item;
            currentValue = null;
            return aux!!
        }
    }
    /*   get() = 0
       set(value) {}*/
    private var m:Int
    private val LOADFACTOR = 0.75f

    constructor() {
        elements = arrayOfNulls<Node<Any?>>(10) as Array<Node<E>?>
        m=elements.size
    }

    private fun index(e:E):Int{
        val idx=e.hashCode()%m
        return if(idx<0) idx+m else idx
    }

    private fun search(element:E):Node<E>?{
        val idx=index(element)
        var current:Node<E>?=elements[idx]
        while(current!=null){
            if(current.item==element) return current
            current=current.let{it.next} //o próprio
        }
        return null
    }
    override fun contains(element: E): Boolean {
        return search(element) != null
    }

    private fun expand(){
        // em vez de 2*m podemos colocar 2*elements.size
        val newTable=arrayOfNulls<Any?>(2*m) as Array<Node<E>?>
        m*=2 //m=2*m
        for(j in 0 until elements.size){
            var current:Node<E>?=elements[j]
            while(current!=null){
             //   elements[j]= elements[j].let{it?.next}
                elements[j]= checkNotNull(elements[j]).next
                //ligar o current à nova lista
                val k= index(current.item)
                current.next=newTable[k]
                if(newTable[k]!=null) newTable[k]?.previous=current
                newTable[k]=current
                //atualizar o current na tabela antiga
                current=elements[j]
            }
        }
        elements=newTable
    }
 //Um conjunto sem repetições
    override fun add(element: E): Boolean {
        if(search(element) != null) return false
     else{
         if( size.toDouble()/m>=LOADFACTOR) expand()
            val  new=Node<E>(element)
            val i=index(element)
            new.next=elements[i]
            if(elements[i]!=null) elements[i]?.previous=new
            elements[i]=new

            size++
            return true
        }
    }

    override fun remove(element: E): Boolean {
        TODO("Not yet implemented")
    }


}