package com.example.bst.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNodeDTO {
    public int value;
    public TreeNodeDTO left;
    public TreeNodeDTO right;

    public TreeNodeDTO() {}
    public TreeNodeDTO(int value) { this.value = value; }
}
