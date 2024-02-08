package AM0.cicdpipeline.controller;

import AM0.cicdpipeline.model.Item;
import AM0.cicdpipeline.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemController itemController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllItems() throws Exception {
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");

        given(itemRepository.findAll()).willReturn(Arrays.asList(item1, item2));

        mockMvc.perform(get("/items/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(item1.getName())))
                .andExpect(jsonPath("$[1].name", is(item2.getName())));
    }

    @Test
    void createItem() throws Exception {
        Item newItem = new Item("NewItem");
        Item savedItem = new Item("NewItem");

        given(itemRepository.save(any(Item.class))).willReturn(savedItem);

        mockMvc.perform(post("/items/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(newItem.getName())));
    }

    @Test
    void getItemById() throws Exception {
        Item item = new Item("Item");
        given(itemRepository.findById("1")).willReturn(Optional.of(item));

        mockMvc.perform(get("/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(item.getName())));
    }

    @Test
    void updateItem() throws Exception {
        Item existingItem = new Item("ExistingItem");
        existingItem.setId("1");
        Item updatedItem = new Item("UpdatedItem");
        given(itemRepository.findById("1")).willReturn(Optional.of(existingItem));
        given(itemRepository.save(any(Item.class))).willReturn(updatedItem);

        mockMvc.perform(put("/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(updatedItem.getName())));
    }

    @Test
    void deleteItem() throws Exception {
        mockMvc.perform(delete("/items/1"))
                .andExpect(status().isOk());
    }
}
