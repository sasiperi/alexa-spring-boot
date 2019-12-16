# Spring Boot Starter for Alexa (Alexa v1 SDK)

This project is a spring boot starter, that helps to Host a Custom Skill as a Web Service, using SpringBoot. This boot starter, 
* Auto configures Speechlet, abstracts all the boilerplate code that is needed Alexa Skill Kit.
* Provides default implementation for generic intents, that would occur during the life cycle of the custom intents (start session, wakeup words, ending sessions and Alexa Build in Intents such as welcome/hello). This all can be managed by configuring proper responses in the application.propeties

This project is licenced under Apache v2

This guide walks you through the process of building an application that uses Spring Boot Starter Alexa, to build custom skill as a web service.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### What you will build
You will build simple Hello World custom skill, as a webservice (endpoint /helloworld)

### Prerequisites  (what you will need)

* About 15 minutes to build skill, using starter project
* Abput 15 minutes to test.
** About 10 minutes to configure your skill on Alexa (you will need account to access Alexa Developer Console
** About 5 minutes to test using Alexa Voice/Echo simulator
* A favorite text editor or IDE
* JDK 1.8 or later
* Gradle 4+ or Maven 3.2+
* You can also import the sample code (under samples/alexa-helloworld-springboot-starter-pcf, which is created as a maven project) straight into your IDE as maven project:
** Eclipse/Spring Tool Suite (STS)
** IntelliJ IDEA

```
Give examples
```

### How to use boot starter and create skill? 

A step by step series of examples that tell you how to get a development env running

#### Add maven dependency
Current release version is 1.0.
https://github.com/sasiperi/alexa-spring-boot/blob/86300097178b1a57f77aa05d19451fe098211a70/samples/alexa-helloworld-springboot-starter-pcf/pom.xml#L28-L32


```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests


```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
