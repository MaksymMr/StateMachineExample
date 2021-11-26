package ua.maksym.repo

import cats.data.EitherT
import ua.maksym.data.State
import ua.maksym.errors.Err

trait StateRepo[F[_]] {
  def addState(name: String): EitherT[F, Err, State]
  def removeState(id: Long): EitherT[F, Err, State]
  def getState(id: Long): EitherT[F, Err, State]
}