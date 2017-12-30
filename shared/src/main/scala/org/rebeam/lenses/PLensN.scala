package org.rebeam.lenses

import monocle.PLens

abstract class PLensN[S, T, A, B] extends PLens[S, T, A, B] {
  def name: String
}

