package com.example.bst;

import java.time.Instant;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.bst.model.TreeRecord;
import com.example.bst.repo.TreeRecordRepository;
import com.example.bst.service.BSTService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@Import(BSTService.class)
class BSTControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @MockBean TreeRecordRepository repo;

    @Test
    void processNumbersStoresAndReturnsTrees() throws Exception {
        // Mock repository save
        Mockito.when(repo.save(any(TreeRecord.class))).thenAnswer(inv -> {
            TreeRecord r = inv.getArgument(0);
            // simulate DB assigning an ID
            try {
                var idField = TreeRecord.class.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(r, "mock-id-123");
            } catch (Exception ignored) {}
            r.setCreatedAt(Instant.now());
            return r;
        });

        var body = Map.of("numbers", "8, 3, 10, 1, 6, 14, 4, 7, 13");

        mvc.perform(post("/process-numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(body)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.inputNumbers.length()").value(9))
           .andExpect(jsonPath("$.bst.value").value(8))
           .andExpect(jsonPath("$.balancedBst").exists());
    }
}
