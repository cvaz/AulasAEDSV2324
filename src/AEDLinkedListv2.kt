
class AEDLinkedListv2<E>: AEDMutableList<E> {
    //Lista Simplesmente Ligada, Não Circular e sem sentinela
    private class Node<E>{
        var item: E?
        var next: Node<E>?=null

        constructor(element:E?){
            item=element
        }
    }

    override var size: Int=0
    //inicialmente a lista está vazia
    private var head:Node<E>?=null

    fun addFirst(element:E):Boolean{
        val new=Node(element)
        new.next=head
        head=new
        //  new.previous=head.previous
        //  head.previous.next=new
        // head.previous=new
        size++
        return true
    }

    override fun add(element: E): Boolean {
        val new=Node(element)
        var cur=head
        var previous:Node<E>?=null
        while(cur!=null){
            previous=cur
            cur=cur.next
        }
        if(previous==null){
            head=new
        }
        else {
            previous.next = new
        }
        size++
        return true
    }

    private fun search(element:E):Node<E>?{
        var current=head
        while(current!=null){
            if(current.item==element) return current
            current=current.next
        }
        return null
    }

    override fun contains(element: E)=search(element)!=null

    override fun remove(element: E): Boolean {
        var current=head
        var previous:Node<E>?=null
        while(current!=null){
            if(current.item==element) break
            previous=current
            current=current.next
        }
        if(previous==null){
            //ou a lista é vazia
            if(head==null) return false
            else{
                //é o primeiro elemento da lista a remover
                head=head.let { it?.next }
                size--
                return true
            }
        }
        else{
            if(current==null) return false
            else{
                previous.next=current
                size--
                return true
            }
        }

    }

    override fun add(pos: Int, element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun remove(pos: Int): Boolean {
        TODO("Not yet implemented")
    }




    override fun get(i: Int): E {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }



}