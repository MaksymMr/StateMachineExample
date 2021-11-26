package ua.maksym.services

import cats.Monad
import cats.data.EitherT
import ua.maksym.data.Entity
import ua.maksym.errors.Err
import ua.maksym.repo._

class EntityService[F[_]: Monad](
  stateRepo: StateRepo[F],
  entityRepo: EntityRepo[F],
  transitionRepo: TransitionRepo[F],
  transitionLogRepo: TransitionLogRepo[F]
) {

  def addEntity(name: String, stateID: Long): EitherT[F, Err, Long] = {
    for {
      _ <- stateRepo.getState(stateID)
      entity <- entityRepo.add(name, stateID)
    } yield entity
  }

  def getEntity(id: Long): EitherT[F, Err, Entity] = entityRepo.get(id)

  def removeEntity(id: Long): EitherT[F, Err, Entity] = {
    for {
      _ <- transitionLogRepo.removeForEntity(id)
      removed <- entityRepo.remove(id)
    } yield removed
  }

  def transitEntity(entityID: Long, toStateID: Long): EitherT[F, Err, Entity] = {
    for {
      e <- entityRepo.get(entityID)
      _      <- transitionRepo.getTransition(e.stateID, toStateID)
      currState <- stateRepo.getState(e.stateID)
      updated <- entityRepo.updateState(entityID, toStateID)
      nextState <- stateRepo.getState(updated.stateID)
      _ <- transitionLogRepo.add(entityID, currState.name, nextState.name)
    } yield updated
  }
}
