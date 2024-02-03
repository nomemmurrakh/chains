package nomemmurrakh.chains

data class Validator(
    var next: Validator? = null,
    var message: String? = null,
    var predicate: () -> Boolean = { false },
) {
    fun validate(): Result<Boolean> =
        if (predicate())
            next?.validate() ?: Result.success(true)
        else Result.failure(IllegalStateException(message ?: "A message was not specified"))

    fun Validator.nextOrThis(): Validator = next?.nextOrThis() ?: this
}