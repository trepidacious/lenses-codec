package org.rebeam.lenses

import monocle.PPrism

abstract class PPrismN[S, T, A, B] extends PPrism[S, T, A, B] {
  def name: String
}

