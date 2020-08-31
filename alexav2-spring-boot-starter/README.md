# Spring Boot Starter for Alexa (Alexa v1 SDK)

This project is a spring boot starter for alexa sdk version2 namely ask-sdk, that helps to Host a Custom Skill as a Web Service, using SpringBoot. This boot starter, 
* Auto configures Speechlet, abstracts all the boilerplate code that is needed Alexa Skill Kit.
* Provides default implementation for generic intents, that would occur during the life cycle of the custom intents (start session, wakeup words, ending sessions and Alexa Build in Intents such as welcome/hello). This all can be managed by configuring proper responses in the application.propeties

#### *Version Note*
**Amazon Alexa SDK V2(ASK SDK) still requires Java 8. And thus this starter also built with java 8.

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

- The referance (hello world) project using this version of the boot-starter is here [alexav2-helloworld-springboot-starter-pcf](https://github.com/sasiperi/alexa-spring-boot/tree/master/alexav2-spring-boot-starter)
- Here is a step by step approach of how this can be used.

#### Add maven (or gradel) dependency
Crate a spring boot starter project using spring boot initializer available in IDE (rest) or from start.spring.io.

#### Add maven dependency
* Open pom.xl (or gradle) and spring-boot-starter-alexa dependency.
* Current release version is 1.0.

https://github.com/sasiperi/alexa-spring-boot/blob/86300097178b1a57f77aa05d19451fe098211a70/samples/alexa-helloworld-springboot-starter-pcf/pom.xml#L28-L32

~~~xml
    		<dependency>
			<groupId>io.github.sasiperi</groupId>
			<artifactId>alexav2-spring-boot-starter</artifactId>
			<version>1.0.2</version>		
		</dependency>
~~~

#### Configure application properties. 
* You can in your YAML or .properties file use tab for hints of all available properties and the default values provided.
* You can override these in your application's app.props (or YAML)
* All the available properties start with **alexa.**
* Check the additional properties meta data for details of what each property means, what are the allowed values.
below are the availableample properties
* Important ones are 
   a) URI path, an endpoint name specific to your skill that you would like Alexa to send the requests to. This will be used by the starter while registering the ask-sdk skill servelet.
   b) application id, unique id provided by amazon while creating/configuring the skill on dev console.
   c) the card-title

~~~.prperties

###The application id that alexa(dev) provides amzn1.ask.skill.xxxxxxx###
alexa.application-id=amzn1.ask.skill.481fb850-g95a-5345-9h29-14fbbc889944

###### card title that you want to go on the account alexa.amazon and in the appstore ####
alexa.card-title=alexa-hello

#####the (servelet mapping) URI path (enpoint) that Alexa would use to send the requests to ############
alexa.speechlet-uri-mappings=/alexaHello

##################### Default intents (that can be overriden) ##################
alexa.intent.stop-intent = AMAZON.StopIntent
alexa.intent.cancel-intent = AMAZON.CancelIntent
alexa.intent.help-intent = AMAZON.HelpIntent
alexa.intent.fallback-intent=AMAZON.FallbackIntent

############## Various responses, for generic actions and intents ###################
alexa.response.good-bye= Good Bye Sample Spring Boot Hello 
alexa.response.hello-intent=Hello Sample Spring Boot Hello
alexa.response.help-intent=Help Sample Spring Boot Hello
alexa.response.welcome=Welcome Sample Spring Boot Hello
~~~

End with an example of getting some data out of the system or using it for a little demo

#### Creating Custom Skill Speechlet
1. Starter will auto cinfigure ASK SDK for you. ASK-SK v2 version modularized the intents, via handlers. Default implementaion the following handlers is been provided in the starter, these handlers are auto injected already, can be overridden.
   a) CancelAndStopIntentHandler
   b) FallbackIntentHandler
   c) HelpIntentHandler
   d) LaunchReuestHandler
   e) SessionEndedRequestHandler
