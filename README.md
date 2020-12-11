# Command line User Prompt Processor ![Maven Central](https://img.shields.io/maven-central/v/com.github.vatbub/commandlineUserPromptProcessor)
This is a library for user interaction during the execution of a cli application.
Oftentimes, you will find yourself in the need to request user input using `System.in` or a `Scanner`.
You would print a prompt like `Please enter the file name: ` using `System.println()` and then use something like `scanner.nextLine()` to parse the input.

This is where this library comes in. You tell it what to prompt the user on the command line and what user input to expect.
It will prompt the user and then parse the input for you so that you don't have to handle all the edge cases.

**Note**: This library is still in the early alpha stage!

## Download and maven dependency
Since there is no official release yet, you need to build it on your own using the command `mvn install`.
This will install the library to your local maven repository. You can then use it in your project with this dependency:
```xml
<dependency>
    <groupId>com.github.vatbub</groupId>
    <artifactId>commandlineUserPromptProcessor</artifactId>
    <version>1.1</version>
</dependency>
```

## Usage

```java
import com.github.vatbub.commandlineUserPromptProcessor.Prompt;
import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParsableBoolean;
import com.github.vatbub.commandlineUserPromptProcessor.parsers.ParseException;

public class Sample {
    public static void main(String[] args) {
        try {
            Prompt prompt = new Prompt("Are you a human", new ParsableBoolean());
            ParsableBoolean result = (ParsableBoolean) prompt.doPrompt();
            System.out.println("Your answer was " + result.toValue());
        } catch (ParseException e) {
            System.out.println("Invalid input");
        }

        try {
            boolean defaultValue = true;
            Prompt prompt = new Prompt("Are you a human", new ParsableBoolean(defaultValue));
            ParsableBoolean result = (ParsableBoolean) prompt.doPrompt();
            System.out.println("Your answer was " + result.toValue());
        } catch (ParseException e) {
            // will actually not occur because the default value is returned in the case of invalid input
            System.out.println("Invalid input");
        }
    }
}
```

### Creating your own return type
Currently, the library supports only boolean and string return types (though this list will shortly become longer!).
However, you may find yourself in the situation where you have custom classes or types that you need to request from the user. 
In that case, you can simply implement the `Parsable<T>` interface and supply that instead of the `ParsableBoolean` in the example above. 
