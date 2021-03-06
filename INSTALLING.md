# Installing JavaMarkdown
You can install JavaMarkdown through either Gradle or Maven. However, since this project is currently hosted on GitHub, there are additional steps for importing this module in your project

## Gradle [^1]

[^1]: https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package


In order to use this package, you will need to both authenticate and register the registry with your application.

1. Create or add to a `.env` file with the following:
```dotenv
# .env
USERNAME= # Github Username
TOKEN= # Github Token
```

2. Add the maven plugin to gradle

```groovy
//build.gradle
plugins {
    id 'maven'
}
```

3. Import parameters from `.env` into the project

```groovy
//build.gradle
def props = new Properties()
file(".env").withInputStream { props.load(it)}
```

4. Add and authenticate the maven repository to your registered repositories

```groovy
//build.gradle
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/LittleTealeaf/JavaMarkdown")
        credentials {
            username = project.findProperty("gpr.user") ?: props.getProperty("USERNAME")
            password = project.findProperty("gpr.key") ?: props.getProperty("TOKEN")
        }
    }
}

```

5. Add the dependency to your dependencies

```groovy
//build.gradle
dependencies {
    compile: 'org.tealeaf:javamarkdown:{version}'
}
```

## Maven [^2]

> *Disclaimer, steps have not been verified to work, if someone wants to verify / correct this section, please feel free to make a fork and pull request the corrections*

[^2]: https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#installing-a-package\\

In order to use this package, you will need to both authenticate and register the registry with your application

1. Create or modify `~/.m2/settings.xml` to add the server

```xml
<!-- ~/.m2/settings.xml -->
<settings>
    <activeProfiles>
        <activeProfile>github</activeProfile>
    </activeProfiles>

    <profiles>
        <profile>
            <id>github</id>
            <repositories>
                <repository>
                    <id>central</id>
                    <url>https://repo1.maven.org/maven2</url>
                </repository>
                <repository>
                    <id>JavaMarkdown</id>
                    <url>https://maven.pkg.github.com/LittleTealeaf/JavaMarkdown</url>
                </repository>
            </repositories>
        </profile>
    </profiles>
    <servers>
        <server>
            <id>JavaMarkdown</id>
            <username><!-- GitHub Username  --></username>
            <password><!-- GitHub Personal Access Token --></password>
        </server>
    </servers>
</settings>
```

2. Add the repository in your `pom.xml`

```xml
<dependencies>
    <dependency>
        <groupId>org.tealeaf</groupId>
        <artifactId>javamarkdown</artifactId>
        <version><!-- VERSION --></version>
    </dependency>
</dependencies>
```