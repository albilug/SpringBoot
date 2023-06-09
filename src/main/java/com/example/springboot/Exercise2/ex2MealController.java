package com.example.springboot.Exercise2;

import com.example.springboot.Meal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// BUONGIORNISSIMO CAFFÈ
@RestController
public class ex2MealController {
	private List<Meal> listaCibo = Arrays.asList(
			new Meal("Pollo", "Pollo....", 20),
			new Meal("Carote", "Carote... ma allo spiedo", 5),
			new Meal("Zebra in brodo", "Zebra con brodino", 4),
			new Meal("Funghi", "Porcini ottimi", 10),
			new Meal("Mele e pere", "Macedonia", 15),
			new Meal("Porchetta", "Oink oink", 88),
			new Meal("Riso sushi", "Salmone con anisakis", 23)
	);

	private List<Meal> soupOfTheDay = Arrays.asList(
			new Meal("Ossa di piccione in brodino", "Pollo....", 20),
			new Meal("Pollo in brodo", "Pollo ancora... ma allo spiedo", 4),
			new Meal("Pollo in brodo", "Brodo.. con pollo", 4),
			new Meal("Coscia in brodo", "Coscia... di pollo", 10),
			new Meal("Pollo arrosto in brodo", "Finalmente un buon pollo", 15),
			new Meal("Pollo con brodo fungo", "Porcini con pollo", 88),
			new Meal("Pollo in brodo", "Apelle figlio di A-Pollo...", 4)
	);

	@GetMapping("/get/day-dependent-meal/")
	public ResponseEntity<?> getChefsSpecial(
			@PathVariable("dayOfTheWeekIndex") int dayOfTheWeekIndex,
			@PathVariable("isSoupOfTheDay") boolean isSoupOfTheDay) {
		if (dayOfTheWeekIndex < 0 || dayOfTheWeekIndex > 6 ){
			return ResponseEntity.badRequest().body("Brutta richiesta");
		}
		if (isSoupOfTheDay) {return ResponseEntity.ok(soupOfTheDay.get (dayOfTheWeekIndex));}
		else {return ResponseEntity.ok(listaCibo.get(dayOfTheWeekIndex));}
	}

	@GetMapping("/list/{listIndex}")
	public ResponseEntity<Meal> mealList(
			@PathVariable("listIndex") int listIndex) {
		return ResponseEntity.ok(listaCibo.get(listIndex));
	}

	@GetMapping("/meal/{name}")
	public ResponseEntity<?> mealName(
			@PathVariable("name") String name) {
		for (Meal meal : listaCibo) {
			if (meal.getName().equals(name)) {
				return ResponseEntity.ok(meal);
			}
		}
		return ResponseEntity.badRequest().body("Non c'è!");
	}

	@GetMapping("/meal/description-match/{phrase}")
	public Meal mealDesc(@PathVariable("phrase") String descrizione) {
		for (Meal meal : listaCibo){
			if (meal.getDescription().equalsIgnoreCase(descrizione)){return meal;}
		}
		return null;
	}

	@GetMapping("/meal/price")
	public ResponseEntity<List<Meal>> mealPriceRange(
			@RequestParam("min") double min,
			@RequestParam("max") double max){
		List<Meal> mealsGiusti = new ArrayList<>();
		for(Meal meal: listaCibo){
			if(meal.getPrice()>=min && meal.getPrice()<=max){
				mealsGiusti.add(meal);}
		}
		if(!mealsGiusti.isEmpty()){return ResponseEntity.ok(mealsGiusti);}
		else{return ResponseEntity.notFound().build();}
	}
}