2. These handlers can be extended and the "handle" method can be overridden to create specific implemenation.
3. For any custom intenets, specific to you skills, you will need to create a spring class (with @Component), that extends RequestHandler. Starter lib auto adds all custom handlers that are extending RequestHandler and annotated with spring @Componnet to the 
skills request handlers list by autowiring them.
4. starter also injects AlexaProperties, a comvenient class (bean) injected, to access any props starting with "alexa." a can be autowired.


##### Authentication, Autherization and Account Linking
  * To enable authentication, autherization, you may need to override other methods in the default impl.
  * An example project to demonstrate Auth using OAUTH2 (using auth-code grant) and does the account linking, is under samples/alexa-oms (sample order tracking skill for authorized customer).

~~~java
@Component
public class HelloWorldIntentHandler implements RequestHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldIntentHandler.class);
    
    @Autowired
    private AlexaProperties alexaProps;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.amazon.ask.request.handler.GenericRequestHandler#canHandle(java.lang.
     * Object)
     */

    @Override
    public boolean canHandle(HandlerInput input)
    {
        return input.matches(intentName("HelloWorldIntent"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.amazon.ask.request.handler.GenericRequestHandler#handle(java.lang.
     * Object)
     */
    @Override
    public Optional<Response> handle(HandlerInput input)
    {
        
        LOG.info("onIntent requestId={}, sessionId={}", input.getRequest().getRequestId(), input.getRequestEnvelope().getSession().getSessionId());
        return input.getResponseBuilder().
                withSpeech(alexaProps.getResponse().getGoodBye()).
                withSimpleCard(alexaProps.getCardTitle(), alexaProps.getResponse().getWelcome()).
                build();
    }

}
~~~


## Running the tests

* Get an account to Amazon Alexa developer console.
* Add and configure your skill.  
    - the endpoint requires a public hhtp/https url, which can inherit certs from the main domain e.g. hosted on PWS, can inherit from PWS
    - OR for local testing, Amazon susggests NGROK that can expose a HTTP/HTTPS urls, that can be used to configure endpoints, which would rout the request to an application running on your localhost:port.
* Click on the skill and create your speech assets.
* Sample hello world sppech assets for this sample application can be found here, that can be copy pasted.
: [Hello World Speech Assets](https://github.com/sasiperi/alexa-spring-boot/tree/master/samples/alexav2-helloworld-springboot-starter-pcf/src/main/resources/speechAssets)

* Go to Alexa Skill Simulator, activate your skill, using the wake up word.
* You can now test default intents and HelloWorld custom intent as shown in the below screen shot.
**You can see Alexa responses are created based on the configuration, as shown in the below Screenshot.

![Alexa Simulator Test](https://sasiperi.github.io/blog//static/img/alexa-helloworld-springboot-starter.png)

### Break down into end to end tests


## Deployment


## Built and Released with

* [Maven](https://maven.apache.org/) - Dependency Management
* [SonaType](https://oss.sonatype.org/)
* [Nexus Repo](https://rometools.github.io/rome/) - Artifacts Repo
* [Maven Central](https://repo.maven.apache.org/maven2/io/github/sasiperi/alexa-spring-boot-starter/)
* ngrok [https://ngrok.com/]  really rocks, and lets you test skills locally, on local host.

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
### Inspiration
1. [David Conway](https://www.linkedin.com/in/david-conway-31681513/?lipi=urn%3Ali%3Apage%3Ad_flagship3_search_srp_top%3BYB6kGZP%2FQdSP%2Fzr%2B2vFEzw%3D%3D&licu=urn%3Ali%3Acontrol%3Ad_flagship3_search_srp_top-search_srp_result&lici=FXLx3kG0REi70kl3UZAElw%3D%3D), Managing Director, Morgan Stanely
2. [Jim Shingler](https://github.com/jshingler), Director, Enterprise Arch, Cardinal Health


