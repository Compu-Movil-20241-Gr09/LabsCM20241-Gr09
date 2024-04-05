package co.edu.udea.compumovil.gr09_20241.lab1.data

import android.util.Pair
import co.edu.udea.compumovil.gr09_20241.lab1.R

object DataSource {
    val genderOptions = listOf(
        R.string.male,
        R.string.female/*,
        R.string.other*/
    )

    val scholarityLevels = listOf(
        R.string.primary,
        R.string.secondary,
        R.string.university,
        R.string.other
    )

    val latamCountries = listOf(
        "Argentina",
        "Bolivia",
        "Brasil",
        "Chile",
        "Colombia",
        "Costa Rica",
        "Cuba",
        "Ecuador",
        "El Salvador",
        "Guatemala",
        "Haiti",
        "Honduras",
        "Mexico",
        "Nicaragua",
        "Panama",
        "Paraguay",
        "Peru",
        "Republica Dominicana",
        "Uruguay",
        "Venezuela"
    )

    val latamCities = listOf(
        Pair("Argentina", "Buenos Aires"),
        Pair("Bolivia", "Sucre"),
        Pair("Brasil", "Brasilia"),
        Pair("Chile", "Santiago de Chile"),
        Pair("Colombia", "Bogotá"),
        Pair("Colombia", "Medellín"),
        Pair("Costa Rica", "San José"),
        Pair("Cuba", "La Habana"),
        Pair("Ecuador", "Quito"),
        Pair("El Salvador", "San Salvador"),
        Pair("Guatemala", "Ciudad de Guatemala"),
        Pair("Haiti", "Puerto Príncipe"),
        Pair("Honduras", "Tegucigalpa"),
        Pair("Mexico", "Ciudad de México"),
        Pair("Nicaragua", "Managua"),
        Pair("Panama", "Panamá"),
        Pair("Paraguay", "Asunción"),
        Pair("Perú", "Lima"),
        Pair("Republica Dominicana", "Santo Domingo"),
        Pair("Uruguay", "Montevideo"),
        Pair("Venezuela", "Caracas")
    )
}