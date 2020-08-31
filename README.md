=======
# pseudonymized: Dados pessoais identificaveis 

## algoritmos, 
    MD5, SHA-2, etc.

## Integration
    Write to a Kafka-enabled Event Hub

## Include in sbt
```sh
    libraryDependencies += "xpi-tokenization" %% "anonymize" % "0.0.1" % "provided"
```

## livy -> http://localhost:8998/
```sh
    kubectl --namespace spark \
    port-forward livy-0 8998
```

## historyserver -> http://localhost:18080/
```sh
    kubectl --namespace spark \
    port-forward historyserver-698894c8b8-2wclh 18080
```