package com.egemeninceler.kutuphanem.enums

enum class FragmentName {
    KUTUPHANEM {
        override fun toString(): String {
            return "Kütüphanem"
        }
    },

    RAHATLATICI_SESLER {
        override fun toString(): String {
            return "Rahatlatıcı Sesler"
        }
    }
}