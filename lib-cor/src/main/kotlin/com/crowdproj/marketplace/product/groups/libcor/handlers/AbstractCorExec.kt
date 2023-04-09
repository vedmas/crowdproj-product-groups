import com.crowdproj.marketplace.product.groups.libcor.ICorExec

abstract class AbstractCorExec<T>(
    override val title: String,
    override val description: String = "",
    private val blockOn: suspend T.() -> Boolean = {true},
    private val blockExcept: suspend T.(Throwable) -> Unit = {}
): ICorExec<T> {
    protected abstract suspend fun handle(context: T)

    override suspend fun exec(context: T) {
        if (on(context)) {
            try {
                handle(context)
            } catch (e: Exception) {
                except(context, e)
            }
        }
    }

    private suspend fun on(context: T): Boolean = context.blockOn()
    private suspend fun except(context: T, e: Throwable) = context.blockExcept(e)
}