package org.rebeam

package object lenses {
  type LensN[S, A]      = PLensN[S, S, A, A]
}
