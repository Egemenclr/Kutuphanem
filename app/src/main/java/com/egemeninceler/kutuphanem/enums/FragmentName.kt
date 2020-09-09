package com.egemeninceler.kutuphanem.enums

enum class FragmentName {
    KUTUPHANEM {
        override fun toString(): String {
            return "Kütüphanem"
        }
    },
    KITAP_BUL {
        override fun toString(): String {
            return "Kitap Bul"
        }
    },
    RAHATLATICI_SESLER {
        override fun toString(): String {
            return "Rahatlatıcı Sesler"
        }
    }
}