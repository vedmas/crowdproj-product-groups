import com.crowdproj.marketplace.product.groups.api.models.*
import com.crowdproj.marketplace.product.groups.app.ktor.process
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

suspend fun ApplicationCall.createPg() {
    val request = receive<PgCreateRequest>()
    val context = ProductGroupContext()
    context.fromTransport(request)
    process(context)
    respond(context.toTransportCreate())
}

suspend fun ApplicationCall.readPg() {
    val request = receive<PgReadRequest>()
    val context = ProductGroupContext()
    context.fromTransport(request)
    process(context)
    respond(context.toTransportRead())
}

suspend fun ApplicationCall.updatePg() {
    val request = receive<PgUpdateRequest>()
    val context = ProductGroupContext()
    context.fromTransport(request)
    process(context)
    respond(context.toTransportUpdate())
}

suspend fun ApplicationCall.searchPg() {
    val request = receive<PgSearchRequest>()
    val context = ProductGroupContext()
    context.fromTransport(request)
    process(context)
    respond(context.toTransportSearch())
}

suspend fun ApplicationCall.deletePg() {
    val request = receive<PgDeleteRequest>()
    val context = ProductGroupContext()
    context.fromTransport(request)
    process(context)
    respond(context.toTransportDelete())
}