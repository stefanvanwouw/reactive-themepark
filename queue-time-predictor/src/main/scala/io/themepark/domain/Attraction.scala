package io.themepark.domain

import java.util.UUID

/**
  * State an attraction can be in.
  */
object AttractionState extends Enumeration {
  /**
    * IDLE: The attraction is stopped and waiting for new visitors to hop in.
    * RUNNING: The attraction is running its usual program.
    * BROKEN: (part of) the attraction is broken and needs to be repaired.
    * IN_MAINTENANCE: The attraction is currently being repaired.
    */
  val IDLE, RUNNING, BROKEN, IN_MAINTENANCE = Value
}

/**
  * Type class for AttractionId
  * @param v The underlying type (UUID).
  */
class AttractionId(val v: UUID) extends AnyVal with Serializable {

  /**
    * Converts the underlying UUID to a String
    * @return String representing the UUID.
    */
  override def toString: String = v.toString

}


/**
  * A Themepark attraction.
  * @param id Unique identifier for refering to this attraction.
  * @param name Non-unique name of the attraction.
  * @param capacity Number of people that can be in the attraction when in RUNNING state.
  * @param state The state of the attraction (e.g. if it is running or not).
  * @param visitorsInQueue A sequence of the current visitors that are in the queue.
  * @param visitorsOnRide A set of the current visitors that are on the ride.
  */
case class Attraction(id: AttractionId,
                      name: String,
                      capacity: Int,
                      state: AttractionState.Value,
                      visitorsInQueue: Seq[VisitorId],
                      visitorsOnRide: Set[VisitorId])
