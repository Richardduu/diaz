package cl.usm.tel335

import cl.usm.tel335.model.Animal
import cl.usm.tel335.model.tipos
import cl.usm.tel335.model.entidades
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.httpDateFormat
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}



fun Application.module() {
    configureSerialization()

    routing {
        route("/animal") {
            get("/") {
                call.respond(HttpStatusCode.OK, entidades)
            }
            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val entidad = entidades.find { it.id == id }
                if (entidad != null) {
                    call.respond(entidad)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Entidad no encontrada")
                }
            }

            post("/") {
                val entidad = call.receive<Animal>()
                val index = entidades.indexOfFirst { it.id == entidad.id }

                if (index == -1) {
                    entidades.add(entidad)
                    call.respond(HttpStatusCode.Created,"Entidad creada correctamente")
                } else {
                    entidades[index] = entidad
                    call.respond(HttpStatusCode.OK,"Entidad reemplazada correctamente")
                }
            }

            put("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "ID inválido")
                    return@put
                }

                val index = entidades.indexOfFirst { it.id == id }
                if (index == -1) {
                    call.respond(HttpStatusCode.NotFound, "Entidad no encontrada")
                } else {
                    val actualizada = call.receive<Animal>()
                    entidades[index] = Animal(id = id, nombre = actualizada.nombre)
                    call.respond(HttpStatusCode.OK, "Entidad reemplazada correctamente")
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val entidad = entidades.find { it.id == id }
                if (entidad != null) {
                    entidades.remove(entidad)
                    call.respond(HttpStatusCode.OK, "Entidad eliminada correctamente")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Entidad no encontrada")
                }
            }
                    route("/tipo") { //3 tipos: mamifero, ave e insecto
                        get("/") {
                            call.respond(HttpStatusCode.OK, tipos)
                        }
                        get("/mamifero") {
                            call.respond(HttpStatusCode.OK, tipos.filter { it.tipo == "mamifero" })
                        }
                        get("/ave") {
                            call.respond(HttpStatusCode.OK, tipos.filter { it.tipo == "ave" })
                        }
                        get("/insecto") {
                            call.respond(HttpStatusCode.OK, tipos.filter { it.tipo == "insecto" })
                    }}
                }
            }

        }

fun Application.configureSerialization(){
    install(ContentNegotiation){
        json()
    }
}