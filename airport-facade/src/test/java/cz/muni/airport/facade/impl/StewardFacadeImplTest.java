package cz.muni.airport.facade.impl;

import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.StewardFacade;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.BeanMappingService;
import cz.muni.airport.services.impl.StewardServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
@RunWith(MockitoJUnitRunner.class)
public class StewardFacadeImplTest {
    @Mock
    private StewardServiceImpl stewardServiceImpl;
    @Mock
    private BeanMappingService beanMapperService;
    @InjectMocks
    private StewardFacade stewardFacade = new StewardFacadeImpl();
    
    List<Steward> data;
    
    public StewardFacadeImplTest() {
    }
    
    @Before 
    public void initialize() { 
        data = new ArrayList<>();
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
        when(stewardServiceImpl.addSteward(s1)).thenReturn(s1);
        
        StewardDTO created = stewardFacade.createSteward(sDTO1);
       
        assertEquals(sDTO1, created);
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
        when(stewardServiceImpl.updateSteward(s1)).thenReturn(s1);
        
        StewardDTO updated = stewardFacade.updateSteward(sDTO1);
       
        assertEquals(sDTO1, updated);
   
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
        verify(stewardServiceImpl).removeSteward(s1);
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
        when(stewardServiceImpl.getSteward(anyLong())).thenReturn(s1);
        
        StewardDTO result = stewardFacade.getSteward(Long.MIN_VALUE);
        
        verify(beanMapperService).mapTo(s1, StewardDTO.class);
        verify(stewardServiceImpl).getSteward(Long.MIN_VALUE);
   
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
        when(stewardServiceImpl.getStewardByName(name, last)).thenReturn(res);
        
        List<StewardDTO> result = stewardFacade.getStewardByName(name, last);
        
        verify(beanMapperService).mapTo(res, StewardDTO.class);
        verify(stewardServiceImpl).getStewardByName(name, last);
        
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
        when(stewardServiceImpl.getAllStewards()).thenReturn(res);
        
        List<StewardDTO> result = stewardFacade.getAllStewards();
         
        verify(beanMapperService).mapTo(res, StewardDTO.class);
        verify(stewardServiceImpl).getAllStewards(); 
        
    }
    
}
