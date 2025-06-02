package com.abes.fdms.service;

import com.abes.fdms.dto.FoodItemDto;
import com.abes.fdms.exception.DuplicateItemException;
import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link FoodService}.
 * Tests adding, restocking, and removing food items.
 */
class FoodServiceTest {

    private FoodService foodService;

    /**
     * Sets up a clean state before each test.
     */
    @BeforeEach
    void setUp() {
        CollectionUtil.reset(); // Ensure clean state before each test
        foodService = new FoodService();
    }

    /**
     * Tests successful addition of a new food item.
     */
    @Test
    void testAddNewItem_Success() throws Exception {
        foodService.addNewItem("TestPizza", 120.0, 5);
        FoodItemDto item = foodService.getFoodItemByName("TestPizza");
        assertNotNull(item);
        assertEquals("TestPizza", item.getName());
    }

    /**
     * Tests adding a food item that already exists throws exception.
     */
    @Test
    void testAddNewItem_AlreadyExists() throws Exception {
        foodService.addNewItem("TestBurger", 80.0, 3);
        assertThrows(DuplicateItemException.class, () -> {
            foodService.addNewItem("TestBurger", 90.0, 2);
        });
    }

    /**
     * Tests restocking an existing food item.
     */
    @Test
    void testRestockItem() throws Exception {
        foodService.addNewItem("TestFries", 50.0, 2);
        foodService.restockItem("TestFries", 3);
        assertEquals(5, foodService.getMenu().get(foodService.getFoodItemByName("TestFries")));
    }

    /**
     * Tests removing a food item.
     */
    @Test
    void testRemoveItem() throws Exception {
        foodService.addNewItem("TestRemove", 60.0, 1);
        foodService.removeItem("TestRemove");
        assertNull(foodService.getFoodItemByName("TestRemove"));
    }
}
