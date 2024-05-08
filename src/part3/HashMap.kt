package serie2.part3

class HashMap<K, V> (initialCapacity: Int = 16, val loadFactor: Float = 0.75f) : MutableMap<K, V>{
    private class HashNode<K, V>(override val key: K, override var value: V,
                                 var next: HashNode<K, V>? = null
                                ): MutableMap.MutableEntry<K,V> {
        var hc = key.hashCode()
        override fun setValue(newValue: V): V {
            val oldValue = value
            value = newValue
            return oldValue
        }
    }

    private var table: Array<HashNode<K, V>?> = arrayOfNulls(initialCapacity)
    private var count = 0
    override val size: Int
        get() = count

    override val capacity: Int
        get() = table.size

    private fun hash( hc: Int ): Int = hc.and( 0x7fff_ffff ) % table.size

    override fun iterator(): Iterator<MutableMap.MutableEntry<K, V>> {
        return object : Iterator<MutableMap.MutableEntry<K, V>> {
            private var index = 0
            private var node = table[index]
            override fun hasNext(): Boolean {
                while (index < table.size && node == null) {
                    node = table[index++]
                }
                return node != null
            }

            override fun next(): MutableMap.MutableEntry<K, V> {
                if ( hasNext() )
                    node?.let {
                        node = it.next
                        return it
                    }
                throw NoSuchElementException()
            }
        }
    }

    override fun put(key: K, value: V): V? {
        var index = hash(key.hashCode() )
        var node = table[index]
        while (node != null) {
            if (node.key == key) {
                return node.setValue(value)
            }
            node = node.next
        }
        if (count >= table.size * loadFactor) {
            expand()
            index = hash(key.hashCode())
        }
        table[index] = HashNode(key, value, table[index])
        ++count
        return null
    }

    override fun get(key: K): V? {
        val index = key.hashCode() % table.size
        var node = table[index]
        while (node != null) {
            if (node.key == key) {
                return node.value
            }
            node = node.next
        }
        return null
    }

    private fun expand() {
        val oldTable = table
        table= Array<HashNode<K, V>?>(table.size * 2) { null }
        for (node in oldTable) {
            var n = node
            while (n != null) {
                val next = n.next
                val index = hash( n.hc )
                n.next = table[index]
                table[index] = n
                n = next
            }
        }
    }
}