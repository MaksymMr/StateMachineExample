package ua.maksym.repo

import cats.data.EitherT
import ua.maksym.data.Entity
import ua.maksym.errors.Err

trait EntityRepo[F[_]] {
  def add(name: String, stateID: Long): EitherT[F, Err, Long]
  def get(id: Long): EitherT[F, Err, Entity]
  def remove(id: Long): EitherT[F, Err, Entity]
  def updateState(entityID: Long, stateID: Long): EitherT[F, Err, Entity]
}
