package com.example.bst;

import com.example.bst.model.TreeNodeDTO;
import com.example.bst.service.BSTService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BSTServiceTest {

    private final BSTService svc = new BSTService();

    @Test
    void parsesCsvAndSpaces() {
        List<Integer> nums = svc.parseNumbers("8, 3  10 , 1\t6\n14 4,7 13");
        assertEquals(List.of(8,3,10,1,6,14,4,7,13), nums);
    }

    @Test
    void sequentialBstBuildsCorrectRoot() {
        TreeNodeDTO root = svc.buildSequentialBST(List.of(8,3,10,1,6,14,4,7,13));
        assertNotNull(root);
        assertEquals(8, root.value);
        assertEquals(3, root.left.value);
        assertEquals(10, root.right.value);
    }
}
