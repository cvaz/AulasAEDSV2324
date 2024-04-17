class AEDArrayList_Immutable<E>: AEDList<E>{

    private var elements: Array<E>

    constructor(array:Array<E>) {
        elements=array
    }


    override val size: Int
        get() = elements.size


    override fun get(i: Int): E {
        return elements[i]
    }

    override fun isEmpty()=size==0
    override fun contains(element: E): Boolean {
        return elements.contains(element)
    }

}