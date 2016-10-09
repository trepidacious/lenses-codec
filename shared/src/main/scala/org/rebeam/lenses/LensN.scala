package org.rebeam.lenses

import monocle._

import scalaz.Functor
import scala.language.higherKinds

object LensN{
  def apply[S, A](n: String, lens: Lens[S, A]) = new LensN[S, A] {
    def get(s: S): A = lens.get(s)
    def set(b: A): S => S = lens.set(b)
    def modifyF[F[_]: Functor](f: A => F[A])(s: S): F[S] = lens.modifyF(f)(s)
    def modify(f: A => A): S => S = lens.modify(f)
    def name: String = n
  }
}
