package com.example.demo;

import com.example.demo.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoRepositoryTest {

    @Autowired
    private DemoRepository demoRepository;
    private static final MongoDBContainer mongoDbContainer =
            new MongoDBContainer(DockerImageName.parse("mongo:4.4.2"));

    static {
        mongoDbContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
        registry.add("spring.data.mongodb.database", () -> "demo");
        registry.add("spring.data.mongodb.authentication-database", () -> "admin");
    }

    @Test
    @DisplayName("Should load correctly nested json from generic class")
    void should_load_nested(){
        DemoEntity expected = buildDemoModel();
        demoRepository.save(expected);
        Optional<DemoEntity> actual = demoRepository.findById("id");
        actual.ifPresent(demoEntity -> assertEquals(expected,actual.get()));
    }

    private DemoEntity buildDemoModel() {
        var firstNest = new DemoNomenclatureModel("a");
        var secondNest = new DemoCharacteristicModel(new NestedModel(1));
        return new DemoEntity("id", new WrapperModel<>(Set.of(firstNest), "search"),new WrapperModel<>(Set.of(secondNest), "s"));
    }


}
