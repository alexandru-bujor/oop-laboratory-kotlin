package task1

class MyQueue<T>(private val capacity: Int) {
    private val items = mutableListOf<T>()

    fun enqueue(item: T) {
        if (items.size >= capacity) {
            throw IllegalStateException("Queue is full")
        }
        items.add(item)
    }

    fun dequeue(): T {
        if (items.isEmpty()) {
            throw IllegalStateException("Queue is empty")
        }
        return items.removeAt(0)
    }

    fun size(): Int = items.size

    fun isEmpty(): Boolean = items.isEmpty()
}
