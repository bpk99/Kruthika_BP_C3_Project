import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService Test = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
public void Restaurant_Details_for_Testing(){
    LocalTime openingTime = LocalTime.parse("09:00:00");
    LocalTime closingTime = LocalTime.parse("23:00:00");
    restaurant = Test.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    restaurant.addToMenu("Sweet Corn soup",119);
    restaurant.addToMenu("Vegetable lasagne", 269);
}

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        Restaurant_Details_for_Testing();
        assertEquals(restaurant.getName(),Test.findRestaurantByName("Amelie's cafe").getName());
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        assertThrows(restaurantNotFoundException.class,() ->{Test.findRestaurantByName("Pantry d'or");},"Restaurant not found");
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        Restaurant_Details_for_Testing();
        int initialNumberOfRestaurants = Test.getRestaurants().size();
        Test.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, Test.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        Restaurant_Details_for_Testing();
        assertThrows(restaurantNotFoundException.class,()->Test.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        Restaurant_Details_for_Testing();
        int initialNumberOfRestaurants = Test.getRestaurants().size();
        Test.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,Test.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}