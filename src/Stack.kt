interface Stack<E> {
    fun push(value: E)
    fun pop():E?
    fun peek(): E?
    fun isEmpty(): Boolean
}