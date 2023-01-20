package dev.seabat.android.serverlessbbs.constants

import dev.seabat.android.serverlessbbs.R

enum class BbsThreadColor(val colorString: String, val colorResId: Int) {
    MINTCREAM("mintcream", R.color.mintcream),
    ALICEBLUE("aliceblue", R.color.aliceblue),
    IVORY("ivory", R.color.ivory),
    BEIGE("beige", R.color.beige),
    SEASHELL("seashell", R.color.seashell),
    LAVENDER("lavender", R.color.lavender);

    companion object {
        fun covertToId(colorString: String): Int {
            return when (colorString) {
                "mintcream" -> MINTCREAM.colorResId
                "alicebule" -> ALICEBLUE.colorResId
                "ivory" -> IVORY.colorResId
                "beige" -> BEIGE.colorResId
                "seashell" -> SEASHELL.colorResId
                "lavender" -> LAVENDER.colorResId
                else -> R.color.white
            }
        }
    }
}