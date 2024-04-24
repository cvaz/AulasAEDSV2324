class AEDLinkedList<E>:AEDMutableList<E> {
//lista duplamente ligada, circular e com sentinela
    private class Node<E>{
        var item: E?
        var next: Node<E>
        var previous: Node<E>

        constructor(element:E?){
            item=element
            next=this
            previous=this
        }

    }
    override var size: Int=0
    private var head=Node<E>(null)

    override fun add(element: E): Boolean {
        val new=Node(element)
        new.next=head
        new.previous=head.previous
        head.previous.next=new
        head.previous=new
        size++
        return true
    }

    private fun search(element:E):Node<E>?{
        var current=head.next
        while(current!=head){
         if(current.item==element) return current
         current=current.next
        }
        return null
    }

    override fun get(i: Int): E {
        var current=head.next
        var count=0
        while(current!=head && count<i){
            current=current.next
            count++
        }
        if(count==i && current!=head) return current.item!!
        throw IndexOutOfBoundsException()
    }
    


    override fun contains(element: E)=search(element)!=null

    override fun remove(element: E): Boolean {
        val toRemove=search(element)
        if(toRemove == null) return false
        toRemove.previous.next=toRemove.next
        toRemove.next.previous=toRemove.previous
        size--
        return true
    }

    override fun isEmpty(): Boolean {
        return head==head.previous && head==head.next
    }
    override fun add(pos: Int, element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun remove(pos: Int): Boolean {
        TODO("Not yet implemented")
    }









}