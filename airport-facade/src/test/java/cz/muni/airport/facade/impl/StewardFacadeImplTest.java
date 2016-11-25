package cz.muni.airport.facade.impl;

import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.StewardFacade;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.BeanMappingService;
import cz.muni.airport.services.StewardService;
import cz.muni.airport.services.impl.StewardServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
@RunWith(MockitoJUnitRunner.class)
public class StewardFacadeImplTest {
    @Mock
    private StewardService stewardService;
    @Mock
    private BeanMappingService beanMapperService;
    @InjectMocks
    private StewardFacade stewardFacade = new StewardFacadeImpl();
        
    public StewardFacadeImplTest() {
    }
        
    @Test
    public void shouldAutowireDependencies() {
        assertNotNull(stewardFacade);
    }
    
    private StewardDTO prepareStewardDTO1(){
        StewardDTO steward = new StewardDTO();
        steward.setFirstName("John");
        steward.setLastName("Malkovich");
        return steward;
    }
    private Steward prepareSteward1(){
        Steward steward = new Steward();
        steward.setFirstName("John");
        steward.setLastName("Malkovich");
        return steward;
    }

    /**
     * Test of createSteward method, of class StewardFacadeImpl.
     */
    @Test
    public void testCreateSteward() {
        System.out.println("createSteward");
        
        StewardDTO sDTO1 = prepareStewardDTO1();
        Steward s1 = prepareSteward1();
        
        when(beanMapperService.mapTo(sDTO1, Steward.class)).thenReturn(s1);
        when(beanMapperService.mapTo(s1, StewardDTO.class)).thenReturn(sDTO1);
        when(stewardService.addSteward(s1)).thenReturn(s1);
        
        StewardDTO created = stewardFacade.createSteward(sDTO1);
       
        assertEquals(sDTO1, created);
        verify(stewardService).addSteward(s1);
    }

    /**
     * Test of updateSteward method, of class StewardFacadeImpl.
     */
    @Test
    public void testUpdateSteward() {
        System.out.println("updateSteward");
        
        StewardDTO sDTO1 = prepareStewardDTO1();
        Steward s1 = prepareSteward1();
        
        when(beanMapperService.mapTo(sDTO1, Steward.class)).thenReturn(s1);
        when(beanMapperService.mapTo(s1, StewardDTO.class)).thenReturn(sDTO1);
        when(stewardService.updateSteward(s1)).thenReturn(s1);
        
        StewardDTO updated = stewardFacade.updateSteward(sDTO1);
       
        assertEquals(sDTO1, updated);
        verify(stewardService).updateSteward(s1);
   
    }

    /**
     * Test of deleteSteward method, of class StewardFacadeImpl.
     */
    @Test
    public void testDeleteSteward() {
        System.out.println("deleteSteward");
        
        StewardDTO sDTO1 = prepareStewardDTO1();
        Steward s1 = prepareSteward1();
        
        when(beanMapperService.mapTo(sDTO1, Steward.class)).thenReturn(s1);
        when(beanMapperService.mapTo(s1, StewardDTO.class)).thenReturn(sDTO1);
        
        stewardFacade.deleteSteward(sDTO1);
        
        verify(beanMapperService).mapTo(sDTO1, Steward.class);
        verify(stewardService).removeSteward(s1);
    }

    /**
     * Test of getSteward method, of class StewardFacadeImpl.
     */
    @Test
    public void testGetSteward() {
        System.out.println("getSteward");
        
        StewardDTO sDTO1 = prepareStewardDTO1();
        Steward s1 = prepareSteward1();
        
        when(beanMapperService.mapTo(sDTO1, Steward.class)).thenReturn(s1);
        when(beanMapperService.mapTo(s1, StewardDTO.class)).thenReturn(sDTO1);
        when(stewardService.getSteward(anyLong())).thenReturn(s1);
        
        StewardDTO result = stewardFacade.getSteward(Long.MIN_VALUE);
        
        verify(beanMapperService).mapTo(s1, StewardDTO.class);
        verify(stewardService).getSteward(Long.MIN_VALUE);
   
    }

    /**
     * Test of getStewardByName method, of class StewardFacadeImpl.
     */
    @Test
    public void testGetStewardByName() {
        System.out.println("getStewardByName");
              
        StewardDTO sDTO1 = prepareStewardDTO1();
        Steward s1 = prepareSteward1();
        
        String name = "John", last = "Malkovich";
        List<Steward> res = new ArrayList<>();
        res.add(s1);
        
        when(beanMapperService.mapTo(sDTO1, Steward.class)).thenReturn(s1);
        when(beanMapperService.mapTo(s1, StewardDTO.class)).thenReturn(sDTO1);
        when(stewardService.getStewardByName(name, last)).thenReturn(res);
        
        List<StewardDTO> result = stewardFacade.getStewardByName(name, last);
        
        verify(beanMapperService).mapTo(res, StewardDTO.class);
        verify(stewardService).getStewardByName(name, last);
        
    }

    /**
     * Test of getAllStewards method, of class StewardFacadeImpl.
     */
    @Test
    public void testGetAllStewards() {
        System.out.println("getAllStewards");
        
        StewardDTO sDTO1 = prepareStewardDTO1();
        Steward s1 = prepareSteward1();
        List<Steward> res = new ArrayList<>();
        res.add(s1);
        
        when(beanMapperService.mapTo(sDTO1, Steward.class)).thenReturn(s1);
        when(beanMapperService.mapTo(s1, StewardDTO.class)).thenReturn(sDTO1);
        when(stewardService.getAllStewards()).thenReturn(res);
        
        List<StewardDTO> result = stewardFacade.getAllStewards();
         
        verify(beanMapperService).mapTo(res, StewardDTO.class);
        verify(stewardService).getAllStewards(); 
        
    }
    
}
