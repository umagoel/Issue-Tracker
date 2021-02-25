package com.newcreation.jira.project;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @TableGenerator(name = "CATEGORY_ID", table = "ID_GENERATOR", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "CATEGORY_ID", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CATEGORY_ID")
    @Column(name = "CATEGORY_ID")
    private int id;
    @Column(name="CATEGORY_NAME")
    private String name;
}
