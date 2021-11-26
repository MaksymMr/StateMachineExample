package ua.maksym.repo

import cats.data.EitherT
import ua.maksym.data.Transition
import ua.maksym.errors.Err

trait TransitionRepo[F[_]] {
  def addTransition(from: Long, to: Long): EitherT[F, Err, Transition]
  def getTransition(from: Long, to: Long): EitherT[F, Err, Transition]
  def getTransitionsFrom(from: Long): EitherT[F, Err, Seq[Transition]]
  def removeTransitionsFrom(from: Long): EitherT[F, Err, Seq[Transition]]
  def removeTransitionsTo(to: Long): EitherT[F, Err, Seq[Transition]]
  def removeTransition(from: Long, to: Long): EitherT[F, Err, Transition]
}