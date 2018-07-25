
package me.kihwankim

import kotlin.js.Json

@JsNonModule
@JsModule("lodash")
external val lodash: dynamic

@JsNonModule
@JsModule("vue")
external class Vue(option: VueOptions)

external interface VueOptions : Json {
    var el: Any
    var data: Json
    var computed: Json
    var methods: Json
}

fun <T : Json> Json(): T = js("({})")
fun <T : Json> Json(init: T.() -> Unit): T = Json<T>().apply(init)

fun Vue(init: VueOptions.() -> Unit): Vue = Vue(Json<VueOptions>().apply(init))

external interface Model: Json {
    var firstname: String
    var lastname: String
}

external interface Computed: Json {
    var fullname: () -> String
}

inline fun <T> thisAs(): T = js("this")


fun run() {
    println(lodash.capitalize("hello world"))

    val vm: dynamic = Vue {
        el = "#example"
        data = Json<Model> {
            firstname = "Kihwan"
            lastname = "Kim"
        }

        computed = Json<Computed> {
            fullname = {
                val self = thisAs<Model>()

                "${self.firstname} ${self.lastname}"
            }
        }



    }

}
