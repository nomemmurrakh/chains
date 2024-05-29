package nomemmurrakh.chains

data class Validator(
    val next: Validator? = null,
    val message: String? = null,
    val block: () -> Boolean = { false }
) {
    fun validate(): Result<Unit> =
        if (block())
            next?.validate() ?: Result.success(Unit)
        else Result.failure(IllegalStateException(message ?: "A message was not specified"))
}

internal class ValidatorBuilder {

    private var next: Validator? = null
    private var message: String? = null
    private var block: () -> Boolean = { false }

    fun setNext(next: Validator?) {
        this.next = next
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun setBlock(block: () -> Boolean) {
        this.block = block
    }

    fun build(): Validator = Validator(next, message, block)
}