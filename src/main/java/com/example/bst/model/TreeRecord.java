package com.example.bst.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("trees")
public class TreeRecord {
    @Id
    private String id;

    private List<Integer> inputNumbers;
    private TreeNodeDTO bst;          // sequential-insert BST
    private TreeNodeDTO balancedBst;  // bonus: balanced BST
    private Instant createdAt;

    public String getId() { return id; }
    public List<Integer> getInputNumbers() { return inputNumbers; }
    public void setInputNumbers(List<Integer> inputNumbers) { this.inputNumbers = inputNumbers; }
    public TreeNodeDTO getBst() { return bst; }
    public void setBst(TreeNodeDTO bst) { this.bst = bst; }
    public TreeNodeDTO getBalancedBst() { return balancedBst; }
    public void setBalancedBst(TreeNodeDTO balancedBst) { this.balancedBst = balancedBst; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
