package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("demo")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DemoEntity {

    @Id
    private String id;

    private WrapperModel<Set<DemoCharacteristicModel>> characteristic;
    private WrapperModel<Set<DemoNomenclatureModel>> nomenclature;

}
