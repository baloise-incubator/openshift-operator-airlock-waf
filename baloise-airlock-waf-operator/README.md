# baloise-airlock-waf-operator project

An OpenShift Operator watching for Lifecycle Events of OpenShift Routes and 
create,update or delete waf mappings based on the events.

## Uses
* https://fabric8.io/guide/javaLibraries.html
* https://github.com/fabric8io/kubernetes-client
* https://github.com/fabric8io/kubernetes-client/blob/master/doc/CHEATSHEET.md

## Todo
At the moment we need a manuall added dependency to fabric8 openshift-client.
This is needed as long as quarkus not have a extension for the openshift-client.
see https://github.com/quarkusio/quarkus/issues/3200
-> Result - we could not build a native version of the application yet

## Docker Image
```
### Local Build Docker Image
If you add some COPY commands in Dockerfile don't forget to update `.dockerignore` !!
```
./mvnw clean package
docker build -f src/main/docker/Dockerfile.jvm -t devopsselfservice/airlock-waf-operator-jvm .  
```
### Local run Docker Image
If you are using the fabric8 KubernetesClient from inside a Pod, it will load ~/.kube/config from the ServiceAccount volume mounted inside the Pod.
To be able to access the readiness and liveness probes urls from your docker host add -p 8080:8080
To local run the Docker Container use the docker command below. 

```
docker run --rm -ti -v <local-path-to>/config:/.kube/config -p8080:8080 devopsselfservice/airlock-waf-operator-jvm
```

for example...
```
docker run --rm -ti -v ~/.kube/config:/.kube/config -p8080:8080 devopsselfservice/airlock-waf-operator-jvm
```
 
The .kube/config file must be mounted manually in this case. 
The local config file should contain the content below. 
The ServiceAccount Token could be found in OpenShift Project kafa-self-service-config-prod in Ressources > Secrets 
see. https://developers.redhat.com/blog/2020/05/20/getting-started-with-the-fabric8-kubernetes-java-client/  

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw compile quarkus:dev
./mvnw -Dquarkus.http.port=8081 compile quarkus:dev
```

# Test locally outside docker Container
If you like to test the watcher to a remote Openshift you can provide a `KubernetesClientProducer` like this
```java
@ApplicationScoped
public class KubernetesClientProducer {

  @Produces
  public KubernetesClient kubernetesClient() {
    Config config = new ConfigBuilder()
        .withMasterUrl("https://console.os2.balgroupit.com:443")
        .withOauthToken("<service-account-oauth-token")
        .withNamespace("kafka-test")
        .build();
    return new DefaultKubernetesClient(config);
  }
}
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `baloise-airlock-waf-operator-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/baloise-airlock-waf-operator-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/baloise-airlock-waf-operator-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.