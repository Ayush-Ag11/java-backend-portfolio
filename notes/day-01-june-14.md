# notes/day-01-june-14.md

## StackOverflowError experiment
- Stack crashed at depth: 6433
- Default thread stack size on my JVM: ~512KB
- Each frame holds: method arguments + local variables + return address
- Error type: java.lang.Error (not Exception — JVM level)
- Real-world cause: circular references in toString(), equals(), hashCode()
- Common in Spring: bidirectional JPA @OneToMany + @ManyToOne with Lombok @ToString

## Key distinction I now know cold:
- StackOverflowError  → Stack memory full → too many method calls
- OutOfMemoryError    → Heap memory full → too many objects

### When main() method called stackMethod() recursively in MemoryDemo.java class then JVM builds this:

```
STACK (grows upward, last in first out)
┌─────────────────────────────┐
│ stackMethod(5)              │  ← created last, destroyed first
│   depth = 5, localVar = 50  │
├─────────────────────────────┤
│ stackMethod(4)              │
│   depth = 4, localVar = 40  │
├─────────────────────────────┤
│ stackMethod(3)              │
│   depth = 3, localVar = 30  │
├─────────────────────────────┤
│ stackMethod(2)              │
│   depth = 2, localVar = 20  │
├─────────────────────────────┤
│ stackMethod(1)              │
│   depth = 1, localVar = 10  │
├─────────────────────────────┤
│ main()                      │  ← created first, destroyed last
└─────────────────────────────┘

Each box = one stack frame
Each frame holds its own copy of local variables
When method returns → frame is popped and variables are destroyed
Stack overflow = too many frames, no space left
```
## Heap vs Stack

| | Heap | Stack |
|---|---|---|
| What lives here | Objects, static variables | Method calls, local variables |
| Lifetime | Until GC collects it | Until method returns |
| Shared across threads | Yes | No — each thread has its own stack |
| Size | Large (GBs) | Small (512KB–1MB per thread) |
| Error when full | OutOfMemoryError | StackOverflowError |


## OutOfMemoryError experiment
- Heap exhausted after: 1969 MB
- Error: OutOfMemoryError: Java heap space
- Cause: list held references to all objects → GC could not collect
- Type: java.lang.Error (not Exception)

## OOM types to know for interviews
- Java heap space       → too many live objects
- GC overhead exceeded  → GC working but nothing freed
- Metaspace             → too many classes loaded
- Direct buffer memory  → NIO off-heap exhausted
