package service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import task1.SimpleQueue

class ServiceTest {

    @Test
    fun testService() {
        Assertions.assertNull(null)
    }

    @Test
    fun testEnqueueOnEmptyQueue() {

        // Create an empty queue with a capacity of 5
        val queue = SimpleQueue<Int>(5)

        // The queue should be empty initially
        Assertions.assertTrue(queue.isEmpty())

        // Enqueue an element into the empty queue
        queue.enqueue(10)

        // Check the size after enqueueing
        Assertions.assertEquals(1, queue.size())

        // Check if the front of the queue is correct (first element enqueued)
        Assertions.assertEquals(10, queue.dequeue())
    }

    @Test
    fun testDequeueFromEmptyQueue() {
        // Create an empty queue with a capacity of 0
        val queue = SimpleQueue<Int>(0)

        // Try to dequeue from an empty queue and expect an exception
        val exception = Assertions.assertThrows(IllegalStateException::class.java) {
            queue.dequeue() // This should throw an IllegalStateException
        }

        // Verify the exception message
        Assertions.assertEquals("Queue is empty", exception.message)
    }

}
