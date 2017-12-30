package org.rebeam.lenses

import monocle._

import scala.reflect.ClassTag
import scalaz.\/

// TODO Add a macro like GenPrism but including the name based on the type - look at Circe to see how its macros
// find that name for sealed traits
object PrismN {

  //No idea why IDEA doesn't like using new PrismN[S, A] instead of PPrism, even though it's identical
  def apply[S, A](_name: String)(_getOption: S => Option[A])(_reverseGet: A => S): PrismN[S, A] = new PPrismN[S, S, A, A] {

    private val p = Prism[S, A](_getOption)(_reverseGet)

    def getOrModify(s: S): S \/ A = p.getOrModify(s)
    def reverseGet(b: A): S = p.reverseGet(b)
    def getOption(s: S): Option[A] = p.getOption(s)

    def name: String = _name
  }

  def classTag[S, A <: S](name: String)(implicit ct: ClassTag[A]): PrismN[S, A] = narrow(name)(ct.unapply)

  def partialNarrow[S, A <: S](name: String)(pf: PartialFunction[S, A]): PrismN[S, A] = PrismN(name)(pf.lift)(a=>a:S)

  def narrow[S, A <: S](name: String)(f: S => Option[A]): PrismN[S, A] = PrismN(name)(f)(a=>a:S)

}
