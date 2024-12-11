package task1

class CircularQueue<T>(private val capacity: Int) : Queue<T> {
    private val elements: Array<Any?> = arrayOfNulls<Any?>(capacity) // Array to store elements
    private var head = 0 // Index of the front element
    private var tail = 0 // Index where the next element will be added
    private var size = 0 // Current number of elements

    override fun enqueue(element: T) {
        if (size == capacity) {
            throw IllegalStateException("Queue is full")
        }
        elements[tail] = element // Add element at tail
        tail = (tail + 1) % capacity // Wrap around if needed
        size++
    }

    override fun dequeue(): T {
        if (isEmpty()) {
            throw IllegalStateException("Queue is empty")
        }
        val front = elements[head] as T
        head = (head + 1) % capacity // Move head forward
        size--
        return front
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun size(): Int {
        return size
    }
}

