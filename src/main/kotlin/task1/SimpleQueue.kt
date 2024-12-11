package task1

class SimpleQueue<T>(private val capacity: Int) : Queue<T> {
    private val elements: Array<Any?> = arrayOfNulls(capacity) // Array to store elements
    private var size: Int = 0 // Current number of elements

    override fun enqueue(element: T) {
        if (size == capacity) {
            throw IllegalStateException("Queue is full")
        }
        elements[size++] = element // Add element at the end
    }

    override fun dequeue(): T {
        if (isEmpty()) {
            throw IllegalStateException("Queue is empty")
        }
        @Suppress("UNCHECKED_CAST")
        val front = elements[0] as T // Cast to the correct type after checking the type
        // Shift all elements to the left
        for (i in 1 until size) {
            elements[i - 1] = elements[i]
        }
        elements[size - 1] = null // Nullify the last element for cleanup
        size--
        return front
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun size(): Int {
        return size
    }

    fun isNotEmpty(): Boolean = size > 0
}
