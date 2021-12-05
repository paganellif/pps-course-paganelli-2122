package it.pps.course.u13

case class UsedCar(
  make: String,
  model: String,
  kmDriven: Int,
  yearOfManufacturing: Int,
  hasGps: Boolean = false,
  hasAc: Boolean = false,
  hasAirBags: Boolean = false,
  hasAbs: Boolean = false
) {
  require(yearOfManufacturing > 1970, "Incorrect year")
  require(checkMakeAndModel(), "Incorrect aplly and model")

  private def checkMakeAndModel(): Boolean = make match {
    case "Maruti" => model == "alto"
    case "Toyota" => model == "Corolla"
    case _ => true
  }
}

object TryUsedCars extends App {
  val usedMaruti = UsedCar(model = "alto", make = "Maruti", kmDriven = 10000, yearOfManufacturing = 1980, hasAbs = true)
  println(usedMaruti)

  val usedCorolla = usedMaruti.copy(make = "Toyota", model = "Corolla")
  println(usedCorolla)

}
