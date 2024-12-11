package task1

class PriorityQueue<T : Comparable<T>>(private val capacity: Int) : Queue<T> {
    private val elements: Array<Comparable<T>?> = arrayOfNulls(capacity) // Use Array for sorting
    private var size: Int = 0

    override fun enqueue(element: T) {
        if (size == capacity) {
            throw IllegalStateException("Queue is full")
        }

        // Find the correct position for the new element
        var i: Int = size - 1 // Start from the last index of the current elements
        for (j in size - 1 downTo 0) {
            if (element.compareTo(elements[j] as T) < 0) {
                elements[j + 1] = elements[j] // Shift larger elements to the right
                i = j // Update the position of 'i' during the shift
            } else {
                break // Stop when the correct position is found
            }
        }

        // Insert the new element
        elements[i + 1] = element
        size++
    }

    override fun dequeue(): T {
        if (isEmpty()) {
            throw IllegalStateException("Queue is empty")
        }
        return elements[--size] as T // Remove the last element (highest-priority)
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun size(): Int {
        return size
    }
}
