class SimpleQueue<T>(private val capacity: Int) : Queue<T> {
    private val elements: ArrayList<T> = ArrayList(capacity)

    override fun enqueue(element: T) {
        if (elements.size >= capacity) {
            throw IllegalStateException("Queue is full")
        }
        elements.add(element)
    }

    override fun dequeue(): T {
        if (isEmpty()) {
            throw IllegalStateException("Queue is empty")
        }
        return elements.removeAt(0)
    }

    override fun size(): Int = elements.size

    override fun isEmpty(): Boolean = elements.isEmpty()
}
