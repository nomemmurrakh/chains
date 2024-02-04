[![](https://jitpack.io/v/nomemmurrakh/chains.svg)](https://jitpack.io/#nomemmurrakh/chains)

# Dependency
```groovy
// add in settings.gradle or root build.gradle
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		mavenCentral()
		maven { url 'https://jitpack.io' }
	}
}

// module-level build.gradle
dependencies {
	implementation 'com.github.nomemmurrakh:chains:1.0.2'
}
```

# Chains - Validation Library in Kotlin

**Validations without error messages**

```kotlin
chain {
	validator { password.isNotEmpty() } + 
	validator { password.length >= 8 }  
}
```

The above code can be used when you only want to know if the validations are successful or not, and you don't care about the error messages.

**Validations with error messages**

```kotlin
chain {  
    "Password must not be empty".unless { password.isNotEmpty() } + 
    "Password must be greater or equals to 8 chars".unless { password.length >= 8 }  
}
```

You can read the above code as follow:
"throw **error message** unless the given condition is met"

**Combine Chains**

    fun password(password: String) =
        chain {
            "Password must not be empty".unless { password.isNotEmpty() } +
                    "Password must be greater or equals to 8 chars".unless { password.length >= 8 }
        }

    fun email(email: String) =
        chain {
            "Email must not be empty".unless { email.isNotEmpty() } +
                    "Email must contains @ sign".unless { email.contains("@") }
        }

    val combined = chain { email("example.com") + password("12345") }

You can combine independent chains or validators together to form a more complex chain.

**Single validator**

    val validator = Validator(  
	    message = "Error Message",  
	    predicate = { true }  
    )

If you want to validate only one value, you can directly use the ***Validator*** class. 

**Start Validation**

    val result = chain { validator { "someone@example.com".contains("@") } }.validate()

You can start validation by calling ***validate()*** on a ***Validator*** object.

> This library uses **Kotlin Result** class to encapsulate either success or failure of the validation. 
> The validation starts **lazily** only when ***validate()*** is called.
 
**Handling Error Messages**

It may be required to perform a task after a chain is validated for example after the email chain is validated, you may want to reflect its result **(success or failure)** to the UI. For this purpose, you can use the ***fold()*** function.

    fun updateState() {  
	    email("someone@example.com")  
		    .validate()  
		    .fold(  
			    { println("is validation successful: $it") }, // gets a boolean  
			    { print(it.message) } // gets a throwable consisting of the error message if given  
		    )  
    }

**Best Practice** (Based on my opinions)

 - make a separate function for each chain for modularity
 - combine separate and independent chains either by assigning to a new variable or creating a new function

#### Suggestions would be appreciated <3
If you like the library please give a star, thank you!
