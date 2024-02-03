package nomemmurrakh.chains

fun main() {

    fun email(email: String) =
        chain {
            "Email must not be empty".unless { email.isNotEmpty() } +
                    "Email must contains @ sign".unless { email.contains("@") }
        }

    fun password(password: String) =
        chain {
            "Password must not be empty".unless { password.isNotEmpty() } +
                    "Password must be greater or equals to 8 char".unless { password.length >= 8 }
        }

    val combined = chain { email("someoneexample.com") + password("12345") }

    println(combined.validate())
}