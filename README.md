# Bogus Lang

Bogus is a bogus language on top of JVM. It is something that is known as AST Walking Evaluator, and it is done simply  
just for educational purposes and fun.

It's worse than javascript. But it works. At some point at least.

## Syntax Example

```
let a = 1;

fun sum(a, b) = a + b;

fun with_scope(a) {
    let b = 5;
    return a - b + 10:
}

let result = with_scope(sum(29, 40));
```

## Running

Start the REPL

```
mvn package && java -cp target/bogus-1.0-SNAPSHOT.jar lang.bogus.BogusRepl
```

Evaluate file

```
mvn package && java -cp target/bogus-1.0-SNAPSHOT.jar lang.bogus.Bogus program.bogus
```
