package com.pocketguide.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.*;

import com.pocketguide.exception.OutgoNotFoundException;
import com.pocketguide.model.Outgo;
import com.pocketguide.model.enums.Tag;
import com.pocketguide.repository.OutgoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class OutgoServiceTest {

    @Mock
    OutgoRepository outgoRepository;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createSpentTest() {
        OutgoService outgoService = new OutgoService(outgoRepository);

        Outgo outgo = new Outgo();
        outgo.setDate(new Date());
        outgo.setDescription("Test");
        outgo.setEnterpriseName("Test");
        outgo.setTag(Tag.ALIMENTO);

        when(outgoRepository.save(anyObject())).thenReturn(outgo);

        Outgo response = outgoService.save(outgo);

        assertEquals(outgo, response);
    }

    @Test
    public void findAllTest() {
        OutgoService outgoService = new OutgoService(outgoRepository);

        Outgo outgoAlimento = new Outgo();
        outgoAlimento.setDate(new Date());
        outgoAlimento.setDescription("Test 1");
        outgoAlimento.setEnterpriseName("Test 1");
        outgoAlimento.setTag(Tag.ALIMENTO);

        Outgo outgoTransporte = new Outgo();
        outgoTransporte.setDate(new Date());
        outgoTransporte.setDescription("Test 2");
        outgoTransporte.setEnterpriseName("Test 2");
        outgoTransporte.setTag(Tag.TRANSPORTE);

        List<Outgo> outgoing = new ArrayList<>();
        outgoing.add(outgoAlimento);
        outgoing.add(outgoTransporte);

        when(outgoRepository.findAll()).thenReturn(outgoing);

        List<Outgo> response = outgoService.listAll();

        assertEquals(outgoing, response);
    }

    @Test
    public void findByIdTest() {
        OutgoService outgoService = new OutgoService(outgoRepository);

        Outgo outgoAlimento = new Outgo();
        outgoAlimento.setDate(new Date());
        outgoAlimento.setDescription("Test 1");
        outgoAlimento.setEnterpriseName("Test 1");
        outgoAlimento.setTag(Tag.ALIMENTO);

        Optional<Outgo> outgo = Optional.of(outgoAlimento);

        when(outgoRepository.findById(anyLong())).thenReturn(outgo);

        Outgo response = new Outgo();

        try {
            response = outgoService.findById(1L);
        } catch (OutgoNotFoundException e) {}

        assertEquals(outgoAlimento, response);
    }

    @Test
    public void findByIdErrorTest() {
        OutgoService outgoService = new OutgoService(outgoRepository);

        Outgo outgoAlimento = new Outgo();
        outgoAlimento.setDate(new Date());
        outgoAlimento.setDescription("Test 1");
        outgoAlimento.setEnterpriseName("Test 1");
        outgoAlimento.setTag(Tag.ALIMENTO);

        Optional<Outgo> outgo = Optional.of(outgoAlimento);

        when(outgoRepository.findById(anyLong())).thenReturn(Optional.empty());


        try {
            outgoService.findById(1L);
        } catch (Exception e) {
            assertEquals(OutgoNotFoundException.class, e.getClass());
            assertEquals("Spent with id 1 was not found", e.getMessage());
        }
    }

    @Test
    public void deleteByIdTest() {
        OutgoService outgoService = new OutgoService(outgoRepository);

        doNothing().when(outgoRepository).deleteById(anyLong());

        try {
            outgoService.deleteById(1L);
        } catch (Exception e) {
            assertEquals(1, 2);
        }
    }

    @Test
    public void deleteByIdErrorTest() {
        OutgoService outgoService = new OutgoService(outgoRepository);

        doThrow(new IllegalArgumentException()).when(outgoRepository).deleteById(anyLong());

        try {
            outgoService.deleteById(1L);
        } catch (Exception e) {
            assertEquals(OutgoNotFoundException.class, e.getClass());
            assertEquals("Spent with id 1 was not found", e.getMessage());
        }
    }
}

