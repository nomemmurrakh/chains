package nomemmurrakh.chains

class ChainBuilder {
    fun validator(block: () -> Boolean): Validator =
        ValidatorBuilder().apply { setBlock(block) }.build()

    fun String.unless(block: () -> Boolean): Validator =
        ValidatorBuilder().apply {
            setMessage(this@unless)
            setBlock(block)
        }.build()

    operator fun Validator.plus(validator: Validator): Validator =
        apply { nextOrThis().next = validator }

    private fun Validator.nextOrThis(): Validator = next?.nextOrThis() ?: this
}

fun chain(builder: ChainBuilder.() -> Validator): Validator =
    with(ChainBuilder()) { builder() }