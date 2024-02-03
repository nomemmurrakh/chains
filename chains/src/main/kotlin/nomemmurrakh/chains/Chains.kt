package nomemmurrakh.chains

object ChainBuilderScope {

    fun String.unless(predicate: () -> Boolean): Validator =
        Validator(null, this, predicate)

    fun validator(init: Validator.() -> Unit): Validator =
        Validator().apply { init() }

    operator fun Validator.plus(validator: Validator): Validator =
        apply { nextOrThis().next = validator }
}

fun chain(builder: ChainBuilderScope.() -> Validator): Validator =
    with(ChainBuilderScope) { builder() }
