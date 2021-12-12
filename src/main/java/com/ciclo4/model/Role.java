package com.ciclo4.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="roles")
public class Role {
	
    @Id
    private Integer id;
    @Indexed(unique=true,direction = IndexDirection.DESCENDING,dropDups=true)

    private String name;
   
    private String description;
    
}
