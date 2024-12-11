package task1

interface Queue<T> {
    fun enqueue(element: T)  // Add an element to the queue
    fun dequeue(): T         // Remove and return the front element
    fun isEmpty(): Boolean   // Check if the queue is empty
    fun size(): Int          // Get the number of elements
}

