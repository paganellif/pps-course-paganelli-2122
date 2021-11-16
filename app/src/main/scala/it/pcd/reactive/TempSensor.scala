package it.pcd.reactive

import io.reactivex.rxjava3.core.{BackpressureStrategy, Flowable}

import java.lang.Thread.sleep
import java.util.Random
import java.util.concurrent.{Executors, ScheduledExecutorService, ScheduledFuture}

case class BaseTimeValue() {
  private var time: Double = 0.0
  private val exec: ScheduledExecutorService = Executors.newScheduledThreadPool(1)

  def start: ScheduledFuture[_] =
    exec.scheduleAtFixedRate(() => {
      time = time + 0.01
    }, 0, 100, java.util.concurrent.TimeUnit.MILLISECONDS)
  
  def getCurrentValue: Double = time
}

case class TempSensor(min: Double, max: Double, spikeFreq: Double) {
  private var currentValue: Double = 0.0
  private val gen = new Random(System.nanoTime())
  private val zero = (max + min)*0.5
  private val range = (max - min)*0.5
  private val spikeVar = range*10
  private val time = BaseTimeValue()
  private val exec: ScheduledExecutorService = Executors.newScheduledThreadPool(1)

  def startSensor(): Unit = {
    time.start
    exec.scheduleAtFixedRate(() => {
      var newValue: Double = zero + Math.sin(time.getCurrentValue)*range*0.8 + (-0.5 + gen.nextDouble())*range*0.2

      if (gen.nextDouble() <= spikeFreq)
        newValue = currentValue + spikeVar

      currentValue = newValue
    }, 0, 100, java.util.concurrent.TimeUnit.MILLISECONDS)
  }

  def stopSensor(): Unit = exec.shutdown()

  def getCurrentValue: Double = currentValue
}

case class TempStream(min: Double, max: Double, spikeFreq: Double, freq: Int){
  private val tempSensor: TempSensor = TempSensor(min, max, spikeFreq)
  private val tempStream: Flowable[Double] = Flowable.create(emitter => {
    new Thread(() => {
      tempSensor.startSensor()

      while(true){
        emitter.onNext(tempSensor.getCurrentValue)
        try {
          sleep(freq)
        } catch {
          case e: InterruptedException => e.printStackTrace()
        }
      }
    }).start()
    
  }, BackpressureStrategy.LATEST)

  def toFlowable: Flowable[Double] = this.tempStream
}

object MyApp extends App {
  TempStream(20, 40, 0.1, 500).toFlowable.subscribe(println(_))
}