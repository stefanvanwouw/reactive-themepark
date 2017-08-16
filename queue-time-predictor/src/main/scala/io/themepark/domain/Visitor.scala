package io.themepark.domain

import java.util.UUID

/**
  * Type class for VisitorId
  * @param v The underlying type (UUID).
  */
class VisitorId(val v: UUID) extends AnyVal with Serializable {

  /**
    * Converts the underlying UUID to a String
    * @return String representing the UUID.
    */
  override def toString: String = v.toString
}

/**
  * A visitor in the themepark.
  * @param id The id of the visitor (see this as an ID belonging to an RFID bracelet for example).
  * @param name The informal name of the visitor (non-unique).
  */
case class Visitor(id: VisitorId, name: String)
