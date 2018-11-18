package com.example.http4stemplate.persistence.utils

object Reflection {

  object Object {

    def forString[T](path: String): T = {
      import scala.reflect.runtime.universe
      val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
      val module = runtimeMirror.staticModule(path)
      val obj = runtimeMirror.reflectModule(module)
      obj.instance.asInstanceOf[T]
    }
  }

}
