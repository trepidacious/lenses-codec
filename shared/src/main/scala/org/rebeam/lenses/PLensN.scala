package org.rebeam.lenses

import monocle.{Lens, PLens}

import scalaz.Functor

import scala.language.higherKinds

abstract class PLensN[S, T, A, B] extends PLens[S, T, A, B] {
  def name: String
}

