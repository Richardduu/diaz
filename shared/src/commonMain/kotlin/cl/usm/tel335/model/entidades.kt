package cl.usm.tel335.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


val entidades = mutableListOf(
    Animal(
        id = 1,
        nombre = "Gato"
    ),
    Animal(
        id = 2,
        nombre = "Perro"
    ),
    Animal(
        id = 3,
        nombre = "Gaviota"
    ),
    Animal(
        id = 4,
        nombre = "Loro"
    ),
    Animal(
        id = 5,
        nombre = "Abeja"
    ),
    Animal(
        id = 6,
        nombre = "Araña"
    )
)

val tipos = mutableListOf(
    Tipo(
        nombre = "Gato",
        tipo = "mamifero",
        id = 1,
    ),
    Tipo(
        nombre = "Perro",
        tipo = "mamifero",
        id = 2,
    ),
    Tipo(
        nombre = "Gaviota",
        tipo = "ave",
        id = 3,
    ),
    Tipo(
        nombre = "Loro",
        tipo = "ave",
        id = 4,
    ),
    Tipo(
        nombre = "Abeja",
        tipo = "insecto",
        id = 5,
    ),
    Tipo(
        nombre = "Araña",
        tipo = "insecto",
        id = 6,
    )
)

@Serializable
data class Animal(
    val id: Int,
    val nombre: String
)

@Serializable
data class Tipo(
    val tipo: String,
    val id: Int,
    val nombre: String
)
// intente que sea una herencia pero tenia o problemas al compilar o problemas con el server, asique los separe. espero que me entienda (estuve +2 horas intentando arreglar solo esa parte)