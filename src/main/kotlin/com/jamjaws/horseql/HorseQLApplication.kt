package com.jamjaws.horseql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class HorseQLApplication

fun main(args: Array<String>) {
	runApplication<HorseQLApplication>(*args)
}
