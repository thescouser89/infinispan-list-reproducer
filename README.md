# Reproducer for Cache when serializing a list of object

This reproducer is using embedded Infinispan using Protobuf


## Compiling the source code

```
mvn clean install
```


## Running the reproducer when the cache has as value a list of objects

```
# Clear the folder where infinispan stores the data
rm -rf ~/.infinispan-reproducer

# Insert data
java -jar target/infinispan-list-reproducer-jar-with-dependencies.jar fail insert

# Loading the data from cache and failing
java -jar target/infinispan-list-reproducer-jar-with-dependencies.jar fail
```


## Running the reproducer when the cache has as value as object `ListWrapper`

```
# Clear the folder where infinispan stores the data
rm -rf ~/.infinispan-reproducer

# Insert data
java -jar target/infinispan-list-reproducer-jar-with-dependencies.jar success insert

# Loading the data from cache and succeeding to read the data from cache
java -jar target/infinispan-list-reproducer-jar-with-dependencies.jar success
```
