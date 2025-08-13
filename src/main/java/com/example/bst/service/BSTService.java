package com.example.bst.service;

import com.example.bst.model.TreeNodeDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BSTService {

    /** Parse either CSV/spaces string like "5, 2, 8, 2" */
    public List<Integer> parseNumbers(String csvOrSpaceSeparated) {
        if (csvOrSpaceSeparated == null || csvOrSpaceSeparated.isBlank()) return List.of();
        String s = csvOrSpaceSeparated.replaceAll("[\n\t]", " ").trim();
        if (s.isBlank()) return List.of();
        String[] parts = s.split("[,\s]+");
        List<Integer> out = new ArrayList<>();
        for (String p : parts) {
            if (!p.isBlank()) out.add(Integer.parseInt(p));
        }
        return out;
    }

    /** Build BST by sequential insertion (duplicates are ignored). */
    public TreeNodeDTO buildSequentialBST(List<Integer> nums) {
        TreeNodeDTO root = null;
        for (int v : nums) {
            root = insert(root, v);
        }
        return root;
    }

    private TreeNodeDTO insert(TreeNodeDTO root, int v) {
        if (root == null) return new TreeNodeDTO(v);
        if (v < root.value) root.left = insert(root.left, v);
        else if (v > root.value) root.right = insert(root.right, v);
        // ignore duplicates
        return root;
    }

    /** Bonus: build a balanced BST containing the unique values. */
    public TreeNodeDTO buildBalancedBST(List<Integer> nums) {
        List<Integer> sortedUnique = nums.stream().distinct().sorted().collect(Collectors.toList());
        return buildBalanced(sortedUnique, 0, sortedUnique.size() - 1);
    }

    private TreeNodeDTO buildBalanced(List<Integer> a, int lo, int hi) {
        if (lo > hi) return null;
        int mid = (lo + hi) >>> 1;
        TreeNodeDTO n = new TreeNodeDTO(a.get(mid));
        n.left  = buildBalanced(a, lo, mid - 1);
        n.right = buildBalanced(a, mid + 1, hi);
        return n;
    }
}
