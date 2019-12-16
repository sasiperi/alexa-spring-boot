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

#### Add maven (or gradel) dependency
Crate a spring boot starter project using spring boot initializer available in IDE (rest) or from start.spring.io.

#### Add maven dependency
* Open pom.xl (or gradle) and spring-boot-starter-alexa dependency.
* Current release version is 1.0.

https://github.com/sasiperi/alexa-spring-boot/blob/86300097178b1a57f77aa05d19451fe098211a70/samples/alexa-helloworld-springboot-starter-pcf/pom.xml#L28-L32

~~~xml
    <dependency>
			<groupId>io.github.sasiperi</groupId>
			<artifactId>spring-boot-starter-alexa</artifactId>
			<version>1.0</version>		
		</dependency>
~~~

#### Configure application properties. 
* You can in your YAML or .properties file use tab for hints of all available properties and the default values provided.
* You can override these in your application's app.props (or YAML)
* All the available properties start with **spring.alexa**
* Check the additional properties meta data for details of what each property means, what are the allowed values.
below are the availableample properties

~~~.prperties

###The application id that alexa(dev) provides amzn1.ask.skill.xxxxxxx###
spring.alexa.application-id=amzn1.ask.skill.481fb850-g95a-5345-9h29-14fbbc889944

###### card title that you want to go on the account alexa.amazon and in the appstore ####
spring.alexa.card-title=alexa-hello

#####Comma sepratated list of speechlet URI mappings, which will be invoked for intent(s) ############
spring.alexa.speechlet-uri-mappings=/alexaHello

############## Various responses, for generic actions and intents ###################
spring.alexa.response.good-bye= Good Bye Sample Spring Boot Hello 
spring.alexa.response.hello-intent=Hello Sample Spring Boot Hello
spring.alexa.response.help-intent=Help Sample Spring Boot Hello
spring.alexa.response.welcome=Welcome Sample Spring Boot Hello
~~~

End with an example of getting some data out of the system or using it for a little demo

#### Creating Custom Skill Speechlet
Starter will auto cinfigure ASK SDK for you. You need to override onIntent() method of the default Specchlet implementation.
Example snippet

[Example Snippet(https://github.com/sasiperi/alexa-spring-boot/blob/2c0a3eea40fe27da5d3b12c9dde126c5a968bb1d/samples/alexa-helloworld-springboot-starter-pcf/src/main/java/io/github/sasiperi/alexa/spring/boot/examples/service/HelloWorldSpeechlet.java#L47-L75)

**Note:

  * AlexaProperties bean is injected by starter and can be autowired to detect all/any props starting with spring.alexa.

##### Authentication, Autherization and Account Linking
  * To enable authentication, autherization, you may need to override other methods in the default impl.
  * An example project to demonstrate Auth using OAUTH2 (using auth-code grant) and does the account linking, is under samples/alexa-oms (sample order tracking skill for authorized customer).

~~~java
@Service
public class HelloWorldSpeechlet extends SkillSpeechletDefaultImpl
{
    private static final Logger log = LoggerFactory.getLogger(HelloWorldSpeechlet.class);


    @Autowired
    private AlexaProperties alexaProps;


    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException
    {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());


        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;


        if ("HelloWorldIntent".equals(intentName))
        {
            return getResponse(alexaProps.getResponse().getHelloIntent());
        } else if ("AMAZON.HelpIntent".equals(intentName))
        {
            return getResponse(alexaProps.getResponse().getWelcome());
        } else
        {
            throw new SpeechletException("Invalid Intent");
        }
    }


}

~~~


## Running the tests

* Get an account to Amazon Alexa developer console.
* Add and configure your skill.
* Click on the skill and create your speech assets.
* Sample hello world sppech assets for this sample application can be found here, that can be copy pasted.
: [Hello World Speech Assets](https://github.com/sasiperi/alexa-spring-boot/tree/master/samples/alexa-helloworld-springboot-starter-pcf/src/main/resources/speechAssets)

* Go to Alexa Skill Simulator, activate your skill, using the wake up word.
* You can now test default intents and HelloWorld custom intent as shown in the below screen shot.
**You can see Alexa responses are created based on the configuration, as shown in the below Screenshot.

[Alexa Simulator Test](https://sasiperi.github.io/blog//static/img/alexa-helloworld-springboot-starter.png)

### Break down into end to end tests


## Deployment


## Built and Released with

* [Maven](https://maven.apache.org/) - Dependency Management
* [SonaType](https://oss.sonatype.org/)
* [Nexus Repo](https://rometools.github.io/rome/) - Artifacts Repo
* [Maven Central](https://repo.maven.apache.org/maven2/io/github/sasiperi/alexa-spring-boot-starter/)

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/sasiperi/spring-boot-starter-alexa/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

I use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Sasi Peri** - *Initial work* - [sasiperi](https://github.com/sasiperi)
* **Home Page and Blog** [*Homepage & Blog*](sasiperi.github.io)


## License

This project is licensed under the Apache V2 License - see the [LICENSE](https://github.com/sasiperi/alexa-spring-boot/blob/master/LICENSE) file for details

## Acknowledgments


