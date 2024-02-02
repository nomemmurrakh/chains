package nomemmurrakh.chains

object ChainBuilderScope {

    fun String.unless(predicate: () -> Boolean): Validator =
        Validator(message = this, predicate = predicate)

    fun validator(init: Validator.() -> Unit): Validator =
        Validator().apply { init() }

    operator fun Validator.plus(validator: Validator): Validator {
        next = validator
        return this
    }
}

fun chain(builder: ChainBuilderScope.() -> Validator): Validator =
    with(ChainBuilderScope) { builder() }
