package com.juanarroyes.reactiveapirest.service;

import com.juanarroyes.reactiveapirest.dto.EngineDTO;
import com.juanarroyes.reactiveapirest.entity.Engine;
import com.juanarroyes.reactiveapirest.exception.AppException;
import com.juanarroyes.reactiveapirest.exception.DataNotFoundException;
import com.juanarroyes.reactiveapirest.repository.EngineRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import reactor.core.publisher.Mono;

import javax.swing.text.html.parser.Entity;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class EngineServiceTest {

    @Mock
    private EngineRepository repository;

    private EngineService service;

    @BeforeEach
    public void init() {
        service = new EngineService(repository);
    }

    @Test
    public void testCreateEngine() {
        EngineDTO engineDTO = generateEngineDTO();
        Engine engine = generateEngine();

        given(repository.save(any(Engine.class))).willReturn(Mono.just(engine));
        Mono<Engine> result = service.createEngine(engineDTO);
        assertEquals(engine, result.block());
        then(repository).should(times(1)).save(any(Engine.class));
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void testGetEngineByIdSuccess() {
        UUID id = UUID.randomUUID();
        Engine engine = this.generateEngine();

        given(repository.findById(any(UUID.class))).willReturn(Mono.just(engine));
        Mono<Engine> result = service.getEngineById(id);
        assertEquals(engine, result.block());
        then(repository).should(times(1)).findById(any(UUID.class));
        then(repository).shouldHaveNoMoreInteractions();
    }

    private EngineDTO generateEngineDTO() {
        EngineDTO engineDTO = new EngineDTO();

        engineDTO.setId(UUID.randomUUID());
        engineDTO.setModel("LC8 GT");
        engineDTO.setDisplacement(1301);
        engineDTO.setType("4T");
        engineDTO.setProvision("V at 75º");
        engineDTO.setDistribution("DOHC");
        engineDTO.setNumberOfCylinders(2);
        engineDTO.setValvesPerCylinder(4);
        engineDTO.setPowerType("Injection");
        engineDTO.setRefrigeration("water");
        engineDTO.setCv(175);
        engineDTO.setKw(130f);
        engineDTO.setRpmFullPower(9500);
        engineDTO.setTorque(141);
        engineDTO.setRpmFullTorque(6700);
        engineDTO.setMaxRpm(10500);
        engineDTO.setFuelType("Gasoline 95/98");
        engineDTO.setAntipollutionRegulation("Euro IV");
        engineDTO.setStarterType("Electronic DCI");
        engineDTO.setPrimaryTransmission("Gear cascade");
        engineDTO.setSecondaryTransmission("Chain");
        engineDTO.setClutch("Multi-disc");
        engineDTO.setClutchActuation("Hydraulic");
        engineDTO.setNumberOfGears(6);
        return engineDTO;
    }

    public Engine generateEngine() {
        Engine engine = new Engine();
        engine.setId(UUID.randomUUID());
        engine.setModel("LC8 GT");
        engine.setDisplacement(1301);
        engine.setType("4T");
        engine.setProvision("V at 75º");
        engine.setDistribution("DOHC");
        engine.setNumberOfCylinders(2);
        engine.setValvesPerCylinder(4);
        engine.setPowerType("Injection");
        engine.setRefrigeration("water");
        engine.setCv(175);
        engine.setKw(130f);
        engine.setRpmFullPower(9500);
        engine.setTorque(141);
        engine.setRpmFullTorque(6700);
        engine.setMaxRpm(10500);
        engine.setFuelType("Gasoline 95/98");
        engine.setAntipollutionRegulation("Euro IV");
        engine.setStarterType("Electronic DCI");
        engine.setPrimaryTransmission("Gear cascade");
        engine.setSecondaryTransmission("Chain");
        engine.setClutch("Multi-disc");
        engine.setClutchActuation("Hydraulic");
        engine.setNumberOfGears(6);
        return engine;
    }
}
