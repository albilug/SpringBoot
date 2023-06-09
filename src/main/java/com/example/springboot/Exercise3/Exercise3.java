package com.example.springboot.Exercise3;
import com.example.springboot.Meal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ex_3")
public class Exercise3 {
        private List<Meal> meals = new ArrayList<>(Arrays.asList(
                new Meal("Carotone", "Carote con burro", 10),
                new Meal("Pandispagna", "Solo pane... ma dalla spagna", 5),
                new Meal("Grilli fritti", "CRI CRI CRI", 3))
        );

        @GetMapping(value = "/meal/get")
        public ResponseEntity<List<Meal>> getMeal() {
            return ResponseEntity.ok(meals);
        }


        @PostMapping(value = "/meal")
        public ResponseEntity<String> postMeal(@RequestBody Meal meal) {
            this.meals.add(meal);
            return ResponseEntity.ok("Meal Added!");
        }

        @PutMapping(value = "/meal/{name}")
        public ResponseEntity<String> putMeal(@PathVariable("name") String name, @RequestBody Meal meal) {
            this.meals.removeIf(m -> m.getName().equals(m.getName()));
            this.meals.add(meal);
            return ResponseEntity.ok("Updated");
        }

        @DeleteMapping(value = "/meal/{name}")
        public ResponseEntity<String> deleteMeal(@PathVariable("name") String name) {
            this.meals.removeIf(m -> m.getName().equals(name));
            return ResponseEntity.ok("Deleted");
        }

        @DeleteMapping(value = "/meal/price/{price}")
        public ResponseEntity<String> deleteMeal2(@PathVariable("price") double price) {
            this.meals.removeIf(m -> m.getPrice() > price);
            return ResponseEntity.ok("Deleted");
        }

        @PutMapping(value = "/meal/{name}/price")
        public ResponseEntity<String> putMeal2(@PathVariable("name") String name, @RequestBody Double updatedPrice) {
            Meal meal = this.meals.stream().filter(m -> m.getName().equals(name)).toList().get(0);
            this.meals.removeIf(m -> m.getName().equals(name));
            meal.setPrice(updatedPrice);
            this.meals.add(meal);
            return ResponseEntity.ok("Prezzo Aggiornato");
        }
    }
