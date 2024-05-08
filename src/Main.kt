fun main() {
    val ar=Array<Int>(5,{i->i})
    val list=AEDArrayList_Immutable<Int>(ar)
    println(list.size)
    println(list.get(0))
    val ar1=Array<String>(5,{i->"string $i"})
    val list1=AEDArrayList_Immutable<String>(ar1)
    println(list1.size)
    println(list1.get(0))
    println(list1[0])
    val list3=AEDArrayList<Int>()
    val list4=AEDLinkedList<String>()
    val list5=AEDLinkedList<Int>()
    val mySet=AEDHashSet<Int>()
    mySet.add(0)
    mySet.add(4)
    for(i in mySet) {
        println(i)
    }
    val mySet2=HashSet<Int>()
    mySet2.add(0)
    mySet2.add(4)
    for(i in mySet2) {
        println(i)
    }
    val it=mySet2.iterator()
    while(it.hasNext()) {
        println(it.next())
    }
}