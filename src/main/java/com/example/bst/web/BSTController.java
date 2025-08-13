package com.example.bst.web;

import com.example.bst.model.TreeNodeDTO;
import com.example.bst.model.TreeRecord;
import com.example.bst.repo.TreeRecordRepository;
import com.example.bst.service.BSTService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
public class BSTController {

    private final BSTService bstService;
    private final TreeRecordRepository repo;

    public BSTController(BSTService bstService, TreeRecordRepository repo) {
        this.bstService = bstService;
        this.repo = repo;
    }

    /** Accepts JSON like { "numbers": "5,2,8,1,3" } or { "numbersList": [5,2,8,1,3] } */
    @PostMapping(value = "/process-numbers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> processNumbers(@RequestBody ProcessRequest req) {
        List<Integer> nums = (req.numbersList != null && !req.numbersList.isEmpty())
                ? req.numbersList
                : bstService.parseNumbers(req.numbers);

        TreeNodeDTO bst = bstService.buildSequentialBST(nums);
        TreeNodeDTO balanced = bstService.buildBalancedBST(nums);

        TreeRecord rec = new TreeRecord();
        rec.setInputNumbers(nums);
        rec.setBst(bst);
        rec.setBalancedBst(balanced);
        rec.setCreatedAt(Instant.now());
        rec = repo.save(rec);

        return Map.of(
                "id", rec.getId(),
                "inputNumbers", rec.getInputNumbers(),
                "bst", rec.getBst(),
                "balancedBst", rec.getBalancedBst(),
                "createdAt", rec.getCreatedAt()
        );
    }

    /** Returns all previous trees as JSON (newest first) */
    @GetMapping(value = "/previous-trees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TreeRecord> previousTrees() {
        List<TreeRecord> all = repo.findAll();
        all.sort(Comparator.comparing(TreeRecord::getCreatedAt).reversed());
        return all;
    }

    /** Simple healthcheck (optional) */
    @GetMapping("/health")
    public Map<String, String> health() { return Map.of("status", "ok"); }

    public static class ProcessRequest {
        public String numbers;            // "5, 2, 8, 1, 3"
        public List<Integer> numbersList; // [5,2,8,1,3]
    }
}
