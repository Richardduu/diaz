package cl.usm.tel335.model
import kotlinx.serialization.Serializable

val entidades = mutableListOf(
    Entidades(
        id = 1,
        nombre = "Animal",
    ),
)

@Serializable
data class Entidades(
    val id: Int,
    val nombre: String
)
