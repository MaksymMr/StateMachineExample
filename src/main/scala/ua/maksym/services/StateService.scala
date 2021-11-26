package ua.maksym.services

import cats.Monad
import cats.data.EitherT
import ua.maksym.data.State
import ua.maksym.errors.Err
import ua.maksym.repo.{StateRepo, TransitionRepo}

class StateService[F[_]: Monad](stateRepo: StateRepo[F], transitionRepo: TransitionRepo[F]) {

  def addState(name: String): EitherT[F, Err, State] = stateRepo.addState(name)

  def removeState(id: Long): EitherT[F, Err, State] = {
    for {
      _ <- transitionRepo.removeTransitionsFrom(id)
      _ <- transitionRepo.removeTransitionsTo(id)
      state <- stateRepo.removeState(id)
    } yield state
  }
}
