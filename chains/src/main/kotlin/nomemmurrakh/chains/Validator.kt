package nomemmurrakh.chains

class Validator(
    val message: String? = null,
    val block: () -> Boolean = { false }
) {
    var next: Validator? = null
        internal set

    fun validate(): Result<Unit> =
        if (block())
            next?.validate() ?: Result.success(Unit)
        else Result.failure(IllegalStateException(message ?: "A message was not specified"))
}

internal class ValidatorBuilder {

    private var message: String? = null
    private var block: () -> Boolean = { false }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun setBlock(block: () -> Boolean) {
        this.block = block
    }

    fun build(): Validator = Validator(message, block)
}