---
title: Bogus-lang
description: Bogus is definitely not production grade general purpose programming language.
---

Bogus is definitely not production grade dynamically typed general purpose programming language. It is an adventure in crafting structured 
programming language from scratch. Every cool programming language must have a documentation site, and so does bogus. 

Bogus is implemented with java and thus runs on JVM. At 0.1.X versions bogus is
AST walking evaluator and does not emit any bytecode. It's possible, that later revisions will contain
bytocode emitting, but it's also possible, that yours truly will just write a brand-new language, as bogus does not
contain any interesting syntax.

Implementing production grade general purpose programming language is a huge task. Language must have at least following features,
which are completely missing in bogus.

* Threads or Async I/O - bogus is blocking single threaded language, and thus for example implementing concurrent HTTP server is impossible
* Java interop
* Comprehensive STD - basic string, integer functions, file reading and writing  and all that
* Optimisation - bogus does not do any optimisation like inlining
* Regular Expressions...

But, you can generate fibonacci sequences with bogus, until the stack limit exceeds 😂

## Syntax

**Hello world**

```
println("Hello World");
```

**Defining a variable**

```
let foo = 1;
let bar = "Hello World!"
```

**Single expression function definition**

```
fun sum(a, b) = a + b;
```

**Function with body definition**

```
fun sum(a, b) {
    return a + b;
}
```
